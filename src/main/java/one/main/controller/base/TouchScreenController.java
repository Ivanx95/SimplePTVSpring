package one.main.controller.base;

import org.springframework.context.ApplicationListener;

import com.sun.glass.events.MouseEvent;

import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.util.Duration;
import one.main.base.StageController;
import one.main.controller.Dialog1Controller;
import one.main.events.ForceCloseEvent;
import one.main.support.GUIState;

public abstract class TouchScreenController   extends StageController implements Initializable, ApplicationListener<ForceCloseEvent> {

	private final long MIN_STATIONARY_TIME = 12000l; 
	private BooleanProperty keepLocl= new SimpleBooleanProperty(true);
	private PauseTransition pause;
	
	
	@Override
	public void onShow() {
		
		BooleanProperty mouseMoving = new SimpleBooleanProperty(true);
        mouseMoving.addListener((obs, wasMoving, isNowMoving) -> {
           if (! isNowMoving) {
        	   ForceCloseEvent customSpringEvent = new ForceCloseEvent(this, "Closing");
			     this.applicationEventPublisher.publishEvent(customSpringEvent);
			     keepLocl.set(false);
           }
        });
        
        //TODO: Bind stage on showing property to boolean property
//        this.getStage().onShowingProperty().
        
        keepLocl.set(true);
        pause = new PauseTransition(Duration.millis(MIN_STATIONARY_TIME));
        pause.setOnFinished(e -> 
        	{
        		if(keepLocl.get()) {
        			mouseMoving.set(false);
            		pause.playFromStart();
            		mouseMoving.set(true);	
        		}else {
        			mouseMoving.set(true);
        		}
        			
        		
        		
        	}
        );
        
        this.getScene().setOnMouseMoved(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
	            mouseMoving.set(true);
	            pause.playFromStart();
			}
		});
        
        this.getScene().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				mouseMoving.set(true);
	            pause.playFromStart();
				
			}
		});
        
		 this.getScene().setOnMouseEntered(new EventHandler() {

				@Override
				public void handle(Event event) {
					TouchScreenController.this.getScene().setCursor(Cursor.DISAPPEAR); 
					
				}
			});
		 pause.playFromStart();
	}
	
	@Override
	public void onApplicationEvent(ForceCloseEvent event) {
		this.onForceClose();
	}
}

package one.main.controller.base;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import one.main.base.StageController;
import one.main.controller.Dialog1Controller;

public abstract class TouchScreenController   extends StageController implements Initializable{

	@Override
	public void onShow() {
		
		 this.getScene().setOnMouseEntered(new EventHandler() {

				@Override
				public void handle(Event event) {
					TouchScreenController.this.getScene().setCursor(Cursor.DISAPPEAR); 
					
				}
			});
	}
}

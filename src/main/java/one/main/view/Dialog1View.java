package one.main.view;

import org.springframework.beans.factory.annotation.Qualifier;

import de.felixroske.jfxsupport.FXMLView;
import javafx.stage.Modality;
import one.main.base.StageController;
import one.main.components.aop.LogExecutionTime;
import one.main.support.JavaFxView;

@Qualifier("popUp1")
@FXMLView( bundle="one.main.i18n.message", stageStyle = "UNDECORATED", title="view.dialog1.title")
public class Dialog1View extends JavaFxView{

	public Dialog1View(String string) {
		super(string);
	}

	@Override
	@LogExecutionTime
	public StageController createController(Modality mode) {
		// TODO Auto-generated method stub
		return super.createController(mode);
	}
	
	

	
	
}

package one.main.view;

import org.springframework.beans.factory.annotation.Qualifier;

import de.felixroske.jfxsupport.FXMLView;
import one.main.support.JavaFxView;


@FXMLView( bundle="one.main.i18n.message", stageStyle = "UNDECORATED")
public class Dialog2View extends JavaFxView{

	public Dialog2View(String string) {
		super(string);
	}
	
	

	
}

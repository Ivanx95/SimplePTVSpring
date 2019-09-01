package one.main.view;

import org.springframework.beans.factory.annotation.Qualifier;

import de.felixroske.jfxsupport.FXMLView;
import one.main.support.JavaFxView;

@Qualifier("popUp1")
@FXMLView( bundle="one.main.i18n.message", stageStyle = "UNDECORATED")
public class Dialog1View extends JavaFxView{

	public Dialog1View(String string) {
		super(string);
	}
	
	

	
}

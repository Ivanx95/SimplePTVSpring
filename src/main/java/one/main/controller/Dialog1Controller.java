package one.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Qualifier;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.Initializable;
import one.main.base.StageController;

@FXMLController
@Qualifier("popUp1")
public class Dialog1Controller extends StageController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.err.println("Hi");
		
	}

	
	
}

package one.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import one.main.base.StageController;
import one.main.view.Dialog1View;
import one.main.view.custom.window.WindowDownButton;
import one.main.view.custom.window.WindowDownMenuController;

@FXMLController
public class Dialog2Controller extends StageController implements Initializable{

	
	
	@FXML
	private WindowDownMenuController windowController;
	
	@FXML
	private WindowDownButton btn1;
	@FXML
	private WindowDownButton btn2;
	@FXML
	private WindowDownButton btn3;
	
	@Autowired
	Dialog1View view;
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Dialog2Controller.this.close();
				
			}
		});
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.err.println("Im a message");
				
			}
		});
		
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				 StageController controlr=	view.createController(Modality.APPLICATION_MODAL);
				 controlr.getStage().showAndWait();
				 
			}
		});
	}
	
	
	
}

	

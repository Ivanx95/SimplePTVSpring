package one.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import one.main.base.StageController;
import one.main.view.Dialog1View;
import one.main.view.custom.window.WindowDownButton;
import one.main.view.custom.window.WindowDownMenuController;

@FXMLController
public class Dialog2Controller extends StageController implements Initializable{

	
	
	@FXML
	private WindowDownMenuController windowController;

	
	@Autowired
	Dialog1View view;
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		WindowDownButton btn = new WindowDownButton();
		
		btn.setImage(new Image(getClass().getResource("/images/add.png").toExternalForm()));
		btn.setText(resources.getString("view.dialog2.menubtn.1"));
	}

	@Override
	public void onShow() {
	
		
	}

	@Override
	public void onSettingScene() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

	

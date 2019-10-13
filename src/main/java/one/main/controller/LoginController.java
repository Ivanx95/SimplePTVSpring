package one.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import one.main.base.StageController;
import one.main.controller.base.AlertBuilder;
import one.main.controller.base.TouchScreenController;
import one.main.model.User;
import one.main.model.service.UserService;
import one.main.view.Dialog1View;

@FXMLController
public class LoginController extends TouchScreenController {

	@FXML
	private Button btnAccept;
	
	@Autowired
	private Dialog1View dialogView;
	
	@Autowired
	private UserService userService;
	
	
	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	
	@Override
	public void onSettingScene() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		
		btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoginController.this.login();
			}
		});
		
	}
	
	
	
	private void login() {

		try {
			User user = new User();
			
			user.setUser(userField.getText());
			user.setPassword(passwordField.getText());
			
			user=userService.simpleAccess(user);
			
			if(user==null) {
				System.err.println("No access");
				
			}else {
				StageController controlr=	dialogView.createControllerFullScreen(Modality.APPLICATION_MODAL);
				 userService.setLoggedUser(user);
				 userField.clear();
				 passwordField.clear();
				 controlr.getStage().showAndWait();
				
			}
		} catch (Exception e) {
			Alert alert=AlertBuilder.createAlertExceptionWaning(e);
			
			 Platform.runLater(()->alert.showAndWait());
		}
		
		 
	}

}

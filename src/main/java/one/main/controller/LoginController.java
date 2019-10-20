package one.main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import one.main.base.StageController;
import one.main.controller.base.AlertBuilder;
import one.main.controller.base.TouchScreenController;
import one.main.events.ForceCloseEvent;
import one.main.experimental.view.VirtualKeyBoardLayout;
import one.main.model.User;
import one.main.model.service.UserService;
import one.main.support.GUIState;
import one.main.view.Dialog1View;
import one.main.view.custom.VirtualKeyBoard;

@FXMLController
public class LoginController extends StageController { 

	@FXML
	private Button btnAccept;
	
	@Autowired
	private Dialog1View dialogView;
	
	@Autowired
	private UserService userService;
	
	@FXML
	VirtualKeyBoardLayout embbededKeyBoard;
	
	
	@FXML
	private TextField userField;
	
	@FXML
	private VBox root;
	
	@FXML
	private PasswordField passwordField;
	
	private Boolean triggered=true;
	@Override
	public void onSettingScene() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		
		this.btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoginController.this.login();
			}
		});
		
		
		
//		VirtualKeyBoard vkb = new VirtualKeyBoard();
//	    
//	    // just add a border to easily visualize the boundary of the keyboard:
//	    vkb.view().setStyle("-fx-border-color: darkblue; -fx-border-radius: 5;");
//	    
//	    root.getChildren().add(vkb.view());
		
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
				
				if (triggered) {
					StageController controlr=	dialogView.createControllerFullScreen(Modality.APPLICATION_MODAL);
					 userService.setLoggedUser(user);
					 userField.clear();
					 passwordField.clear();
					 controlr.getStage().showAndWait();
					 
				}else {
					this.getStage().close();
				}
				
			}
		} catch (Exception e) {
			Alert alert=AlertBuilder.createAlertExceptionWaning(e);
			
			 Platform.runLater(()->alert.showAndWait());
		}
		
		 
	}
	@Override
	public void onShow() {
		
	    
	}
	

}

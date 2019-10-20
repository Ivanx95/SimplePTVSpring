package one.main.base;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import one.main.support.JavaFxView;
import one.main.support.NeoJavaxApplicationSupport;

@FXMLController
@Scope("prototype")
public abstract class StageController implements Initializable{

	Stage stage;

	Scene scene;
	
	Parent parent;

	 @Autowired
	 protected ApplicationEventPublisher applicationEventPublisher;
	 
	List<String> methodProxy;
	public abstract void onShow();
	
	public abstract void onSettingScene();
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}


	private ResourceBundle bundle;

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	


	public void setParent(Parent parent) {
		this.parent = parent;
	}



	public Stage getStage() {
		return stage;
	}
	
	protected void close() {
		if(this.stage!=null) {
			this.stage.close();
		}else {
			
		}
	}

	public Scene getScene() {
		return scene;
	}

	public Parent getParent() {
		return parent;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		this.setBundle(resources);
	}
	
	
	protected void onForceClose() {
		this.getStage().close();
	}
	
	
	
}

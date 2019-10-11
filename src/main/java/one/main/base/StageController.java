package one.main.base;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import one.main.support.JavaFxView;
import one.main.support.NeoJavaxApplicationSupport;

@FXMLController
@Scope("prototype")
public abstract class StageController {

	Stage stage;

	Scene scene;
	
	Parent parent;

	List<String> methodProxy;
	public abstract void onShow();
	
	public abstract void onSettingScene();
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}



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
	
	
	
	
	
	
	
	
}

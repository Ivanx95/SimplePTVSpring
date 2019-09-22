package one.main.view.custom.window;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class WindowDownMenuController  extends HBox{

	
	
	public WindowDownMenuController() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("window_down_menu.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	

	
}

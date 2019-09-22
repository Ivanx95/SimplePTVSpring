package one.main.support;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import one.main.view.custom.window.WindowDownButton;


public class WindowTransactionalMenuController extends HBox {

	


	public WindowTransactionalMenuController() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("window_transactional_down_menu.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
}

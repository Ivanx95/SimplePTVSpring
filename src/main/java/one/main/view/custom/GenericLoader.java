package one.main.view.custom;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public interface GenericLoader {

	
	default void load(String xmlLoacation)  {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(xmlLoacation));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}

package one.main.view.custom.window;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * 
 * @author storm
 *
 */
public class WindowDownButton extends VBox{

	@FXML
	private Label label;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Button mainBtn;
	
	public WindowDownButton() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_button.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public final Image getImage() {
		return imageView.getImage();
	}

	public final ObjectProperty<Image> imageProperty() {
		return imageView.imageProperty();
	}

	public final void setImage(Image arg0) {
		imageView.setImage(arg0);
		
		
	}

	public final String getText() {
		return label.getText();
	}

	public final void setText(String value) {
		label.setText(value);
	}

	public final StringProperty textProperty() {
		return label.textProperty();
	}

	public final void setOnAction(EventHandler<ActionEvent> value) {
		mainBtn.setOnAction(value);
	}
	
	


	
	

	
	
}

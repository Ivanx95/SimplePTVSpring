package one.main.view.custom.transactional;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class WindowMenuTransactional2 extends VBox{

	
	
	

//	@FXML
//	private WindowDownButton btnDelete;
//	
//	@FXML
//	private WindowDownButton btnExit;
//	
//	@FXML
//	private WindowDownButton btnAdd;
//	
//	@FXML
//	private WindowDownButton btnSave;
//	
	
	
	public WindowMenuTransactional2() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("window_transactional.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

//	public void addBtn(WindowDownButton btn) {
//		this.mainContainer.getChildren().add(btn);
//	}
//
//	public WindowDownButton getBtnDelete() {
//		return btnDelete;
//	}
//
//	public WindowDownButton getBtnExit() {
//		return btnExit;
//	}
//	
	
	

	
}

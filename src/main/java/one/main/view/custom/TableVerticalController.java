package one.main.view.custom;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class TableVerticalController<T,S> extends HBox {

	
	@FXML
	private TableView<T> table;
	
	@FXML
	private TableColumn<T, S> idColumn;
	
	@FXML
	private Button upBtn;
	
	@FXML
	private Button down;
	
	public TableVerticalController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("table_vertical_paginated.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		System.err.println(table.getProperties());	
	}

	public TableColumn<T, S> getIdColumn() {
		return idColumn;
	}

	public Button getUpBtn() {
		return upBtn;
	}

	public Button getDown() {
		return down;
	}

	public TableView<T> getTable() {
		return table;
	}
	
	
}


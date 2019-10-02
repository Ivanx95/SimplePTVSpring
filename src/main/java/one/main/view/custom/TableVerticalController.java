package one.main.view.custom;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import one.behaivior.paginator.CustomPaginator;

public class TableVerticalController<T,S> extends HBox implements CustomPaginator<T>, Initializable {

	private IntegerProperty pageSizeProperty;
	private IntegerProperty pageIndexProperty;
	
	private ObservableList<T> data = FXCollections.observableArrayList(new ArrayList<>());
	@FXML
	private TableView<T> table;
	
	@FXML
	private TableColumn<T, S> idColumn;
	
	@FXML
	private Button upBtn;
	
	@FXML
	private Button downBtn;
	
	@FXML
	private Label tableTitle;
	@Override
	public void createPage(int pageIndex) {

	    int fromIndex = pageIndex * pageSizeProperty.get();
	    int toIndex = Math.min(fromIndex + pageSizeProperty.get(), data.size());
	    int nextFromIndex=(pageIndex +1)* pageSizeProperty.get(); 
	    
	    if(nextFromIndex>toIndex) {
	    	downBtn.setDisable(true);
	    	if(pageIndex<=0) {
	    		upBtn.setDisable(true);	
	    	}else {
	    		upBtn.setDisable(false);
	    	}
	    	
	    }else {
	    	downBtn.setDisable(false);
	    	
	    	if(pageIndex>0) {
	    		upBtn.setDisable(false);	
	    	}
	    	
	    	
	    }
	    if(fromIndex>toIndex) {
	    	return;
	    }
	    ObservableList<T> items =FXCollections.observableArrayList(data.subList(fromIndex, toIndex));
	    table.setItems(items);

	    
	}
	
	
	public TableVerticalController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("table_vertical_paginated.fxml"));


		loader.setRoot(this);
		loader.setController(this);

		 
		    
		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		pageSizeProperty= new SimpleIntegerProperty(1);
		pageIndexProperty = new SimpleIntegerProperty(0);
		
		upBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
				pageIndexProperty.set(pageIndexProperty.get()-1);
				if(pageIndexProperty.get()<=0) {
					upBtn.setDisable(true);
				}
			}
		});
		
		downBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				pageIndexProperty.set(pageIndexProperty.get()+1);
				
			}
		});
		data.addListener(new ListChangeListener<T>() {

			@Override
			public void onChanged(Change<? extends T> c) {
				
				
				if(c.getList().isEmpty()) {
					pageIndexProperty.set(0);
				}
				createPage(pageIndexProperty.get());
				
			}
			
		});
		
		pageIndexProperty.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				createPage(newValue.intValue());
			}
		});
		
		createPage(pageIndexProperty.get());
		
		
		
	}

	public TableColumn<T, S> getIdColumn() {
		return idColumn;
	}

	public Button getUpBtn() {
		return upBtn;
	}

	public Button getDown() {
		return downBtn;
	}

	public TableView<T> getTable() {
		return table;
	}

	public final IntegerProperty pageSizeProperty() {
		return pageSizeProperty;
	}

	public final void setPageSize(int pageSize) {
		this.pageSizeProperty.set(pageSize);
	}
	
	public final int getPageSize() {
		return this.pageSizeProperty.get();
	}
	public final BooleanProperty disableUpProperty() {
		return this.upBtn.disableProperty();
	}
	
	public final BooleanProperty disableDownProperty() {
		return this.downBtn.disableProperty();
	}
	
	public final void setDisableUp(boolean value) {
		this.upBtn.setDisable(value);
	}
	
	public final void setDisableDown(boolean value) {
		this.downBtn.setDisable(value);
	}
	
	public final boolean getDisableUp() {
		return this.upBtn.disabledProperty().get();
	}
	
	public final boolean getDisableDown() {
		return this.downBtn.disabledProperty().get();
	}

	public ObservableList<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data=FXCollections.observableArrayList(data);
	}

	public void setTableTitle(String title) {
		this.tableTitle.setText(title);
	}
	
	public String getTableTitle() {
		return this.tableTitle.getText();
	}
	
	public StringProperty tableTitleProperty() {
		return this.tableTitle.textProperty();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	
}


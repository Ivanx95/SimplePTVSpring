package one.main.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.BindingAnnotationFieldTypePattern;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.PropertyReaderHelper;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import one.main.components.aop.LogExecutionTime;
import one.main.controller.base.AlertBuilder;
import one.main.controller.base.TouchScreenController;
import one.main.events.ForceCloseEvent;
import one.main.jfx.model.SaleFX;
import one.main.jfx.model.SaleItemFX;
import one.main.model.Sale;
import one.main.model.SaleItem;
import one.main.model.User;
import one.main.model.repository.UserRepository;
import one.main.model.service.SaleService;
import one.main.support.Constant;
import one.main.support.WindowTransactionalMenuController;
import one.main.view.Dialog2View;
import one.main.view.custom.TableVerticalController;
import one.main.view.custom.window.WindowDownButton;
import one.main.view.custom.window.WindowDownMenuController;

@FXMLController
public class Dialog1Controller extends TouchScreenController {

	
	
	@FXML
	private WindowDownMenuController windowController;
	
	@FXML
	private WindowDownButton btn1;
	@FXML
	private WindowDownButton btn2;
	@FXML
	private WindowDownButton btn3;
	
	@FXML
	private WindowDownButton btnDelete;
	
	@FXML
	private Label totalLbl;
	
	@FXML
	private TableVerticalController<SaleItemFX, Number> tableController;
	
	private ObservableList<SaleItemFX> saleData = FXCollections.observableArrayList();
	
	
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	@Autowired
	Dialog2View view;

	@Autowired
	private UserRepository userRepository;
	
	private DoubleBinding total2;

	private Sale sale;
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		this.setBundle(resources);
		
		
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Dialog1Controller.this.close();
				
			}
		});
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				onAddItem();
//				 
				
			}
		});
		
//		btn3.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				User aux = new User();
//				aux.setPassword("123456");
//				aux.setUser("Pancho");
//				userRepository.save(aux);
//				
//			}
//		});
		tableController.getIdColumn().setCellValueFactory(cellData-> cellData.getValue().getIdProperty());
		
		
		tableController.getTable().getColumns().addAll(configureColumns());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SaleItemFX selectedSale = tableController.getTable().getSelectionModel().getSelectedItem();
				tableController.getData().remove(selectedSale);
			}
		});
		
		total2 = Bindings.createDoubleBinding(() -> 
		tableController.getData().stream().collect(Collectors.summingDouble(SaleItemFX::getAmount)),
		tableController.getData());
		
		tableController.setTableTitle(resources.getString("view.dialog1.table1.title"));
		
		totalLbl.textProperty().bind(Bindings.createStringBinding(
		        () -> total2.getValue() == null 
	              ? "" 
	              : currencyFormat.format(total2.getValue().doubleValue()),         
	              total2));
		
		this.tableController.getTable().setPlaceholder(new Label(resources.getString("view.global.emptyTable")));
		
	}

	private List<TableColumn<SaleItemFX, ?>> configureColumns(){
		
		List<TableColumn<SaleItemFX, ?>>colums = new ArrayList<>();
		
		TableColumn<SaleItemFX, String> descColumn = new TableColumn<>();
		
		descColumn.setCellValueFactory(cellData-> cellData.getValue().getDescriptionProperty());
		descColumn.setText(this.getBundle().getString("view.dialog1.table1.amount"));
		
		colums.add(descColumn);
		
		TableColumn<SaleItemFX, Number> quantityColumn = new TableColumn<>();
		
		quantityColumn.setCellValueFactory(cellData-> cellData.getValue().getQuantityProperty());
		quantityColumn.setText(this.getBundle().getString("view.dialog1.table1.quantity"));
		
		TableColumn<SaleItemFX, Number> unitPriceColumn = new TableColumn<>();
		
		unitPriceColumn.setCellValueFactory(cellData-> cellData.getValue().getUnitPriceProperty());
		unitPriceColumn.setText(this.getBundle().getString("view.dialog1.table1.unitPrice"));
		
		TableColumn<SaleItemFX, Number> amountColumn = new TableColumn<>();
		
		amountColumn.setCellValueFactory(cellData-> cellData.getValue().getAmountProperty());
		amountColumn.setText(this.getBundle().getString("view.dialog1.table1.amount"));
		
		
		colums.add(quantityColumn);
		
		colums.add(unitPriceColumn);
		
		colums.add(amountColumn);
		
		return colums;
		
	}
	@Override
	public void onShow() {
		
		
		 super.onShow();
	}

	@Override
	public void onSettingScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void close() {
		 tableController.getData().clear();
		super.close();
	}
	
//	@LogExecutionTime
	private void onAddItem() {
		 
			try {
				SaleItemFX sale = new SaleItemFX(1L, "Maruchan", BigDecimal.ONE, BigDecimal.TEN,  BigDecimal.TEN,  BigDecimal.ZERO);
				
				tableController.getData().add(sale);
			} catch (Exception e) {
				// TODO: handle exception
				
			
				Alert alert=AlertBuilder.createAlertExceptionWaning(e);
				
				 Platform.runLater(()->alert.showAndWait());
			}
			
	}
	
	
	
}

	


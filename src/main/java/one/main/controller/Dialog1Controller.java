package one.main.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.BindingAnnotationFieldTypePattern;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.PropertyReaderHelper;
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
import javafx.scene.control.Label;
import one.main.controller.base.TouchScreenController;
import one.main.model.Sale;
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
	
	private int count=0;
	@FXML
	private Label totalLbl;
	
	@FXML
	private TableVerticalController<Sale, String> tableController;
	
	private ObservableList<Sale> saleData = FXCollections.observableArrayList();
	
	private DoubleProperty total = new SimpleDoubleProperty();
	
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	@Autowired
	Dialog2View view;
	
	@Autowired
	private SaleService saleService;

	private DoubleBinding total2;
	
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		
		
		
                
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Dialog1Controller.this.close();
				
			}
		});
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				 Sale sale=new Sale(10L, "Ivan"+count, "Yuni", LocalDateTime.now(), new BigDecimal(100));
				
				count++;
				Dialog1Controller.this.tableController.getData().add(sale);
				
			}
		});
		
		tableController.getIdColumn().setCellValueFactory(cellData-> cellData.getValue().getClientProperty());
		
		
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Sale selectedSale = tableController.getTable().getSelectionModel().getSelectedItem();
				tableController.getData().remove(selectedSale);
			}
		});
		
		total2 = Bindings.createDoubleBinding(() -> 
		tableController.getData().stream().collect(Collectors.summingDouble(Sale::getAmount)),
		tableController.getData());
		
		tableController.setTableTitle(resources.getString("view.dialog1.table1.title"));
		
		totalLbl.textProperty().bind(Bindings.createStringBinding(
		        () -> total2.getValue() == null 
	              ? "" 
	              : currencyFormat.format(total2.getValue().doubleValue()),         
	              total2));
		
		this.tableController.getTable().setPlaceholder(new Label(resources.getString("view.global.emptyTable")));
		
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
	
	
	
}

	

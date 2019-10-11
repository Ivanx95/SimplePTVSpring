package one.main.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import one.main.components.aop.LogExecutionTime;
import one.main.controller.base.TouchScreenController;
import one.main.model.Sale;
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
	
	private int count=0;
	@FXML
	private Label totalLbl;
	
	@FXML
	private TableVerticalController<Sale, String> tableController;
	
	private ObservableList<Sale> saleData = FXCollections.observableArrayList();
	
	
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	@Autowired
	Dialog2View view;

	@Autowired
	private UserRepository userRepository;
	
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
				onAddItem();
				
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
	
//	@LogExecutionTime
	private void onAddItem() {
		 
			try {
				Sale sale=new Sale(10L, "Ivan"+count, "Yuni", LocalDateTime.now(), new BigDecimal(100));
				
				count++;
				Dialog1Controller.this.tableController.getData().add(sale);
				
				User aux = new User();
				aux.setPassword("123456");
				aux.setUser("Pancho");
				userRepository.save(aux);
			} catch (Exception e) {
				// TODO: handle exception
				
				Alert alert = new Alert(AlertType.ERROR);
//				alert.setTitle("Ha sucedido un error");
				alert.setHeaderText("Codigo de error: ");
				alert.setContentText("Ha sucedido un error, para mas información favor de comunicarse a: ");
				alert.initStyle(StageStyle.UNDECORATED);
				// Create expandable Exception.
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				String exceptionText = sw.toString();

				Label label = new Label("The exception stacktrace was:");

				TextArea textArea = new TextArea(exceptionText);
				textArea.setEditable(false);
				textArea.setWrapText(true);

				textArea.setMaxWidth(Double.MAX_VALUE);
				textArea.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea, Priority.ALWAYS);
				GridPane.setHgrow(textArea, Priority.ALWAYS);

				GridPane expContent = new GridPane();
				expContent.setMaxWidth(Double.MAX_VALUE);
				expContent.add(label, 0, 0);
				expContent.add(textArea, 0, 1);

				// Set expandable Exception into the dialog pane.
				alert.getDialogPane().setExpandableContent(expContent);

				 Platform.runLater(()->alert.showAndWait());
			}
			
	}
	
}

	

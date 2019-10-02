package one.main.controller;


import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import one.main.base.StageController;
import one.main.components.PayMethodController;
import one.main.model.Sale;
import one.main.model.service.SaleService;
import one.main.support.NeoJavaxApplicationSupport;
import one.main.view.Dialog1View;
import one.main.view.Dialog2View;

@FXMLController
public class PanelController  implements Initializable{

	@Autowired
	Dialog1View view;
	
	@Autowired
	PayMethodController paymentController;
	
	@Autowired
	SaleService saleService;
	
	@FXML
	MenuItem newButton;
	
	@Value("${one.main.payment.test}")
	private String testInject;


	public  PanelController() {
		System.err.println("Created");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		newButton.setOnAction(e->{
		System.err.println(testInject);
		 StageController controlr=	view.createController(Modality.APPLICATION_MODAL);
		 controlr.getStage().showAndWait();
		 
		});
	}
	
}

package one.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import one.main.controller.Dialog1Controller;
import one.main.support.NeoJavaxApplicationSupport;
import one.main.view.Dialog1View;
import one.main.view.PanelView;

@SpringBootApplication

public class Application extends NeoJavaxApplicationSupport{

	
	public static void main(String[] args) {
		launch(Application.class, PanelView.class,args);
	}


	
}

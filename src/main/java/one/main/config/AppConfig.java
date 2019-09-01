package one.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import one.main.base.StageController;
import one.main.components.PayMethodController;
import one.main.controller.Dialog1Controller;
import one.main.controller.Dialog2Controller;
import one.main.controller.PanelController;
import one.main.view.Dialog1View;
import one.main.view.Dialog2View;
import one.main.view.PanelView;

@Configuration
public class AppConfig {

	@Bean
	public PanelView getPanel() {
		return new PanelView();
	}
	
	
	
	@Bean
	public Dialog1View getPopUp1() {
		return new Dialog1View("popUp1");
	}
	
	@Bean
	public Dialog2View getPopUp2() {
		return new Dialog2View("popUp2");
	}
	
	
	@Bean
	public PanelController getPanelController() {
		return new PanelController();
	}
	
	
	@Bean
	public Dialog1Controller popUp1() {
		return new Dialog1Controller();
	}
	
	
	@Bean
	public Dialog2Controller popUp2() {
		return new Dialog2Controller();
	}
	
	
	@Bean
	public StageController getStageController() {
		return new StageController() {
			
		};
				
	}
}

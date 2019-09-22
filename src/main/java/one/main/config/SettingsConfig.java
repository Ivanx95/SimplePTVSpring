package one.main.config;

import java.net.MalformedURLException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import one.main.components.CreditCardController;
import one.main.components.PayMethodController;
import one.main.components.aop.MonitoringAndExceptionAspect;
import one.main.model.service.SaleService;
import one.main.model.service.impl.SaleServiceImpl;
import one.main.tools.FileClassLoader;

@Configuration
@ConditionalOnProperty(name = "one.main.payment.enabled", 
    havingValue = "true", matchIfMissing = true)
@EnableAspectJAutoProxy
public class SettingsConfig {

	private FileClassLoader loader = new FileClassLoader();

	@Bean
	public PayMethodController creditCartImplementation() {
		try {
			return loader.<PayMethodController>getInstance("/home/jaisen/eclipse-workspace/SimplePTVSpring/src/main/resources/CreditCard.jar");
//			return new CreditCard();
		} catch (ClassNotFoundException | MalformedURLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new IllegalStateException(e);
		}
	}
	
	@Bean
	public MonitoringAndExceptionAspect getmonitorBean() {
		return new MonitoringAndExceptionAspect();
	}
	
	@Bean
	public SaleService getSaleService() {
		return new SaleServiceImpl();
	}
}

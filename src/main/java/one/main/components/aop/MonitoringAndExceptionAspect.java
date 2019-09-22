package one.main.components.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Aspect
@Component
public class MonitoringAndExceptionAspect {

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		Object proceed = null;

		try {
			proceed=	joinPoint.proceed();
			long executionTime = System.currentTimeMillis() - start;

			System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
			return proceed;
		} catch (Throwable e) {
			createError(e);
			throw e;
		}

	}
	
	private void createError(Throwable throwable) {
		 Alert alert = new Alert(AlertType.ERROR, "Ha sucedido un error, favor de comunicarse con soporte t�cnico: " + throwable.getMessage());
		 
		 Platform.runLater(()->alert.show());
	}
}

package one.main.components.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

@Aspect
@Component
@Deprecated
/**
 * 
 * @author jaisen
 * @Deprecated not working with controller 
 * with fxml fields controller converted to 
 * #JDKDynamicProxy and fail injecting fxml fields
 * Will be removed in further versions
 */
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
		Alert alert = new Alert(AlertType.ERROR);
		
//		alert.setTitle("Ha sucedido un error");
		alert.setHeaderText("Codigo de error: ");
		alert.setContentText("Para mas información favor de comunicarse a: ");
		alert.initStyle(StageStyle.UNDECORATED);
		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
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

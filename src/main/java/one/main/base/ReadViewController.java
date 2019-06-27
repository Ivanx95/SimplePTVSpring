package one.main.base;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javafx.stage.Modality;
import one.main.support.JavaFxView;
import one.main.support.NeoJavaxApplicationSupport;

public abstract class ReadViewController<T> extends StageController {

	public abstract T init();

	public void createPopUp(Class<? extends JavaFxView> view, Consumer consume) {

//		if (stage != null) {
//			stage.show();
//			return;
//		}
//		CompletableFuture.supplyAsync(() -> {
//			return init();
//		}).thenAccept(new Consumer<T>() {
//
//			@Override
//			public void accept(T model) {
//				stage = NeoJavaxApplicationSupport.createStage(view, Modality.APPLICATION_MODAL);
//				stage.show();
//			}
//		});

	}
}

package one.main.support;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class SplashScreen {

	private static String DEFAULT_IMAGE = "/images/slpashScreen.png";
	private ProgressBar splashProgressBar;
	/**
	 * Override this to create your own splash pane parent node.
	 *
	 * @return A standard image
	 */
	public Parent getParent() {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		final ImageView imageView = new ImageView(getClass().getResource(getImagePath()).toExternalForm());
		imageView.setFitWidth(bounds.getWidth()/2);
		
//		final String finalCssProp =this.cssProperty.replace("$img$", getClass().getResource(getImagePath()).toExternalForm());
		this.splashProgressBar = new ProgressBar();
		splashProgressBar.setPrefWidth(bounds.getWidth()/2);
		imageView.setFitHeight(bounds.getHeight()/2);
		final VBox vbox = new VBox();
//		vbox.setStyle(finalCssProp);
		vbox.getChildren().addAll(imageView,splashProgressBar);

		return vbox;
	}
	
	
	private String cssProperty ="-fx-background-image: url(\"$img$\");"
								+ "-fx-background-size: cover;";
	

	/**
	 * Customize if the splash screen should be visible at all.
	 *
	 * @return true by default
	 */
	public boolean visible() {
		return true;
	}

	/**
	 * Use your own splash image instead of the default one.
	 *
	 * @return "/splash/javafx.png"
	 */
	public String getImagePath() {
		return DEFAULT_IMAGE;
	}

	public ProgressBar getSplashProgressBar() {
		return splashProgressBar;
	}
	
	
	

}

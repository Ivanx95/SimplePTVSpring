package one.main.support;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import one.main.base.StageController;
import one.main.config.AppConfig;
import one.main.config.JPAConfig;
import one.main.config.SettingsConfig;
import one.main.controller.base.AlertBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.felixroske.jfxsupport.PropertyReaderHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The Class NeoJavaxApplicationSupport.
 *
 * @author Felix Roske
 */
@SuppressWarnings("WeakerAccess")
public abstract class NeoJavaxApplicationSupport extends Application {
    private static Logger LOGGER = LoggerFactory.getLogger(NeoJavaxApplicationSupport.class);

    private static String[] savedArgs = new String[0];

    static Class<? extends JavaFxView> savedInitialView;
    static SplashScreen splashScreen;
    private static ConfigurableApplicationContext applicationContext;


    private static List<Image> icons = new ArrayList<>();
    protected final List<Image> defaultIcons = new ArrayList<>();

    private final CompletableFuture<Runnable> splashIsShowing;

    protected NeoJavaxApplicationSupport() {
        splashIsShowing = new CompletableFuture<>();
    }

    public static Stage getStage() {
        return GUIState.getStage();
    }

    public static Scene getScene() {
        return GUIState.getScene();
    }

    public static HostServices getAppHostServices() {
        return GUIState.getHostServices();
    }

    public static SystemTray getSystemTray() {
        return GUIState.getSystemTray();
    }

    
    public static JavaFxView getView(final Class<? extends JavaFxView> window) {
    	  final JavaFxView view = applicationContext.getBean(window);
    	  
    	  return view;
    }
    
    /**
     * @param window The FxmlView derived class that should be shown.
     * @param mode   See {@code javafx.stage.Modality}.
     */
    public static StageController createStage(final Class<? extends JavaFxView> window, final Modality mode) {
        final JavaFxView view = applicationContext.getBean(window);
        Stage newStage = new Stage();

        Scene newScene;
        if (view.getView().getScene() != null) {
            // This view was already shown so
            // we have a scene for it and use this one.
            newScene = view.getView().getScene();
        } else {
            newScene = new Scene(view.getView());
        }

        newStage.setScene(newScene);
        newStage.initModality(mode);
        newStage.initOwner(getStage());
        newStage.setTitle(view.getDefaultTitle());
        newStage.initStyle(view.getDefaultStyle());

        Object obj=  view.getPresenter();
       
        if(obj instanceof StageController) {
        	StageController controller=((StageController)obj);
        	controller.setStage(newStage);
        	controller.setScene(newScene);
			return controller;
			
		}else {
			throw new IllegalStateException("Not stage controller");
		}
        
       
    }
    
    /**
     * @param window The FxmlView derived class that should be shown.
     * @param mode   See {@code javafx.stage.Modality}.
     */
    public static void showView(final Class<? extends JavaFxView> window, final Modality mode) {
        final JavaFxView view = applicationContext.getBean(window);
        Stage newStage = new Stage();

        Scene newScene;
        if (view.getView().getScene() != null) {
            // This view was already shown so
            // we have a scene for it and use this one.
            newScene = view.getView().getScene();
        } else {
            newScene = new Scene(view.getView());
        }

        newStage.setScene(newScene);
        newStage.initModality(mode);
        newStage.initOwner(getStage());
        newStage.setTitle(view.getDefaultTitle());
        newStage.initStyle(view.getDefaultStyle());

        newStage.showAndWait();
        Object obj=  view.getPresenter();
       
        if(obj instanceof StageController) {
			((StageController)obj).setStage(newStage);
		}
    }

    private void loadIcons(ConfigurableApplicationContext ctx) {
        try {
            final List<String> fsImages = PropertyReaderHelper.get(ctx.getEnvironment(), Constant.KEY_APPICONS);

            if (!fsImages.isEmpty()) {
                fsImages.forEach((s) ->
                        {
                            Image img = new Image(getClass().getResource(s).toExternalForm());
                            icons.add(img);
                        }
                );
            } else { // add factory images
                icons.addAll(defaultIcons);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load icons: ", e);
        }


    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#init()
     */
    @Override
    public void init() throws Exception {
        // Load in JavaFx Thread and reused by Completable Future, but should no be a big deal.
        defaultIcons.addAll(loadDefaultIcons());
        CompletableFuture.supplyAsync(() ->{
           
        	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, SettingsConfig.class, JPAConfig.class);
        	
        	return context;
        }
        ).whenComplete((ctx, throwable) -> {
            if (throwable != null) {
                LOGGER.error("Failed to load spring application context: ", throwable);
                Platform.runLater(() -> showErrorAlert(throwable));
            } else {
                Platform.runLater(() -> {
                    loadIcons(ctx);
                    launchApplicationView(ctx);
                });
            }
        }).thenAcceptBothAsync(splashIsShowing, (ctx, closeSplash) -> {
            Platform.runLater(closeSplash);
        });
    }


    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage stage) throws Exception {

        GUIState.setStage(stage);
//        GUIState.setHostServices(this.getHostServices());
        final Stage splashStage = new Stage(StageStyle.TRANSPARENT);

//        Screen screen = Screen.getPrimary();
//        Rectangle2D bounds = screen.getVisualBounds();

//        splashStage.setX(bounds.getMinX());
//        splashStage.setY(bounds.getMinY());
//        splashStage.setWidth(bounds.getWidth());
//        splashStage.setHeight(bounds.getHeight());
//        

		if (NeoJavaxApplicationSupport.splashScreen.visible()) {
			final Scene splashScene = new Scene(splashScreen.getParent(), Color.TRANSPARENT);
//			splashScreen.getSplashProgressBar().setPrefWidth(bounds.getWidth());
			splashStage.setScene(splashScene);
            splashStage.getIcons().addAll(defaultIcons);
            splashStage.initStyle(StageStyle.TRANSPARENT);
            beforeShowingSplash(splashStage);
            splashStage.show();
		}

        splashIsShowing.complete(() -> {
        	
            showInitialView();
            if (NeoJavaxApplicationSupport.splashScreen.visible()) {
                splashStage.hide();
                splashStage.setScene(null);
            }
        });
    }


    /**
     * Show initial view.
     */
    private void showInitialView() {
        final String stageStyle = applicationContext.getEnvironment().getProperty(Constant.KEY_STAGE_STYLE);
        if (stageStyle != null) {
            GUIState.getStage().initStyle(StageStyle.valueOf(stageStyle.toUpperCase()));
        } else {
            GUIState.getStage().initStyle(StageStyle.DECORATED);
        }

        beforeInitialView(GUIState.getStage(), applicationContext);

        showView(savedInitialView);
    }


    /**
     * Launch application view.
     */
    private void launchApplicationView(final ConfigurableApplicationContext ctx) {
        NeoJavaxApplicationSupport.applicationContext = ctx;
    }

    /**
     * Show view.
     *
     * @param newView the new view
     */
    public static void showView(final Class<? extends JavaFxView> newView) {
        try {
            final JavaFxView view = applicationContext.getBean(newView);

            if (GUIState.getScene() == null) {
                GUIState.setScene(new Scene(view.getView()));
            } else {
                GUIState.getScene().setRoot(view.getView());
            }
            GUIState.getStage().setScene(GUIState.getScene());

            applyEnvPropsToView(GUIState.getStage());

          
            GUIState.getStage().getIcons().addAll(icons);
            GUIState.getStage().show();

        } catch (Throwable t) {
            LOGGER.error("Failed to load application: ", t);
            showErrorAlert(t);
        }
    }

    /**
     * Show error alert that close app.
     *
     * @param throwable cause of error
     */
    private static void showErrorAlert(Throwable throwable) {
        Alert alert =  AlertBuilder.createAlertExceptionWaning(throwable);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    /**TODO: Move to controller or {@link JavaFxView#createController(Modality)}
     * Apply env props to view.
     */
    private  static void applyEnvPropsToView(Stage stage) {
        PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), Constant.KEY_TITLE, String.class,
                stage::setTitle);

        PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), Constant.KEY_STAGE_WIDTH, Double.class,
                stage::setWidth);

        PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), Constant.KEY_STAGE_HEIGHT, Double.class,
                stage::setHeight);

        PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), Constant.KEY_STAGE_RESIZABLE, Boolean.class,
                stage::setResizable);
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#stop()
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        if (applicationContext != null) {
            applicationContext.close();
        } // else: someone did it already
    }

    /**
     * Sets the title. Allows to overwrite values applied during construction at
     * a later time.
     *
     * @param title the new title
     */
    protected static void setTitle(final String title) {
        GUIState.getStage().setTitle(title);
    }

    /**
     * Launch app.
     *
     * @param appClass the app class
     * @param view     the view
     * @param args     the args
     */
    public static void launch(final Class<? extends Application> appClass,
                              final Class<? extends JavaFxView> view, final String[] args) {

        launch(appClass, view, new SplashScreen(), args);
    }
    /**
     * Launch app.
     * @deprecated To be more in line with javafx.application please use launch
     * @param appClass the app class
     * @param view     the view
     * @param args     the args
     */
    @Deprecated
    public static void launchApp(final Class<? extends Application> appClass,
                              final Class<? extends JavaFxView> view, final String[] args) {

        launch(appClass, view, new SplashScreen(), args);
    }

    /**
     * Launch app.
     *
     * @param appClass     the app class
     * @param view         the view
     * @param splashScreen the splash screen
     * @param args         the args
     */
    public static void launch(final Class<? extends Application> appClass,
                              final Class<? extends JavaFxView> view, final SplashScreen splashScreen, final String[] args) {
        savedInitialView = view;
        savedArgs = args;

        if (splashScreen != null) {
            NeoJavaxApplicationSupport.splashScreen = splashScreen;
        } else {
            NeoJavaxApplicationSupport.splashScreen = new SplashScreen();
        }

        if (SystemTray.isSupported()) {
            GUIState.setSystemTray(SystemTray.getSystemTray());
        }

        Application.launch(appClass, args);
    }
    /**
     * Launch app.
     *
     * @deprecated To be more in line with javafx.application please use launch
     * @param appClass     the app class
     * @param view         the view
     * @param splashScreen the splash screen
     * @param args         the args
     */
    @Deprecated
    public static void launchApp(final Class<? extends Application> appClass,
                              final Class<? extends JavaFxView> view, final SplashScreen splashScreen, final String[] args) {
        launch(appClass, view, splashScreen, args);
    }
    
    /**
     * Gets called after full initialization of Spring application context
     * and JavaFX platform right before the initial view is shown.
     * Override this method as a hook to add special code for your app. Especially meant to
     * add AWT code to add a system tray icon and behavior by calling
     * GUIState.getSystemTray() and modifying it accordingly.
     * <p>
     * By default noop.
     *
     * @param stage can be used to customize the stage before being displayed
     * @param ctx   represents spring ctx where you can loog for beans.
     */
    public void beforeInitialView(final Stage stage, final ConfigurableApplicationContext ctx) {
    }

    public void beforeShowingSplash(Stage splashStage) {

    }

    public Collection<Image> loadDefaultIcons() {
        return Arrays.asList(new Image(getClass().getResource("/icons/gear_16x16.png").toExternalForm()),
                new Image(getClass().getResource("/icons/gear_24x24.png").toExternalForm()),
                new Image(getClass().getResource("/icons/gear_36x36.png").toExternalForm()),
                new Image(getClass().getResource("/icons/gear_42x42.png").toExternalForm()),
                new Image(getClass().getResource("/icons/gear_64x64.png").toExternalForm()));
    }
}

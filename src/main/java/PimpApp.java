import controller.ControllerFactory;
import controller.IController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code PimpApp} class represents the entry point for the Pimp application.
 */
public final class PimpApp extends Application {

  @Override
  public void start(Stage stage) {
    // TODO... create model and view instances
    IController controller = ControllerFactory.createController(stage);
  }

  /**
   * Runs the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    Application.launch();
  }
}
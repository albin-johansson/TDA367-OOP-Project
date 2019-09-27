import controller.ControllerFactory;
import controller.IController;
import java.awt.Color;
import javafx.application.Application;
import javafx.stage.Stage;
import model.IModel;
import model.ModelFactory;
import model.canvas.layer.ILayer;
import model.canvas.layer.LayerFactory;
import view.IView;
import view.ViewFactory;

/**
 * The {@code PimpApp} class represents the entry point for the Pimp application.
 */
public final class PimpApp extends Application {

  @Override
  public void start(Stage stage) {
    IModel model = ModelFactory.createModel();
    IView view = ViewFactory.createView(model);
    IController controller = ControllerFactory.createController(model, view, stage);
    model.addCanvasUpdateListener(view);
    controller.run();

    model.addLayer(LayerFactory.createRasterLayer(1000,1000));
    model.selectLayer(0);


  }

  /**
   * Launches the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    Application.launch();

  }
}
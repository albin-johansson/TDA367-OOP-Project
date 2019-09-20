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

    ILayer layer = LayerFactory.createRasterLayer(100,200);
    for(int i = 0 ; i < 100; i++){
      layer.setPixel(i,6, Color.GREEN);
      layer.setPixel(i,7, Color.GREEN);
      layer.setPixel(i,8, Color.GREEN);
      layer.setPixel(i,9, Color.GREEN);
      layer.setPixel(i,10, Color.GREEN);
      layer.setPixel(i,11, Color.GREEN);
      layer.setPixel(i,12, Color.GREEN);
      layer.setPixel(i,13, Color.GREEN);
      layer.setPixel(i,14, Color.GREEN);
    }
    /*layer.setPixel(5,6, Color.GREEN);
    layer.setPixel(5,7, Color.GREEN);
    layer.setPixel(5,8, Color.BLACK);
    layer.setPixel(5,9, Color.BLACK);
    layer.setPixel(5,10, Color.BLACK);*/

    model.addLayer(layer);

    ILayer layer2 = LayerFactory.createRasterLayer(50,200);
    for(int i = 0 ; i < 50; i++){
      layer2.setPixel(i,6, Color.RED);
      layer2.setPixel(i,7, Color.RED);
      layer2.setPixel(i,8, Color.RED);
      layer2.setPixel(i,9, Color.RED);
      layer2.setPixel(i,10, Color.RED);
    }

    model.addLayer(layer2);

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
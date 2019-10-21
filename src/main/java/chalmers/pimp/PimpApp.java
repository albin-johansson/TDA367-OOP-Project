package chalmers.pimp;

import chalmers.pimp.controller.ControllerFactory;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.view.IView;
import chalmers.pimp.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@code chalmers.pimp.PimpApp} class represents the entry point for the Pimp application.
 */
public final class PimpApp extends Application {

  /**
   * Launches the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    Application.launch();
  }

  @Override
  public void start(Stage stage) {
    IModel model = ModelFactory.createModel();
    IView view = ViewFactory.createView(model);
    IController controller = ControllerFactory.createController(model, view, stage);

    model.addCanvasUpdateListener(view);

    controller.run();

    controller.centerViewport();
    model.notifyColorUpdateListeners();

    view.repaint();
  }
}
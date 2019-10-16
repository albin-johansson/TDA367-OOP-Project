package chalmers.pimp.controller;

import chalmers.pimp.model.IModel;
import chalmers.pimp.view.IView;
import java.io.IOException;
import javafx.stage.Stage;

/**
 * The {@code ControllerFactory} class is a factory for creating instances of the {@code
 * IController} interface.
 */
public final class ControllerFactory {

  private ControllerFactory() {
  }

  /**
   * Creates and returns a model instance.
   *
   * @param model the associated model instance.
   * @param view  the associated model instance.
   * @param stage the parent stage instance.
   * @return a model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IController createController(IModel model, IView view, Stage stage) {
    try {
      return new ControllerImpl(model, view, stage);
    } catch (IOException e) {
      throw new RuntimeException("Failed to create chalmers.pimp.controller instance: " + e);
    }
  }
}
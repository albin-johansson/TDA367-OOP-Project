package controller;

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
   * Creates and returns a controller instance.
   *
   * @return a controller instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IController createController(Stage stage) {
    try {
      return new ControllerImpl(stage);
    } catch (IOException e) {
      throw new RuntimeException("Failed to create controller instance: " + e);
    }
  }
}
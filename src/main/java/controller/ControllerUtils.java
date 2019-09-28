package controller;

import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * The {@code ControllerUtils} class is a utility class related to JavaFX-controllers.
 */
public final class ControllerUtils {

  private ControllerUtils() {
  }

  /**
   * Makes the supplied node the controller for the specified FXML-file.
   *
   * @param node the node instance that will made the controller for the FXML-file.
   * @param url  the path of the FXML-file.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static void makeController(Node node, URL url) {
    Objects.requireNonNull(node, "Null node!");
    Objects.requireNonNull(url, "Null URL!");
    try {
      FXMLLoader loader = new FXMLLoader(url);
      loader.setRoot(node);
      loader.setController(node);
      loader.load();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
package controller.components;

import controller.ControllerUtils;
import controller.IController;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.Resources;

/**
 * The {@code ToolbarPane} class represents the top pane in the Pimp application.
 */
final class ToolbarPane extends AnchorPane {

  private final IController controller; // TODO use this with menu item actions

  /**
   * @param controller the associated controller instance.
   * @throws IOException          if the associated FXML file cannot be read.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ToolbarPane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);
    ControllerUtils.makeController(this, Resources.find(getClass(), "toolbar.fxml"));
  }

  @FXML
  private void exit() {
    Platform.exit();
  }
}
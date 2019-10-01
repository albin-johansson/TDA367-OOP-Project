package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code ToolbarPane} class represents the top pane in the Pimp application.
 */
final class ToolbarPane extends AnchorPane {

  private final IController controller; // TODO use this with menu item actions

  /**
   * @param controller the associated chalmers.pimp.controller instance.
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

  @FXML
  private void createNewLayer() {
    controller.createNewLayer();
  }
}



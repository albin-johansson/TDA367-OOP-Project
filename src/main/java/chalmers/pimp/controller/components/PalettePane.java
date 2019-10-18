package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code PalettePane} class represents the pane that holds the buttons for the various mouse
 * tools (the "palette"). This class is designed to be used to delegate tool selection calls to a
 * controller.
 *
 * @see IController
 */
final class PalettePane extends AnchorPane {

  private final IController controller;

  /**
   * @param controller the associated controller instance.
   * @throws IOException          if the associated FXML file cannot be loaded.
   * @throws NullPointerException if the supplied controller is {@code null}.
   */
  PalettePane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);
    ControllerUtils.makeController(this, Resources.find(getClass(), "palette_pane.fxml"));
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectPencil() {
    controller.selectRasterPen();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectEraser() {
    controller.selectEraser();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectMoveTool() {
    controller.selectMoveTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectRectangleTool() {
    controller.selectRectangleTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectRotateTool() {
    controller.selectRotateTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectDoodleTool() {
    controller.selectDoodleTool();
  }
}
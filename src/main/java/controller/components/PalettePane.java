package controller.components;

import controller.ControllerUtils;
import controller.IController;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.Resources;

/**
 * The {@code PalettePane} class represents the pane that holds the buttons for the various mouse
 * tools (the "palette").
 */
final class PalettePane extends AnchorPane {

  private final IController controller;

  /**
   * @param controller the associated controller instance.
   * @throws IOException if the associated FXML file cannot be loaded.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  PalettePane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);
    ControllerUtils.makeController(this, Resources.find(getClass(), "palette_pane.fxml"));
    setStyle("-fx-background-color: cyan;");
  }

  @FXML
  private void selectPencil() {
    controller.selectPencil();
  }

  @FXML
  private void selectEraser() {
    controller.selectEraser();
  }

  @FXML
  private void selectBucket() {
    controller.selectBucket();
  }
}
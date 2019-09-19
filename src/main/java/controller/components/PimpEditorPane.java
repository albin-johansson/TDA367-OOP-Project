package controller.components;

import controller.ControllerUtils;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import util.Resources;

/**
 * The {@code PimpEditorPane} class represents the main editor pane for the Pimp application.
 */
public final class PimpEditorPane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private Canvas canvas;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  public PimpEditorPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "root.fxml"));
    setStyle("-fx-background-color: gray;");
  }

  /**
   * @return a refference to the graphicscontext used by the canvas
   */
  public GraphicsContext getGraphics() {
    return canvas.getGraphicsContext2D();
  }
}
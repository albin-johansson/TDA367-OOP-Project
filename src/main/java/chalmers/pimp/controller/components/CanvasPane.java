package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code CanvasPane} represents the canvas pane which holds the {@link Canvas} instance used
 * used for custom rendering.
 */
final class CanvasPane extends AnchorPane {

  private final Canvas canvas;

  /**
   * @throws IOException          if the associated FXML-file cannot be loaded.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  CanvasPane(IController controller) throws IOException {
    Objects.requireNonNull(controller);

    ControllerUtils.makeController(this, Resources.find(getClass(), "canvas_pane.fxml"));

    canvas = new Canvas();

    getChildren().add(canvas);
    canvas.widthProperty().bind(widthProperty());
    canvas.heightProperty().bind(heightProperty());

    setOnMousePressed(controller::selectedToolPressed);
    setOnMouseReleased(controller::selectedToolReleased);
  }

  /**
   * Returns the graphics context associated with this canvas pane.
   *
   * @return the graphics context associated with this canvas pane.
   */
  GraphicsContext getGraphics() {
    return canvas.getGraphicsContext2D();
  }
}
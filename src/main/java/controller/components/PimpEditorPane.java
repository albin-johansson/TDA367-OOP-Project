package controller.components;

import controller.ControllerUtils;
import controller.IController;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Resources;

/**
 * The {@code PimpEditorPane} class represents the main editor pane for the Pimp application.
 */
public final class PimpEditorPane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private Canvas canvas;

  @FXML
  @SuppressWarnings("unused")
  private VBox verticalLayerBox;

  @FXML
  @SuppressWarnings("unused")
  private HBox horizontalToolBar;

  private final IController controller;

  /**
   * @param controller the parent controller instance.
   * @throws IOException if the associated FXML file cannot be found.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public PimpEditorPane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);

    ControllerUtils.makeController(this, Resources.find(getClass(), "root.fxml"));
    setStyle("-fx-background-color: gray;");

    canvas.setOnMousePressed(event -> {
      // TODO...
    });

    canvas.setOnMouseMoved(event -> {
      // TODO...
    });

    canvas.setOnMouseReleased(event -> {
      // TODO...
    });
  }

  /**
   * Returns the graphics context used by the main canvas.
   *
   * @return the graphics context used by the main canvas.
   */
  public GraphicsContext getGraphics() {
    return canvas.getGraphicsContext2D();
  }
}
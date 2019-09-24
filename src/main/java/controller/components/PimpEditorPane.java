package controller.components;

import controller.ControllerUtils;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
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

  /**
   * Sets what function will be executed when the user press the mouse over the canvas
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasPressed(EventHandler<? super MouseEvent> e) {
    this.canvas.setOnMousePressed(e);
  }

  /**
   * Sets what function will be executed when drag is first detected
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasReleased(EventHandler<? super MouseEvent> e) {
    this.canvas.setOnMouseReleased(e);
  }

  /**
   * Set what function to execute when the mouse is hold and dragged The function will be each time
   * the position change
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasDragged(EventHandler<? super MouseEvent> e) {
    this.canvas.setOnMouseDragged(e);
  }
}
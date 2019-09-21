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
   * Sets what function will be executed when the user clicks the canvas
   * The function will be executed when the the mouse is released
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasClick(EventHandler<? super MouseEvent> e){
    this.canvas.setOnMouseClicked(e);
  }

  /**
   * Set what function to execute when the mouse is hold and dragged
   * The function will be each time the position change
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasDragged(EventHandler<? super MouseEvent> e){
    this.canvas.setOnMouseDragged(e);
  }

  /**
   * Sets what function will be executed when drag is first detected
   *
   * @param e the function which will be executed
   */
  public void setOnCanvasDragStart(EventHandler<? super MouseEvent> e){
    this.canvas.setOnDragDetected(e);
  }
}
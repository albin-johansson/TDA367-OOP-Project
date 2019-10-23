package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModelSizeListener;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code InfoPane} class represents the bottom pane displaying interesting data about the
 * project.
 *
 * @see IModelSizeListener
 */
final class InfoPane extends AnchorPane implements IModelSizeListener {

  @FXML
  @SuppressWarnings("unused")
  private Label canvasWidth;
  @FXML
  @SuppressWarnings("unused")
  private Label canvasHeight;
  @FXML
  @SuppressWarnings("unused")
  private Label xPos;
  @FXML
  @SuppressWarnings("unused")
  private Label yPos;
  @FXML
  @SuppressWarnings("unused")
  private Label layerWidth;
  @FXML
  @SuppressWarnings("unused")
  private Label layerHeight;
  @FXML
  private Label layerRotation;


  /**
   * @throws IOException if the associated FXML-file cannot be loaded.
   */
  InfoPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "info_pane.fxml"));
  }

  /**
   * Updates the values of the mouse coordinate labels.
   *
   * @param mouseX   the x-coordinate of the mouse.
   * @param mouseY   the y-coordinate of the mouse.
   * @param viewport the viewport that is currently being used.
   * @throws NullPointerException if the supplied viewport is {@code null}.
   */
  void updateMouseCoordinates(int mouseX, int mouseY, IReadOnlyViewport viewport) {
    Objects.requireNonNull(viewport);

    int x = mouseX - viewport.getX();
    int y = mouseY - viewport.getY();

    xPos.setText(Integer.toString(x));
    yPos.setText(Integer.toString(y));
  }

  /**
   * "Disables" the mouse coordinate labels. This method should be used when the mouse coordinates
   * are outside the canvas pane.
   */
  void disableMouseCoordinates() {
    xPos.setText("-");
    yPos.setText("-");
  }

  /**
   * Sets the value of the canvas width label.
   *
   * @param width the new value of the canvas width label.
   */
  void setCanvasWidthLabel(int width) {
    canvasWidth.setText(Integer.toString(width));
  }

  /**
   * Sets the value of the canvas height label.
   *
   * @param height the new value of the canvas height label.
   */
  void setCanvasHeightLabel(int height) {
    canvasHeight.setText(Integer.toString(height));
  }

  /**
   * Sets the value of the active layer width label.
   *
   * @param width the new value of the active layer width label.
   */
  void setLayerWidthLabel(int width) {
    layerWidth.setText(Integer.toString(width));
  }

  /**
   * Sets the value of the active layer height label.
   *
   * @param height the new value of the active layer height label.
   */
  void setLayerHeightLabel(int height) {
    layerHeight.setText(Integer.toString(height));
  }

  @Override
  public void sizeUpdated(int width, int height) {
    setCanvasWidthLabel(width);
    setCanvasHeightLabel(height);
  }

  /**
   * Updates the layerRotation label.
   *
   * @param rotation the layer with the new rotation.
   */
  void setLayerRotationLabel(String rotation) {
    layerRotation.setText(rotation);
  }
}

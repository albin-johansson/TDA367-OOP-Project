package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code InfoPane} class represents the bottom pane displaying interesting data about the
 * project.
 */
class InfoPane extends AnchorPane {

  @FXML
  private Label canvasWidth;
  @FXML
  private Label canvasHeight;
  @FXML
  private Label xPos;
  @FXML
  private Label yPos;
  @FXML
  private Label layerWidth;
  @FXML
  private Label layerHeight;
  @FXML
  private Label layerRotation;

  InfoPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "info_pane.fxml"));
  }

  /**
   * Updates the canvasWidth Label.
   *
   * @param string the new width.
   */
  void setCanvasWidthLabel(String string) {
    canvasWidth.setText(string);
  }

  /**
   * Updates the canvasHeight Label.
   *
   * @param string the new height.
   */
  void setCanvasHeightLabel(String string) {
    canvasHeight.setText(string);
  }

  /**
   * Updates the mouse-coordinates displayed.
   *
   * @param e the associated mouseEvent.
   */
  void updateCoordinates(MouseEvent e) {
    xPos.setText(String.valueOf((int) e.getX()));
    yPos.setText(String.valueOf((int) e.getY()));
  }

  /**
   * Stops displaying mouse-coordinates when the mouse leaves the canvas.
   *
   * @param e the associated mouseEvent.
   */
  void turnOffCoordinates(MouseEvent e) {
    xPos.setText("-");
    yPos.setText("-");
  }

  /**
   * Updates the layerWidth Label.
   *
   * @param string the new width.
   */
  void setLayerWidthLabel(String string) {
    layerWidth.setText(string);
  }

  /**
   * Updates the layerHeight Label.
   *
   * @param string the new height.
   */
  void setLayerHeightLabel(String string) {
    layerHeight.setText(string);
  }

  /**
   * Updates the layerRotation label.
   *
   * @param string the layer with the new rotation.
   */
  void setLayerRotationLabel(String string) {
    layerRotation.setText(string);
  }
}

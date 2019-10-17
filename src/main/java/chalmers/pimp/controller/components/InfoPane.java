package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModelSizeListener;
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

  InfoPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "info_pane.fxml"));
  }

  void updateCoordinates(MouseEvent e) {
    xPos.setText(Integer.toString((int) e.getX()));
    yPos.setText(Integer.toString((int) e.getY()));
  }

  void turnOffCoordinates() {
    xPos.setText("-");
    yPos.setText("-");
  }

  void setCanvasWidthLabel(int width) {
    canvasWidth.setText(Integer.toString(width));
  }

  void setCanvasHeightLabel(int height) {
    canvasHeight.setText(Integer.toString(height));
  }

  void setLayerWidthLabel(int width) {
    layerWidth.setText(Integer.toString(width));
  }

  void setLayerHeightLabel(int height) {
    layerHeight.setText(Integer.toString(height));
  }

  @Override
  public void sizeUpdated(int width, int height) {
    setCanvasWidthLabel(width);
    setCanvasHeightLabel(height);
  }
}
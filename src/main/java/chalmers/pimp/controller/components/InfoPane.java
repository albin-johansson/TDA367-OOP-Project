package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

final class InfoPane extends AnchorPane {

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

  void setCanvasWidthLabel(int width) {
    canvasWidth.setText(String.valueOf(width));
  }

  void setCanvasHeightLabel(int height) {
    canvasHeight.setText(String.valueOf(height));
  }

  void updateCoordinates(MouseEvent e) {
    xPos.setText(String.valueOf((int) e.getX()));
    yPos.setText(String.valueOf((int) e.getY()));
  }

  void turnOffCoordinates() {
    xPos.setText("-");
    yPos.setText("-");
  }

  void setLayerWidthLabel(String width) {
    layerWidth.setText(width);
  }

  void setLayerHeightLabel(String height) {
    layerHeight.setText(height);
  }
}

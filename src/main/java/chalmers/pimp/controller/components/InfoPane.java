package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.IModel;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InfoPane extends AnchorPane {

  @FXML
  private Label canvasWidth;
  @FXML
  private Label canvasHeight;
  @FXML
  private Label xPos;
  @FXML
  private Label yPos;

  InfoPane(IController controller, IModel model) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "info_pane.fxml"));
  }

  void setCanvasWidthLabel(String string) {
    canvasWidth.setText(string);
  }

  void setCanvasHeightLabel(String string) {
    canvasHeight.setText(string);
  }

  void updateCoordinates(MouseEvent e){
    xPos.setText(String.valueOf((int)e.getX()));
    yPos.setText(String.valueOf((int)e.getY()));
  }

  void turnOffCoordinates(MouseEvent e){
    xPos.setText("-");
    yPos.setText("-");
  }
}

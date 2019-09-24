package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.Resources;

public class LayerItemManagerPane extends AnchorPane {

  @FXML
  private StackPane stackPane;

  @FXML
  private AnchorPane emptyLayerPane;

  @FXML
  private VBox layerItemVBox;

  LayerItemManagerPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item_manager.fxml"));
    setStyle("-fx-background-color: gray;");
  }
}

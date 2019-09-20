package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import util.Resources;

public class LayerItemPane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private TextField textField;

  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;

  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  public LayerItemPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    setStyle("-fx-background-color: gray;");
  }
}

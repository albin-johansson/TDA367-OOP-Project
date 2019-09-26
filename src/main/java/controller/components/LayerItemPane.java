package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.canvas.layer.IReadOnlyLayer;
import util.Resources;

/**
 * The {@code LayerItemPane} class represents a layer item in the view.
 */
final class LayerItemPane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private TextField textField;

  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;

  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;

  private IReadOnlyLayer layer;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   * @throws NullPointerException if the IReadOnlyLayer argument is null
   */
  LayerItemPane(IReadOnlyLayer layer) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    setStyle("-fx-background-color: gray;");
    this.layer = Objects.requireNonNull(layer);
  }

  void update() {

  }
}

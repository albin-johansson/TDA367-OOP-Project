package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.IModel;
import model.canvas.layer.IReadOnlyLayer;
import util.Resources;

/**
 * The {@code LayerItemPane} class represents a layer item in the view.
 */
final class LayerItemPane extends AnchorPane {

  private static final Image EYE_OPEN_IMAGE;
  private static final Image EYE_CLOSED_IMAGE;

  static {
    try {
      URL path = (Resources.find(LayerItemPane.class, "images/eye_closed.png"));
      EYE_CLOSED_IMAGE = new Image(path.toURI().toString());
      path = (Resources.find(LayerItemPane.class, "images/eye_open.png"));
      EYE_OPEN_IMAGE = new Image(path.toURI().toString());
    } catch (Exception e) {
     throw new RuntimeException(e);
    }
  }

  @FXML
  @SuppressWarnings("unused")
  private TextField textField;

  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;

  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;

  private IModel model;

  private IReadOnlyLayer layer;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   * @throws NullPointerException if the IReadOnlyLayer argument is null
   */
  LayerItemPane(IModel model, IReadOnlyLayer layer) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    setStyle("-fx-background-color: gray;");
    this.model = Objects.requireNonNull(model);
    this.layer = Objects.requireNonNull(layer);
  }

  @FXML
  private void toggleVisibility() {
    for (IReadOnlyLayer layer : model.getLayers()) {
      if (this.layer.equals(layer)) {
        model.setLayerVisibility(layer,
            toggleButton.getToggleGroup().getSelectedToggle().isSelected());
      }
    }
  }

  void update() {

  }
}

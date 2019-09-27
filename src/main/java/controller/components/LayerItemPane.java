package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
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
  private Label textLabel;

  @FXML
  @SuppressWarnings("unused")
  private ToggleButton toggleButton;

  @FXML
  @SuppressWarnings("unused")
  private ImageView imageView;

  private IModel model;
  private IReadOnlyLayer layer;

  /**
   * @param model an reference to the {@code IModel}.
   * @param layer the layer this {@code LayerItem} represents.
   * @throws IOException          if the associated FXML file cannot be found.
   * @throws NullPointerException if the IReadOnlyLayer argument is null
   */
  LayerItemPane(IModel model, IReadOnlyLayer layer) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    setStyle("-fx-background-color: gray;");
    this.model = Objects.requireNonNull(model);
    this.layer = Objects.requireNonNull(layer);
    updateVisibilityImage();
  }

  /**
   * Toggles the visibility boolean connected with this layerItem's layer through the model'a
   * method
   */
  @FXML
  private void toggleVisibility() {
    for (IReadOnlyLayer l : model.getLayers()) {
      if (layer.equals(l)) {
        model.setLayerVisibility(l, toggleButton.isSelected());
        break;
      }
    }
    updateVisibilityImage();
  }

  /**
   * Updates the image used on the visibility button based on "this" layers visibility in the
   * model.
   */
  private void updateVisibilityImage() {
    if (layer.isVisible()) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }
}

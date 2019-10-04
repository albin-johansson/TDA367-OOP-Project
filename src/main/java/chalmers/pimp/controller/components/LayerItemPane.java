package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code LayerItemPane} class represents a layer item in the chalmers.pimp.view.
 */
final class LayerItemPane extends AnchorPane {

  private static final Image EYE_OPEN_IMAGE;
  private static final Image EYE_CLOSED_IMAGE;

  static {
    try {
      URL path = (Resources.find(LayerItemPane.class, "images/light/eye_closed.png"));
      EYE_CLOSED_IMAGE = new Image(path.toURI().toString());
      path = (Resources.find(LayerItemPane.class, "images/light/eye_open.png"));
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
    this.model = Objects.requireNonNull(model);
    this.layer = Objects.requireNonNull(layer);
    textLabel.setText(layer.getName());
    updateVisibilityImage();
  }

  /**
   * Toggles the visibility boolean connected with this layerItem's layer through the
   * chalmers.pimp.model's method
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
   * Updates this layerItem's layer's name through the chalmers.pimp.model.
   */
  @FXML
  private void updateLayerName() {
    String temp = textLabel.getText();
    if (temp == "") {
      textLabel.setText(layer.getName());
    } else {
      model.setLayerName(layer, textLabel.getText());
    }
  }

  /**
   * Sets this layer item's associated layer as the active layer.
   */
  @FXML
  private void updateActiveLayer() {
    model.selectLayer(layer);
    showIfLayerIsSelected();
  }

  @FXML
  private void decreaseZIndex() {
    model.moveLayer(layer, -1);
  }

  @FXML
  private void increaseZIndex() {
    model.moveLayer(layer, 1);
  }

  @FXML
  private void removeLayer(){
    model.removeLayer(layer);
  }

  //TODO Rethink if below methods should all be private and called by single update method...

  /**
   * Updates this pane, runs all related private methods.
   */
  void update() {
    updateVisibilityImage();
    showIfLayerIsSelected();
  }

  /**
   * Updates the image used on the visibility button based on "this" layers visibility in the
   * chalmers.pimp.model.
   */
  private void updateVisibilityImage() {
    if (layer.isVisible()) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }

  /**
   * Sets the style for this {@code LayerItemPane} to Gray if the corresponding Layer is active.
   * Reverts to CSS default otherwise.
   */
  private void showIfLayerIsSelected() {
    if (layer == model.getActiveLayer()) {
      this.setStyle("-fx-background-color: -selected-color");
    } else {
      this.setStyle("");
    }
  }

  /**
   * Returns the {@code IReadOnlyLayer} that this LayerItem represents.
   *
   * @return the layer this LayerItem represents.
   */
  IReadOnlyLayer getLayer() {
    return layer;
  }
}

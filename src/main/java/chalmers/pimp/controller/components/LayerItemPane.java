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
      URL path = Resources.find(LayerItemPane.class, "images/light/eye_closed.png");
      EYE_CLOSED_IMAGE = new Image(path.toURI().toString());

      path = Resources.find(LayerItemPane.class, "images/light/eye_open.png");
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

  private final IModel model;
  private final IReadOnlyLayer layer;
  private final int associatedLayerIndex;

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

    associatedLayerIndex = layer.getDepthIndex();

    updateVisibilityImage();
    updateSelectionState();
  }

  @FXML
  @SuppressWarnings("unused")
  private void toggleVisibility() {
    model.setLayerVisibility(associatedLayerIndex, toggleButton.isSelected());
    updateVisibilityImage();
//    updateVisibilityImage();
  }

  @FXML
  @SuppressWarnings("unused")
  private void updateLayerName() {
    String temp = textLabel.getText();
    if (temp.isEmpty()) {
      textLabel.setText(layer.getName());
    } else {
      model.setLayerName(layer, textLabel.getText());
    }
  }

  @FXML
  @SuppressWarnings("unused")
  private void updateActiveLayer() {
    model.selectLayer(associatedLayerIndex);
    updateSelectionState();
//    showIfLayerIsSelected();
  }

  @FXML
  @SuppressWarnings("unused")
  private void decreaseZIndex() {
    model.moveLayer(layer, -1);
  }

  @FXML
  @SuppressWarnings("unused")
  private void increaseZIndex() {
    model.moveLayer(layer, 1);
  }

  //TODO Rethink if below methods should all be private and called by single update method...

  private void updateVisibilityImage() {
    if (layer.isVisible()) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }

  private void updateSelectionState() {
    if (model.getActiveLayer() == null) {
      return;
    }

    if (associatedLayerIndex == model.getActiveLayer().getDepthIndex()) {
      setStyle("-fx-background-color: -selected-color;");
    } else {
      setStyle("");
    }
  }
}

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
 * The {@code LayerItemPane} class represents a "layer item pane", which contains information about
 * a specific layer in the model. Instances of this class are designed to be "disposed" upon layer
 * model updates. In other words, instances of the {@code LayerItemPane} class are meant to be
 * short-lived.
 *
 * @see LayerItemContainerPane
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
  @FXML
  @SuppressWarnings("unused")
  private ImageView layerTypeIcon;

  private final IModel model;
  private final IReadOnlyLayer layer; // TODO remove
  private final int associatedLayerIndex;

  /**
   * @param model the associated model instance.
   * @param layer the layer the created layer item pane will represent.
   * @throws IOException          if the associated FXML file cannot be found.
   * @throws NullPointerException if any references are {@code null}.
   */
  LayerItemPane(IModel model, IReadOnlyLayer layer) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item.fxml"));
    this.model = Objects.requireNonNull(model);
    this.layer = Objects.requireNonNull(layer);

    textLabel.setText(layer.getName());
    associatedLayerIndex = layer.getDepthIndex();

    if (model.getActiveLayer().getDepthIndex() == associatedLayerIndex) {
      setStyle("-fx-background-color: -selected-color;");
    }

    if (layer.isVisible()) {
      imageView.setImage(EYE_OPEN_IMAGE);
    } else {
      imageView.setImage(EYE_CLOSED_IMAGE);
    }
  }

//  void setTypeIcon() {
//
//    //TODO Fix themes
//    String path = "images/light/" + layer.getLayerType().name().toLowerCase() + ".png";
//
//    try {
//      layerTypeIcon.setImage(new Image(Resources.find(getClass(), path).toURI().toString()));
//    } catch (Exception e) {
//      System.err.println("Failed to load layerTypeIcon icon! Exception: " + e);
//    }
//  }

  @FXML
  @SuppressWarnings("unused")
  private void toggleVisibility() {
    model.setLayerVisibility(associatedLayerIndex, toggleButton.isSelected());
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
  }

  @FXML
  @SuppressWarnings("unused")
  private void decreaseZIndex() {
    model.changeLayerDepthIndex(layer, -1);
  }

  @FXML
  @SuppressWarnings("unused")
  private void increaseZIndex() {
    model.changeLayerDepthIndex(layer, 1);
  }

  @FXML
  @SuppressWarnings("unused")
  private void removeLayer() {
    model.removeLayer(layer);
  }
}
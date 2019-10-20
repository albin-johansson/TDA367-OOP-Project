package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.LayerUpdateEvent;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.service.LayerImageService;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The {@code LayerItemContainerPane} class contains layer items that represent layers found in the
 * model.
 *
 * @see LayerItemPane
 */
final class LayerItemContainerPane extends AnchorPane implements ILayerUpdateListener {

  private final IModel model;
  @FXML
  @SuppressWarnings("unused")
  private StackPane stackPane; // TODO rename
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane emptyLayerPane;
  @FXML
  @SuppressWarnings("unused")
  private VBox layerItemVBox;

  /**
   * @param model the associated model instance.
   * @throws NullPointerException if the supplied model is {@code null}.
   * @throws IOException          if the associated FXML file cannot be found.
   */
  LayerItemContainerPane(IModel model) throws IOException {
    this.model = Objects.requireNonNull(model);
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_pane.fxml"));
  }

  @Override
  public void layersUpdated(LayerUpdateEvent event) {
    layerItemVBox.getChildren().clear();

    if (event.getAmountOfLayers() == 0) {
      emptyLayerPane.toFront();
      return;
    }

    emptyLayerPane.toBack();
    for (IReadOnlyLayer layer : event.getLayers()) {
      try {
        var layerItemPane = new LayerItemPane(model, layer.getDepthIndex());
        layerItemPane.setImage(LayerImageService.getLayerImage(layer));
        layerItemVBox.getChildren().add(0, layerItemPane);
      } catch (Exception e) {
        System.out.println("Failed to create layer item pane! Exception: " + e);
      }
    }
  }
}

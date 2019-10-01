package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent;
import chalmers.pimp.util.Resources;

/**
 * The {@code LayerItemManagerPane} class manages the layer items in the chalmers.pimp.view. Listens to the chalmers.pimp.model
 * for updates.
 */
final class LayerItemManagerPane extends AnchorPane implements ILayerUpdateListener {

  @FXML
  private StackPane stackPane;

  @FXML
  private AnchorPane emptyLayerPane;

  //Should the itemManager have real layers or just the chalmers.pimp.view aspect?
  @FXML
  private VBox layerItemVBox;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  LayerItemManagerPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_pane.fxml"));
  }

  /**
   * The update method from the Interface {@Code} ILayerUpdateListener that updates this Managers
   * LayerItems
   */
  @Override
  public void layersUpdated(LayerUpdateEvent e) {
    for (Node node : layerItemVBox.getChildren()) {
      LayerItemPane layerItemPane = (LayerItemPane) node;
      //TODO
    }
  }

  /**
   * Adds a LayerItemPane to this managers layerItemVBox
   *
   * @param layerItemPane the layerItemPane to add to the VBox for display
   */
  void addLayerItemPane(LayerItemPane layerItemPane) {
    layerItemVBox.getChildren().add(layerItemPane);
    updateEmptyLayerPane();
  }

  private void updateEmptyLayerPane() {
    if (layerItemVBox.getChildren().isEmpty()) {
      emptyLayerPane.toFront();
    } else {
      emptyLayerPane.toBack();
    }
  }
}

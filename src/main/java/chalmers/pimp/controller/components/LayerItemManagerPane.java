package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The {@code LayerItemManagerPane} class manages the layer items in the chalmers.pimp.view. Listens
 * to the chalmers.pimp.model for updates.
 */
final class LayerItemManagerPane extends AnchorPane implements ILayerUpdateListener {

  @FXML
  private StackPane stackPane;

  @FXML
  private AnchorPane emptyLayerPane;

  @FXML
  private VBox layerItemVBox;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  LayerItemManagerPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_pane.fxml"));
  }

  /**
   * The update method from the Interface {@Code ILayerUpdateListener} that updates this Managers
   * LayerItems
   */
  @Override
  public void layersUpdated(LayerUpdateEvent e) {
    LayerItemPane layerItemPane = null;
    for (Node node : layerItemVBox.getChildren()) {
      LayerItemPane temp = (LayerItemPane) node;
      if (e.getLayer() == temp.getLayer()) {
        layerItemPane = temp;
        break;
      }
    }
    if (layerItemPane == null || e == null) {
      return;
    }

    switch (e.getType()) {
      case REMOVED:
        layerItemVBox.getChildren().removeAll(layerItemPane);
        break;
      case VISIBILITY_TOGGLED:
        break;
      case SELECTED:
        break;
      case EDITED:
        break;
      case LAYER_ORDER_CHANGED:
        updateLayerOrder();
        break;
      case CREATED:
        break;
      default:
        throw new IllegalStateException("" + e.getType());
    }
    //TODO

    for (Node node : layerItemVBox.getChildren()) {
      layerItemPane = (LayerItemPane) node;
      layerItemPane.update();
    }
    updateEmptyLayerPane();
  }

  /**
   * Method to move LayerItemPanes in this managers VBox forward or backwards {@code Steps}. Forward
   * is a positive number and backwards a negative number.
   *
   * @param layerItemPane the {@code LayerItemPane} to be moved.
   * @param steps         the number of steps, positive forwards and negative means backwards.
   */
  private void moveLayerItemPane(LayerItemPane layerItemPane, int steps) {
    int toPos = layerItemVBox.getChildren().indexOf(layerItemPane) + steps;
    layerItemVBox.getChildren().remove(layerItemPane);
    layerItemVBox.getChildren().add(toPos, layerItemPane);
  }

  /**
   * Updates the order of the LayerItemPanes to represent the order in the model.
   */
  private void updateLayerOrder() {
    int listSize = layerItemVBox.getChildren().size();
    LayerItemPane[] layerItemPaneList = new LayerItemPane[listSize];
    LayerItemPane layerItemPane;
    for (Node node : layerItemVBox.getChildren()) {
      layerItemPane = (LayerItemPane) node;
      layerItemPaneList[layerItemPane.getLayer().getDepthIndex()] = layerItemPane;
    }
    layerItemVBox.getChildren().clear();
    for (LayerItemPane l : layerItemPaneList) {
      addLayerItemPane(l);
    }
//    layerItemVBox.getChildren().addAll(layerItemPaneList);
  }

  /**
   * Adds a LayerItemPane to this managers layerItemVBox
   *
   * @param layerItemPane the layerItemPane to add to the VBox for display
   */
  void addLayerItemPane(LayerItemPane layerItemPane) {
    layerItemPane.setTypeIcon();
    layerItemVBox.getChildren().add(0, layerItemPane);
    updateEmptyLayerPane();
  }


  /**
   * Checks if the {@code layerItemVBox} is empty and moves the {@code emptyLayerPane} accordingly.
   * When empty, toFront, and vice-versa.
   */
  private void updateEmptyLayerPane() {
    if (layerItemVBox.getChildren().isEmpty()) {
      emptyLayerPane.toFront();
    } else {
      emptyLayerPane.toBack();
    }
  }
}

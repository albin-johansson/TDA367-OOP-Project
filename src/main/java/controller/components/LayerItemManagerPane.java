package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.canvas.layer.IReadOnlyLayer;
import util.Resources;

/**
 * The {@code LayerItemManagerPane} class manages the layer items in the view. Listens to the model
 * for updates.
 */
public class LayerItemManagerPane extends AnchorPane {

  @FXML
  private StackPane stackPane;

  @FXML
  private AnchorPane emptyLayerPane;

  //Should the itemManager have real layers or just the view aspect?
  @FXML
  private VBox layerItemVBox;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  LayerItemManagerPane() throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item_manager.fxml"));
    setStyle("-fx-background-color: gray;");
  }

  

  /**
   * Creates the LayerItems for the view, based on a {@code IReadOnlyLayer}
   *
   * @param layer the {@code IReadOnlyLayer} that will be created as a view component
   * @return the corresponding {@code LayerItemPane} created from the {@code IReadOnlyLayer}
   */
  private LayerItemPane createLayerItemPane(IReadOnlyLayer layer) {
    try {
      return new LayerItemPane();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param layerItemPane the layerItemPane to add to the VBox for display
   */
  private void addLayerItemPane(LayerItemPane layerItemPane) {
    try {
      layerItemVBox.getChildren().add(layerItemPane);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

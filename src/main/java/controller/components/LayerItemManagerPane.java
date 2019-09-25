package controller.components;

import com.sun.org.apache.bcel.internal.generic.FieldOrMethod;
import controller.ControllerUtils;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.IModel;
import model.canvas.ILayerUpdateListener;
import model.canvas.layer.IReadOnlyLayer;
import util.Resources;

/**
 * The {@code LayerItemManagerPane} class manages the layer items in the view. Listens to the model
 * for updates.
 */
public class LayerItemManagerPane extends AnchorPane implements ILayerUpdateListener {

  @FXML
  private StackPane stackPane;

  @FXML
  private AnchorPane emptyLayerPane;

  //Should the itemManager have real layers or just the view aspect?
  @FXML
  private VBox layerItemVBox;

  IModel iModel;

  /**
   * @throws IOException if the associated FXML file cannot be found.
   */
  LayerItemManagerPane(IModel iModel) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "layer_item_manager.fxml"));
    setStyle("-fx-background-color: gray;");
    this.iModel = iModel;
    layersUpdated();
  }

  public void layersUpdated(){
    layerItemVBox = new VBox();
    for (IReadOnlyLayer layer: iModel.getLayers()){
      addLayerItemPane(createLayerItemPane(layer));
    }
  }

  /**
   * Creates the LayerItems for the view, based on a {@code IReadOnlyLayer}
   *
   * @param layer the {@code IReadOnlyLayer} that will be created as a view component
   * @return the corresponding {@code LayerItemPane} created from the {@code IReadOnlyLayer}
   */
  private LayerItemPane createLayerItemPane(IReadOnlyLayer layer) {
    try {
      return new LayerItemPane(layer);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param layerItemPane the layerItemPane to add to the VBox for display
   */
  private void addLayerItemPane(LayerItemPane layerItemPane) {
      layerItemVBox.getChildren().add(layerItemPane);
  }
}

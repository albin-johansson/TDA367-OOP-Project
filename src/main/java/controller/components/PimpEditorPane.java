package controller.components;

import controller.ControllerUtils;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.IModel;
import model.canvas.layer.IReadOnlyLayer;
import model.canvas.layer.LayerFactory;
import util.AnchorPanes;
import util.Resources;

/**
 * The {@code PimpEditorPane} class represents the main editor pane for the Pimp application.
 */
public final class PimpEditorPane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private Canvas canvas;

  @FXML
  @SuppressWarnings("unused")
  private AnchorPane verticalAnchorPane;

  @FXML
  @SuppressWarnings("unused")
  private HBox horizontalToolBar;

  private IModel model;
  private LayerItemManagerPane layerItemManagerPane;

  /**
   * @param model a reference to the a IModel
   * @throws IOException if the associated FXML file cannot be found.
   */
  public PimpEditorPane(IModel model) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "root.fxml"));
    setStyle("-fx-background-color: gray;");
    this.model = Objects.requireNonNull(model);
    model.addLayer(LayerFactory.createRasterLayer(10, 10));
    model.addLayer(LayerFactory.createRasterLayer(20, 10));
    layerItemManagerPane = new LayerItemManagerPane();
    verticalAnchorPane.getChildren().add(layerItemManagerPane);
    AnchorPanes.setAnchors(layerItemManagerPane, 0, 0, 0, 0);
    populateLayerItemManagerPane();
  }


  /**
   * @return a reference to the graphicscontext used by the canvas
   */
  public GraphicsContext getGraphics() {
    return canvas.getGraphicsContext2D();
  }

  private void populateLayerItemManagerPane() {
    for (IReadOnlyLayer layer : model.getLayers()) {
      layerItemManagerPane.addLayerItemPane(createLayerItemPane(layer));
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
}
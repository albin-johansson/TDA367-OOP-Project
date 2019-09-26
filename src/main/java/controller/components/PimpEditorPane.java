package controller.components;

import controller.ControllerUtils;
import controller.IController;
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
  
  private final IController controller;

  /**
   * @param model a reference to the a IModel
   * @param controller the parent controller instance.
   * @throws IOException if the associated FXML file cannot be found.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public PimpEditorPane(IModel model, IController controller) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "root.fxml"));
    setStyle("-fx-background-color: gray;");
    this.model = Objects.requireNonNull(model);
    this.controller = Objects.requireNonNull(controller);
    model.addLayer(LayerFactory.createRasterLayer(10, 10));
    model.addLayer(LayerFactory.createRasterLayer(20, 10));
    layerItemManagerPane = new LayerItemManagerPane();
    verticalAnchorPane.getChildren().add(layerItemManagerPane);
    AnchorPanes.setAnchors(layerItemManagerPane, 0, 0, 0, 0);
    populateLayerItemManagerPane();
    
    canvas.setOnMousePressed(event -> {
      // TODO...
    });

    canvas.setOnMouseMoved(event -> {
      // TODO...
    });

    canvas.setOnMouseReleased(event -> {
      // TODO...
    });
  }


  /**
   * Returns the graphics context used by the main canvas.
   *
   * @return the graphics context used by the main canvas.
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
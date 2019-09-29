package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.util.AnchorPanes;
import chalmers.pimp.util.Resources;

/**
 * The {@code PimpEditorPane} class represents the main editor pane for the Pimp application.
 */
public final class PimpEditorPane extends AnchorPane {

  private final IModel model;
  private final IController controller;
  private final LayerItemManagerPane layerItemManagerPane;
  private final ToolbarPane toolbarPane;
  private final PalettePane palettePane;
  @FXML
  @SuppressWarnings("unused")
  private Canvas canvas;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane topAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane rightAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane leftAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private HBox horizontalToolBar;

  /**
   * @param model      the associated chalmers.pimp.model instance.
   * @param controller the parent chalmers.pimp.controller instance.
   * @throws IOException          if the associated FXML file cannot be found.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public PimpEditorPane(IModel model, IController controller) throws IOException {
    ControllerUtils.makeController(this, Resources.find(getClass(), "root.fxml"));
    this.model = Objects.requireNonNull(model);
    this.controller = Objects.requireNonNull(controller);

    toolbarPane = new ToolbarPane(controller);
    topAnchorPane.getChildren().add(toolbarPane);
    AnchorPanes.setAnchors(toolbarPane, 0, 0, 0, 0);

    layerItemManagerPane = new LayerItemManagerPane();
    model.addLayerUpdateListener(layerItemManagerPane);
    rightAnchorPane.getChildren().add(layerItemManagerPane);
    AnchorPanes.setAnchors(layerItemManagerPane, 0, 0, 0, 0);

    palettePane = new PalettePane(controller);
    leftAnchorPane.getChildren().add(palettePane);
    AnchorPanes.setAnchors(palettePane, 0, 0, 0, 0);

    populateLayerItemManagerPane();

    canvas.setOnMousePressed(controller::selectedToolPressed);
    canvas.setOnMouseDragged(controller::selectedToolDragged);
    canvas.setOnMouseReleased(controller::selectedToolReleased);
  }

  /**
   * Returns the graphics context used by the main canvas.
   *
   * @return the graphics context used by the main canvas.
   */
  public GraphicsContext getGraphics() {
    return canvas.getGraphicsContext2D();
  }

  /**
   * Populates this PEP's LayerItemManagerPane with LayerItems based on the layers in the chalmers.pimp.model this
   * PEP has
   */
  private void populateLayerItemManagerPane() {
    for (IReadOnlyLayer layer : model.getLayers()) {
      layerItemManagerPane.addLayerItemPane(createLayerItemPane(layer));
    }
  }

  /**
   * Creates the LayerItems for the chalmers.pimp.view, based on a {@code IReadOnlyLayer}
   *
   * @param layer the {@code IReadOnlyLayer} that will be created as a chalmers.pimp.view component
   * @return the corresponding {@code LayerItemPane} created from the {@code IReadOnlyLayer}
   */
  private LayerItemPane createLayerItemPane(IReadOnlyLayer layer) {
    try {
      return new LayerItemPane(model, layer);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
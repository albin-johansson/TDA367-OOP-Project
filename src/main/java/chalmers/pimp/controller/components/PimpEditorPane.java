package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent.EventType;
import chalmers.pimp.util.AnchorPanes;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code PimpEditorPane} class represents the main editor pane for the Pimp application.
 */
public final class PimpEditorPane extends AnchorPane implements ILayerUpdateListener {

  private final IModel model;
  private final IController controller;
  private final LayerItemManagerPane layerItemManagerPane;
  private final ToolbarPane toolbarPane;
  private final PalettePane palettePane;
  private final InfoPane infoPane;
  private final CanvasPane canvasPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane topAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane centerPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane rightAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane leftAnchorPane;
  @FXML
  @SuppressWarnings("unused")
  private AnchorPane bottomAnchorPane;

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
    model.addUndoRedoListener(toolbarPane);

    canvasPane = new CanvasPane(controller);
    centerPane.getChildren().add(canvasPane);
    AnchorPanes.setAnchors(canvasPane, 0, 0, 0, 0);

    layerItemManagerPane = new LayerItemManagerPane();
    model.addLayerUpdateListener(layerItemManagerPane);
    model.addLayerUpdateListener(this);
    rightAnchorPane.getChildren().add(layerItemManagerPane);
    AnchorPanes.setAnchors(layerItemManagerPane, 0, 0, 0, 0);

    palettePane = new PalettePane(controller);
    leftAnchorPane.getChildren().add(palettePane);
    AnchorPanes.setAnchors(palettePane, 0, 0, 0, 0);

    infoPane = new InfoPane(controller, model);
    bottomAnchorPane.getChildren().add(infoPane);
    AnchorPanes.setAnchors(infoPane, 0, 0, 0, 0);

    initiateInfoPane();

    populateLayerItemManagerPane();
  }

  /**
   * Adds listeners to the Canvas which in turn call for the infoPane to update itself.
   */
  private void initiateInfoPane() {
    canvasPane.getGraphics().getCanvas().heightProperty()
        .addListener((observable, oldvalue, newvalue) ->
            infoPane.setCanvasHeightLabel(String.valueOf(newvalue.intValue()))
        );

    canvasPane.getGraphics().getCanvas().widthProperty()
        .addListener((observable, oldvalue, newvalue) ->
            infoPane.setCanvasWidthLabel(String.valueOf(newvalue.intValue()))
        );

    canvasPane.setOnMouseDragged((e)->{
      infoPane.updateCoordinates(e);
      controller.selectedToolDragged(e);
    });

    canvasPane.setOnMouseMoved(infoPane::updateCoordinates);
    canvasPane.setOnMouseExited(infoPane::turnOffCoordinates);

  }

  /**
   * Returns the graphics context used by the main canvas.
   *
   * @return the graphics context used by the main canvas.
   */
  public GraphicsContext getGraphics() {
    return canvasPane.getGraphics();
  }

  /**
   * Populates this PEP's LayerItemManagerPane with LayerItems based on the layers in the
   * chalmers.pimp.model this PEP has
   */
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
      return new LayerItemPane(model, layer);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Will only act if {@code EventType} is CREATED, and will then create a new LayerItemPane with
   * the supplied layer
   *
   * @param e the {@code LayerUpdateEvent} which houses the EventType and associated Layer
   */
  @Override
  public void layersUpdated(LayerUpdateEvent e) {
    if (e.getType() == EventType.CREATED) {
      layerItemManagerPane.addLayerItemPane(createLayerItemPane(e.getLayer()));
    }
  }
}
package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.IModel;
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
public final class PimpEditorPane extends AnchorPane {

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
    Objects.requireNonNull(model);
    Objects.requireNonNull(controller);

    var toolbarPane = new ToolbarPane(controller);
    topAnchorPane.getChildren().add(toolbarPane);
    AnchorPanes.setAnchors(toolbarPane, 0, 0, 0, 0);
    model.addUndoRedoListener(toolbarPane);

    var canvasPane = new CanvasPane(controller);
    centerPane.getChildren().add(canvasPane);
    AnchorPanes.setAnchors(canvasPane, 0, 0, 0, 0);

    this.canvasPane = canvasPane;

    var layerItemManagerPane = new LayerItemContainerPane(model);
    model.addLayerUpdateListener(layerItemManagerPane);
    rightAnchorPane.getChildren().add(layerItemManagerPane);
    AnchorPanes.setAnchors(layerItemManagerPane, 0, 0, 0, 0);

    var palettePane = new PalettePane(controller);
    leftAnchorPane.getChildren().add(palettePane);
    AnchorPanes.setAnchors(palettePane, 0, 0, 0, 0);

    var infoPane = new InfoPane(controller, model);
    bottomAnchorPane.getChildren().add(infoPane);
    AnchorPanes.setAnchors(infoPane, 0, 0, 0, 0);

    initiateInfoPane(canvasPane, infoPane, controller, model);

  }

  /**
   * Adds listeners to the Canvas which in turn call for the infoPane to update itself.
   */
  private void initiateInfoPane(CanvasPane canvasPane, InfoPane infoPane, IController controller, IModel model) {
    canvasPane.getGraphics().getCanvas().heightProperty()
        .addListener((observable, oldvalue, newvalue) ->
            infoPane.setCanvasHeightLabel(String.valueOf(newvalue.intValue()))
        );

    canvasPane.getGraphics().getCanvas().widthProperty()
        .addListener((observable, oldvalue, newvalue) ->
            infoPane.setCanvasWidthLabel(String.valueOf(newvalue.intValue()))
        );

    canvasPane.setOnMouseDragged((e) -> {
      infoPane.updateCoordinates(e);
      controller.selectedToolDragged(e);
    });

    canvasPane.setOnMouseMoved(infoPane::updateCoordinates);
    canvasPane.setOnMouseExited(infoPane::turnOffCoordinates);

    model.addLayerUpdateListener(event->{
      infoPane.setLayerHeightLabel(String.valueOf(model.getActiveLayer().getHeight()));
      infoPane.setLayerWidthLabel(String.valueOf(model.getActiveLayer().getLineWidth1()));
    });
  }

  /**
   * Returns the graphics context used by the main canvas.
   *
   * @return the graphics context used by the main canvas.
   */
  public GraphicsContext getGraphics() {
    return canvasPane.getGraphics();
  }

}
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
  private final UtilityPane utilityPane;

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

    // Toolbar (TOP)
    var toolbarPane = new ToolbarPane(controller);
    topAnchorPane.getChildren().add(toolbarPane);
    AnchorPanes.setZeroAnchors(toolbarPane);
    model.addUndoRedoListener(toolbarPane);

    // Utility (RIGHT)
    utilityPane = new UtilityPane(model);
    rightAnchorPane.getChildren().add(utilityPane);
    AnchorPanes.setZeroAnchors(utilityPane);

    // Canvas (CENTER)
    canvasPane = new CanvasPane(controller);
    centerPane.getChildren().add(canvasPane);
    AnchorPanes.setZeroAnchors(canvasPane);

    // Palette pane (LEFT)
    var palettePane = new PalettePane(controller);
    leftAnchorPane.getChildren().add(palettePane);
    AnchorPanes.setZeroAnchors(palettePane);
    model.addLayerUpdateListener(event -> {
      palettePane.updateEnabledTools(model.getActiveLayer());
    });

    // Info pane (DOWN)
    var infoPane = new InfoPane();
    bottomAnchorPane.getChildren().add(infoPane);
    AnchorPanes.setZeroAnchors(infoPane);

    initiateInfoPane(canvasPane, infoPane, controller, model);
  }

  /**
   * Adds listeners to the Canvas which in turn call for the infoPane to update itself.
   */
  private void initiateInfoPane(CanvasPane canvasPane, InfoPane infoPane, IController controller,
      IModel model) {
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
      if (model.getActiveLayer() != null) {
        infoPane.setLayerRotationLabel(String.valueOf((int) model.getActiveLayer().getRotation()));
      }
    });

    canvasPane.setOnMouseMoved(infoPane::updateCoordinates);
    canvasPane.setOnMouseExited(infoPane::turnOffCoordinates);

    model.addLayerUpdateListener(event -> {
      if (model.getActiveLayer() != null) {
        infoPane.setLayerHeightLabel(String.valueOf(model.getActiveLayer().getHeight()));
        infoPane.setLayerWidthLabel(String.valueOf(model.getActiveLayer().getWidth()));
        infoPane.setLayerRotationLabel(String.valueOf((int) model.getActiveLayer().getRotation()));
      }
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

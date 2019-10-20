package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.controller.IController;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerType;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * The {@code PalettePane} class represents the pane that holds the buttons for the various mouse
 * tools (the "palette"). This class is designed to be used to delegate tool selection calls to a
 * controller.
 *
 * @see IController
 */
final class PalettePane extends AnchorPane {

  @FXML
  @SuppressWarnings("unused")
  private ToggleButton pixelPenButton;
  @FXML
  @SuppressWarnings("unused")
  private ToggleButton eraserButton;
  @FXML
  @SuppressWarnings("unused")
  private ToggleButton doodleButton;

  private final IController controller;

  /**
   * @param controller the associated controller instance.
   * @throws IOException          if the associated FXML file cannot be loaded.
   * @throws NullPointerException if the supplied controller is {@code null}.
   */
  PalettePane(IController controller) throws IOException {
    this.controller = Objects.requireNonNull(controller);
    ControllerUtils.makeController(this, Resources.find(getClass(), "palette_pane.fxml"));
    enableRasterTools(false);
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectPencil() {
    controller.selectRasterPen();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectEraser() {
    controller.selectEraser();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectMoveTool() {
    controller.selectMoveTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectRectangleTool() {
    controller.selectRectangleTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectRotateTool() {
    controller.selectRotateTool();
  }

  @FXML
  @SuppressWarnings("unused")
  private void selectDoodleTool() {
    controller.selectDoodleTool();
  }

  /**
   * Disables raster layer tools if selected layer is not a raster, enables them if it is. Will also
   * fire the doodle button if an invalid button was active.
   *
   * @param activeLayer the currently selected Layer.
   */
  void updateEnabledTools(IReadOnlyLayer activeLayer) {
    boolean activeLayerIsRaster = activeLayer.getLayerType() == LayerType.RASTER;
    boolean enableRasterTools = activeLayer != null && activeLayerIsRaster;
    enableRasterTools(enableRasterTools);
    
    if (!enableRasterTools && rasterToolIsSelected()) {
      doodleButton.fire();
    }
  }

  /**
   * Checks if a raster tool is selected.
   *
   * @return true if any raster tool is currently selected.
   */
  private boolean rasterToolIsSelected() {
    return (pixelPenButton.isSelected() || eraserButton.isSelected());
  }

  /**
   * Sets if raster tools should be enabled {@code True} or not {@code False}
   *
   * @param bool the {@code boolean} to decide if the raster tools should be set or not.
   */
  private void enableRasterTools(boolean bool) {
    pixelPenButton.setDisable(!bool);
    eraserButton.setDisable(!bool);
  }
}
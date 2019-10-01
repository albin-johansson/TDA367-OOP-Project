package chalmers.pimp.model;

import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final Canvas canvas;
  private ITool selectedTool;

  ModelImpl() {
    canvas = new Canvas();
  }

  @Override
  public void addLayer(ILayer layer) {
    canvas.addLayer(layer);
  }

  @Override
  public void removeLayer(ILayer layer) {
    canvas.removeLayer(layer);
  }

  @Override
  public void removeLayer(int layerIndex) {
    canvas.removeLayer(layerIndex);
  }

  @Override
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
  }

  @Override
  public void setPixel(IPixel pixel) {
    canvas.setPixel(pixel);
  }

  @Override
  public void setPixels(int x, int y, PixelData pixelData) {
    canvas.setPixels(x, y, pixelData);
  }

  @Override
  public void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible) {
    canvas.setLayerVisible(layer, isVisible);
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    canvas.setLayerVisible(layerIndex, isVisible);
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }

  @Override
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    canvas.addLayerUpdateListener(listener);
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return canvas.getLayers();
  }

  @Override
  public int getAmountOfLayers() {
    return canvas.getAmountOfLayers();
  }

  @Override
  public void setSelectedTool(ITool selectedTool) {
    this.selectedTool = selectedTool;
  }

  @Override
  public void selectedToolPressed(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.pressed(mouseStatus);
    }
  }

  @Override
  public void selectedToolDragged(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.dragged(mouseStatus);
    }
  }

  @Override
  public void selectedToolReleased(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.released(mouseStatus);
    }
  }

  /**
   * Checks if there is a active tool selected.
   *
   * @return true if there is a tool selected.
   */
  private boolean hasSelectedTool() {
    return selectedTool != null;
  }
}
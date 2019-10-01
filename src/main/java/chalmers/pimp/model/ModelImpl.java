package chalmers.pimp.model;

import java.awt.Color;
import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
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
  public void selectLayer(IReadOnlyLayer layer) {
    canvas.selectLayer(layer);
  }

  @Override
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
  }

  @Override
  public void setPixel(int x, int y, Color color) {
    canvas.setPixel(x, y, color);
  }

  @Override
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    canvas.setLayerName(layer, layerName);
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    canvas.setLayerName(layerIndex, layerName);
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
  public IReadOnlyLayer getActiveLayer() {
    return canvas.getActiveLayer();
  }

  @Override
  public void setSelectedTool(ITool selectedTool) {
    this.selectedTool = selectedTool;
  }

  @Override
  public void selectedToolPressed(MouseStatus mouseStatus) {
    //TODO Implement
  }

  @Override
  public void selectedToolDragged(MouseStatus mouseStatus) {
    //TODO Implement
  }

  @Override
  public void selectedToolReleased(MouseStatus mouseStatus) {
    //TODO Implement
  }
}
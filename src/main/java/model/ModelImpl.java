package model;

import java.awt.Color;
import model.canvas.Canvas;
import model.canvas.ICanvasUpdateListener;
import model.canvas.layer.ILayer;
import model.canvas.layer.IReadOnlyLayer;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final Canvas canvas;

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
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
  }

  @Override
  public void setPixel(int x, int y, Color color) {
    canvas.setPixel(x, y, color);
  }

  @Override
  public void setLayerVisibility(boolean isVisible) {
    canvas.setLayerVisible(isVisible);
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return canvas.getLayers();
  }

  @Override
  public int getAmountOfLayers() {
    return canvas.getAmountOfLayers();
  }
}
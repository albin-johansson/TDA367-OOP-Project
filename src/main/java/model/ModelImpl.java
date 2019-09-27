package model;

import java.awt.Color;
import model.canvas.Canvas;
import model.canvas.ICanvasUpdateListener;
import model.canvas.ILayerUpdateListener;
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
  public void removeLayer(int layerIndex) {
    canvas.removeLayer(layerIndex);
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
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    canvas.setLayerVisible(isVisible);
  }

  @Override
  public void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible) {
    canvas.setLayerVisible(isVisible);
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
}
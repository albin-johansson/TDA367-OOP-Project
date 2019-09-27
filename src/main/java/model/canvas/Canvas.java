package model.canvas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.canvas.layer.ILayer;
import model.canvas.layer.IReadOnlyLayer;

/**
 * The {@code Canvas} class is responsible for handling layers.
 */
public final class Canvas {

  private final CanvasUpdateListenerComposite canvasUpdateListeners;
  private final LayerUpdateListenerComposite layerUpdateListeners;
  private final List<ILayer> layers;
  private ILayer activeLayer;

  public Canvas() {
    canvasUpdateListeners = new CanvasUpdateListenerComposite();
    layerUpdateListeners = new LayerUpdateListenerComposite();
    layers = new ArrayList<>(20);
    activeLayer = null;
  }

  /**
   * Verifies that there is an active layer. If that isn't the case, an exception is thrown.
   *
   * @throws IllegalStateException if there is no active layer.
   */
  private void verifyActiveLayerExistence() {
    if (activeLayer == null) {
      throw new IllegalStateException("No available active layer!");
    }
  }

  /**
   * Verifies that there is supplied layer. If that isn't the case, an exception is thrown.
   *
   * @throws IllegalStateException if there is no active layer.
   */
  private void verifyIndexedLayerExistence(int layerIndex) {
    if (layerIndex < 0 || layerIndex >= layers.size()) {
      throw new IllegalStateException("Indexed layer does not exist!");
    }
  }

  /**
   * Verifies that there is supplied layer. If that isn't the case, an exception is thrown.
   *
   * @throws IllegalStateException if there is no active layer.
   */
  private void verifyLayerExistence(IReadOnlyLayer layer) {
    if (layer == null) {
      throw new IllegalStateException("Layer does not exist!");
    }
  }

  /**
   * Sets the color of the pixel at the specified coordinate, in the active layer. The coordinates
   * are zero-indexed.
   *
   * @param x the x-coordinate of the pixel that will be changed.
   * @param y the y-coordinate of the pixel that will be changed.
   * @param color the new color of the pixel.
   * @throws IllegalStateException if there is no active layer.
   * @throws IndexOutOfBoundsException if the specified coordinate is out-of-bounds.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void setPixel(int x, int y, Color color) {
    verifyActiveLayerExistence();
    activeLayer.setPixel(x, y, color);
    canvasUpdateListeners.canvasUpdated();
  }

  /**
   * Sets the visibility property value for the supplied layer.
   *
   * @param isVisible {@code true} if the supplied layer should be visible; {@code false}
   * otherwise.
   * @throws IllegalStateException if there is no supplied layer.
   */
  public void setLayerVisible(IReadOnlyLayer readOnlyLayer, boolean isVisible) {
    verifyLayerExistence(readOnlyLayer);
    for (ILayer layer : layers) {
      if (readOnlyLayer == layer) {
        layer.setVisible(isVisible);
        break;
      }
    }
    notifyAllListeners();
  }

  /**
   * Sets the visibility property value for the supplied indexed layer.
   *
   * @param isVisible {@code true} if the supplied indexed layer should be visible; {@code false}
   * otherwise.
   * @throws IllegalStateException if there is supplied indexed layer.
   */
  public void setLayerVisible(int layerIndex, boolean isVisible) {
    verifyIndexedLayerExistence(layerIndex);
    layers.get(layerIndex).setVisible(isVisible);
    notifyAllListeners();
  }

  /**
   * Selects the layer associated with the specified index (it's made active). The layer index is
   * zero-indexed.
   *
   * @param layerIndex the index associated with the layer that will be made active.
   * @throws IllegalArgumentException if the supplied index isn't associated with a layer.
   */
  public void selectLayer(int layerIndex) {
    if ((layerIndex < 0) || (layerIndex >= layers.size())) {
      throw new IllegalArgumentException("Invalid layer index: " + layerIndex);
    } else {
      activeLayer = layers.get(layerIndex);
    }
    layerUpdateListeners.layersUpdated();
  }

  /**
   * Adds a layer to the canvas. Duplicates are not allowed.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer is already present in the canvas.
   */
  public void addLayer(ILayer layer) {
    Objects.requireNonNull(layer);

    if (layers.contains(layer)) {
      throw new IllegalArgumentException("Attempted to add the same layer multiple times!");
    }

    layers.add(layer);
    notifyAllListeners();
  }

  /**
   * Removes the supplied layer from the canvas. This method has no effect if the specified layer
   * isn't contained in the canvas.
   *
   * @param layer the layer that will be removed, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void removeLayer(ILayer layer) {
    Objects.requireNonNull(layer);
    layers.remove(layer);
    notifyAllListeners();
  }

  /**
   * Removes the layer associated with the specified layer index. The layer index is zero-indexed.
   *
   * @param layerIndex the layer index associated with the layer that will be removed
   * (zero-indexed).
   * @throws IllegalArgumentException if the specified index isn't associated with a layer.
   */
  public void removeLayer(int layerIndex) {
    if ((layerIndex < 0) || (layerIndex >= layers.size())) {
      throw new IllegalArgumentException("Invalid layer index: " + layerIndex);
    }
    layers.remove(layerIndex);
    notifyAllListeners();
  }

  /**
   * Adds a canvas update listener to the canvas.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvasUpdateListeners.add(listener);
  }

  /**
   * Adds a layer update listener to the canvas.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerUpdateListeners.add(listener);
  }

  /**
   * Notifies the listeners for this Canvas
   */
  private void notifyAllListeners() {
    layerUpdateListeners.layersUpdated();
    canvasUpdateListeners.canvasUpdated();
  }

  /**
   * Returns the current amount of layers in the canvas.
   *
   * @return the current amount of layers in the canvas.
   */
  public int getAmountOfLayers() {
    return layers.size();
  }

  /**
   * Returns all of the layers present in the canvas.
   *
   * @return all of the layers present in the canvas.
   */
  public Iterable<ILayer> getLayers() {
    return layers;
  }
}
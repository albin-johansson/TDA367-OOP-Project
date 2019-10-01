package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent;
import chalmers.pimp.model.canvas.layer.LayerUpdateEvent.EventType;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
   * Sets the pixel at the pixels coordinate, in the active layer. The coordinates are
   * zero-indexed.
   *
   * @param pixel the pixel to be set
   * @throws IllegalStateException     if there is no active layer.
   * @throws IndexOutOfBoundsException if the specified coordinate is out-of-bounds.
   * @throws NullPointerException      if any arguments are {@code null}.
   */
  public void setPixel(IPixel pixel) {
    verifyActiveLayerExistence();
    activeLayer.setPixel(pixel);
    canvasUpdateListeners.canvasUpdated();
  }

  /**
   * Copies the pixels from the PixelData to the layer.
   *
   * @param x         the x coordinate of the PixelData.
   * @param y         the y coordinate of the PixelData.
   * @param pixelData the pixel data to be copied.
   */
  public void setPixels(int x, int y, IReadOnlyPixelData pixelData) {
    verifyActiveLayerExistence();
    for (Iterable<? extends IReadOnlyPixel> row : pixelData.getPixels()) {
      for (IReadOnlyPixel p : row) {
        activeLayer.setPixel(PixelFactory.createPixelWithOffset(p, x, y));
      }
    }
    canvasUpdateListeners.canvasUpdated();
  }

  /**
   * Sets the visibility property value for the supplied layer.
   *
   * @param isVisible {@code true} if the supplied layer should be visible; {@code false}
   *                  otherwise.
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
    notifyAllListeners(new LayerUpdateEvent(EventType.VISIBILITY_TOGGLED, activeLayer));
  }

  /**
   * Sets the visibility property value for the supplied indexed layer.
   *
   * @param isVisible {@code true} if the supplied indexed layer should be visible; {@code false}
   *                  otherwise.
   * @throws IllegalStateException if there is supplied indexed layer.
   */
  public void setLayerVisible(int layerIndex, boolean isVisible) {
    verifyIndexedLayerExistence(layerIndex);
    layers.get(layerIndex).setVisible(isVisible);
    notifyAllListeners(new LayerUpdateEvent(EventType.VISIBILITY_TOGGLED, layers.get(layerIndex)));
  }

  /**
   * Selects the supplied layer.
   *
   * @param layer the supplied layer that will be made active.
   * @throws IllegalArgumentException if the supplied layer isn't associated with a layer.
   */
  public void selectLayer(IReadOnlyLayer layer) {
    verifyLayerExistence(layer);
    ILayer temp = null;
    for (ILayer l : layers) {
      if (layer == l) {
        temp = l;
        activeLayer = l;
        break;
      }
    }
    Objects.requireNonNull(temp); //TODO Bad fix, remind me later
    layerUpdateListeners.layersUpdated(new LayerUpdateEvent(EventType.SELECTED, activeLayer));
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
    layerUpdateListeners.layersUpdated(new LayerUpdateEvent(EventType.SELECTED, activeLayer));
  }

  /**
   * Adds a layer to the canvas. Duplicates are not allowed.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer is already present in the canvas.
   */
  public void addLayer(ILayer layer) {
    Objects.requireNonNull(layer);

    if (layers.contains(layer)) {
      throw new IllegalArgumentException("Attempted to add the same layer multiple times!");
    }

    layers.add(layer);
    notifyAllListeners(new LayerUpdateEvent(EventType.CREATED, layer));
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
    notifyAllListeners(new LayerUpdateEvent(EventType.REMOVED, layer));
  }

  /**
   * Removes the layer associated with the specified layer index. The layer index is zero-indexed.
   *
   * @param layerIndex the layer index associated with the layer that will be removed
   *                   (zero-indexed).
   * @throws IllegalArgumentException if the specified index isn't associated with a layer.
   */
  public void removeLayer(int layerIndex) {
    if ((layerIndex < 0) || (layerIndex >= layers.size())) {
      throw new IllegalArgumentException("Invalid layer index: " + layerIndex);
    }
    notifyAllListeners(new LayerUpdateEvent(EventType.REMOVED, layers.remove(layerIndex)));
  }

  /**
   * Moves the supplied layer {@code steps} in the list, were negative number moves the layer back
   * (and vice versa).
   *
   * @param layer the layer to be moved.
   * @param steps the number of steps
   */
  public void moveLayer(IReadOnlyLayer layer, int steps) {
    if (getAmountOfLayers() < 2) {
      return;
    }
    verifyLayerExistence(layer);
    ILayer temp = null;
    ILayer layerToMove = null;
    int index = 0;
    for (ILayer l : layers) {
      if (layer == l) {
        layerToMove = l;
        if (index + steps < layers.size() && index + steps > -1) {
          temp = layers.get(index + steps);
          layers.set(index + steps, layerToMove);
          layers.set(index, temp);
          if (steps > 0) {
            notifyAllListeners(new LayerUpdateEvent(EventType.POSITION_MOVED_FORWARDS, layer));
          } else {
            notifyAllListeners(new LayerUpdateEvent(EventType.POSITION_MOVED_BACKWARDS, layer));
          }
        }
        break;
      }
      index++;
    }
  }

  /**
   * Sets the name of a given layer.
   *
   * @param layer     the layer to have it's name changed.
   * @param layerName the new name for the layer.
   */
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    Objects.requireNonNull(layer);
    for (ILayer l : layers) {
      if (layer == l) {
        l.setName(layerName);
        break;
      }
    }
    notifyAllListeners(new LayerUpdateEvent(EventType.EDITED, layer));
  }

  /**
   * Sets the name of a given indexed layer.
   *
   * @param layerIndex the list index for the layer.
   * @param layerName  the new name for the layer.
   */
  public void setLayerName(int layerIndex, String layerName) {
    if ((layerIndex < 0) || (layerIndex >= layers.size())) {
      throw new IllegalArgumentException("Invalid layer index: " + layerIndex);
    }
    layers.get(layerIndex).setName(layerName);
    notifyAllListeners(new LayerUpdateEvent(EventType.EDITED, layers.get(layerIndex)));
  }

  /**
   * Adds a canvas update listener to the canvas.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvasUpdateListeners.add(listener);
  }

  /**
   * Adds a layer update listener to the canvas.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerUpdateListeners.add(listener);
  }

  /**
   * Notifies the listeners for this Canvas
   */
  private void notifyAllListeners(LayerUpdateEvent e) {
    layerUpdateListeners.layersUpdated(e);
    canvasUpdateListeners.canvasUpdated();
  }

  public int getAmountOfLayers() {
    return layers.size();
  }

  /**
   * Returns the current active layer.
   *
   * @return the current active layer.
   */
  public IReadOnlyLayer getActiveLayer() {
    return activeLayer;
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

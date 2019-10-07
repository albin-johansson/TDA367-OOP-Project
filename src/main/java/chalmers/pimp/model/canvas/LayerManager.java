package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code LayerManager} class is responsible for handling multiple layer instances.
 *
 * @see ILayer
 */
final class LayerManager {

  private final LayerUpdateListenerComposite layerUpdateListeners;
  private final List<ILayer> layers;
  private ILayer activeLayer;

  LayerManager() {
    layerUpdateListeners = new LayerUpdateListenerComposite();
    layers = new ArrayList<>(10);
    activeLayer = null;
  }

  /**
   * Creates a copy of the supplied layer manager.
   *
   * @param layerManager the layer manager that will be copied.
   * @throws NullPointerException if the supplied layer manager is {@code null}.
   */
  LayerManager(LayerManager layerManager) {
    Objects.requireNonNull(layerManager);

    layers = new ArrayList<>(layerManager.layers.size());
    for (ILayer layer : layerManager.layers) {
      layers.add(layer.copy());
    }

    if (layerManager.activeLayer != null) {
      activeLayer = layers.get(layerManager.activeLayer.getDepthIndex());
    }

    layerUpdateListeners = new LayerUpdateListenerComposite(layerManager.layerUpdateListeners);
  }

  /**
   * Resets all of the depth indices for all of the layers in the manager. This method does
   * <b>not</b> cause any listener notifications.
   */
  private void resetDepthValues() {
    int i = 0;
    for (ILayer layer : layers) {
      layer.setDepthIndex(i);
      i++;
    }
  }

  /**
   * Indicates whether or not the supplied index is in bounds.
   *
   * @param index the index that will be checked.
   * @return {@code true} if the index is in bounds; {@code false} otherwise.
   */
  private boolean inBounds(int index) {
    return (index >= 0) && (index < layers.size());
  }

  /**
   * Searches for the supplied layer and returns it if it's found.
   *
   * @param readOnlyLayer the layer to look for.
   * @return a layer instance if a match is found; {@code null} otherwise.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  private ILayer findMatch(IReadOnlyLayer readOnlyLayer) {
    Objects.requireNonNull(readOnlyLayer);
    for (ILayer layer : layers) {
      if (layer == readOnlyLayer) {
        return layer;
      }
    }
    return null;
  }

  /**
   * Notifies all registered layer update listeners. Beware that this method doesn't trigger a very
   * informative event.
   */
  void notifyListeners() {
    layerUpdateListeners.layersUpdated(new LayerUpdateEvent(layers, layers.size()));
  }

  /**
   * Adds the supplied layer update listener to the layer manager. This method has no effect if the
   * supplied listener has been added previously.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied listener is {@code null}.
   */
  void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerUpdateListeners.add(listener);
  }

  /**
   * Changes the depth index (the z-value) of the supplied layer with the supplied offset. This
   * method has no effect if the supplied offset is zero or if the supplied layer isn't registered
   * in the layer manager.
   *
   * @param layer  the layer that will be moved, may not be {@code null}.
   * @param deltaZ the z-axis offset.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void changeDepthIndex(IReadOnlyLayer layer, int deltaZ) {
    Objects.requireNonNull(layer);
    if (deltaZ == 0) {
      return;
    }

    ILayer match = findMatch(layer);
    if (match != null) {

      boolean movingFirstLayerBack = (match.getDepthIndex() == 0) && (deltaZ < 0);
      boolean movingLastLayerForward = (match.getDepthIndex() == layers.size() - 1) && (deltaZ > 0);
      if (movingFirstLayerBack || movingLastLayerForward) {
        return;
      }

      layers.add(layer.getDepthIndex() + deltaZ, layers.remove(layer.getDepthIndex()));
      resetDepthValues();

      var event = new LayerUpdateEvent(layers, layers.size());
      layerUpdateListeners.layersUpdated(event);
    }
  }

  /**
   * Selects the layer associated with the supplied index. This method has no effect if the supplied
   * index isn't associated with a layer.
   *
   * @param index the index associated with the desired layer.
   */
  void selectLayer(int index) {
    if (inBounds(index)) {
      activeLayer = layers.get(index);

      var event = new LayerUpdateEvent(layers, layers.size());
      event.setSelectionUpdated(true);

      layerUpdateListeners.layersUpdated(event);
    }
  }

  /**
   * Adds a layer to the manager. This method has no effect if the supplied layer is already
   * contained within the manager.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void addLayer(ILayer layer) {
    ILayer match = findMatch(layer);
    if (match == null) {
      layers.add(layer);

      resetDepthValues();

      if (getAmountOfLayers() == 1) {
        selectLayer(layer.getDepthIndex());
      }

      var event = new LayerUpdateEvent(layers, layers.size());
      event.setAddedLayer(layer);

      layerUpdateListeners.layersUpdated(event);
    }
  }

  /**
   * Updates the active layer after the removal of the supplied layer. This method changes the
   * active layer
   *
   * @param removed
   */
  private void updateActiveLayerAfterRemoval(IReadOnlyLayer removed) {
    if (inBounds(removed.getDepthIndex() - 1)) {
      activeLayer = layers.get(removed.getDepthIndex() - 1);
    } else if (inBounds(removed.getDepthIndex() + 1)) {
      activeLayer = layers.get(removed.getDepthIndex() + 1);
    } else {
      activeLayer = null;
    }

    var event = new LayerUpdateEvent(layers, layers.size());
    event.setSelectionUpdated(true);
    event.setRemovedLayer(removed);
    layerUpdateListeners.layersUpdated(event);
  }

  /**
   * Removes the supplied layer from the manager. This method has no effect if the supplied layer
   * isn't found in the manager.
   *
   * @param layer the layer that will be removed, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void removeLayer(IReadOnlyLayer layer) {
    ILayer match = findMatch(layer);
    if (match != null) {
      removeLayer(match.getDepthIndex());
      updateActiveLayerAfterRemoval(match);
    }
  }

  /**
   * Removes the layer associated with the supplied index. This method has no effect if the supplied
   * index is out-of-bounds.
   *
   * @param index the index of the layer that will be removed.
   */
  void removeLayer(int index) {
    if (inBounds(index)) {
      ILayer removed = layers.get(index);
      layers.remove(index);
      updateActiveLayerAfterRemoval(removed);
    }
  }

  /**
   * Sets a pixel value in the active layer.
   *
   * @param pixel the pixel that will be set in the active layer.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  void setActiveLayerPixel(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (activeLayer != null) {
      activeLayer.setPixel(pixel);
    }
  }

  /**
   * Sets a group of pixels in the active layer. This method has no effect if there isn't an active
   * layer.
   *
   * @param x         the start x-coordinate.
   * @param y         the start y-coordinate.
   * @param pixelData the pixel data that contains all of the pixels.
   * @throws NullPointerException if the supplied pixel data is {@code null}.
   */
  void setActiveLayerPixels(int x, int y, IReadOnlyPixelData pixelData) {
    Objects.requireNonNull(pixelData);

    if (activeLayer == null) {
      return;
    }

    for (Iterable<? extends IReadOnlyPixel> row : pixelData.getPixels()) {
      for (IReadOnlyPixel pixel : row) {
        int dx = x - activeLayer.getX();
        int dy = y - activeLayer.getY();
        activeLayer.setPixel(PixelFactory.createPixelWithOffset(pixel, dx, dy));
      }
    }
  }

  /**
   * Moves the currently active layer. This method has no effect if there is no active layer.
   *
   * @param dx the x-axis offset that will be used.
   * @param dy the y-axis offset that will be used.
   */
  void moveActiveLayer(int dx, int dy) {
    if (activeLayer != null) {
      activeLayer.move(dx, dy);
    }
  }

  /**
   * Sets the x-coordinate of the currently active layer. This method has no effect if there is no
   * active layer.
   *
   * @param x the new x-coordinate of the currently active layer.
   */
  void setActiveLayerX(int x) {
    if (activeLayer != null) {
      activeLayer.setX(x);
    }
  }

  /**
   * Sets the y-coordinate of the currently active layer. This method has no effect if there is no
   * active layer.
   *
   * @param y the new y-coordinate of the currently active layer.
   */
  void setActiveLayerY(int y) {
    if (activeLayer != null) {
      activeLayer.setY(y);
    }
  }

  /**
   * Sets the visibility property of the layer associated with the supplied depth index. This method
   * has no effect if the supplied index isn't associated with a layer.
   *
   * @param index     the depth index of the layer that will be renamed.
   * @param isVisible {@code true} if the layer should be visible; {@code false} otherwise.
   */
  void setLayerVisibility(int index, boolean isVisible) {
    if (inBounds(index)) {
      layers.get(index).setVisible(isVisible);
    }
  }

  /**
   * Sets the name of the layer associated with the supplied depth index. This method has no effect
   * if the supplied index isn't associated with a layer.
   *
   * @param index the depth index of the layer that will be renamed.
   * @param name  the new name of the layer, may not be {@code null}.
   * @throws NullPointerException if any references are {@code null}.
   */
  void setLayerName(int index, String name) {
    Objects.requireNonNull(name);
    if (inBounds(index)) {
      layers.get(index).setName(name);
    }
  }

  /**
   * Returns the current amount of layers in the manager.
   *
   * @return the current amount of layers in the manager.
   */
  int getAmountOfLayers() {
    return layers.size();
  }

  /**
   * Returns the currently active layer.
   *
   * @return the currently active layer; {@code null} if there is no active layer.
   */
  IReadOnlyLayer getActiveLayer() {
    return activeLayer;
  }

  /**
   * Returns all of the layers present in the manager.
   *
   * @return all of the layers present in the manager.
   */
  Iterable<? extends IReadOnlyLayer> getLayers() {
    return layers;
  }

  @Override
  public int hashCode() {
    return Objects.hash(activeLayer, layers);
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Active layer: " + activeLayer + ", #layers: " + layers.size();
    return "(" + id + " | " + state + ")";
  }
}
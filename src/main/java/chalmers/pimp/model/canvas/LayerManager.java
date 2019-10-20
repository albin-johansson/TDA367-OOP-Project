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

    if (layerManager.hasActiveLayer()) {
      activeLayer = layers.get(layerManager.activeLayer.getDepthIndex());
    }

    layerUpdateListeners = new LayerUpdateListenerComposite(layerManager.layerUpdateListeners);
  }

  /**
   * Resets all of the depth indices for all of the layers in the manager. This method does
   * <b>not</b> cause any listener notifications.
   */
  private void resetDepthValues() {
    int index = 0;
    for (ILayer layer : layers) {
      layer.setDepthIndex(index);
      index++;
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
   * Offsets the depth index (the z-value) of the layer associated with the supplied layer depth
   * index with the supplied offset. This method has no effect if the supplied offset is zero or if
   * there isn't a layer associated with the supplied layer depth index.
   *
   * @param layerIndex the layer depth index associated with the layer that will be "moved".
   * @param dz         the z-axis offset, may be either negative or positive.
   */
  void changeDepthIndex(int layerIndex, int dz) {
    if (dz == 0) {
      return;
    }

    if (inBounds(layerIndex)) {
      boolean movingFirstLayerBack = (layerIndex == 0) && (dz < 0);
      boolean movingLastLayerForward = (layerIndex == (layers.size() - 1)) && (dz > 0);
      if (movingFirstLayerBack || movingLastLayerForward) {
        return;
      }

      layers.add(layerIndex + dz, layers.remove(layerIndex));
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
   * Adds a layer to the manager and makes it the active layer. This method has no effect if the
   * supplied layer is already contained within the manager. The supplied layer will be made the
   * active layer if it is the only present layer in the manager.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void addLayer(ILayer layer) {
    ILayer match = findMatch(layer);
    if (match == null) {
      layers.add(layer);

      resetDepthValues();

      activeLayer = layer;

      var event = new LayerUpdateEvent(layers, layers.size());
      event.setAddedLayer(layer);
      event.setSelectionUpdated(true);

      layerUpdateListeners.layersUpdated(event);
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

      boolean removedLayerWasActive = hasActiveLayer() && (index == activeLayer.getDepthIndex());

      if (removedLayerWasActive) {
        if (inBounds(index - 1)) {
          activeLayer = layers.get(index - 1);
        } else if (inBounds(index + 1)) {
          activeLayer = layers.get(index + 1);
        } else {
          activeLayer = null;
        }
      }

      ILayer removedLayer = layers.remove(index);

      resetDepthValues();

      var event = new LayerUpdateEvent(layers, layers.size());
      event.setSelectionUpdated(true);
      event.setRemovedLayer(removedLayer);

      layerUpdateListeners.layersUpdated(event);
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
    if (hasActiveLayer()) {
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

    if (!hasActiveLayer()) {
      return;
    }

    for (Iterable<? extends IReadOnlyPixel> row : pixelData.getPixels()) {
      for (IReadOnlyPixel pixel : row) {
        // TODO this is a little bit strange?
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
    if (hasActiveLayer()) {
      activeLayer.move(dx, dy);
    }
  }

  /**
   * Rotates the currently active layer. This method has no effect if there is no active layer.
   *
   * @param alpha the rotation in degrees.
   */
  void rotateActiveLayer(double alpha) {
    if (hasActiveLayer()) {
      activeLayer.setRotation(alpha);
    }
  }

  /**
   * Sets the x-coordinate of the currently active layer. This method has no effect if there is no
   * active layer.
   *
   * @param x the new x-coordinate of the currently active layer.
   */
  void setActiveLayerX(int x) {
    if (hasActiveLayer()) {
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
    if (hasActiveLayer()) {
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
   * if the supplied index isn't associated with a layer. Invoking this method doesn't trigger any
   * events.
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
   * Returns the name of the layer associated with the supplied layer depth index. This method
   * returns an empty string if there is no corresponding layer.
   *
   * @param layerIndex the layer depth index of the desired layer.
   * @return the name of the layer associated with the supplied layer depth index; the empty string
   * is returned if no match is found.
   */
  String getLayerName(int layerIndex) {
    if (inBounds(layerIndex)) {
      return layers.get(layerIndex).getName();
    } else {
      return "";
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
   * Indicates whether or not the layer associated with the supplied index is visible. This method
   * has no effect if the supplied index is out-of-bounds.
   *
   * @param layerIndex the layer index of the layer that will be checked.
   * @return {@code true} if the layer associated with the supplied index is visible; {@code false}
   * otherwise.
   */
  boolean isLayerVisible(int layerIndex) { // TODO test
    if (inBounds(layerIndex)) {
      return layers.get(layerIndex).isVisible();
    } else {
      return false;
    }
  }

  /**
   * Indicates whether or not there is an active layer.
   *
   * @return {@code true} if there is an active layer; {@code false} otherwise.
   */
  boolean hasActiveLayer() {
    return activeLayer != null;
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
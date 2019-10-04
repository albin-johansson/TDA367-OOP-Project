package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.Objects;

/**
 * The {@code Canvas} class is responsible for handling layers.
 */
public final class Canvas {

  private final CanvasUpdateListenerComposite canvasUpdateListeners;
  private final LayerManager layerManager;

  public Canvas() {
    canvasUpdateListeners = new CanvasUpdateListenerComposite();
    layerManager = new LayerManager();
  }

  /**
   * Creates a copy of the supplied canvas.
   *
   * @param canvas the canvas that will be copied.
   * @throws NullPointerException if the supplied canvas is {@code null}.
   */
  public Canvas(Canvas canvas) {
    Objects.requireNonNull(canvas);
    canvasUpdateListeners = new CanvasUpdateListenerComposite(canvas.canvasUpdateListeners);
    layerManager = new LayerManager(canvas.layerManager);
  }

  public void notifyLayerUpdateListeners() {
    layerManager.notifyListeners();
  }

  public void notifyCanvasUpdateListeners() {
    canvasUpdateListeners.canvasUpdated();
  }
//  /**
//   * Verifies that there is supplied layer. If that isn't the case, an exception is thrown.
//   *
//   * @throws IllegalStateException if there is no active layer.
//   * @throws NullPointerException  if the supplied layerIndex is null;
//   */
//  private void verifyIndexedLayerExistence(int layerIndex) {
//    if (layerIndex < 0 || layerIndex >= layers.size()) {
//      throw new IllegalStateException("Indexed layer does not exist!");
//    }
//  }

//  /**
//   * Checks if a layer exists.
//   *
//   * @return true if there is a layer on the specified index, false otherwise.
//   */
//  public boolean layerExists(int i) {
//    boolean tooSmall = i < 0;
//    boolean tooLarge = i > (getAmountOfLayers() - 1);
//    return !tooSmall && !tooLarge;
//  }
//
//  /**
//   * Verifies that there is supplied layer. If that isn't the case, an exception is thrown.
//   *
//   * @throws IllegalStateException if the supplied layer doesn't exist in the model.
//   * @throws NullPointerException  if the supplied layer is null.
//   */
//  private void verifyLayerExistence(IReadOnlyLayer layer) {
//    Objects.requireNonNull(layer);
//    for (IReadOnlyLayer l : layers) {
//      if (l == layer) {
//        return;
//      }
//    }
//    throw new IllegalStateException("Supplied layer does not exist in model!");
//  }
//
//  /**
//   * Sets all the layer's depth index in this Canvas
//   */
//  private void setLayersDepthIndex() {
//    int index = 0;
//    for (ILayer l : layers) {
//      l.setDepthIndex(index++);
//    }
//  }

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
    layerManager.setActiveLayerPixel(pixel);
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
    layerManager.setActiveLayerPixels(x, y, pixelData);
    canvasUpdateListeners.canvasUpdated();
  }

  @Deprecated
  public void setLayerVisibility(IReadOnlyLayer readOnlyLayer, boolean isVisible) {
    layerManager.setLayerVisibility(readOnlyLayer.getDepthIndex(), isVisible);
    canvasUpdateListeners.canvasUpdated();
  }

  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    layerManager.setLayerVisibility(layerIndex, isVisible);
  }

  @Deprecated
  public void selectLayer(IReadOnlyLayer layer) {
    layerManager.selectLayer(layer.getDepthIndex());
  }

  /**
   * Selects the layer associated with the specified index (it's made active). The layer index is
   * zero-indexed.
   *
   * @param layerIndex the index associated with the layer that will be made active.
   * @throws IllegalArgumentException if the supplied index isn't associated with a layer.
   */
  public void selectLayer(int layerIndex) {
    layerManager.selectLayer(layerIndex);
  }

  /**
   * Adds a layer to the canvas. Duplicates are not allowed.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer is already present in the canvas.
   */
  public void addLayer(ILayer layer) {
    layerManager.addLayer(layer);
  }

  /**
   * Removes the supplied layer from the canvas. This method has no effect if the specified layer
   * isn't contained in the canvas.
   *
   * @param layer the layer that will be removed, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void removeLayer(IReadOnlyLayer layer) {
    layerManager.removeLayer(layer);
  }

  /**
   * Removes the layer associated with the specified layer index. The layer index is zero-indexed.
   *
   * @param layerIndex the layer index associated with the layer that will be removed
   *                   (zero-indexed).
   * @throws IllegalArgumentException if the specified index isn't associated with a layer.
   */
  public void removeLayer(int layerIndex) {
    layerManager.removeLayer(layerIndex);
  }

  /**
   * Moves the supplied layer {@code steps} in the list, were negative number moves the layer back
   * (and vice versa). "Back" gives it a smaller index (a smaller z index).
   *
   * @param layer  the layer to be moved.
   * @param deltaZ the number of steps to change its z value.
   * @throws IllegalStateException if the supplied layer doesn't exist in the model.
   * @throws NullPointerException  if the supplied layer is null.
   */
  public void changeDepthIndex(IReadOnlyLayer layer, int deltaZ) {
    layerManager.changeDepthIndex(layer, deltaZ);
  }

  /**
   * Sets the name of a given layer.
   *
   * @param layer     the layer to have it's name changed.
   * @param layerName the new name for the layer.
   */
  @Deprecated
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    layerManager.setLayerName(layer.getDepthIndex(), layerName);
  }

  public void setLayerName(int layerIndex, String layerName) {
    layerManager.setLayerName(layerIndex, layerName);
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

  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerManager.addLayerUpdateListener(listener);
//    layerUpdateListeners.add(listener);
  }

  public void setLayerX(int x) {
    layerManager.setActiveLayerX(x);
  }

  public void setLayerY(int y) {
    layerManager.setActiveLayerY(y);
  }

  /**
   * Notifies all the canvas listeners.
   */
  public void notifyAllCanvasListeners() {
    canvasUpdateListeners.canvasUpdated();
  }

  /**
   * Returns the amount of layers in this canvas's layers list.
   *
   * @return the number of layers.
   */
  public int getAmountOfLayers() {
    return layerManager.getAmountOfLayers();
  }

  /**
   * Returns the current active layer.
   *
   * @return the current active layer.
   */
  public IReadOnlyLayer getActiveLayer() {
    return layerManager.getActiveLayer();
  }

  /**
   * Returns all of the layers present in the canvas.
   *
   * @return all of the layers present in the canvas.
   */
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return layerManager.getLayers();
  }

  /**
   * Inserts a layer at a specific index. Index 0 will insert the layer on index 0 and everything
   * else will get "pushed back".
   *
   * @param index the specific index on which to insert the new layer on.
   * @param layer the layer to be inserted.
   * @throws IndexOutOfBoundsException if the provided index is out of bounds.
   */
  public void addLayer(int index, ILayer layer) {

//    layers.add(index, layer);
  }

  /**
   * Moves the layer by increasing (or decreasing) with x and y amount.
   *
   * @param xAmount the amount moved in dimension x.
   * @param yAmount the amount moved in dimension y.
   */
  public void moveSelectedLayer(int xAmount, int yAmount) {
    layerManager.moveActiveLayer(xAmount, yAmount);
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Layer manager: " + layerManager;
    return "(" + id + " | " + state + ")";
  }
}

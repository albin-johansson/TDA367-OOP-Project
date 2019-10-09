package chalmers.pimp.model.canvas;

import chalmers.pimp.model.ICopiable;
import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code ICanvas} interface specifies objects that represent and handle the various layers in
 * the application. This interface is a subinterface of {@code IMementoTarget} and {@code
 * ICopiable}.
 *
 * @see IMementoTarget
 * @see ICopiable
 * @see CanvasFactory
 * @see ILayer
 */
public interface ICanvas extends IMementoTarget<CanvasMemento>, ICopiable<ICanvas> {

  /**
   * Notifies all registered canvas update listeners.
   */
  void notifyCanvasUpdateListeners();

  /**
   * Notifies all registered layer update listener. Beware that this method doesn't trigger a very
   * informative event.
   */
  void notifyLayerUpdateListeners();

  /**
   * Adds a canvas update listener to the canvas. This method has no effect if the supplied listener
   * has already been added.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied listener is {@code null}.
   */
  void addCanvasUpdateListener(ICanvasUpdateListener listener);

  /**
   * Adds a layer update listener to the canvas. This method has no effect if the supplied listener
   * has already been added.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied listener is {@code null}.
   */
  void addLayerUpdateListener(ILayerUpdateListener listener);

  /**
   * Adds a layer to the canvas. This method has no effect if the supplied layer is already
   * contained within the canvas.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void addLayer(ILayer layer);

  /**
   * Removes the specified layer from the canvas. This method has no effect if the supplied layer
   * isn't contained in the canvas.
   *
   * @param layer the layer that will be removed, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void removeLayer(IReadOnlyLayer layer);

  /**
   * Removes the layer associated with the supplied depth index. This method has no effect if the
   * supplied index is out-of-bounds.
   *
   * @param layerIndex the index of the layer that will be removed.
   */
  void removeLayer(int layerIndex);

  /**
   * Makes the layer associated with the supplied index active. This method has no effect if the
   * supplied index isn't associated with a layer.
   *
   * @param layerIndex the depth index associated with the layer that will be made active.
   */
  void selectLayer(int layerIndex);

  void changeDepthIndex(int layerIndex, int dz);

  /**
   * Moves the currently active layer. This method has no effect if there is no active layer.
   *
   * @param dx the x-axis offset that will be used.
   * @param dy the y-axis offset that will be used.
   */
  void moveActiveLayer(int dx, int dy);

  /**
   * Sets the visibility property of the layer associated with the supplied depth index. This method
   * has no effect if the supplied index isn't associated with a layer.
   *
   * @param layerIndex the depth index of the layer that will be renamed.
   * @param isVisible  {@code true} if the layer should be visible; {@code false} otherwise.
   */
  void setLayerVisibility(int layerIndex, boolean isVisible);

  /**
   * Sets the name of the layer associated with the supplied depth index. This method has no effect
   * if the supplied index isn't associated with a layer.
   *
   * @param layerIndex the depth index of the layer that will be renamed.
   * @param layerName  the new name of the layer, may not be {@code null}.
   * @throws NullPointerException if any references are {@code null}.
   */
  void setLayerName(int layerIndex, String layerName);

  /**
   * Sets a pixel value in the active layer.
   *
   * @param pixel the pixel that will be set in the active layer.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  void setActiveLayerPixel(IPixel pixel);

  /**
   * Sets a group of pixels in the active layer. This method has no effect if there isn't an active
   * layer.
   *
   * @param x         the start x-coordinate.
   * @param y         the start y-coordinate.
   * @param pixelData the pixel data that contains all of the pixels.
   * @throws NullPointerException if the supplied pixel data is {@code null}.
   */
  void setActiveLayerPixels(int x, int y, IReadOnlyPixelData pixelData);

  /**
   * Sets the x-coordinate of the currently active layer. This method has no effect if there is no
   * active layer.
   *
   * @param x the new x-coordinate of the currently active layer.
   */
  void setActiveLayerX(int x);

  /**
   * Sets the y-coordinate of the currently active layer. This method has no effect if there is no
   * active layer.
   *
   * @param y the new y-coordinate of the currently active layer.
   */
  void setActiveLayerY(int y);

  /**
   * Returns the current amount of layers in the canvas.
   *
   * @return the current amount of layers in the canvas.
   */
  int getAmountOfLayers();

  /**
   * Indicates whether or not the layer associated with the supplied index is visible. This method
   * has no effect if the supplied index is out-of-bounds.
   *
   * @param layerIndex the layer index of the layer that will be checked.
   * @return {@code true} if the layer associated with the supplied index is visible; {@code false}
   * otherwise.
   */
  boolean isLayerVisible(int layerIndex);

  String getLayerName(int layerIndex);

  /**
   * Returns the currently active layer.
   *
   * @return the currently active layer; {@code null} if there is none.
   */
  IReadOnlyLayer getActiveLayer();

  /**
   * Returns all of the layers contained in the canvas.
   *
   * @return all of the layers contained in the canvas.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();
}
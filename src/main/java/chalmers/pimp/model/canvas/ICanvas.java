package chalmers.pimp.model.canvas;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorChangeListener;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyRasterData;

/**
 * The {@code ICanvas} interface specifies objects that represent and handle the various layers in
 * the application. This interface is a subinterface of {@code IMementoTarget} and {@code
 * ICopiable}.
 *
 * @see IMementoTarget
 * @see CanvasFactory
 * @see ILayer
 */
public interface ICanvas extends IMementoTarget<CanvasMemento>, Cloneable, IColorChangeListener {

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
   * contained within the canvas. The added layer will automatically be made the active layer. Note!
   * Do <b>not</b> keep and use the supplied reference to the layer, since a copy of the supplied
   * layer will be used.
   *
   * @param layer the layer that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  void addLayer(ILayer layer);

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

  /**
   * Offsets the depth index (the z-value) of the layer associated with the supplied layer depth
   * index with the supplied offset. This method has no effect if the supplied offset is zero or if
   * there isn't a layer associated with the supplied layer depth index.
   *
   * @param layerIndex the layer depth index associated with the layer that will be "moved".
   * @param dz         the z-axis offset, may be either negative or positive.
   */
  void changeDepthIndex(int layerIndex, int dz);

  /**
   * Moves the currently active layer. This method has no effect if there is no active layer.
   *
   * @param dx the x-axis offset that will be used.
   * @param dy the y-axis offset that will be used.
   */
  void moveActiveLayer(int dx, int dy);

  /**
   * Rotates the currently active layer. This method has no effect if there is no active layer.
   *
   * @param alpha the rotation in degrees.
   */
  void setActiveLayerRotation(int alpha);

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
  void setActiveLayerPixels(int x, int y, IReadOnlyRasterData pixelData);

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

  /**
   * Indicates whether or not there is a currently active layer.
   *
   * @return {@code true} if there is an active layer; {@code false} otherwise.
   */
  boolean hasActiveLayer();

  /**
   * Returns the name of the layer associated with the supplied layer depth index. This method
   * returns an empty string if there is no corresponding layer.
   *
   * @param layerIndex the layer depth index of the desired layer.
   * @return the name of the layer associated with the supplied layer depth index; the empty string
   * is returned if no match is found.
   */
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

  /**
   * Sets the color of the active layer if the active layer is colorable.
   *
   * @param color the color to be set. Does nothing if the color is {@code null}.
   */
  void setActiveLayerColor(IColor color);

  /**
   * Creates and returns a copy of the canvas.
   *
   * @return a copy of the canvas.
   */
  ICanvas clone();
}
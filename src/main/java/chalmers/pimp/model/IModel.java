package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;

/**
 * The {@code IModel} interface specifies the facade for the main model component in the
 * application. This interface is a subinterface of {@link IChangeable} and {@link IMementoTarget}.
 */
public interface IModel extends IChangeable, IMementoTarget<ModelMemento> {

  /**
   * Notifies all canvas update listeners.
   */
  void notifyCanvasUpdateListeners();

  /**
   * Adds a canvas update listener to the chalmers.pimp.model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void addCanvasUpdateListener(ICanvasUpdateListener listener);

  /**
   * Adds a undo/redo listener to the model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if the supplied listener is {@code null}.
   */
  void addUndoRedoListener(IUndoRedoListener listener);

  /**
   * Adds a layer update listener to the chalmers.pimp.model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addLayerUpdateListener(ILayerUpdateListener listener);

  void startMovingLayer(int x, int y);

  void updateMovingLayer(int x, int y);

  void stopMovingLayer();

  /**
   * Starts a stroke.
   *
   * @param pixel the pixel affected by the stroke.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void startStroke(IPixel pixel, int diameter);

  /**
   * Updates an ongoing stroke. This method has no effect if there is no ongoing stroke.
   *
   * @param pixel the pixel affected by the stroke.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void updateStroke(IPixel pixel);

  /**
   * Ends an ongoing stroke. This method has no effect if there is no ongoing stroke.
   *
   * @param pixel the pixel affected by the stroke.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void endStroke(IPixel pixel);

  /**
   * Adds the supplied layer to the chalmers.pimp.model.
   *
   * @param layer the layer that will be added.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer has been added to the
   *                                  chalmers.pimp.model previously.
   */
  void addLayer(ILayer layer);

  /**
   * Removes the specified layer from the chalmers.pimp.model.
   *
   * @param layer the layer that will be removed.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void removeLayer(ILayer layer);

  /**
   * Removes the layer associated with the specified index. The layer index is zero-indexed.
   *
   * @param layerIndex the layer associated with the specified index (zero-indexed).
   * @throws IllegalArgumentException if the specified index isn't associated with a layer.
   */
  void removeLayer(int layerIndex);

  /**
   * Selects the supplied layer.
   *
   * @param layer the supplied layer that will be made active.
   * @throws IllegalArgumentException if the supplied layer isn't associated with a layer.
   */
  void selectLayer(IReadOnlyLayer layer);

  /**
   * Selects the layer associated with the specified index.
   *
   * @param layerIndex the index associated with the layer that will be made active.
   * @throws IllegalArgumentException if the supplied index isn't associated with a layer.
   */
  void selectLayer(int layerIndex);

  /**
   * Moves the supplied layer {@code steps} in the list, were negative number moves the layer back
   * (and vice versa).
   *
   * @param layer the layer to be moved.
   * @param steps the number of steps
   */
  void moveLayer(IReadOnlyLayer layer, int steps);

  /**
   * Sets the the pixel at the pixels coordinate, in the active layer. The coordinates are
   * zero-indexed.
   *
   * @param pixel the pixel to be set
   * @throws IllegalStateException     if there is no active layer.
   * @throws IndexOutOfBoundsException if the specified coordinate is out-of-bounds.
   * @throws NullPointerException      if any arguments are {@code null}.
   */
  void setActiveLayerPixel(IPixel pixel);

  /**
   * Sets the color of multiple pixels in the model using the PixelData Class.
   *
   * @param x         start x value of the PixelData.
   * @param y         start y value of the PixelData.
   * @param pixelData the PixelData representing the pixels to be set.
   */
  void setActiveLayerPixels(int x, int y, PixelData pixelData);

  /**
   * Sets the name of a layer.
   *
   * @param layer     the layer to have it's name changed.
   * @param layerName the new name for the layer.
   */
  void setLayerName(IReadOnlyLayer layer, String layerName);

  /**
   * Sets the name of an indexed layer.
   *
   * @param layerIndex the index for the layer.
   * @param layerName  the new name for the layer.
   */
  void setLayerName(int layerIndex, String layerName);

  /**
   * Sets the visibility property value for the supplied layer.
   *
   * @param layer     the {@code layer} which will have it's visibility changed.
   * @param isVisible {@code true} if the active layer should be visible; {@code false} otherwise.
   * @throws IllegalStateException if there is no active layer.
   */
  void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible);

  /**
   * Sets the visibility property value for the supplied layer.
   *
   * @param layerIndex the {@code int} index of the layer which will have it's visibility changed.
   * @param isVisible  {@code true} if the active layer should be visible; {@code false} otherwise.
   * @throws IllegalStateException if there is no active layer.
   */
  void setLayerVisibility(int layerIndex, boolean isVisible);

  /**
   * Moves the active layer by x- and y-amount.
   *
   * @param dx the amount moved in dimension x.
   * @param dy the amount moved in dimension y.
   */
  void moveActiveLayer(int dx, int dy);

  /**
   * Can be Null if user chooses to deselect a tool.
   *
   * @param tool the tool to be selected.
   */
  void setSelectedTool(ITool tool);

  /**
   * Tells the chalmers.pimp.model that the selected tool has been pressed.
   *
   * @param mouseStatus the status of the mouse.
   */
  void selectedToolPressed(MouseStatus mouseStatus);

  /**
   * Tells the chalmers.pimp.model that the selected tool has been dragged.
   *
   * @param mouseStatus the status of the mouse.
   */
  void selectedToolDragged(MouseStatus mouseStatus);

  /**
   * Tells the chalmers.pimp.model that the selected tool has been Released
   *
   * @param mouseStatus the status of the mouse.
   */
  void selectedToolReleased(MouseStatus mouseStatus);

  /**
   * Sets the models renderer.
   *
   * @param renderer the specific renderer implementation.
   * @throws NullPointerException if the supplied renderer is {@code null}.
   */
  void setRenderer(IRenderer renderer);

  /**
   * Returns the models renderer.
   *
   * @return the models renderer.
   */
  IRenderer getRenderer();

  /**
   * Returns the currently active layer.
   *
   * @return the currentyl active layer; {@code null} otherwise.
   */
  IReadOnlyLayer getActiveLayer();

  /**
   * Returns all of the layers in the model.
   *
   * @return all of the layers in the model.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();
}
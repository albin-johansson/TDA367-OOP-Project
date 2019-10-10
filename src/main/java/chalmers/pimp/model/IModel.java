package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;

/**
 * The {@code IModel} interface specifies the facade for the main chalmers.pimp.model component in
 * the Pimp application.
 */
public interface IModel extends IChangeable {

  // TODO create ILayerModel interface and use it as an aggregate

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
   * @param IReadOnlyLayer the layer that will be removed.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void removeLayer(IReadOnlyLayer layer);

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
   * Restores the state of the model to the one that is represented by the supplied memento object.
   *
   * @param memento the memento object used to restore the model state.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void restore(ModelMemento memento);

  /**
   * Sets the the pixel at the pixels coordinate, in the active layer. The coordinates are
   * zero-indexed.
   *
   * @param pixel the pixel to be set
   * @throws IllegalStateException     if there is no active layer.
   * @throws IndexOutOfBoundsException if the specified coordinate is out-of-bounds.
   * @throws NullPointerException      if any arguments are {@code null}.
   */
  void setPixel(IPixel pixel);

  /**
   * Sets the color of multiple pixels in the model using the PixelData Class.
   *
   * @param x         start x value of the PixelData.
   * @param y         start y value of the PixelData.
   * @param pixelData the PixelData representing the pixels to be set.
   */
  void setPixels(int x, int y, PixelData pixelData);

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

  /**
   * Returns all of the layers in the chalmers.pimp.model.
   *
   * @return all of the layers in the chalmers.pimp.model.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();

  /**
   * Returns the current amount of layers in the chalmers.pimp.model.
   *
   * @return the current amount of layers in the chalmers.pimp.model.
   */
  int getAmountOfLayers();

  /**
   * Returns the current amount of layers in the canvas.
   *
   * @return the current amount of layers in the canvas.
   */
  IReadOnlyLayer getActiveLayer();

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
   * Replaces a layer of a specific index with a new layer. Does nothing if there is no layer on the
   * specified index.
   *
   * @param index the index of the layer to change.
   * @param layer the new layer.
   */
  void replaceLayer(int index, ILayer layer);

  /**
   * Creates a snap shot of the current state of the model.
   *
   * @return a snap shot of the current state of the model.
   */
  ModelMemento createSnapShot();

  /**
   * Moves the active layer by x- and y-amount.
   *
   * @param xAmount the amount moved in dimension x.
   * @param yAmount the amount moved in dimension y.
   */
  void moveSelectedLayer(int xAmount, int yAmount);

  /**
   * Returns the models renderer.
   *
   * @return the models renderer.
   */
  IRenderer getRenderer();

  /**
   * Sets the models renderer.
   *
   * @param renderer the specific renderer implementation.
   * @throws NullPointerException if the supplied renderer is {@code null}.
   */
  void setRenderer(IRenderer renderer);

  /**
   * Notifies all canvas update listeners.
   */
  void notifyAllCanvasUpdateListeners();

  /**
   * Sets the color that should be used for drawing items on the canvas.
   *
   * @param color the new color.
   */
  void setSelectedColor(IColor color);

  /**
   * Returns the selected color that is used for when creating new shapes, drawings etc.
   *
   * @return the selected color that is used for when creating new shapes, drawings etc.
   */
  IColor getSelectedColor();
}
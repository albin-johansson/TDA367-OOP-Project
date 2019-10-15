package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
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
   * Adds a layer update listener to the model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addLayerUpdateListener(ILayerUpdateListener listener);

  /**
   * Starts moving the currently active layer. This method has no effect if there is no active
   * layer.
   *
   * @param x the new x-coordinate of the layer.
   * @param y the new y-coordinate of the layer.
   */
  void startMovingActiveLayer(int x, int y);

  /**
   * Updates the movement of the currently active layer. This method has no effect if the {@link
   * IModel#startMovingActiveLayer(int, int)} method hasn't been invoked before invoking this
   * method.
   *
   * @param x the new x-coordinate of the layer.
   * @param y the new y-coordinate of the layer.
   */
  void updateMovingActiveLayer(int x, int y);

  /**
   * Stops the movement of the currently active layer. This method has no effect if the {@link
   * IModel#startMovingActiveLayer(int, int)} method hasn't been invoked before invoking this
   * method.
   */
  void stopMovingActiveLayer();

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
   * Adds the supplied layer to the model. The added layer will automatically be made the active
   * layer. Note! Do <b>not</b> keep and use the supplied reference to the layer, since a copy of
   * the supplied layer will be used.
   *
   * @param layer the layer that will be added.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer has been added to the model previously.
   */
  void addLayer(ILayer layer);

  /**
   * Removes the layer associated with the specified index. The layer index is zero-indexed.
   *
   * @param layerIndex the layer associated with the specified index (zero-indexed).
   * @throws IllegalArgumentException if the specified index isn't associated with a layer.
   */
  void removeLayer(int layerIndex);

  /**
   * Selects the layer associated with the specified index.
   *
   * @param layerIndex the index associated with the layer that will be made active.
   * @throws IllegalArgumentException if the supplied index isn't associated with a layer.
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
  void changeLayerDepthIndex(int layerIndex, int dz);

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
   * Sets the name of an indexed layer.
   *
   * @param layerIndex the index for the layer.
   * @param layerName  the new name for the layer.
   */
  void setLayerName(int layerIndex, String layerName);

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
   * Tells the model that the selected tool has been pressed.
   *
   * @param mouseStatus the status of the mouse.
   */
  void selectedToolPressed(MouseStatus mouseStatus);

  /**
   * Tells the model that the selected tool has been dragged.
   *
   * @param mouseStatus the status of the mouse.
   */
  void selectedToolDragged(MouseStatus mouseStatus);

  /**
   * Tells the model that the selected tool has been Released
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
   * Indicates whether or not the layer associated with the supplied index is visible. This method
   * has no effect if the supplied index is out-of-bounds.
   *
   * @param layerIndex the layer index of the layer that will be checked.
   * @return {@code true} if the layer associated with the supplied index is visible; {@code false}
   * otherwise.
   */
  boolean isLayerVisible(int layerIndex);

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
   * Returns the models renderer.
   *
   * @return the models renderer.
   */
  IRenderer getRenderer();

  /**
   * Returns the currently active layer. Note! Use this method with caution since the returned
   * reference may become invalidated as a result of executing other actions.
   *
   * @return the currently active layer; {@code null} otherwise.
   */
  IReadOnlyLayer getActiveLayer(); // TODO remove, high risk of invalid use

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

  /**
   * Returns all of the layers in the model.
   *
   * @return all of the layers in the model.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();
}
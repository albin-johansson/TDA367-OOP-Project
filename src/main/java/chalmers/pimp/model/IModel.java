package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorChangeListener;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.viewport.IReadOnlyViewport;

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
   * Adds a canvas update listener to the model.
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
   * Adds a canvas size listener to the model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addModelSizeListener(IModelSizeListener listener);

  /**
   * Draws all of the drawables contained in the model.
   *
   * @param renderer the renderer that will be used.
   * @throws NullPointerException if the supplied renderer is {@code null}.
   */
  void draw(IRenderer renderer);

  /**
   * Moves the viewport.
   *
   * @param dx the x-axis offset, may be negative.
   * @param dy the y-axis offset, may be negative.
   */
  void moveViewport(int dx, int dy);

  /**
   * Centers the viewport over the model canvas.
   */
  void centerViewport();

  /**
   * Sets the width of the viewport.
   *
   * @param width the new height of the viewport.
   * @throws IllegalArgumentException if the supplied width isn't greater than one.
   */
  void setViewportWidth(int width);

  /**
   * Sets the height of the viewport.
   *
   * @param height the new height of the viewport.
   * @throws IllegalArgumentException if the supplied height isn't greater than one.
   */
  void setViewportHeight(int height);

  /**
   * Returns the width of the model canvas. Note! This is not the width of the viewport.
   *
   * @return the width of the model canvas.
   */
  int getWidth();

  /**
   * Returns the height of the model canvas. Note! This is not the height of the viewport.
   *
   * @return the height of the model canvas.
   */
  int getHeight();

  /**
   * Returns a copy of the current viewport.
   *
   * @return a copy of the current viewport.
   */
  IReadOnlyViewport getViewport();

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
   * @param pixel    the pixel affected by the stroke.
   * @param diameter the diameter of the stroke.
   * @param color    the color that will be used.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void startStroke(IPixel pixel, int diameter, IColor color);

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
   * Removes the layer associated with the specified index. The layer index is zero-indexed. This
   * method has no effect if there is no active layer.
   *
   * @param layerIndex the layer associated with the specified index (zero-indexed).
   */
  void removeLayer(int layerIndex);

  /**
   * Selects the layer associated with the specified index. This method has no effect if there is no
   * active layer.
   *
   * @param layerIndex the index associated with the layer that will be made active.
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
   * zero-indexed. This method has no effect if there is no active layer.
   *
   * @param pixel the pixel to be set.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  void setActiveLayerPixel(IPixel pixel);

  /**
   * Sets the name of an indexed layer. This method has no effect if the supplied index isn't
   * associated with a layer.
   *
   * @param layerIndex the index for the layer.
   * @param layerName  the new name for the layer.
   */
  void setLayerName(int layerIndex, String layerName);

  /**
   * Sets the visibility property value for the supplied layer.  This method has no effect if the
   * supplied index isn't associated with a layer.
   *
   * @param layerIndex the {@code int} index of the layer which will have it's visibility changed.
   * @param isVisible  {@code true} if the layer should be visible; {@code false} otherwise.
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
   * Starts rotating the currently active layer. This method has no effect if there is no active
   * layer.
   *
   * @param x the x-coordinate of the initial mouse point to calculate rotation in relation to said
   *          mouse point.
   * @param y the y-coordinate of the initial mouse point to calculate rotation in relation to said
   *          mouse point.
   */
  void startRotatingActiveLayer(int x, int y);

  /**
   * Updates the rotation of the currently active layer. This method has no effect if the {@link
   * IModel#startMovingActiveLayer(int, int)} method hasn't been invoked before invoking this
   * method.
   *
   * @param x the new x-coordinate of the layer.
   * @param y the new y-coordinate of the layer.
   */
  void updateRotatingActiveLayer(int x, int y);

  /**
   * Stops the rotation of the currently active layer. This method has no effect if the {@link
   * IModel#startMovingActiveLayer(int, int)} method hasn't been invoked before invoking this
   * method.
   */
  void stopRotatingActiveLayer();

  /**
   * Rotates the active layer by alpha degrees.
   *
   * @param alpha the rotation in degrees.
   */
  void rotateActiveLayer(int alpha);

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
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void selectedToolPressed(MouseStatus mouseStatus);

  /**
   * Tells the model that the selected tool has been dragged.
   *
   * @param mouseStatus the status of the mouse.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void selectedToolDragged(MouseStatus mouseStatus);

  /**
   * Tells the model that the selected tool has been Released
   *
   * @param mouseStatus the status of the mouse.
   * @throws NullPointerException if the supplied mouse status is {@code null}.
   */
  void selectedToolReleased(MouseStatus mouseStatus);

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
   * Returns the models renderer.
   *
   * @return the models renderer.
   */
  IRenderer getRenderer();

  /**
   * Sets the models renderer.
   *
   * @param renderer the specific renderer implementation.
   */
  void setRenderer(IRenderer renderer);

  /**
   * Returns the currently active layer. Note! Use this method with caution since the returned
   * reference may become invalidated as a result of executing other actions.
   *
   * @return the currently active layer; {@code null} otherwise.
   */
  IReadOnlyLayer getActiveLayer();

  /**
   * Returns the selected color that is used for when creating new shapes, drawings etc.
   *
   * @return the selected color that is used for when creating new shapes, drawings etc.
   */
  IColor getSelectedColor();

  /**
   * Sets the color that should be used for drawing items on the canvas.
   *
   * @param color the new color.
   * @throws NullPointerException if the provided color is {@code null}.
   */
  void setSelectedColor(IColor color);

  /**
   * Returns all of the layers in the model.
   *
   * @return all of the layers in the model.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();

  /**
   * Adds a listener.
   *
   * @param listener a listener that listens to color changes.
   * @throws NullPointerException if the provided observer is null.
   */
  void addColorChangeListener(IColorChangeListener listener);

  void notifyColorUpdateListeners();
}
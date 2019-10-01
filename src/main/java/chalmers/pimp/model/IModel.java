package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.tools.ITool;
import java.awt.Color;

/**
 * The {@code IModel} interface specifies the facade for the main chalmers.pimp.model component in
 * the Pimp application.
 */
public interface IModel {

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
   * Sets the color of the pixel at the specified coordinate, in the active layer. The coordinates
   * are zero-indexed.
   *
   * @param x     the x-coordinate of the pixel that will be changed.
   * @param y     the y-coordinate of the pixel that will be changed.
   * @param color the new color of the pixel.
   * @throws IllegalStateException     if there is no active layer.
   * @throws IndexOutOfBoundsException if the specified coordinate is out-of-bounds.
   * @throws NullPointerException      if any arguments are {@code null}.
   */
  void setPixel(int x, int y, Color color);

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
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addCanvasUpdateListener(ICanvasUpdateListener listener);

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
}
package model;

import java.awt.Color;
import model.canvas.ICanvasUpdateListener;
import model.canvas.layer.ILayer;
import model.canvas.layer.IReadOnlyLayer;
import model.tools.ITool;

/**
 * The {@code IModel} interface specifies the facade for the main model component in the Pimp
 * application.
 */
public interface IModel {

  /**
   * Adds the supplied layer to the model.
   *
   * @param layer the layer that will be added.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied layer has been added to the model previously.
   */
  void addLayer(ILayer layer);

  /**
   * Removes the specified layer from the model.
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
   * Selects the layer associated with the specified index.
   *
   * @param layerIndex the index associated with the layer that will be made active.
   * @throws IllegalArgumentException if the supplied index isn't associated with a layer.
   */
  void selectLayer(int layerIndex);

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
   * Sets the visibility property value for the active layer.
   *
   * @param isVisible {@code true} if the active layer should be visible; {@code false} otherwise.
   * @throws IllegalStateException if there is no active layer.
   */
  void setLayerVisibility(boolean isVisible);

  /**
   * Adds a canvas update listener to the model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException     if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addCanvasUpdateListener(ICanvasUpdateListener listener);

  /**
   * Returns all of the layers in the model.
   *
   * @return all of the layers in the model.
   */
  Iterable<? extends IReadOnlyLayer> getLayers();

  /**
   * Returns the current amount of layers in the model.
   *
   * @return the current amount of layers in the model.
   */
  int getAmountOfLayers();

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
}
package model.canvas.layer;

import java.awt.Color;

/**
 * The {@code ILayer} interface specifies objects that represent some sort of layer. This interface
 * is a subinterface to {@code IReadOnlyLayer}.
 *
 * @see IReadOnlyLayer
 */
public interface ILayer extends IReadOnlyLayer {

  /**
   * Sets the pixel color at the specified coordinates. The coordinates are zero-indexed.
   *
   * @param x the x-coordinate of the pixel that will be changed.
   * @param y the y-coordinate of the pixel that will be changed.
   * @param color the new color of the specified pixel.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void setPixel(int x, int y, Color color);

  /**
   * Sets the value of the visible property of this layer.
   *
   * @param isVisible {@code true} if the layer should be visible; {@code false} otherwise.
   */
  void setVisible(boolean isVisible);

  /**
   * Sets the x-coordinate of the layer.
   *
   * @param x the new x-coordinate of the layer.
   */
  void setX(int x);

  /**
   * Sets the y-coordinate of the layer.
   *
   * @param y the new y-coordinate of the layer.
   */
  void setY(int y);
}
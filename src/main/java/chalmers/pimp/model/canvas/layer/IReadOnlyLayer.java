package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IDrawable;
import chalmers.pimp.model.Point;

/**
 * The {@code IReadOnlyLayer} interface specifies the basic read-only methods for layers.
 */
public interface IReadOnlyLayer extends IDrawable, Cloneable {

  /**
   * Indicates whether or not the layer is visible.
   *
   * @return {@code true} if the layer is visible; {@code false} otherwise.
   */
  boolean isVisible();

  /**
   * Returns the x-coordinate of the layer.
   *
   * @return the x-coordinate of the layer.
   */
  int getX();

  /**
   * Returns the y-coordinate of the layer.
   *
   * @return the y-coordinate of the layer.
   */
  int getY();

  /**
   * Returns the layer's name
   *
   * @return the layer's name as a {@code String}
   */
  String getName();

  /**
   * Returns the layer's depth index, 0 as furthest back.
   *
   * @return the depth index.
   */
  int getDepthIndex();

  /**
   * Returns a point that represents the center of the layer.
   *
   * @return a point that represents the center of the layer.
   */
  Point getCenterPoint();

  /**
   * Returns the layer type of this layer.
   *
   * @return the layer type of this layer.
   */
  LayerType getLayerType();

  /**
   * Returns the rotation of this layer.
   *
   * @return the rotation of this layer.
   */
  int getRotation();

  /**
   * Returns the alpha value of this layer.
   *
   * @return the alpha value of this layer.
   */
  double getAlpha();

  /**
   * Returns the width of the layer.
   *
   * @return the width of the layer.
   */
  int getWidth();

  /**
   * Returns the height of the layer.
   *
   * @return the height of the layer.
   */
  int getHeight();

  /**
   * Returns a copy of the layer.
   *
   * @return a copy of the layer.
   * @throws IllegalStateException if the layer cannot be copied.
   */
  IReadOnlyLayer clone();
}
package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.ICopiable;
import chalmers.pimp.model.IDrawable;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code IReadOnlyLayer} interface specifies the basic read-only methods for layers.
 */
public interface IReadOnlyLayer extends IDrawable, ICopiable<ILayer> {

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
   * Returns a pixel representation of this layer, which is read-only.
   *
   * @return a pixel representation of this layer.
   */
  IReadOnlyPixelData getPixelData();

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
  double getRotation();

  /**
   * Returns the alpha value of this layer.
   *
   * @return the alpha value of this layer.
   */
  double getAlpha();
}
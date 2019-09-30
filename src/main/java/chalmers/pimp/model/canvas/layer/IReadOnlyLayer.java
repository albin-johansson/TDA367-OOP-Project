package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code IReadOnlyLayer} interface specifies the basic read-only methods for layers.
 */
public interface IReadOnlyLayer {

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
   * Returns the type of the layer.
   *
   * @return the type of the layer.
   */
  LayerType getLayerType();
}
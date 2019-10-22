package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.pixeldata.IPixel;

/**
 * The {@code IRasterLayer} is a subinterface of {@code ILayer} and extends its functionality
 * suitable for a raster.
 *
 * @see ILayer
 */
public interface IRasterLayer extends ILayer {

  /**
   * Sets the color of a pixel specified by a specific coordinate. The coordinates are based on the
   * provided pixels coordinates and the color on the provided pixels color.
   *
   * @param pixel the pixel to be set
   * @throws NullPointerException if any arguments are {@code null}.
   */
  void setPixel(IPixel pixel);
}

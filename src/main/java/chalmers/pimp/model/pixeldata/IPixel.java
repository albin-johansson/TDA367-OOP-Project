package chalmers.pimp.model.pixeldata;

/**
 * The {@code IPixel} interface specifies objects that represent a pixel.
 */
public interface IPixel extends IReadOnlyPixel {

  /**
   * Sets the red component value of this pixel. If the supplied value is out-of-bounds, then the
   * closest value (either 0 or 1) will be used.
   *
   * @param red the red component value, in the range [0, 1].
   */
  void setRed(double red);

  /**
   * Sets the green component value of this pixel. If the supplied value is out-of-bounds, then the
   * closest value (either 0 or 1) will be used.
   *
   * @param green the green component value, in the range [0, 1].
   */
  void setGreen(double green);

  /**
   * Sets the blue component value of this pixel. If the supplied value is out-of-bounds, then the
   * closest value (either 0 or 1) will be used.
   *
   * @param blue the blue component value, in the range [0, 1].
   */
  void setBlue(double blue);

  /**
   * Sets the alpha component value of this pixel. If the supplied value is out-of-bounds, then the
   * closest value (either 0 or 1) will be used.
   *
   * @param alpha the alpha component value, in the range [0, 1].
   */
  void setAlpha(double alpha);
}
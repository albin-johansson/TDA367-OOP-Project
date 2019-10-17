package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.IColor;
import java.util.Objects;

/**
 * The {@code PixelFactory} class is a factory class for creating instances of the {@link IPixel}
 * interface.
 */
public final class PixelFactory {

  private PixelFactory() {
  }

  /**
   * Creates and returns a copy of the supplied pixel.
   *
   * @param pixel the pixel that will be copied.
   * @return a copy of the supplied pixel.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  public static IPixel createPixel(IReadOnlyPixel pixel) {
    return new PixelImpl(pixel);
  }

  /**
   * Creates and returns a pixel with the supplied coordinates.
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return A pixel instance.
   */
  public static IPixel createPixel(int x, int y) {
    return new PixelImpl(x, y);
  }

  /**
   * Creates and returns a pixel instance.
   *
   * @param x     the x-coordinate of the pixel.
   * @param y     the y-coordinate of the pixel.
   * @param color the color of the pixel.
   * @return a pixel instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IPixel createPixel(int x, int y, IColor color) {
    Objects.requireNonNull(color);

    IPixel pixel = new PixelImpl(x, y);
    pixel.setRed(color.getRedPercentage());
    pixel.setGreen(color.getGreenPercentage());
    pixel.setBlue(color.getBluePercentage());
    pixel.setAlpha(color.getAlphaPercentage());
    return pixel;
  }

  /**
   * Creates and returns a pixel instance with the specified coordinates and color values. The
   * closest legal value is used if any of the color values are outside the [0, 1] range.
   *
   * @param x     the x-coordinate of the pixel.
   * @param y     the y-coordinate of the pixel.
   * @param red   the amount of red in the range [0,1].
   * @param green the amount of green in the range [0,1].
   * @param blue  the amount of blue in the range [0,1].
   * @param alpha the alpha channel in the range [0, 1].
   * @return the created IPixel
   */
  public static IPixel createPixel(int x, int y, double red, double green, double blue,
      double alpha) {
    IPixel pixel = new PixelImpl(x, y);
    pixel.setRed(red);
    pixel.setGreen(green);
    pixel.setBlue(blue);
    pixel.setAlpha(alpha);
    return pixel;
  }

  /**
   * Creates a copy of the supplied pixel, that has coordinates that are offset by the specified
   * amount.
   *
   * @param pixel the pixel that will be copied.
   * @param dx    the x-axis offset.
   * @param dy    the y-axis offset.
   * @return a copy of the supplied pixel, that has coordinates that are offset by the specified
   * amount.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IPixel createPixelWithOffset(IReadOnlyPixel pixel, int dx, int dy) {
    return createPixel(pixel.getX() + dx, pixel.getY() + dy, pixel.getColor());
  }
}

package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.IReadOnlyColor;

public class PixelFactory {

  /**
   * Creates a new IPixel with the coordinates (x,y)
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return A new IPixel with the coordinates (x,y)
   */
  public static IPixel createPixel(int x, int y) {
    return new PixelImpl(x, y);
  }

  /**
   * Creates a new IPixel with the coordinates (x,y) and the color (r,g,b)
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param r the amount of red [0,1]
   * @param g the amount of green [0,1]
   * @param b the amount of blue [0,1]
   * @return the created IPixel
   */
  public static IPixel createPixel(int x, int y, double r, double g, double b) {
    IPixel pixel = new PixelImpl(x, y);
    pixel.setRed(r);
    pixel.setGreen(g);
    pixel.setBlue(b);
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
   */
  public static IPixel createPixel(IReadOnlyPixel pixel, int dx, int dy) {
    IPixel result = new PixelImpl(pixel.getX() + dx, pixel.getY() + dy);
    IReadOnlyColor color = pixel.getColor();
    result.setRed(color.getRed());
    result.setGreen(color.getGreen());
    result.setBlue(color.getBlue());
    result.setAlpha(color.getAlpha());
    return result;
  }
}

package chalmers.pimp.model.pixeldata;

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
    IPixel pixel = createPixel(x, y);
    pixel.setRed(r);
    pixel.setGreen(g);
    pixel.setBlue(b);
    return pixel;
  }

  /**
   * Creates a clone of pixel with the same color and but with coordinates offset from the original
   * pixel
   *
   * @param pixel   the pixel to be cloned
   * @param xOffset the offset in x-coordinate
   * @param yOffset the offset in y-coordinate
   * @return A new IPixel with the coordinates (pixel.x + xOffset, pixel.y + yOffset)
   */
  public static IPixel createCopyWithOffset(IReadOnlyPixel pixel, int xOffset, int yOffset) {
    IPixel newPixel = createPixel(pixel.getX() + xOffset, pixel.getY() + yOffset, pixel.getRed(),
        pixel.getGreen(), pixel.getBlue());
    return newPixel;
  }
}

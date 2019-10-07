package chalmers.pimp.model.pixeldata;

public interface IReadOnlyPixelData {

  /**
   * Returns a read only matrix of colors (raster).
   *
   * @return the matrix of colors.
   */
  Iterable<? extends Iterable<? extends IReadOnlyPixel>> getPixels();

  /**
   * Returns the color of a specific pixel. Origin is located at the top left corner.
   *
   * @param x the x coordinate of the pixel zero-indexed.
   * @param y the y coordinate of column of the pixel zero-indexed.
   * @return the color of the pixel.
   * @throws IndexOutOfBoundsException if the given coordinates is out of range.
   */
  IReadOnlyPixel getPixel(int x, int y);

  /**
   * Returns the width of this pixel data instance. The width is equivalent to the amount of pixels
   * when counted horizontally.
   *
   * @return the width of this pixel data instance.
   */
  int getWidth();

  /**
   * Returns the height of this pixel data instance. The width is equivalent to the amount of pixels
   * when counted vertically.
   *
   * @return the height of this pixel data instance.
   */
  int getHeight();
}

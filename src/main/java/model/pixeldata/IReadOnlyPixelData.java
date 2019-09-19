package model.pixeldata;

import java.awt.Color;

public interface IReadOnlyPixelData {

  /**
   * Returns a read only matrix of colors (raster).
   *
   * @return the matrix of colors.
   */
  Iterable<? extends Iterable<Color>> getPixels();

  /**
   * Returns the color of a specific pixel. Origin is located at the top left corner.
   *
   * @param x the x coordinate of the pixel zero-indexed.
   * @param y the y coordinate of column of the pixel zero-indexed.
   * @return the color of the pixel.
   * @throws IndexOutOfBoundsException if the given coordinates is out of range.
   */
  Color getPixel(int x, int y);
}

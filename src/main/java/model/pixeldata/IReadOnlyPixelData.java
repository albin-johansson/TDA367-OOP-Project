package model.pixeldata;

import java.awt.Color;
import java.util.Iterator;

public interface IReadOnlyPixelData {

  /**
   * Returns a copy of the matrix of colors (raster).
   *
   * @return the matrix of colors.
   */
  Iterator<? extends Iterator<Color>> getPixels();

  /**
   * Returns the color of a specific pixel.
   *
   * @param row the row of the pixel zero indexed.
   * @param col the column of the pixel zero indexed.
   * @return the color of the pixel.
   */
  Color getPixel(int row, int col);
}

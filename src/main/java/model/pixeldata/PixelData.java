package model.pixeldata;

import java.awt.Color;

/**
 * A matrix of "pixels" in the form of colors, representing a "image".
 *
 * @see Color
 */
public final class PixelData implements IReadOnlyPixelData {

  public PixelData(int width, int height) {
  }

  @Override
  public Iterable<? extends Iterable<Color>> getPixels() {
    return null;
  }

  @Override
  public Color getPixel(int x, int y) {
    return null;
  }

  /**
   * Sets the color of a specific pixel in the pixel matrix. Origin is positioned at top left corner
   * and is zero indexed.
   *
   * @param x     the zero indexed x coordinate of the pixel to change color.
   * @param y     the zero indexed y coordinate of the pixel to change color.
   * @param color the color to be set.
   */
  public void setPixel(int x, int y, Color color) {
  }
}

package model.pixeldata;

import java.awt.Color;

/**
 * A matrix of "pixels" in the form of colors, representing a "image".
 *
 * @see Color
 */
public final class PixelData implements IReadOnlyPixelData {

  private final List<List<Color>> pixels;
  private final List<String> hello = new ArrayList<>();

  /**
   * Creates a PixelData with the given width (amount of pixels horizontally) and height (amount of
   * pixels vertically).
   *
   * @param width  the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @throws IndexOutOfBoundsException if width or height is smaller than or equal to zero.
   */
  public PixelData(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IndexOutOfBoundsException("Width and height must be greater than zero.");
    }

    pixels = new ArrayList<>(height);
    populateMatrix(width, height);
  }

  /**
   * Populates the matrix with opaque colored pixels.
   *
   * @param width  the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @throws IndexOutOfBoundsException if width or height is smaller than or equal to zero.
   */
  private void populateMatrix(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IndexOutOfBoundsException("Width and height must be greater than zero.");
    }

    for (int row = 0; row < height; row++) {
      List<Color> tempRow = new ArrayList<>();

      for (int col = 0; col < width; col++) {
        tempRow.add(new Color(0, 0, 0, 0));
      }

      pixels.add(tempRow);
    }
  }

  @Override
  public Iterable<? extends Iterable<Color>> getPixels() {
    return null;
  }

  @Override
  public Color getPixel(int x, int y) {
    ensureInRange(x, y);

    return pixels.get(y).get(x);
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
  /**
   * Check if a specific coordinate is valid and throws a IndexOutOfBoundsException otherwise.
   *
   * @param x the zero-indexed x coordinate of the pixel.
   * @param y the zero-indexed y coordinate of the pixel.
   * @throws IndexOutOfBoundsException if the given coordinate isn't in the PixelData matrix.
   */
  private void ensureInRange(int x, int y) {
    if (!validCoordinate(x, y)) {
      throw new IndexOutOfBoundsException(
          "X and Y coordinates must be within the size of the PixelData matrix.");
    }
  }
}

package model.pixeldata;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A matrix of "pixels" in the form of colors, representing a "image".
 *
 * @see Color
 */
public final class PixelData implements IReadOnlyPixelData {

  /**
   * A matrix of pixels with a List containing Lists (rows).
   */
  private final List<List<Color>> pixels;

  /**
   * The max width of the matrix.
   */
  private static final int MAX_WIDTH = 100_000;

  /**
   * The max height of the matrix.
   */
  private static final int MAX_HEIGHT = 100_000;

  /**
   * Creates a PixelData with the given width (amount of pixels horizontally) and height (amount of
   * pixels vertically).
   *
   * @param width the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @throws IndexOutOfBoundsException if width or height is smaller than or equal to zero.
   */
  public PixelData(int width, int height) {
    if ((width <= 0) || (width > MAX_WIDTH) || (height <= 0) || (height > MAX_HEIGHT)) {
      throw new IndexOutOfBoundsException("Width and height must be greater than zero.");
    }

    pixels = getPixelDataMatrix(width, height);
  }

  /**
   * Returns a matrix with the desired width and height with opaque colored pixels. Returns a matrix
   * (a list of rows).
   *
   * @param width the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @return List<List < Color>> a matrix of colors. A list containing the rows.
   */
  private List<List<Color>> getPixelDataMatrix(int width, int height) {
    List<List<Color>> tempPixels = new ArrayList<>(height);

    for (int row = 0; row < height; row++) {
      List<Color> tempRow = new ArrayList<>(width);

      for (int col = 0; col < width; col++) {
        tempRow.add(new Color(0, 0, 0, 0));
      }
      tempPixels.add(tempRow);
    }

    return tempPixels;
  }

  @Override
  public Iterable<? extends Iterable<Color>> getPixels() {
    return pixels;
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
   * @param x the zero-indexed x coordinate of the pixel to change color.
   * @param y the zero-indexed y coordinate of the pixel to change color.
   * @param color the color to be set.
   * @throws IndexOutOfBoundsException if the given coordinates is out of range.
   */
  public void setPixel(int x, int y, Color color) {
    if (!validCoordinate(x, y)) {
      throw new IndexOutOfBoundsException(
          "X and Y coordinates must be within the size of the PixelData matrix.");
    }

    pixels.get(y).set(x, color);
  }

  /**
   * Checks if a coordinate is within the matrix.
   *
   * @param x the zero-indexed x coordinate of the pixel.
   * @param y the zero-indexed y coordinate of the pixel.
   * @return true if coordinate is in range, otherwise false.
   */
  private boolean validCoordinate(int x, int y) {
    return !((x < 0) || (y < 0) || (x >= getWidth()) || (y >= getHeight()));
  }

  /**
   * Returns the width (amount of pixels horizontally) of the PixelData.
   *
   * @return the amount of pixels in width.
   */
  public int getWidth() {
    return pixels.get(0).size();
  }

  /**
   * Returns the height (amount of pixels vertically) of the PixelData.
   *
   * @return the amount of pixels in height.
   */
  public int getHeight() {
    return pixels.size();
  }

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

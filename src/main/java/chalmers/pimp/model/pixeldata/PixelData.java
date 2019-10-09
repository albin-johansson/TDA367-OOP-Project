package chalmers.pimp.model.pixeldata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code PixelData} class is a representation of a collection of pixels. This class implements
 * the {@code IReadOnlyPixelData} interface.
 */
public final class PixelData implements IReadOnlyPixelData {

  /**
   * The max width of the matrix.
   */
  private static final int MAX_WIDTH = 100_000;
  /**
   * The max height of the matrix.
   */
  private static final int MAX_HEIGHT = 100_000;
  /**
   * A matrix of pixels with a List containing Lists (rows).
   */
  private final List<List<IPixel>> pixels;

  /**
   * Creates a PixelData with the given width (amount of pixels horizontally) and height (amount of
   * pixels vertically).
   *
   * @param width  the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @throws IndexOutOfBoundsException if width or height is smaller than or equal to zero.
   */
  public PixelData(int width, int height) {
    if ((width <= 0) || (width > MAX_WIDTH) || (height <= 0) || (height > MAX_HEIGHT)) {
      throw new IndexOutOfBoundsException("Bad dimensions: (" + width + "x" + height + ")");
    } else {
      pixels = createPixelDataMatrix(width, height);
    }
  }

  /**
   * Returns a matrix with the desired width and height with opaque colored pixels. Returns a matrix
   * (a list of rows).
   *
   * @param width  the amount of pixels in width.
   * @param height the amount of pixels in height.
   * @return a matrix of colors.
   */
  private List<List<IPixel>> createPixelDataMatrix(int width, int height) {
    List<List<IPixel>> tempPixels = new ArrayList<>(height);

    for (int row = 0; row < height; row++) {
      List<IPixel> tempRow = new ArrayList<>(width);

      for (int col = 0; col < width; col++) {
        tempRow.add(new PixelImpl(col, row));
      }
      tempPixels.add(tempRow);
    }

    return tempPixels;
  }

  /**
   * Check if the specified coordinate is valid, throws an exception otherwise.
   *
   * @param x the x-coordinate that will be checked (zero-indexed).
   * @param y the y-coordinate that will be checked (zero-indexed).
   * @throws IndexOutOfBoundsException if the supplied coordinate is out-of-bounds.
   */
  private void ensureInRange(int x, int y) {
    if (isBadCoordinate(x, y)) {
      throw new IndexOutOfBoundsException("Bad coordinate: (" + x + ", " + y + ")");
    }
  }

  /**
   * Indicates whether or not the specified coordinate is valid (within the bounds of this pixel
   * data).
   *
   * @param x the x-coordinate that will be checked (zero-indexed).
   * @param y the y-coordinate that will be checked (zero-indexed).
   * @return {@code true} if the supplied coordinate is invalid; {@code false} otherwise.
   */
  private boolean isBadCoordinate(int x, int y) {
    return (x < 0) || (y < 0) || (x >= getWidth()) || (y >= getHeight());
  }

  /**
   * Sets a specific pixel in the pixel matrix. Origin is positioned at top left corner and is zero
   * indexed.
   *
   * @param pixel the pixel to be set.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void setPixel(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (!isBadCoordinate(pixel.getX(), pixel.getY())) {
      pixels.get(pixel.getY()).set(pixel.getX(), pixel);
    }
  }

  @Override
  public Iterable<? extends Iterable<? extends IReadOnlyPixel>> getPixels() {
    return pixels;
  }

  @Override
  public IReadOnlyPixel getPixel(int x, int y) {
    ensureInRange(x, y);
    return pixels.get(y).get(x);
  }

  @Override
  public int getWidth() {
    return pixels.get(0).size();
  }

  @Override
  public int getHeight() {
    return pixels.size();
  }
}
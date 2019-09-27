package model.pixeldata;

import java.util.Objects;

/**
 * The {@code PixelImpl} class is an implementation of the {@code IPixel} interface.
 *
 * @see IPixel
 */
final class PixelImpl implements IPixel {

  /**
   * The maximum possible value for a color component.
   */
  private static final double MAX_VALUE = 1;

  /**
   * The minimum possible value for a color component.
   */
  private static final double MIN_VALUE = 0;

  private final int x;
  private final int y;
  private double red;
  private double green;
  private double blue;
  private double alpha;

  /**
   * @param x the x-coordinate of the pixel (the column index).
   * @param y the y-coordinate of the pixel (the row index).
   */
  PixelImpl(int x, int y) {
    this.x = x;
    this.y = y;

    red = MIN_VALUE;
    green = MIN_VALUE;
    blue = MIN_VALUE;
    alpha = MAX_VALUE;
  }

  /**
   * Returns the closest legal color component value to the specified value.
   *
   * @param colorValue the color component value that will be checked.
   * @return the closest legal color component value to the specified value.
   */
  private double getClosestValidValue(double colorValue) {
    if (colorValue < MIN_VALUE) {
      return MIN_VALUE;
    } else {
      return Math.min(colorValue, MAX_VALUE);
    }
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setRed(double red) {
    this.red = getClosestValidValue(red);
  }

  @Override
  public void setGreen(double green) {
    this.green = getClosestValidValue(green);
  }

  @Override
  public void setBlue(double blue) {
    this.blue = getClosestValidValue(blue);
  }

  @Override
  public void setAlpha(double alpha) {
    this.alpha = getClosestValidValue(alpha);
  }

  @Override
  public double getRed() {
    return red;
  }

  @Override
  public double getGreen() {
    return green;
  }

  @Override
  public double getBlue() {
    return blue;
  }

  @Override
  public double getAlpha() {
    return alpha;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, red, green, blue, alpha);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IPixel)) {
      return false;
    }

    IPixel pixel = (IPixel) obj;

    return (pixel.getRed() == red) && (pixel.getGreen() == green) && (pixel.getBlue() == blue) && (
        pixel.getAlpha() == alpha);
  @Override
  public IReadOnlyColor getColor() {
    return color;
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Red: " + red + ", Green: " + green + ", Blue: " + blue + ", Alpha: " + alpha;
    return "(" + id + " | " + state + ")";
  }
}
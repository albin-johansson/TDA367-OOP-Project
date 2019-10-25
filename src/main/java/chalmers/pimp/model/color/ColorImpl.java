package chalmers.pimp.model.color;

import java.util.Objects;

/**
 * The {@code ColorImp} class is an implementation of the {@code IColor} interface. A immutable
 * color.
 *
 * @see IColor
 */
final class ColorImpl implements IColor {

  private final int red;
  private final int green;
  private final int blue;
  private final int alpha;

  /**
   * @param red   the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @param alpha the alpha component [0, 255].
   */
  ColorImpl(int red, int green, int blue, int alpha) {
    this.red = getClosestValue(red);
    this.green = getClosestValue(green);
    this.blue = getClosestValue(blue);
    this.alpha = getClosestValue(alpha);
  }

  /**
   * Returns the closest legal color component value to the specified value.
   *
   * @param val the color component value that will be checked.
   * @return the closest legal color component.
   */
  private int getClosestValue(int val) {
    if (val >= MAX_VALUE) {
      return MAX_VALUE;
    } else {
      return Math.max(val, MIN_VALUE);
    }
  }

  /**
   * Returns the closest percentage between [0, 1].
   *
   * @param percentage the percentage that will be checked, in the range [0, 1].
   * @return the closest percentage.
   */
  private double getClosestPercentage(double percentage) {
    double max = 1;
    double min = 0;

    if (percentage >= max) {
      return max;
    } else {
      return Math.max(min, percentage);
    }
  }

  /**
   * Returns the percentage of the color.
   *
   * @param val the color component.
   * @return the percentage of the color.
   */
  private double getPercentage(int val) {
    return (val / (double) MAX_VALUE);
  }

  /**
   * Converts a percentage between [0, 1] to a value between [0, 255]. An input of 0.5 would return
   * what 50% of 255 is.
   *
   * @param percentage the percentage between [0, 1].
   * @return a int in the range [0, 255]
   */
  private int convertPercentage(double percentage) {
    return (int) (MAX_VALUE * getClosestPercentage(percentage));
  }

  @Override
  public IColor setColor(int red, int green, int blue, int alpha) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public IColor setColor(int red, int green, int blue) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public IColor setPercentageRed(double percentageRed) {
    return new ColorImpl(convertPercentage(percentageRed), green, blue, alpha);
  }

  @Override
  public IColor setPercentageGreen(double percentageGreen) {
    return new ColorImpl(red, convertPercentage(percentageGreen), blue, alpha);
  }

  @Override
  public IColor setPercentageBlue(double percentageBlue) {
    return new ColorImpl(red, green, convertPercentage(percentageBlue), alpha);
  }

  @Override
  public IColor setPercentageAlpha(double percentageAlpha) {
    return new ColorImpl(red, green, blue, convertPercentage(percentageAlpha));
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public IColor setRed(int red) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public IColor setGreen(int green) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public int getBlue() {
    return blue;
  }

  @Override
  public IColor setBlue(int blue) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public int getAlpha() {
    return alpha;
  }

  @Override
  public IColor setAlpha(int alpha) {
    return new ColorImpl(red, green, blue, alpha);
  }

  @Override
  public double getRedPercentage() {
    return getPercentage(red);
  }

  @Override
  public double getGreenPercentage() {
    return getPercentage(green);
  }

  @Override
  public double getBluePercentage() {
    return getPercentage(blue);
  }

  @Override
  public double getAlphaPercentage() {
    return getPercentage(alpha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue, alpha);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IColor)) {
      return false;
    }
    if (obj == this) {
      return true;
    }

    var c = (IColor) obj;

    return (c.getRed() == red)
        && (c.getGreen() == green)
        && (c.getBlue() == blue)
        && (c.getAlpha() == alpha);
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Red: " + red + ", Green: " + green + ", Blue: " + blue + ", Alpha: " + alpha;
    return "(" + id + " | " + state + ")";
  }
}

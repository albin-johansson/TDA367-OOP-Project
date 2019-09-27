package model.color;

/**
 * The {@code ColorImp} class is an implementation of the {@code IColor} interface.
 *
 * @see IColor
 */
public final class ColorImpl implements IColor {

  private int red;
  private int green;
  private int blue;
  private int alpha;
  private static final int MAX_VALUE = 255;
  private static final int MIN_VALUE = 0;

  /**
   * @param red   the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @param alpha the alpha component [0, 255].
   */
  ColorImpl(int red, int green, int blue, int alpha) {
    setColor(red, green, blue, alpha);
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
   * @return the closest percentage.
   */
  private double getClosestPercentage(double val) {
    double max = 1;
    double min = 0;

    if (val >= max) {
      return max;
    } else {
      return Math.max(min, val);
    }
  }

  @Override
  public void setColor(int red, int green, int blue, int alpha) {
    this.red = getClosestValue(red);
    this.green = getClosestValue(green);
    this.blue = getClosestValue(blue);
    this.alpha = getClosestValue(alpha);
  }

  @Override
  public void setColor(int red, int green, int blue) {
    setColor(red, green, blue, MAX_VALUE);
  }

  @Override
  public void setRed(int red) {
    this.red = getClosestValue(red);
  }

  @Override
  public void setGreen(int green) {
    this.green = getClosestValue(green);
  }

  @Override
  public void setBlue(int blue) {
    this.blue = getClosestValue(blue);
  }

  @Override
  public void setAlpha(int alpha) {
    this.alpha = getClosestValue(alpha);
  }

  @Override
  public void setPercentageRed(double percentageRed) {
    red = convertPercentage(percentageRed);
  }

  @Override
  public void setPercentageGreen(double percentageGreen) {
    green = convertPercentage(percentageGreen);
  }

  @Override
  public void setPercentageBlue(double percentageBlue) {
    blue = convertPercentage(percentageBlue);
  }

  @Override
  public void setPercentageAlpha(double percentageAlpha) {
    alpha = convertPercentage(percentageAlpha);
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
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }

  @Override
  public int getAlpha() {
    return alpha;
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

  /**
   * Returns the percentage of the color.
   *
   * @param val the color component.
   * @return the percentage of the color.
   */
  private double getPercentage(int val) {
    return (val / (double) MAX_VALUE);
  }
}

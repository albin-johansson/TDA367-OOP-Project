package model.color;

/**
 * The {@code ColorImp} class is an implementation of the {@code IColor} interface.
 *
 * @see IColor
 */
public final class ColorImp implements IColor {

  private int red;
  private int green;
  private int blue;
  private int alpha;
  private static final int MAX_VALUE = 255;
  private static final int MIN_VALUE = 0;
  private static final String HEX_PATTERN = "#?([\\da-fA-F]{2})([\\da-fA-F]{2})([\\da-fA-F]{2})";

  /**
   * @param red the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue the blue component [0, 255].
   * @param alpha the alpha component [0, 255].
   */
  ColorImp(int red, int green, int blue, int alpha) {
    setColor(red, green, blue, alpha);
  }

  /**
   * Returns the closest legal alpha value to the specified value.
   *
   * @param alpha the alpha value that will be checked.
   * @return the closest legal alpha value.
   */
  private double getClosestAlphaValue(double alpha) {
    if (alpha >= MAX_ALPHA_VALUE) {
      return MAX_ALPHA_VALUE;
    } else {
      return Math.max(alpha, MIN_ALPHA_VALUE);
    }
  }

  /**
   * Returns the closest legal color component value to the specified value.
   *
   * @param val the color component value that will be checked.
   * @return the closest legal color component.
   */
  private int getClosestRGBValue(int val) {
    if (val >= MAX_RGB_VALUE) {
      return MAX_RGB_VALUE;
    } else {
      return Math.max(val, MIN_RGB_VALUE);
    }
  }

  @Override
  public void setColor(String hex) {

  }

  @Override
  public void setRed(int red) {
    this.red = getClosestRGBValue(red);
  }

  @Override
  public void setGreen(int green) {
    this.green = getClosestRGBValue(green);
  }

  @Override
  public void setBlue(int blue) {
    this.blue = getClosestRGBValue(blue);
  }

  @Override
  public void setAlpha(double alpha) {
    this.alpha = getClosestAlphaValue(alpha);
  }

  @Override
  public void setPercentageRed(double percentageRed) {
    red = (int) (MAX_VALUE * percentageRed);
  }

  @Override
  public void setPercentageGreen(double percentageGreen) {
    green = (int) (MAX_VALUE * percentageGreen);
  }

  @Override
  public void setPercentageBlue(double percentageBlue) {
    blue = (int) (MAX_VALUE * percentageBlue);
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
  public double getAlpha() {
    return alpha;
  }

  @Override
  public double getRedPercentage() {
    return (red / (double) MAX_RGB_VALUE);
  }

  @Override
  public double getGreenPercentage() {
    return (green / (double) MAX_RGB_VALUE);
  }

  @Override
  public double getBluePercentage() {
    return (blue / (double) MAX_RGB_VALUE);
  }

  @Override
  public String getAlphaHexCode() {
    // TODO
    return null;
  }

  @Override
  public String getHexCode() {
    // TODO
    return null;
  }
}

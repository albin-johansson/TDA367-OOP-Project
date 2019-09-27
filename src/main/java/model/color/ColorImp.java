package model.color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  /**
   * The regular expression for validating a hex code. Requires a # at the start and can be for
   * example #000 or #000000.
   */
  private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
  private Pattern pattern;
  private Matcher matcher;

  /**
   * @param red   the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @param alpha the alpha component [0, 255].
   */
  ColorImp(int red, int green, int blue, int alpha) {
    setColor(red, green, blue, alpha);
  }

  /**
   * Creates a color by its hex color code. Can also include alpha value at the end. Creates a
   * transparent color if provided hex code is invalid.
   *
   * @param hex the hexadecimal code for the color.
   */
  ColorImp(String hex) {
    setColor(hex);
  }

  /**
   * Initializes the hex regex pattern.
   */
  private void initHexRegEx() {
    pattern = Pattern.compile(HEX_PATTERN);
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

  /**
   * Checks if a hex code is valid. Accepts both RGB and RGBA codes. Must start with #. TODO: Create
   * a better RegEx so that it does not require to start with a #.
   *
   * @param hex the hex code.
   * @return true if the provided hex code is valid.
   */
  private boolean validHexCode(String hex) {
    if ((hex == null) || (hex.length() == 0)) {
      return false;
    }

    // TODO: Update so that the regex validates strings with and without #.
    String stringToValidate;
    if (hex.charAt(0) == '#') {
      stringToValidate = hex;
    } else {
      stringToValidate = "#" + hex;
    }

    matcher = pattern.matcher(stringToValidate);
    return matcher.matches();
  }

  @Override
  public void setColor(String hex) {
    initHexRegEx();

    if (!validHexCode(hex)) {
      setColor(MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE);
      return;
    }

    hex = hex.replace("#", "");

    red = Integer.valueOf(hex.substring(0, 2), 16);
    green = Integer.valueOf(hex.substring(2, 4), 16);
    blue = Integer.valueOf(hex.substring(4, 6), 16);

    // Optional alpha value
    if (hex.length() == 8) {
      alpha = Integer.valueOf(hex.substring(6, 8), 16);
    } else {
      alpha = MAX_VALUE;
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

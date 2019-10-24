package chalmers.pimp.model.color;

/**
 * The {@code IColor} represent a immutable color.
 */
public interface IColor {

  /**
   * The max value for a color component.
   */
  int MAX_VALUE = 255;

  /**
   * The minimum value for a color component.
   */
  int MIN_VALUE = 0;

  /**
   * Returns a new color with the supplied RGBA values.
   *
   * @param red   the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @param alpha the alpha value [0, 255].
   * @return a new color.
   */
  IColor setColor(int red, int green, int blue, int alpha);

  /**
   * Returns a new color with the supplied RGBA values.
   *
   * @param red   the red component in the range [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @return a new color.
   */
  IColor setColor(int red, int green, int blue);

  /**
   * Sets the red percentage of the color in the range [0, 1] and returns a new color.
   *
   * @param red the blue percentage.
   * @return a new color.
   */
  IColor setPercentageRed(double red);

  /**
   * Sets the green percentage of the color in the range [0, 1] and returns a new color.
   *
   * @param green the green percentage.
   * @return a new color.
   */
  IColor setPercentageGreen(double green);

  /**
   * Sets the red percentage of the color in the range [0, 1] and returns a new color.
   *
   * @param blue the blue percentage.
   * @return a new color.
   */
  IColor setPercentageBlue(double blue);

  /**
   * Sets the alpha percentage of the color in the range [0, 1] and returns a new color.
   *
   * @param alpha the alpha percentage.
   * @return a new color.
   */
  IColor setPercentageAlpha(double alpha);

  /**
   * Returns the red component in the range [0, 255].
   *
   * @return the red component.
   */
  int getRed();

  /**
   * Sets the red component in the range [0, 255] and returns a new color.
   *
   * @param red the red component.
   * @return a new color.
   */
  IColor setRed(int red);

  /**
   * Returns the green component in the range [0, 255].
   *
   * @return the green component.
   */
  int getGreen();

  /**
   * Sets the green component in the range [0, 255] and returns a new color.
   *
   * @param green the red component.
   * @return a new color.
   */
  IColor setGreen(int green);

  /**
   * Returns the blue component in the range [0, 255].
   *
   * @return the blue component.
   */
  int getBlue();

  /**
   * Sets the blue component in the range [0, 255] and returns a new color.
   *
   * @param blue the red component.
   * @return a new color.
   */
  IColor setBlue(int blue);

  /**
   * Returns the alpha value in the range [0, 255].
   *
   * @return the alpha value.
   */
  int getAlpha();

  /**
   * Sets the alpha value in the range [0, 255] and returns a new color.
   *
   * @param alpha the alpha value.
   * @return a new color.
   */
  IColor setAlpha(int alpha);

  /**
   * Returns the red percentage of the color in the range [0, 1].
   *
   * @return the red percentage.
   */
  double getRedPercentage();

  /**
   * Returns the green percentage of the color in the range [0, 1].
   *
   * @return the green percentage.
   */
  double getGreenPercentage();

  /**
   * Returns the blue percentage of the color in the range [0, 1].
   *
   * @return the blue percentage.
   */
  double getBluePercentage();

  /**
   * Returns the alpha percentage in the range [0, 1].
   *
   * @return the alpha percentage.
   */
  double getAlphaPercentage();
}

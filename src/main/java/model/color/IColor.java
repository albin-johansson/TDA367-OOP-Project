package model.color;

/**
 * The {@code IColor} represent a color.
 */
public interface IColor extends IReadOnlyColor {

  /**
   * Sets the color by its hexadecimal code. Works both with and without alpha hex.
   *
   * @param hex the hexadecimal code the color should have.
   */
  void setColor(String hex);

  /**
   * Sets the color by RGBA values.
   *
   * @param red   the red component [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   * @param alpha the alpha value [0, 255].
   */
  void setColor(int red, int green, int blue, int alpha);

  /**
   * Sets the color by RGB values.
   *
   * @param red   the red component in the range [0, 255].
   * @param green the green component [0, 255].
   * @param blue  the blue component [0, 255].
   */
  void setColor(int red, int green, int blue);

  /**
   * Sets the red component in the range [0, 255].
   *
   * @param red the red component.
   */
  void setRed(int red);

  /**
   * Sets the green component in the range [0, 255].
   *
   * @param green the red component.
   */
  void setGreen(int green);

  /**
   * Sets the blue component in the range [0, 255].
   *
   * @param blue the red component.
   */
  void setBlue(int blue);

  /**
   * Sets the alpha value in the range [0, 255].
   *
   * @param alpha the alpha value.
   */
  void setAlpha(int alpha);

  /**
   * Sets the red percentage of the color in the range [0, 1].
   *
   * @param red the blue percentage.
   */
  void setPercentageRed(double red);

  /**
   * Sets the green percentage of the color in the range [0, 1].
   *
   * @param green the green percentage.
   */
  void setPercentageGreen(double green);

  /**
   * Sets the red percentage of the color in the range [0, 1].
   *
   * @param blue the blue percentage.
   */
  void setPercentageBlue(double blue);

  /**
   * Sets the alpha percentage of the color in the range [0, 1].
   *
   * @param alpha the alpha percentage.
   */
  void setPercentageAlpha(double alpha);
}
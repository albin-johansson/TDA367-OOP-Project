package model.color;

/**
 * The {@code IReadOnlyColor} represent a color that can only be read.
 */
public interface IReadOnlyColor {

  /**
   * Returns the red component in the range [0, 255].
   *
   * @return the red component.
   */
  int getRed();

  /**
   * Returns the green component in the range [0, 255].
   *
   * @return the green component.
   */
  int getGreen();

  /**
   * Returns the blue component in the range [0, 255].
   *
   * @return the blue component.
   */
  int getBlue();

  /**
   * Returns the alpha value in the range [0, 255].
   *
   * @return the alpha value.
   */
  int getAlpha();

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

  /**
   * Returns the alpha hexadecimal code without a # for the color. A hex code that includes the
   * alpha values at the end.
   *
   * @return the alpha hexadecimal code for the color.
   */
  String getAlphaHexCode();

  /**
   * Returns the hexadecimal code without a # for the color.
   *
   * @return the hexadecimal code for the color.
   */
  String getHexCode();
}
package model.color;

/**
 * The {@code ColorFactory} class is a factory for creating instances of the {@code IColor}
 * interface.
 */
public final class ColorFactory {

  private ColorFactory() {

  }

  /**
   * Creates and returns a transparent color.
   *
   * @return a transparent color.
   */
  public static IColor createColor() {
    return new ColorImpl(0, 0, 0, 0);
  }

  /**
   * Creates and returns a transparent color.
   *
   * @param red   the red component in the range [0, 255].
   * @param green the green component in the range [0, 255].
   * @param blue  the blue component in the range [0, 255].
   * @param alpha the alpha component in the range [0, 255].
   * @return a color specified by the given color components.
   */
  public static IColor createColor(int red, int green, int blue, int alpha) {
    return new ColorImpl(red, green, blue, alpha);
  }

  /**
   * Creates and returns a transparent color.
   *
   * @param red   the red component in the range [0, 255].
   * @param green the green component in the range [0, 255].
   * @param blue  the blue component in the range [0, 255].
   * @return a color specified by the given color components and no transparency.
   */
  public static IColor createColor(int red, int green, int blue) {
    return new ColorImpl(red, green, blue, 1);
  }
}

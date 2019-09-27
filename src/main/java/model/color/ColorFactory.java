package model.color;

/**
 * Creates colors.
 */
public class ColorFactory {

  public static IColor createColor() {
    return new ColorImpl(0, 0, 0, 0);
  }

  public static IColor createColor(int red, int green, int blue, int alpha) {
    return new ColorImpl(red, green, blue, alpha);
  }

  public static IColor createColor(int red, int green, int blue) {
    return new ColorImpl(red, green, blue, 1);
  }

  public static IColor createColor(String hex) {
    return new ColorImpl(hex);
  }
}

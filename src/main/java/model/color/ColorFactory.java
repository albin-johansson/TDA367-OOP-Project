package model.color;

public class ColorFactory {

  public static IColor createColor(int red, int green, int blue, int alpha) {
    return new ColorImp(red, green, blue, alpha);
  }

  public static IColor createColor(int red, int green, int blue) {
    return new ColorImp(red, green, blue, 1);
  }

  public static IColor createColor(String hex) {
    return new ColorImp(hex);
  }
}

package chalmers.pimp.model.color;

/**
 * The {@code Colors} class holds some preset colors.
 */
public final class Colors {

  private Colors() {
  }

  public static final IColor WHITE = ColorFactory.createColor(0xFF, 0xFF, 0xFF);
  public static final IColor BLACK = ColorFactory.createColor(0, 0, 0);
  public static final IColor TRANSPARENT = ColorFactory.createColor(0, 0, 0, 0);
  public static final IColor RED = ColorFactory.createColor(0xFF, 0, 0);
  public static final IColor GREEN = ColorFactory.createColor(0, 0xFF, 0);
  public static final IColor BLUE = ColorFactory.createColor(0, 0, 0xFF);
}
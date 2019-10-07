package chalmers.pimp.model.point;

/**
 * The {@code PointFactory} class is a factory for creating instances of the {@code IPoint}
 * interface.
 */
public class PointFactory {

  private PointFactory() {
  }

  /**
   * Creates and returns a point with the supplied coordinates.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @return a point.
   */
  public static IPoint createPoint(int x, int y) {
    return new Point(x, y);
  }
}

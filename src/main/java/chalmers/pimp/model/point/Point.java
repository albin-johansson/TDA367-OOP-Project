package chalmers.pimp.model.point;

/**
 * The {@code Point} is a implementation of the {@code IPoint} interface.
 */
public final class Point implements IPoint {

  private int x;
  private int y;

  /**
   * Creates and returns a new point.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }
}

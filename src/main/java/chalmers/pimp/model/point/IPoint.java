package chalmers.pimp.model.point;

/**
 * The {@code IPoint} interface represents a point that can be read and set.
 */
public interface IPoint extends IReadOnlyPoint {

  /**
   * Sets the x coordinate for the point.
   *
   * @param x the x coordinate for the point.
   */
  void setX(int x);

  /**
   * Sets the y coordinate for the point.
   *
   * @param y the y coordinate for the point.
   */
  void setY(int y);
}

package chalmers.pimp.model;

import java.util.Objects;

/**
 * The {@code Point} class represents a point. The point is immutable.
 */
public final class Point {

  private final int x;
  private final int y;

  /**
   * Creates and returns a new point.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns a new point with the same y component and the new x component.
   *
   * @param x the new x component.
   * @return a new point with the same y component and the new x component.
   */
  public Point setX(int x) {
    return new Point(x, getY());
  }

  /**
   * Returns a new point with the same x component and the new y component.
   *
   * @param y the new y component.
   * @return a new point with the same x component and the new y component.
   */
  public Point setY(int y) {
    return new Point(getX(), y);
  }

  /**
   * Returns the x component of the point.
   *
   * @return the x component of the point.
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the y component of the point.
   *
   * @return the y component of the point.
   */
  public int getY() {
    return y;
  }  
  /**
   * Returns a new point with xOffset offset in the x-direction
   *
   * @param xOffset the x offset to be added
   * @return a new point with with coordinates (x + xOffset, y)
   */
  public Point addX(int xOffset) {
    return new Point(x + xOffset, y);
  }

  /**
   * Returns a new point with yOffset offset in the y-direction
   *
   * @param yOffset the y offset to be added
   * @return a new point with with coordinates (x, y + yOffset)
   */
  public Point addY(int yOffset) {
    return new Point(x, y + yOffset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Point)) {
      return false;
    }
    if (obj == this) {
      return true;
    }

    var c = (Point) obj;

    return (c.getX() == x) && (c.getY() == y);
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "X: " + x + ", Y: " + y;
    return "(" + id + " | " + state + ")";
  }
}

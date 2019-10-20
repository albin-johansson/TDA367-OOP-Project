package chalmers.pimp.model.viewport;

import chalmers.pimp.model.IArea;
import chalmers.pimp.model.Point;

/**
 * The {@code IReadOnlyViewport} interface specifies objects that may act as read-only viewports
 * that provide methods to obtain relative coordinates to.
 */
public interface IReadOnlyViewport extends IArea {

  /**
   * Returns the x-coordinate of the viewport.
   *
   * @return the x-coordinate of the viewport.
   */
  int getX();

  /**
   * Returns the y-coordinate of the viewport.
   *
   * @return the y-coordinate of the viewport.
   */
  int getY();

  /**
   * Returns a point that represents a translated version of the supplied point.
   *
   * @param point the point that will be translated.
   * @return a point that represents a translated version of the supplied point.
   * @throws NullPointerException if the supplied point is {@code null}.
   */
  Point translate(Point point);

  /**
   * Calculates and returns the translated x-coordinate to the supplied x-coordinate. This method
   * basically adds the viewport x-coordinate and the supplied x-coordinate and returns the result.
   *
   * @param x the x-coordinate that will be translated.
   * @return the translated x-coordinate.
   */
  int getTranslatedX(int x);

  /**
   * Calculates and returns the translated y-coordinate to the supplied x-coordinate. This method
   * basically adds the viewport y-coordinate and the supplied y-coordinate and returns the result.
   *
   * @param y the y-coordinate that will be translated.
   * @return the translated y-coordinate.
   */
  int getTranslatedY(int y);
}

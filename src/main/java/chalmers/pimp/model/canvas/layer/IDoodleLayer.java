package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.Point;

/**
 * The {@code IDoodleLayer} is a subinterface of {@code ILayer} and extends its functionality
 * suitable for a doodle.
 *
 * @see ILayer
 */
public interface IDoodleLayer extends ILayer {

  /**
   * Adds a point which is used while rendering
   *
   * @param p the specified point
   * @throws NullPointerException if the point is {@code null}
   */
  void addPoint(Point p);

  /**
   * Removes all points within threshold distance of the specified point
   *
   * @param p         the specified point
   * @param threshold the threshold distance in pixels
   * @throws NullPointerException if the specified point is {@code null}
   */
  void removePoint(Point p, double threshold);
}


package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.Point;
import chalmers.pimp.model.viewport.IReadOnlyViewport;

public interface IDoodleLayer extends ILayer {

  /**
   * Adds a point which is used wile rendering
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
   */
  void removePoint(Point p, double threshold);
}


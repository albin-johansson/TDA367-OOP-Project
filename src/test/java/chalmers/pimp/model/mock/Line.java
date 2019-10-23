package chalmers.pimp.model.mock;

import chalmers.pimp.model.Point;
import chalmers.pimp.model.pixeldata.IReadOnlyPixel;

public final class Line {

  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;

  public Line(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Line(IReadOnlyPixel p1, IReadOnlyPixel p2) {
    x1 = p1.getX();
    y1 = p1.getY();
    x2 = p2.getX();
    y2 = p2.getY();
  }

  public Line(Point p1, Point p2) {
    x1 = p1.getX();
    y1 = p1.getY();
    x2 = p2.getX();
    y2 = p2.getY();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Line)) {
      return false;
    }
    if (obj == this) {
      return true;
    }

    var other = (Line) obj;

    return ((x1 == other.x1) && (y1 == other.y1) && (x2 == other.x2) && (y2 == other.y2))
        || ((x1 == other.x2) && (y1 == other.y2) && (x2 == other.x1) && (y2 == other.y1));
  }
}
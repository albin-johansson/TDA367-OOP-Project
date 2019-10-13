package chalmers.pimp.model.mock;

import chalmers.pimp.model.pixeldata.IPixel;

public class Line {

  private final int x1, y1, x2, y2;

  public Line(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Line(IPixel p1, IPixel p2) {
    this.x1 = p1.getX();
    this.y1 = p1.getY();
    this.x2 = p2.getX();
    this.y2 = p2.getY();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Line)) {
      return false;
    }

    Line l = (Line) o;

    return (this.x1 == l.x1 && this.y1 == l.y1 && this.x2 == l.x2 && this.y2 == l.y2) || (
        this.x1 == l.x2 && this.y1 == l.y2 && this.x2 == l.x1 && this.y2 == l.y1);
  }
}
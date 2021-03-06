package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * A layer which has a list of points and draws straight lines between the points.
 */
final class Doodle implements IDoodleLayer, IColorable {

  private final List<Point> points;
  private final LayerDelegate layerDelegate;
  private final int lineWidth;
  private IColor color;

  /**
   * @param lineWidth the width of the lines that are drawn.
   * @param color     the color of the doodle layer.
   * @throws NullPointerException if the supplied color is {@code null}.
   */
  Doodle(int lineWidth, IColor color) {
    this.lineWidth = lineWidth;
    this.color = Objects.requireNonNull(color);

    points = new ArrayList<>(20);
    layerDelegate = new LayerDelegate(LayerType.DOODLE);
    layerDelegate.setName("Doodle");
  }

  /**
   * Creates a copy of the supplied doodle.
   *
   * @param doodle the doodle that will be copied.
   * @throws NullPointerException if the supplied doodle is {@code null}.
   */
  private Doodle(Doodle doodle) {
    Objects.requireNonNull(doodle);

    layerDelegate = new LayerDelegate(doodle.layerDelegate);

    points = new ArrayList<>(doodle.points.size());
    points.addAll(doodle.points);

    color = doodle.color;
    lineWidth = doodle.lineWidth;
  }

  @Override
  public boolean isVisible() {
    return layerDelegate.isVisible();
  }

  @Override
  public int getX() {
    List<Integer> xValues = points.stream().map(Point::getX).collect(Collectors.toList());
    int min = getExtreme(xValues, (a, b) -> a > b);
    return min + layerDelegate.getX();
  }

  @Override
  public int getY() {
    List<Integer> yValues = points.stream().map(Point::getY).collect(Collectors.toList());
    int min = getExtreme(yValues, (a, b) -> a > b);
    return min + layerDelegate.getY();
  }

  @Override
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public int getDepthIndex() {
    return layerDelegate.getDepthIndex();
  }

  @Override
  public Point getCenterPoint() {
    return new Point(getX() + (getWidth() / 2), getY() + (getHeight() / 2));
  }

  @Override
  public LayerType getLayerType() {
    return layerDelegate.getLayerType();
  }

  @Override
  public int getRotation() {
    return layerDelegate.getRotationDegrees();
  }

  @Override
  public double getAlpha() {
    return layerDelegate.getAlpha();
  }

  @Override
  public int getWidth() {
    //Returns a list of the values returned from getX() for each item in points
    //.stream() returns a sequential stream considering collection as its source
    List<Integer> xValues = points.stream().map(Point::getX).collect(Collectors.toList());
    return getMostDiff(xValues) + (lineWidth * 2);
  }

  @Override
  public int getHeight() {
    //Returns a list of the values returned from getY() for each item in points
    //.stream() returns a sequential stream considering collection as its source
    List<Integer> yValues = points.stream().map(Point::getY).collect(Collectors.toList());
    return getMostDiff(yValues) + (lineWidth * 2);
  }

  @Override
  public void setVisible(boolean isVisible) {
    layerDelegate.setVisible(isVisible);
  }

  @Override
  public void move(int dx, int dy) {
    layerDelegate.move(dx, dy);
  }

  @Override
  public void setX(int x) {
    List<Integer> xValues = points.stream().map(Point::getX).collect(Collectors.toList());
    int min = getExtreme(xValues, (a, b) -> a > b);

    layerDelegate.setX(x - min);
  }

  @Override
  public void setY(int y) {
    List<Integer> yValues = points.stream().map(Point::getY).collect(Collectors.toList());
    int min = getExtreme(yValues, (a, b) -> a > b);

    layerDelegate.setY(y - min);
  }

  @Override
  public void setName(String name) {
    layerDelegate.setName(name);
  }

  @Override
  public void setDepthIndex(int depthIndex) {
    layerDelegate.setDepthIndex(depthIndex);
  }

  @Override
  public void setRotation(int rotation) {
    layerDelegate.setRotationDegrees(rotation);
  }

  @Override
  public void setAlpha(double alpha) {
    layerDelegate.setAlpha(alpha);
  }

  @Override
  public ILayer clone() {
    try {
      var clone = (Doodle) super.clone();
      return new Doodle(clone);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void draw(IRenderer renderer, IReadOnlyViewport viewport) {
    if (!isVisible() || points.isEmpty()) {
      return;
    }
    renderer.startTransform(getRotation(), viewport.translate(getCenterPoint()));
    renderer.setGlobalAlpha(getAlpha());
    renderer.setBorderColor(color);
    renderer.setFillColor(color);
    renderer.setLineWidth(lineWidth);

    var position = new Point(layerDelegate.getX(), layerDelegate.getY());
    var translatedPoint = viewport.translate(position);

    if (points.size() == 1) {
      Point point = translatedPoint.add(points.get(0));
      renderer.drawLine(point, point); // basically renders a single point
    } else {
      for (int i = 1; i < points.size(); i++) {
        Point startPoint = translatedPoint.add(points.get(i - 1));
        Point endPoint = translatedPoint.add(points.get(i));
        renderer.drawLine(startPoint, endPoint);
      }
    }
    renderer.endTransform();
  }

  /**
   * Returns the largest difference between two integers in the specified list
   *
   * @param list the specified list
   * @return the largest difference between two integers in the specified list
   */
  private Integer getMostDiff(List<? extends Integer> list) {
    int max = getExtreme(list, (a, b) -> a < b);
    int min = getExtreme(list, (a, b) -> a > b);

    return max - min;
  }

  /**
   * Comparing each element and returns element e which satisfies ∀a ∈ list : e ≺ a where ≺ is the
   * predicate function
   *
   * @param list      the list of elements to be compared
   * @param predicate the method witch will make the comparison
   * @return the element e which satisfies ∀a ∈ list : e ≺ a
   */
  private int getExtreme(List<? extends Integer> list,
      BiPredicate<? super Integer, ? super Integer> predicate) {
    Objects.requireNonNull(list);
    if (list.isEmpty()) {
      return 0;
    }

    int extreme = list.get(0);
    for (int i : list) {
      extreme = predicate.test(extreme, i) ? i : extreme;
    }
    return extreme;
  }

  @Override
  public void addPoint(Point p) {
    Objects.requireNonNull(p);
    points.add(p);
  }

  @Override
  public void removePoint(Point p, double threshold) {
    for (Point po : points) {
      if (po.distance(p) <= threshold) {
        points.remove(po);
      }
    }
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public void setColor(IColor color) {
    this.color = Objects.requireNonNull(color);
  }
}

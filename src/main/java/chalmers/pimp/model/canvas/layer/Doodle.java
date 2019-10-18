package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * A layer which has a list of points and draws straight lines between the points.
 */
final class Doodle implements ILayer {

  private final List<Point> points;
  private final LayerDelegate layerDelegate;
  private final IColor color;
  private final int lineWidth;

  /**
   * Creates a layer which has a list of points and draws straight lines between the points.
   *
   * @param lineWidth the width of the line between the points
   * @param color     the color of the lines
   * @throws NullPointerException if color is null
   */
  Doodle(int lineWidth, IColor color) {
    points = new ArrayList<>();
    layerDelegate = new LayerDelegate(LayerType.DOODLE);
    layerDelegate.setName("Doodle");
    this.lineWidth = lineWidth;
    this.color = Objects.requireNonNull(color);
    setRotationAnchorToCenter();
  }

  /**
   * Creates a copy of the specified doodle
   *
   * @param doodle the specified doodle to be copied
   * @throws NullPointerException if color is null
   */
  Doodle(Doodle doodle) throws NullPointerException {
    Objects.requireNonNull(doodle);
    this.layerDelegate = new LayerDelegate(doodle.layerDelegate);
    points = new ArrayList<>();
    color = doodle.color;
    lineWidth = doodle.lineWidth;

    doodle.points.forEach(points::add);
    setRotationAnchorToCenter();
  }

  @Override
  public void setPixel(IPixel pixel) {
    Point p = new Point(pixel.getX(), pixel.getY());
    points.add(p);
    setRotationAnchorToCenter();
  }

  @Override
  public void move(int dx, int dy) {
    layerDelegate.move(dx, dy);
    setRotationAnchorToCenter();
  }

  @Override
  public void setRotationAnchorToCenter() {
    Point temp = new Point(layerDelegate.getX() + (getWidth() / 2),
        layerDelegate.getY() + (getHeight() / 2));
    layerDelegate.setRotationAnchor(temp);
  }

  @Override
  public boolean isVisible() {
    return layerDelegate.isVisible();
  }

  @Override
  public void setVisible(boolean isVisible) {
    layerDelegate.setVisible(isVisible);
  }

  @Override
  public void setX(int x) {
    List<Integer> xVlaues = points.stream().map(Point::getX).collect(Collectors.toList());
    int min = getExtreme(xVlaues, (a, b) -> a > b);

    layerDelegate.setX(x - min);
  }

  @Override
  public void setY(int y) {
    List<Integer> yVlaues = points.stream().map(Point::getY).collect(Collectors.toList());
    int min = getExtreme(yVlaues, (a, b) -> a > b);

    layerDelegate.setY(y - min);
  }

  @Override
  public int getX() {
    List<Integer> xVlaues = points.stream().map(Point::getX).collect(Collectors.toList());
    int min = getExtreme(xVlaues, (a, b) -> a > b);
    return min + layerDelegate.getX();
  }

  @Override
  public int getY() {
    List<Integer> yVlaues = points.stream().map(Point::getY).collect(Collectors.toList());
    int min = getExtreme(yVlaues, (a, b) -> a > b);
    return min + layerDelegate.getY();
  }

  @Override
  public IReadOnlyPixelData getPixelData() {
    //TODO: convert the lines to PixelData
    return null;
  }

  @Override
  public LayerType getLayerType() {
    return layerDelegate.getLayerType();
  }

  @Override
  public double getRotation() {
    return layerDelegate.getRotationDegrees();
  }

  @Override
  public void setRotation(double rotation) {
    layerDelegate.setRotationDegrees(rotation);
  }

  @Override
  public double getAlpha() {
    return layerDelegate.getAlpha();
  }

  @Override
  public void setAlpha(double alpha) {
    layerDelegate.setAlpha(alpha);
  }

  @Override
  public int getWidth() {
    //Returns a list of the values returned from getX() for each item in points
    //.stream() returns a sequential stream considering collection as its source
    List<Integer> xValues = points.stream().map(Point::getX).collect(Collectors.toList());

    return getMostDiff(xValues) + lineWidth * 2;
  }

  @Override
  public int getHeight() {
    //Returns a list of the values returned from getY() for each item in points
    //.stream() returns a sequential stream considering collection as its source
    List<Integer> yValues = points.stream().map(Point::getY).collect(Collectors.toList());

    return getMostDiff(yValues) + lineWidth * 2;
  }

  @Override
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public void setName(String name) {
    layerDelegate.setName(name);
  }

  @Override
  public int getDepthIndex() {
    return layerDelegate.getDepthIndex();
  }

  @Override
  public void setDepthIndex(int depthIndex) {
    layerDelegate.setDepthIndex(depthIndex);
  }

  @Override
  public Point getRotationAnchor() {
    return layerDelegate.getRotationAnchor();
  }

  @Override
  public void setRotationAnchor(Point rotationAnchor) {
    layerDelegate.setRotationAnchorY(rotationAnchor.getY());
    layerDelegate.setRotationAnchorX(rotationAnchor.getX());
  }

  @Override
  public ILayer copy() {
    return new Doodle(this);
  }

  @Override
  public void draw(IRenderer renderer) {
    if (!isVisible() || points.isEmpty()) {
      return;
    }

    int offsX = layerDelegate.getX();
    int offSY = layerDelegate.getY();
    renderer
        .startTransform(layerDelegate.getRotationDegrees(),
            new Point(getX(), getY()),
            getWidth(),
            getHeight());
    renderer.setGlobalAlpha(color.getAlphaPercentage());
    renderer.setLineWidth(lineWidth);
    renderer.setFillColor(color);
    renderer.setBorderColor(color);

    if (points.size() == 1) {
      renderer.drawLine(points.get(0).addX(offsX).addY(offSY),
          points.get(0).addX(offsX).addY(offSY));
      return;
    }

    for (int i = 1; i < points.size(); i++) {
      renderer.drawLine(points.get(i).addX(offsX).addY(offSY),
          points.get(i - 1).addX(offsX).addY(offSY));
    }
    renderer.setGlobalAlpha(0);
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

    Integer extreme = list.get(0);
    for (Integer i : list) {
      extreme = predicate.test(extreme, i) ? i : extreme;
    }
    return extreme;
  }
}

package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.controller.components.ImageChooser;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A layer which has a list of points and draws straight lines between the points.
 */
final class Doodle implements ILayer {

  private final List<Point> points;
  private final LayerDelegate layerDelegate;
  private final IReadOnlyColor color;
  private final int lineWidth;

  /**
   * Creates a layer which has a list of points and draws straight lines between the points.
   *
   * @param lineWidth the width of the line between the points
   * @param color     the color of the lines
   * @throws NullPointerException if color is null
   */
  Doodle(int lineWidth, IReadOnlyColor color) {
    points = new ArrayList<>();
    layerDelegate = new LayerDelegate(LayerType.DOODLE);
    layerDelegate.setName("Doodle");
    this.lineWidth = lineWidth;
    this.color = Objects.requireNonNull(color);
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

    doodle.points.forEach(p -> points.add(p));
    setRotationAnchorToCenter();
  }

  @Override
  public void setPixel(IPixel pixel) {
    Point p = new Point(pixel.getX() - getX(), pixel.getY()- getY());
    points.add(p);
    setRotationAnchorToCenter();
  }

  @Override
  public void setVisible(boolean isVisible) {
    layerDelegate.setVisible(isVisible);
  }

  @Override
  public void move(int dx, int dy) {
    layerDelegate.move(dx, dy);
    setRotationAnchorToCenter();
  }

  @Override
  public void setX(int x) {
    layerDelegate.setX(x);
  }

  @Override
  public void setY(int y) {
    layerDelegate.setY(y);
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
  public void setRotationAnchor(Point rotationAnchor) {
    layerDelegate.setRotationAnchorY(rotationAnchor.getY());
    layerDelegate.setRotationAnchorX(rotationAnchor.getX());
  }

  @Override
  public void setRotationAnchorToCenter() {
    Point temp = new Point(layerDelegate.getX() + (getWidth() / 2),
        layerDelegate.getY() + (getHeight()/ 2));
    layerDelegate.setRotationAnchor(temp);
  }

  @Override
  public void setRotation(double rotation) {
    layerDelegate.setRotationDegrees(rotation);
  }

  @Override
  public void setAlpha(double alpha) {
    layerDelegate.setAlpha(alpha);
  }

  @Override
  public boolean isVisible() {
    return layerDelegate.isVisible();
  }

  @Override
  public int getX() {
    return layerDelegate.getX();
  }

  @Override
  public int getY() {
    return layerDelegate.getY();
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
  public double getAlpha() {
    return layerDelegate.getAlpha();
  }

  @Override
  public int getWidth() {
    List<Integer> list = new ArrayList<>();
    for (Point point : points) {
      Integer x = point.getX();
      list.add(x);
    }
    int xMin = getLowest(list);
    List<Integer> result = new ArrayList<>();
    for (Point point : points) {
      Integer x = point.getX();
      result.add(x);
    }
    int xMax = getHighest(result);

    return xMax - xMin + lineWidth*2;
  }

  @Override
  public int getHeight() {
    int yMin = getLowest(points.stream().map(Point::getY).collect(Collectors.toList()));
    int yMax = getHighest(points.stream().map(Point::getY).collect(Collectors.toList()));

    return yMax - yMin + lineWidth*2;
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
  public Point getRotationAnchor() {
    return layerDelegate.getRotationAnchor();
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
    renderer
        .startTransform(layerDelegate.getRotationDegrees(), layerDelegate.getStartPoint(), getWidth(),
            getHeight());
    renderer.setGlobalAlpha(color.getAlphaPercentage());
    renderer.setLineWidth(lineWidth);
    renderer.setFillColor(color);
    renderer.setBorderColor(color);

    if (points.size() == 1) {
      renderer.drawLine(points.get(0).addX(getX()).addY(getY()),
          points.get(0).addX(getX()).addY(getY()));
      return;
    }

    for (int i = 1; i < points.size(); i++) {
      renderer.drawLine(points.get(i).addX(getX()).addY(getY()),
          points.get(i - 1).addX(getX()).addY(getY()));
    }
    renderer.setGlobalAlpha(0);
    renderer.endTransform();
  }

  /**
   * Returns the lowest integer in a list of integers
   *
   * @param list the specified list of integers
   * @return the smallest integer
   */
  private int getHighest(List<Integer> list) {
    Objects.requireNonNull(list);
    if (list.isEmpty()) {
      return 0;
    }

    int highest = list.get(0);
    for (Integer i : list) {
      highest = (highest < i) ? i : highest;
    }
    return highest;
  }

  /**
   * Returns the lowest integer in a list of integers
   *
   * @param list the specified list of integers
   * @return the smallest integer
   */
  private int getLowest(List<Integer> list) {
    Objects.requireNonNull(list);
    if (list.isEmpty()) {
      return 0;
    }

    int lowest = list.get(0);
    for (Integer i : list) {
      lowest = (lowest > i) ? i : lowest;
    }
    return lowest;
  }
}

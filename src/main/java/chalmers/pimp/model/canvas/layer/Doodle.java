package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A layer which has a list of points and draws straight lines between the points.
 */
final class Doodle implements ILayer {

  private final List<Point> points;
  private final LayerDelegate layerDelegate;
  private final IReadOnlyColor color;
  private final int lineWidth;
  private int width;
  private int height;

  /**
   * @param lineWidth the width of the lines that are drawn.
   * @param color     the color of the doodle layer.
   * @throws NullPointerException if the supplied color is {@code null}.
   */
  Doodle(int lineWidth, IReadOnlyColor color) {
    this.lineWidth = lineWidth;
    this.color = Objects.requireNonNull(color);

    points = new ArrayList<>(16);
    layerDelegate = new LayerDelegate(LayerType.DOODLE);
    layerDelegate.setName("Doodle");
    width = 0;
    height = 0;
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
    width = doodle.width;
    height = doodle.height;
  }

  @Override
  public void setPixel(IPixel pixel) {
    points.add(new Point(pixel.getX(), pixel.getY()));
    if (pixel.getX() > width - lineWidth) {
      width = pixel.getX() + lineWidth;
    }
    if (pixel.getY() > height - lineWidth) {
      height = pixel.getY() + lineWidth;
    }
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
    return null; // FIXME remove
  }

  @Override
  public LayerType getLayerType() {
    return layerDelegate.getLayerType();
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
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
  public ILayer copy() {
    return new Doodle(this);
  }

  @Override
  public void draw(IRenderer renderer, IReadOnlyViewport viewport) {
    if (!isVisible() || points.isEmpty()) {
      return;
    }

    renderer.setLineWidth(lineWidth);
    renderer.setFillColor(color);
    renderer.setBorderColor(color);

    if (points.size() == 1) {
      Point point = points.get(0);
      point = point.addX(viewport.getRelativeX(getX()));
      point = point.addY(viewport.getRelativeY(getY()));
      renderer.drawLine(point, point); // basically renders a single point
      return;
    }

    for (int i = 1; i < points.size(); i++) {
      Point point1 = points.get(i);
      point1 = point1.addX(viewport.getRelativeX(getX()));
      point1 = point1.addY(viewport.getRelativeY(getY()));

      Point point2 = points.get(i - 1);
      point2 = point2.addX(viewport.getRelativeX(getX()));
      point2 = point2.addY(viewport.getRelativeY(getY()));

      renderer.drawLine(point1, point2);
    }
  }
}

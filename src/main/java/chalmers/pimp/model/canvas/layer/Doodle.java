package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
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
    width = 0;
    height = 0;
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
    //TODO: convert the lines to PixelData
    return null;
  }

  @Override
  public LayerType getLayerType() {
    return layerDelegate.getLayerType();
  }

  @Override
  public int getLineWidth1() {
    return lineWidth1;
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
  public void draw(IRenderer renderer) {
    if (!isVisible() || points.isEmpty()) {
      return;
    }

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
  }
}

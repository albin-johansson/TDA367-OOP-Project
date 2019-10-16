package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;

/**
 * The {@code Rectangle} class is an implementation of the {@code ILayer} interface that represents
 * a rectangle.
 */
final class Rectangle implements ILayer {

  private final LayerDelegate layerDelegate;
  private IColor color;
  private final int width;
  private final int height;

  /**
   * Creates a rectangle.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   */
  Rectangle(int x, int y, int width, int height) {
    layerDelegate = new LayerDelegate(LayerType.SHAPE);
    layerDelegate.setX(x);
    layerDelegate.setY(y);
    this.width = width;
    this.height = height;

    // OBVIOUSLY not a good solution. Just a fun easter egg :)
    layerDelegate.setName((width == height) ? "Square" : "Rectangle");

    color = ColorFactory.createColor(255, 137, 243); // FIXME
  }

  /**
   * Creates a copy of the supplied rectangle.
   *
   * @param rectangle the rectangle that will be copied.
   * @throws NullPointerException if the supplied rectangle is {@code null}.
   */
  private Rectangle(Rectangle rectangle) {
    Objects.requireNonNull(rectangle);
    layerDelegate = new LayerDelegate(rectangle.layerDelegate);
    width = rectangle.width;
    height = rectangle.height;
    color = ColorFactory.createColor(rectangle.color);
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
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public int getDepthIndex() {
    return layerDelegate.getDepthIndex();
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
  public IReadOnlyPixelData getPixelData() {
    PixelData pixelData = new PixelData(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixelData.setPixel(PixelFactory.createPixel(col, row, 1, 0, 1, 1));
      }
    }

    return pixelData;
  }

  @Override
  public void draw(IRenderer renderer) {
    if (isVisible()) {
      renderer.setFillColor(color);
      renderer.setBorderColor(color);
      renderer.fillRect(getX(), getY(), width, height);
    }
  }

  @Override
  public ILayer copy() {
    return new Rectangle(this);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Rectangle)) {
      return false;
    }
    if (object == this) {
      return true;
    }

    var rectangle = (Rectangle) object;

    return layerDelegate.equals(rectangle.layerDelegate)
        && (width == rectangle.width)
        && (height == rectangle.height)
        && color.equals(rectangle.color);
  }
}

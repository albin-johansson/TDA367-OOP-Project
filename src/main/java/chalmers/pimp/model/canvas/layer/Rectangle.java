package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.Objects;

/**
 * The {@code Rectangle} class is an implementation of the {@code ILayer} interface that represents
 * a rectangle.
 */
final class Rectangle implements ILayer, IColorable {

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
   * @param color  the color of the rectangle.
   * @throws NullPointerException if the provided color is null.
   */
  Rectangle(int x, int y, int width, int height, IColor color) {
    layerDelegate = new LayerDelegate(LayerType.SHAPE);
    layerDelegate.setX(x);
    layerDelegate.setY(y);
    this.width = width; // FIXME don't allow width/height to be < 0
    this.height = height;
    this.color = Objects.requireNonNull(color);

    // OBVIOUSLY not a good solution. Just a fun easter egg :)
    layerDelegate.setName((width == height) ? "Square" : "Rectangle");
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
    color = rectangle.color;
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
  public void setRotation(int rotation) {
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
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public int getDepthIndex() {
    return layerDelegate.getDepthIndex();
  }

  @Override
  public Point getCenterPoint() {
    return new Point(getX() + (width / 2), getY() + (height / 2));
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
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public IReadOnlyPixelData getPixelData() {
    // Ensures that the pixel data is at least 1x1
    int dx = (width < 1) ? 1 : 0;
    int dy = (height < 1) ? 1 : 0;

    var pixelData = new PixelData(width + dx, height + dy);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixelData.setPixel(PixelFactory.createPixel(col, row, color));
      }
    }

    return pixelData;
  }

  @Override
  public void draw(IRenderer renderer, IReadOnlyViewport viewport) {
    if (isVisible()) {
      renderer.startTransform(getRotation(), viewport.translate(getCenterPoint()));
      renderer.setGlobalAlpha(getAlpha());
      renderer.setFillColor(color);

      int drawX = viewport.getTranslatedX(getX());
      int drawY = viewport.getTranslatedY(getY());
      renderer.fillRect(drawX, drawY, width, height);

      renderer.endTransform();
    }
  }

  @Override
  public ILayer copy() {
    return new Rectangle(this);
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "X: " + getX() + ", Y: " + getY() + ", Width: " + width + ", Height: " + height;
    return "(" + id + " | " + state + ")";
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

  @Override
  public void setColor(IColor color) {
    this.color = Objects.requireNonNull(color);
  }

  @Override
  public IColor getColor() {
    return color;
  }
}

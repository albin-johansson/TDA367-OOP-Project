package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;

/**
 * The {@code Rectangle} class is an implementation of the {@code ILayer} interface that represents
 * a rectangle.
 */
final class Rectangle implements ILayer {

  private final LayerDelegate layerDelegate;
  private final int width;
  private final int height;
  private IColor color;

  /**
   * Creates a rectangle.
   *
   * @param x      the zero-indexed x coordinate of the rectangle.
   * @param y      the zero-indexed y coordinate of the rectangle.
   * @param width  the width of the rectangle.
   * @param height the height of the rectangle.
   */
  Rectangle(int x, int y, int width, int height) {
    layerDelegate = new LayerDelegate();
    layerDelegate.setX(x);
    layerDelegate.setY(y);
    this.width = width;
    this.height = height;
    color = ColorFactory.createColor(255, 137, 243); // FIXME
  }

  @Override
  public void setPixel(IPixel pixel) {
  }

  @Override
  public void setVisible(boolean isVisible) {
    layerDelegate.setVisible(isVisible);
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
  public LayerType getLayerType() {
    return LayerType.SHAPE;
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
    var copy = new Rectangle(getX(), getY(), width, height);
    copy.color = ColorFactory.createColor(color);
    copy.setVisible(isVisible());
    return copy;
  }
}

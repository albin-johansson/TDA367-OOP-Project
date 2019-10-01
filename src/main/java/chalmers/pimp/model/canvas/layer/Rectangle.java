package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import java.awt.Color;

/**
 * The {@code Rectangle} class is an implementation of the {@code ILayer} interface that represents
 * a rectangle.
 */
public class Rectangle implements ILayer {

  private static final LayerType layerType = LayerType.SHAPE;
  private final LayerDelegate layerDelegate;
  private int width;
  private int height;

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
  }

  @Override
  public void setPixel(int x, int y, Color color) {
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
  public IReadOnlyPixelData getPixelData() {
    PixelData pixelData = new PixelData(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixelData.setPixel(col, row, Color.MAGENTA);
      }
    }

    return pixelData;
  }

  @Override
  public LayerType getLayerType() {
    return layerType;
  }
}

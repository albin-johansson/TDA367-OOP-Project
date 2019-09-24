package model.canvas.layer;

import java.awt.Color;
import model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code Rectangle} class is an implementation of the {@code ILayer} interface that represents
 * a rectangle.
 */
public class Rectangle implements ILayer {
  
  private int width;
  private int height;
  private final LayerDelegate layerDelegate;
  private static final LayerType layerType = LayerType.SHAPE;

  /**
   * Creates a rectangle.
   *
   * @param width the width of the rectangle.
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
    return null;
  }

  @Override
  public LayerType getLayerType() {
    return layerType;
  }
}

package model.canvas.layer;

import java.awt.Color;
import model.pixeldata.IReadOnlyPixelData;
import model.pixeldata.PixelData;

/**
 * The {@code Raster} class is an implementation of the {@code ILayer} interface that represents a
 * layer that contains an arbitrary collection of pixels.
 */
final class Raster implements ILayer {

  private static final LayerType layerType = LayerType.RASTER;
  private final LayerDelegate layerDelegate;
  private final PixelData pixelData;

  /**
   * @param width  the width of the raster.
   * @param height the height of the raster.
   */
  Raster(int width, int height) {
    layerDelegate = new LayerDelegate();
    pixelData = new PixelData(width, height);
  }

  @Override
  public void setPixel(int x, int y, Color color) {
    pixelData.setPixel(x, y, color);
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
    return pixelData;
  }

  @Override
  public LayerType getLayerType() {
    return layerType;
  }
}
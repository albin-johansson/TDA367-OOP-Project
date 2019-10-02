package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelData;
import java.util.Objects;

/**
 * The {@code Raster} class is an implementation of the {@code ILayer} interface that represents a
 * layer that contains an arbitrary collection of pixels.
 */
final class Raster implements ILayer {

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

  /**
   * Creates a raster that is a copy of the supplied pixel data.
   *
   * @param pixelData the pixel data that will be copied.
   * @throws NullPointerException if the supplied pixel data is {@code null}.
   */
  Raster(PixelData pixelData) {
    this.pixelData = Objects.requireNonNull(pixelData);
    layerDelegate = new LayerDelegate();
  }

  @Override
  public void setPixel(IPixel pixel) {
    pixelData.setPixel(pixel);
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
    return LayerType.RASTER;
  }

  @Override
  public void draw(IRenderer renderer) {
    renderer.drawImage(pixelData, getX(), getY(), pixelData.getWidth(), pixelData.getHeight());
  }

  @Override
  public ILayer copy() {
    var copy = new Raster(pixelData.getWidth(), pixelData.getHeight());

    for (Iterable<? extends IReadOnlyPixel> pixelRow : pixelData.getPixels()) {
      for (IReadOnlyPixel pixel : pixelRow) {
        copy.setPixel(PixelFactory.createPixel(pixel));
      }
    }

    copy.setVisible(isVisible());
    return copy;
  }
}
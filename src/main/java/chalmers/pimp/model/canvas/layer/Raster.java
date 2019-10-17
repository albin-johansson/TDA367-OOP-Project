package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.pixeldata.IPixel;
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
    layerDelegate = new LayerDelegate(LayerType.RASTER);
    layerDelegate.setName("Raster");
    pixelData = new PixelData(width, height);
    setRotationAnchorToCenter();
  }

  /**
   * Creates a raster that is a copy of the supplied pixel data.
   *
   * @param pixelData the pixel data that will be copied.
   * @throws NullPointerException if the supplied pixel data is {@code null}.
   */
  Raster(PixelData pixelData) {
    this.pixelData = Objects.requireNonNull(pixelData);
    layerDelegate = new LayerDelegate(LayerType.RASTER);
    setRotationAnchorToCenter();
    layerDelegate.setName("Import");
  }

  /**
   * Creates a raster that is a copy of the supplied pixel data and sets the name of the raster.
   *
   * @param pixelData     the pixel data that will be copied.
   * @param pixelDataName the name of the new raster.
   * @throws NullPointerException if the supplied pixel data or pixel data name is {@code null}.
   */
  Raster(PixelData pixelData, String pixelDataName) {
    this.pixelData = Objects.requireNonNull(pixelData);
    layerDelegate = new LayerDelegate(LayerType.RASTER);
    layerDelegate.setName(Objects.requireNonNull(pixelDataName));
  }

  /**
   * Creates a copy of the supplied raster instance.
   *
   * @param raster the raster that will be copied.
   * @throws NullPointerException if the supplied raster is {@code null}.
   */
  private Raster(Raster raster) {
    Objects.requireNonNull(raster);
    pixelData = new PixelData(raster.pixelData);
    layerDelegate = new LayerDelegate(raster.layerDelegate);
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
    Point temp = new Point(layerDelegate.getX() + (pixelData.getWidth() / 2),
        layerDelegate.getY() + (pixelData.getHeight() / 2));
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
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public int getDepthIndex() {
    return layerDelegate.getDepthIndex();
  }

  @Override
  public Point getRotationAnchor() {
    return layerDelegate.getRotationAnchorPoint();
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
    return pixelData.getWidth();
  }

  @Override
  public int getHeight() {
    return pixelData.getHeight();
  }

  @Override
  public IReadOnlyPixelData getPixelData() {
    return pixelData;
  }

  @Override
  public void draw(IRenderer renderer) {
    if (isVisible()) {
      renderer.startTransform(layerDelegate.getRotationDegrees(), layerDelegate.getStartPoint(),
          pixelData.getWidth(), pixelData.getHeight());
      renderer.setGlobalAlpha(layerDelegate.getAlpha());
       renderer.drawImage(pixelData, getX(), getY());
      renderer.setGlobalAlpha(0);
      renderer.endTransform();     
    }
  }

  @Override
  public ILayer copy() {
    return new Raster(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerDelegate, pixelData);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Raster)) {
      return false;
    }
    if (object == this) {
      return true;
    }

    var raster = (Raster) object;

    return layerDelegate.equals(raster.layerDelegate) && pixelData.equals(raster.pixelData);
  }
}
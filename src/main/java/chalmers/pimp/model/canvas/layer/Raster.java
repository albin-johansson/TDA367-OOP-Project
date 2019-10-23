package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IRasterData;
import chalmers.pimp.model.pixeldata.IReadOnlyRasterData;
import chalmers.pimp.model.pixeldata.RasterDataFactory;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.Objects;

/**
 * The {@code Raster} class is an implementation of the {@code IRasterLayer} interface that
 * represents a layer that contains an arbitrary collection of pixels.
 */
final class Raster implements IRasterLayer {

  private final LayerDelegate layerDelegate;
  private final IRasterData rasterData;

  /**
   * @param width  the width of the raster.
   * @param height the height of the raster.
   */
  Raster(int width, int height) {
    layerDelegate = new LayerDelegate(LayerType.RASTER);
    layerDelegate.setName("Raster");
    rasterData = RasterDataFactory.createRasterData(width, height);
  }

  /**
   * Creates a raster that is a copy of the supplied pixel data.
   *
   * @param rasterData the raster data that will be copied.
   * @throws NullPointerException if the supplied raster data is {@code null}.
   */
  Raster(IRasterData rasterData) {
    this.rasterData = Objects.requireNonNull(rasterData);
    layerDelegate = new LayerDelegate(LayerType.RASTER);
    layerDelegate.setName("Import");
  }

  /**
   * Creates a raster that is a copy of the supplied raster data and sets the name of the raster.
   *
   * @param rasterData    the raster data that will be copied.
   * @param pixelDataName the name of the new raster.
   * @throws NullPointerException if any references are {@code null}.
   */
  Raster(IRasterData rasterData, String pixelDataName) {
    this.rasterData = Objects.requireNonNull(rasterData);
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
    rasterData = RasterDataFactory.createRasterData(raster.rasterData);
    layerDelegate = new LayerDelegate(raster.layerDelegate);
  }

  @Override
  public void setPixel(IPixel pixel) {
    rasterData.setPixel(pixel);
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
    return new Point(getX() + (getWidth() / 2), getY() + (getHeight() / 2));
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
    return rasterData.getWidth();
  }

  @Override
  public int getHeight() {
    return rasterData.getHeight();
  }
  
  public IReadOnlyRasterData getPixelData() {
    return rasterData;
  }

  @Override
  public void draw(IRenderer renderer, IReadOnlyViewport viewport) {
    if (isVisible()) {
      renderer.startTransform(getRotation(), viewport.translate(getCenterPoint()));
      renderer.setGlobalAlpha(getAlpha());

      int drawX = viewport.getTranslatedX(getX());
      int drawY = viewport.getTranslatedY(getY());
      renderer.drawImage(rasterData, drawX, drawY);

      renderer.endTransform();
    }
  }

  @Override
  public IRasterLayer copy() {
    return new Raster(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerDelegate, rasterData);
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

    return layerDelegate.equals(raster.layerDelegate) && rasterData.equals(raster.rasterData);
  }
}
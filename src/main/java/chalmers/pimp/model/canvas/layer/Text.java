package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.Objects;

/**
 * The {@code Text} class represents a text layer.
 */
final class Text implements ILayer {

  private final LayerDelegate layerDelegate;
  private IColor color;

  /**
   * @param color the color of the text.
   * @throws NullPointerException if the supplied color is {@code null}.
   */
  public Text(IColor color) {
    this.layerDelegate = new LayerDelegate(LayerType.TEXT);
    this.color = Objects.requireNonNull(color);
  }

  @Override
  public void setPixel(IPixel pixel) {

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
    return null;
  }

  @Override
  public String getName() {
    return layerDelegate.getName();
  }

  @Override
  public int getDepthIndex() {
    return 0;
  }

  @Override
  public LayerType getLayerType() {
    return null;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public ILayer copy() {
    return null;
  }

  @Override
  public void draw(IRenderer renderer) {
    
  }
}

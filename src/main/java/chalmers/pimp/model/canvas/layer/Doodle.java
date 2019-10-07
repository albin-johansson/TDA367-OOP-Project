package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * A layer which has a list of points and draws straight lines between the points.
 */
final class Doodle implements ILayer {

  private final List<IPixel> pixels;
  private final LayerDelegate layerDelegate;

  Doodle() {
    pixels = new ArrayList<>();
    layerDelegate = new LayerDelegate();
  }

  @Override
  public void setPixel(IPixel pixel) {
    pixels.add(pixel);
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
  public IReadOnlyPixelData getPixelData() {
    //TODO: convert the lines to PixelData
    return null;
  }

  @Override
  public LayerType getLayerType() {
    return LayerType.DOODLE;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public int getDepthIndex() {
    return 0;
  }

  @Override
  public ILayer copy() {
    Doodle copy = new Doodle();
    copy.layerDelegate.setX(layerDelegate.getX());
    copy.layerDelegate.setY(layerDelegate.getY());
    copy.layerDelegate.setVisible(layerDelegate.isVisible());

    for (IPixel i : pixels) {
      copy.pixels.add(PixelFactory.createPixel(i));
    }

    return copy;
  }

  @Override
  public void draw(IRenderer renderer) {
    if (pixels.size() == 0) {
      return;
    }

    if (pixels.size() == 1) {
      int x = pixels.get(0).getX() + getX();
      int y = pixels.get(0).getY() + getY();
      renderer.drawLine(x, y, x, y);
      return;
    }

    for (int i = 1; i < pixels.size(); i++) {
      renderer.drawLine(pixels.get(i).getX() + getX(), pixels.get(i).getY() + getY(),
          pixels.get(i - 1).getX() + getX(), pixels.get(i - 1).getY() + getY());
    }
  }
}

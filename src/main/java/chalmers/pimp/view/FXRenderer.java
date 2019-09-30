package chalmers.pimp.view;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;

public final class FXRenderer implements IRenderer {

  @Override
  public void drawRect(int x, int y, int width, int height) {

  }

  @Override
  public void fillRect(int x, int y, int width, int height) {

  }

  @Override
  public void drawEllipse(int x, int y, int radiusX, int radiusY) {

  }

  @Override
  public void fillEllipse(int x, int y, int radiusX, int radiusY) {
  }

  @Override
  public void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y, int width, int height) {

  }

  @Override
  public void drawText(String content, int fontSize, double rotation, IColor color) {

  }

  @Override
  public void setRotation(int rotation) {

  }

  @Override
  public void setFillColor(IColor color) {

  }

  @Override
  public void setBorderColor(IColor color) {

  }

  @Override
  public void setBorderWidth(int width) {

  }
}

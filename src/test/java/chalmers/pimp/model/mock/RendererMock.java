package chalmers.pimp.model.mock;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.ArrayList;
import java.util.List;

public class RendererMock implements IRenderer {

  public final List<Line> lines;

  public RendererMock(){
    lines = new ArrayList<>();
  }

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
  public void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y, int width,
      int height) {

  }

  @Override
  public void drawText(String content, int x, int y, int fontSize) {

  }

  @Override
  public void drawLine(Point p1, Point p2) {
    lines.add(new Line(p1, p2));
  }

  @Override
  public void setRotation(int rotation) {

  }

  @Override
  public void setFillColor(IReadOnlyColor color) {

  }

  @Override
  public void setBorderColor(IReadOnlyColor color) {

  }

  @Override
  public void setBorderWidth(int width) {

  }

  @Override
  public void setLineWidth(int width) {

  }

  @Override
  public int getCanvasWidth() {
    return 0;
  }

  @Override
  public int getCanvasHeight() {
    return 0;
  }
}
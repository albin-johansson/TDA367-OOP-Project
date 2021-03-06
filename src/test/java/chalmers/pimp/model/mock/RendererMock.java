package chalmers.pimp.model.mock;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyRasterData;
import java.util.ArrayList;
import java.util.List;

public class RendererMock implements IRenderer {

  public final List<Line> lines;

  public RendererMock() {
    lines = new ArrayList<>();
  }

  @Override
  public void clear() {

  }

  @Override
  public void drawRect(int x, int y, int width, int height) {

  }

  @Override
  public void fillRect(int x, int y, int width, int height) {

  }

  @Override
  public void drawImage(IReadOnlyRasterData readOnlyPixelData, int x, int y) {

  }

  @Override
  public void drawLine(Point p1, Point p2) {
    lines.add(new Line(p1, p2));
  }

  @Override
  public void startTransform(double rotation, Point centerPoint) {

  }

  @Override
  public void endTransform() {
  }

  @Override
  public void setGlobalAlpha(double alpha) {
  }

  @Override
  public void setFillColor(IColor color) {

  }

  @Override
  public void setBorderColor(IColor color) {

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
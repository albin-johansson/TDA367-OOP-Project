package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.*;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoodleTest {

  static class Line {

    private final int x1, y1, x2, y2;

    Line(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }

    Line(IPixel p1, IPixel p2) {
      this.x1 = p1.getX();
      this.y1 = p1.getY();
      this.x2 = p2.getX();
      this.y2 = p2.getY();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Line)) {
        return false;
      }

      Line l = (Line) o;

      return (this.x1 == l.x1 && this.y1 == l.y1 && this.x2 == l.x2 && this.y2 == l.y2) || (
          this.x1 == l.x2 && this.y1 == l.y2 && this.x2 == l.x1 && this.y2 == l.y1);
    }
  }

  static class RendererMock implements IRenderer {

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
    public void drawLine(int x1, int y1, int x2, int y2) {
      lines.add(new Line(x1, y1, x2, y2));
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

    @Override
    public int getCanvasWidth() {
      return 0;
    }

    @Override
    public int getCanvasHeight() {
      return 0;
    }
  }

  private ILayer doodle;
  private static List<Line> lines;

  @BeforeEach
  void init() {
    doodle = LayerFactory.createDoodle();
    lines = new ArrayList<>();
  }

  @Test
  void isVisible() {
    doodle.setVisible(true);
    assertEquals(doodle.isVisible(), true);
    doodle.setVisible(false);
    assertEquals(doodle.isVisible(), false);
  }

  @Test
  void setX() {
    doodle.setX(10);
    assertEquals(doodle.getX(), 10);
    doodle.setX(20);
    assertEquals(doodle.getX(), 20);
  }

  @Test
  void setY() {
    doodle.setY(10);
    assertEquals(doodle.getY(), 10);
    doodle.setY(20);
    assertEquals(doodle.getY(), 20);
  }

  @Test
  void getLayerType() {
    assertEquals(doodle.getLayerType(), LayerType.DOODLE);
  }

  @Test
  void draw1() {
    doodle.setY(10);
    doodle.setX(20);
    doodle.draw(new RendererMock());
    assertEquals(lines.size(), 0);
  }

  @Test
  void draw2() {
    IPixel p = PixelFactory.createPixel(10, 20);
    doodle.setPixel(p);
    doodle.draw(new RendererMock());
    assertEquals(lines.size(), 1);
    assertEquals(new Line(p, p), lines.get(0));
  }

  @Test
  void draw3() {
    IPixel p = PixelFactory.createPixel(10, 20);
    doodle.setPixel(p);
    doodle.setX(10);
    doodle.setY(20);
    doodle.draw(new RendererMock());
    assertEquals(lines.size(), 1);
    assertEquals(new Line(p.getX() + 10, p.getY() + 20, p.getX() + 10, p.getY() + 20),
        lines.get(0));
  }

  @Test
  void draw4() {
    List<IPixel> pixels = new ArrayList<>();
    int nPixels = 10;
    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    doodle.draw(new RendererMock());

    assertEquals(lines.size(), nPixels - 1);
    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), lines.get(i - 1));
    }
  }

  @Test
  void copy() {
    List<IPixel> pixels = new ArrayList<>();
    int nPixels = 10;
    ILayer doodle2;

    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    doodle2 = doodle.copy();
    doodle2.draw(new RendererMock());

    assertEquals(lines.size(), nPixels - 1);

    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), lines.get(i - 1));
    }
  }
}
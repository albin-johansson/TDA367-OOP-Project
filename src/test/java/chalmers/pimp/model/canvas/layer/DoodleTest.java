package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.mock.Line;
import chalmers.pimp.model.mock.RendererMock;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoodleTest {

  private ILayer doodle;

  @BeforeEach
  void init() {
    doodle = LayerFactory.createDoodle(10, ColorFactory.createColor(0,0,0));
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
    RendererMock renderer = new RendererMock();
    doodle.setY(10);
    doodle.setX(20);
    doodle.draw(renderer);
    assertEquals(renderer.lines.size(), 0);
  }

  @Test
  void draw2() {
    IPixel p = PixelFactory.createPixel(10, 20);
    RendererMock renderer = new RendererMock();
    doodle.setPixel(p);
    doodle.draw(renderer);
    assertEquals(renderer.lines.size(), 1);
    assertEquals(new Line(p, p), renderer.lines.get(0));
  }

  @Test
  void draw3() {
    IPixel p = PixelFactory.createPixel(10, 20);
    RendererMock renderer = new RendererMock();
    doodle.setPixel(p);
    doodle.setX(10);
    doodle.setY(20);
    doodle.draw(renderer);
    assertEquals(renderer.lines.size(), 1);
    assertEquals(new Line(p.getX() + 10, p.getY() + 20, p.getX() + 10, p.getY() + 20),
        renderer.lines.get(0));
  }

  @Test
  void draw4() {
    List<IPixel> pixels = new ArrayList<>();
    int nPixels = 10;
    RendererMock renderer = new RendererMock();
    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    doodle.draw(renderer);

    assertEquals(renderer.lines.size(), nPixels - 1);
    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), renderer.lines.get(i - 1));
    }
  }

  @Test
  void copy() {
    List<IPixel> pixels = new ArrayList<>();
    int nPixels = 10;
    ILayer doodle2;
    RendererMock renderer = new RendererMock();

    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    doodle2 = doodle.copy();
    doodle2.draw(renderer);

    assertEquals(renderer.lines.size(), nPixels - 1);

    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), renderer.lines.get(i - 1));
    }
  }
}
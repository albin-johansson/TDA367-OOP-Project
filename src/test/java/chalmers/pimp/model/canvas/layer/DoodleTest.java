package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.mock.Line;
import chalmers.pimp.model.mock.RendererMock;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import chalmers.pimp.model.viewport.ViewportFactory;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoodleTest {

  private IReadOnlyViewport viewport;
  private static final int lineWidth = 10;
  private ILayer doodle;

  @BeforeEach
  void init() {
    viewport = ViewportFactory.createViewport(0, 0, 10, 10);
    doodle = LayerFactory.createDoodle(lineWidth, ColorFactory.createColor(0, 0, 0));
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
    doodle.setPixel(PixelFactory.createPixel(10,10));
    doodle.setPixel(PixelFactory.createPixel(20,20));
    doodle.setPixel(PixelFactory.createPixel(30,5));

    doodle.setX(10);
    assertEquals(doodle.getX(), 10);
    doodle.setX(20);
    assertEquals(doodle.getX(), 20);
  }

  @Test
  void setY() {
    doodle.setPixel(PixelFactory.createPixel(10,10));
    doodle.setPixel(PixelFactory.createPixel(20,20));
    doodle.setPixel(PixelFactory.createPixel(30,5));

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
    var renderer = new RendererMock();

    doodle.setY(10);
    doodle.setX(20);
    doodle.draw(renderer, viewport);
    assertEquals(renderer.lines.size(), 0);
  }

  @Test
  void draw2() {
    IPixel pixel = PixelFactory.createPixel(10, 20);
    var renderer = new RendererMock();

    doodle.setPixel(pixel);
    doodle.draw(renderer, viewport);
    assertEquals(renderer.lines.size(), 1);
    assertEquals(new Line(pixel, pixel), renderer.lines.get(0));
  }

  @Test
  void draw3() {
//    IPixel pixel = PixelFactory.createPixel(10, 20);
//    doodle.setPixel(pixel);
//    doodle.setX(10);
//    doodle.setY(20);
//
//    var renderer = new RendererMock();
//    doodle.draw(renderer, viewport);
//    assertEquals(renderer.lines.size(), 1);
//
//    var line = new Line(pixel.getX() + 10, pixel.getY() + 20, pixel.getX() + 10, pixel.getY() + 20);
//    assertEquals(line, renderer.lines.get(0));
//    assertEquals(new Line(10, 20, 10, 20), renderer.lines.get(0));
  }

  @Test
  void draw4() {
    int nPixels = 10;
    var pixels = new ArrayList<IPixel>(nPixels);

    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    var renderer = new RendererMock();
    doodle.draw(renderer, viewport);

    assertEquals(renderer.lines.size(), nPixels - 1);
    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), renderer.lines.get(i - 1));
    }
  }

  @Test
  void copy() {
    int nPixels = 10;
    var pixels = new ArrayList<IPixel>(nPixels);

    for (int i = 0; i < nPixels; i++) {
      pixels.add(PixelFactory.createPixel(i * 10, i * 10 + 5));
    }

    for (IPixel p : pixels) {
      doodle.setPixel(p);
    }

    var renderer = new RendererMock();

    ILayer doodle2 = doodle.copy();
    doodle2.draw(renderer, viewport);

    assertEquals(renderer.lines.size(), nPixels - 1);

    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(pixels.get(i), pixels.get(i - 1)), renderer.lines.get(i - 1));
    }
  }

  @Test
  void getHeight() {
    assertEquals(doodle.getHeight(), lineWidth * 2);
    doodle.setPixel(PixelFactory.createPixel(10, 10));
    doodle.setPixel(PixelFactory.createPixel(10, 20));

    assertEquals(doodle.getHeight(), 10 + lineWidth * 2);
  }

  @Test
  void getWidth() {
    assertEquals(doodle.getWidth(), lineWidth * 2);
    doodle.setPixel(PixelFactory.createPixel(10, 10));
    doodle.setPixel(PixelFactory.createPixel(20, 10));

    assertEquals(doodle.getWidth(), 10 + lineWidth * 2);
  }
}
package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.mock.Line;
import chalmers.pimp.model.mock.RendererMock;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import chalmers.pimp.model.viewport.ViewportFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoodleTest {

  private IDoodleLayer doodle;
  private IReadOnlyViewport viewport;
  private static final int lineWidth = 10;

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
    doodle.addPoint(new Point(10, 10));
    doodle.addPoint(new Point(20, 20));
    doodle.addPoint(new Point(30, 5));

    doodle.setX(10);
    assertEquals(doodle.getX(), 10);
    doodle.setX(20);
    assertEquals(doodle.getX(), 20);
  }

  @Test
  void setY() {
    doodle.addPoint(new Point(10, 10));
    doodle.addPoint(new Point(20, 20));
    doodle.addPoint(new Point(30, 5));

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
    Point p = new Point(10, 20);
    RendererMock renderer = new RendererMock();

    doodle.addPoint(p);
    doodle.draw(renderer, viewport);
    assertEquals(renderer.lines.size(), 1);
    assertEquals(new Line(p, p), renderer.lines.get(0));
  }

  @Test
  void draw3() {
    Point p = new Point(10, 20);
    RendererMock renderer = new RendererMock();
    doodle.addPoint(p);
    doodle.setX(10);
    doodle.setY(20);
    doodle.draw(renderer, ViewportFactory.createViewport(0, 0, 100, 100));
    assertEquals(renderer.lines.size(), 1);
    assertEquals(new Line(p, p), renderer.lines.get(0));
  }

  @Test
  void draw4() {
    List<Point> points = new ArrayList<>();
    int nPixels = 10;

    for (int i = 0; i < nPixels; i++) {
      points.add(new Point(i * 10, i * 10 + 5));
    }

    for (Point p : points) {
      doodle.addPoint(p);
    }

    var renderer = new RendererMock();
    doodle.draw(renderer, viewport);

    assertEquals(renderer.lines.size(), nPixels - 1);
    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(points.get(i), points.get(i - 1)), renderer.lines.get(i - 1));
    }
  }

  @Test
  void cloneTest() {
    int nPixels = 10;
    var points = new ArrayList<Point>(nPixels);

    for (int i = 0; i < nPixels; i++) {
      points.add(new Point(i * 10, i * 10 + 5));
    }

    for (Point p : points) {
      doodle.addPoint(p);
    }

    var renderer = new RendererMock();

    ILayer doodle2 = doodle.clone();
    doodle2.draw(renderer, viewport);

    assertEquals(renderer.lines.size(), nPixels - 1);

    for (int i = 1; i < nPixels; i++) {
      assertEquals(new Line(points.get(i), points.get(i - 1)), renderer.lines.get(i - 1));
    }
  }

  @Test
  void getHeight() {
    assertEquals(doodle.getHeight(), lineWidth * 2);
    doodle.addPoint(new Point(10, 10));
    doodle.addPoint(new Point(10, 20));

    assertEquals(doodle.getHeight(), 10 + lineWidth * 2);
  }

  @Test
  void getWidth() {
    assertEquals(doodle.getWidth(), lineWidth * 2);
    doodle.addPoint(new Point(10, 10));
    doodle.addPoint(new Point(20, 10));

    assertEquals(doodle.getWidth(), 10 + lineWidth * 2);
  }
}
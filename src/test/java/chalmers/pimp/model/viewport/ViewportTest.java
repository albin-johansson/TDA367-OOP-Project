package chalmers.pimp.model.viewport;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewportTest {

  private Viewport viewport;

  @BeforeEach
  void setUp() {
    viewport = new Viewport();
  }

  @Test
  void copyCtor() {
    var copy = new Viewport(viewport);

    assertEquals(viewport.getX(), copy.getX());
    assertEquals(viewport.getY(), copy.getY());
    assertEquals(viewport.getWidth(), copy.getWidth());
    assertEquals(viewport.getHeight(), copy.getHeight());
  }

  @Test
  void center() {
    assertThrows(IllegalArgumentException.class, () -> viewport.center(0, 10));
    assertThrows(IllegalArgumentException.class, () -> viewport.center(10, 0));

    int width = 212;
    int height = 512;

    int areaWidth = 124;
    int areaHeight = 617;

    viewport.setWidth(width);
    viewport.setHeight(height);

    viewport.center(areaWidth, areaHeight);

    assertEquals(-(areaWidth - width) / 2, viewport.getX());
    assertEquals(-(areaHeight - height) / 2, viewport.getY());
  }

  @Test
  void move() {
    int x = 1124;
    int y = 8125;
    int dx = 124;
    int dy = -25;

    viewport.setX(x);
    viewport.setY(y);

    viewport.move(dx, dy);

    assertEquals((x + dx), viewport.getX());
    assertEquals((y + dy), viewport.getY());
  }

  @Test
  void setX() {
    assertEquals(0, viewport.getX());

    int x = -3193;
    viewport.setX(x);

    assertEquals(x, viewport.getX());
  }

  @Test
  void setY() {
    assertEquals(0, viewport.getY());

    int y = 2345;
    viewport.setY(y);

    assertEquals(y, viewport.getY());
  }

  @Test
  void setWidth() {
    assertDoesNotThrow(() -> viewport.setWidth(1));
    assertThrows(IllegalArgumentException.class, () -> viewport.setWidth(0));
  }

  @Test
  void setHeight() {
    assertDoesNotThrow(() -> viewport.setHeight(1));
    assertThrows(IllegalArgumentException.class, () -> viewport.setHeight(0));
  }

  @Test
  void getX() {
    assertEquals(0, viewport.getX());

    int x = 9124;
    viewport.setX(x);

    assertEquals(x, viewport.getX());
  }

  @Test
  void getY() {
    assertEquals(0, viewport.getY());

    int y = 3294;
    viewport.setY(y);

    assertEquals(y, viewport.getY());
  }

  @Test
  void getWidth() {
    assertEquals(100, viewport.getWidth());

    int width = 8132;
    viewport.setWidth(width);

    assertEquals(width, viewport.getWidth());
  }

  @Test
  void getHeight() {
    assertEquals(100, viewport.getHeight());

    int height = 92;
    viewport.setHeight(height);

    assertEquals(height, viewport.getHeight());
  }

  @Test
  void getRelativeX() {
    int x = 924;
    int viewportX = 124;
    int expected = x + viewportX;

    viewport.setX(viewportX);
    assertEquals(expected, viewport.getTranslatedX(x));
  }

  @Test
  void getRelativeY() {
    int y = -125;
    int viewportY = 516;
    int expected = y + viewportY;

    viewport.setY(viewportY);
    assertEquals(expected, viewport.getTranslatedY(y));
  }
}
package chalmers.pimp.model.viewport;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewportModelImplTest {

  private IViewportModel model;

  @BeforeEach
  void setUp() {
    model = new ViewportModelImpl();
  }

  @Test
  void center() {
    assertThrows(IllegalArgumentException.class, () -> model.center(0, 10));
    assertThrows(IllegalArgumentException.class, () -> model.center(10, 0));

    int width = 77;
    int height = 123;

    int areaWidth = 83;
    int areaHeight = 781;

    model.setWidth(width);
    model.setHeight(height);

    model.center(areaWidth, areaHeight);

    assertEquals(-(areaWidth - width) / 2, model.getX());
    assertEquals(-(areaHeight - height) / 2, model.getY());
  }

  @Test
  void moveViewport() {
    int x = 7583;
    int y = 985;
    int dx = -29854;
    int dy = 666;

    model.setX(x);
    model.setY(y);

    model.moveViewport(dx, dy);

    assertEquals((x + dx), model.getX());
    assertEquals((y + dy), model.getY());
  }

  @Test
  void setX() {
    assertEquals(0, model.getX());

    int x = -737;
    model.setX(x);

    assertEquals(x, model.getX());
  }

  @Test
  void setY() {
    assertEquals(0, model.getY());

    int y = 7192;
    model.setY(y);

    assertEquals(y, model.getY());
  }

  @Test
  void setWidth() {
    assertDoesNotThrow(() -> model.setWidth(1));
    assertThrows(IllegalArgumentException.class, () -> model.setWidth(0));
  }

  @Test
  void setHeight() {
    assertDoesNotThrow(() -> model.setHeight(1));
    assertThrows(IllegalArgumentException.class, () -> model.setHeight(0));
  }

  @Test
  void getX() {
    assertEquals(0, model.getX());

    int x = 83927;
    model.setX(x);

    assertEquals(x, model.getX());
  }

  @Test
  void getY() {
    assertEquals(0, model.getY());

    int y = 8662;
    model.setY(y);

    assertEquals(y, model.getY());
  }

  @Test
  void getWidth() {
    int width = 818;
    model.setWidth(width);

    assertEquals(width, model.getWidth());
  }

  @Test
  void getHeight() {
    int height = 92;
    model.setHeight(height);

    assertEquals(height, model.getHeight());
  }

  @Test
  void getViewport() {
    int x = 9125;
    int y = -1925;
    int width = 125;
    int height = 37;

    model.setX(x);
    model.setY(y);
    model.setWidth(width);
    model.setHeight(height);

    IReadOnlyViewport viewport = model.getViewport();
    assertEquals(x, viewport.getX());
    assertEquals(y, viewport.getY());
    assertEquals(width, viewport.getWidth());
    assertEquals(height, viewport.getHeight());
  }

  @Test
  void restore() {
    assertThrows(NullPointerException.class, () -> model.restore(null));
  }

  @Test
  void createSnapShot() {
    assertDoesNotThrow(() -> model.createSnapShot());
  }
}
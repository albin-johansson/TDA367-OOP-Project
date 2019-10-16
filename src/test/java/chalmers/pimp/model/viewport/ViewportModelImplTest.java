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
  }

  @Test
  void moveViewport() {
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
  }

  @Test
  void getY() {
  }

  @Test
  void getWidth() {
  }

  @Test
  void getHeight() {
  }

  @Test
  void getViewport() {
  }

  @Test
  void restore() {
  }

  @Test
  void createSnapShot() {
  }
}
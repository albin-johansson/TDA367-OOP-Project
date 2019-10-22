package chalmers.pimp.model.viewport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewportModelMementoTest {

  private Viewport viewport;
  private ViewportModelMemento memento;

  @BeforeEach
  void setUp() {
    viewport = new Viewport();
    memento = new ViewportModelMemento(viewport);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ViewportModelMemento(null));
  }

  @Test
  void getViewport() {
    assertEquals(viewport, memento.getViewport());
  }
}
package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelMementoTest {

  private ModelMemento memento;
  private Canvas canvas;

  @BeforeEach
  void setUp() {
    canvas = new Canvas();
    memento = new ModelMemento(canvas);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ModelMemento(null));
  }

  @Test
  void getCanvas() {
    assertEquals(canvas, memento.getCanvas());
  }
}
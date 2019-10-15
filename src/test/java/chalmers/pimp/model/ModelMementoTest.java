package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelMementoTest {

  private ModelMemento modelMemento;
  private CanvasMemento canvasMemento;

  @BeforeEach
  void setUp() {
    ICanvas canvas = CanvasFactory.createCanvas();
    canvasMemento = canvas.createSnapShot();
    modelMemento = new ModelMemento(canvasMemento, Colors.BLACK);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ModelMemento(null, null));
  }

  @Test
  void getCanvasMemento() {
    assertEquals(canvasMemento, modelMemento.getCanvasMemento());
  }
}
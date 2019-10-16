package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.colormodel.ColorModelFactory;
import chalmers.pimp.model.color.colormodel.ColorModelMemento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelMementoTest {

  private ModelMemento modelMemento;
  private CanvasMemento canvasMemento;
  private ColorModelMemento colorModelMemento;

  @BeforeEach
  void setUp() {
    ICanvas canvas = CanvasFactory.createCanvas();
    canvasMemento = canvas.createSnapShot();
    colorModelMemento = ColorModelFactory.createColorModel(Colors.BLACK).createSnapShot();
    modelMemento = new ModelMemento(canvasMemento, colorModelMemento);
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
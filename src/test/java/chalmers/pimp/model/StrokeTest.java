package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrokeTest {

  private ModelMemento memento;
  private Canvas canvas;
  private Stroke stroke;

  @BeforeEach
  void setUp() {
    canvas = new Canvas();
    memento = new ModelMemento(canvas);
    stroke = new Stroke(memento, 10);
  }

  @Test
  void updatePixels() {
    assertThrows(NullPointerException.class, () -> stroke.updatePixels(null, null));
    assertThrows(NullPointerException.class,
        () -> stroke.updatePixels(ModelFactory.createModel(), null));
  }

  @Test
  void add() {
    assertThrows(NullPointerException.class, () -> stroke.add(null));

    IPixel pixel = PixelFactory.createPixel(0, 0);
    stroke.add(pixel);

    assertEquals(pixel, stroke.getPixels().iterator().next());
  }

  @Test
  void getModelMemento() {
    assertEquals(memento, stroke.getModelMemento());
    assertEquals(canvas, stroke.getModelMemento().getCanvas());
  }

  @Test
  void getPixels() {
    assertNotNull(stroke.getPixels());
  }
}
package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrokeTest {

  private ModelMemento memento;
  private Stroke stroke;

  @BeforeEach
  void setUp() {
    ICanvas canvas = CanvasFactory.createCanvas();
    memento = new ModelMemento(canvas.createSnapShot(), Colors.BLACK);
    stroke = new Stroke(memento, 10, Colors.BLACK);
  }

  @Test
  void add() {
    assertThrows(NullPointerException.class, () -> stroke.add(null));

    IPixel pixel = PixelFactory.createPixel(0, 0);
    stroke.add(pixel);

    assertEquals(pixel, stroke.getPixels().iterator().next());
  }

  @Test
  void updatePixels() {
    assertThrows(NullPointerException.class, () -> stroke.updatePixels(null, null, null));
    assertThrows(NullPointerException.class,
        () -> stroke.updatePixels(CanvasFactory.createCanvas(), null, null));
  }

  @Test
  void getModelMemento() {
    assertEquals(memento, stroke.getModelMemento());
  }

  @Test
  void getPixels() {
    assertNotNull(stroke.getPixels());
  }
}
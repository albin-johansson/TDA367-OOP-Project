package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.Canvas;
import chalmers.pimp.model.canvas.layer.LayerFactory;
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

//    IPixel pixel = PixelFactory.createPixel(10, 20);
//    IModel model = ModelFactory.createModel();
//    model.addLayer(LayerFactory.createRasterLayer(100, 100));
//    model.selectLayer(0);
//
//    stroke.add(model, pixel);

//    assertEquals(pixel, stroke.getPixels().iterator().next());
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
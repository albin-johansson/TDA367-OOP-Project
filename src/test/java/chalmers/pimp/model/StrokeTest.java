package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.colormodel.ColorModelFactory;
import chalmers.pimp.model.color.colormodel.ColorModelMemento;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.viewport.IViewportModel;
import chalmers.pimp.model.viewport.ViewportModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrokeTest {

  private ModelMemento memento;
  private Stroke stroke;

  @BeforeEach
  void setUp() {
    ICanvas canvas = CanvasFactory.createCanvas();
    IViewportModel viewportModel = ViewportModelFactory.createViewportModel();
    ColorModelMemento colorModel = ColorModelFactory.createColorModel().createSnapShot();
    memento = new ModelMemento(canvas.createSnapShot(), viewportModel.createSnapShot(), colorModel);
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
    assertThrows(NullPointerException.class, () -> stroke.updatePixels(null, null));
    assertThrows(NullPointerException.class,
        () -> stroke.updatePixels(CanvasFactory.createCanvas(), null));
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
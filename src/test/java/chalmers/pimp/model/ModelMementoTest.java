package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.viewport.IViewportModel;
import chalmers.pimp.model.viewport.ViewportModelFactory;
import chalmers.pimp.model.viewport.ViewportModelMemento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelMementoTest {

  private ModelMemento modelMemento;
  private CanvasMemento canvasMemento;
  private ViewportModelMemento viewportModelMemento;

  @BeforeEach
  void setUp() {
    ICanvas canvas = CanvasFactory.createCanvas();
    IViewportModel viewportModel = ViewportModelFactory.createViewportModel();
    canvasMemento = canvas.createSnapShot();
    viewportModelMemento = viewportModel.createSnapShot();
    modelMemento = new ModelMemento(canvasMemento, viewportModelMemento);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ModelMemento(null, null));
  }

  @Test
  void getCanvasMemento() {
    assertEquals(canvasMemento, modelMemento.getCanvasMemento());
  }

  @Test
  void getViewportModelMemento() {
    assertEquals(viewportModelMemento, modelMemento.getViewportModelMemento());
  }
}
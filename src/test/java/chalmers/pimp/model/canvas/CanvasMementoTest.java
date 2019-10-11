package chalmers.pimp.model.canvas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasMementoTest {

  private CanvasMemento canvasMemento;
  private LayerManager layerManager;

  @BeforeEach
  private void setUp() {
    layerManager = new LayerManager();
    canvasMemento = new CanvasMemento(layerManager);
  }

  @Test
  void getLayerManager() {
    assertEquals(layerManager, canvasMemento.getLayerManager());
  }
}
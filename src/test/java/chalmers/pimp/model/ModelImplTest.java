package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.pixeldata.PixelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelImplTest {

  private IModel model;

  @BeforeEach
  private void setUp() {
    model = ModelFactory.createModel();
  }

  @Test
  void addLayer() {
    assertThrows(NullPointerException.class, () -> model.addLayer(null));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.addLayer(layer));
  }

  @Test
  void selectLayerByIndex() {
    assertDoesNotThrow(() -> model.selectLayer(0));

    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    assertDoesNotThrow(() -> model.selectLayer(0));
  }

  @Test
  void setPixel() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertThrows(NullPointerException.class, () -> model.setActiveLayerPixel(null));
  }

  @Test
  void setLayerNameByIndex() {
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);

    String temp = layer.getName();
    String nameToBe = "name";
    assertNotSame(temp, nameToBe);

    model.setLayerName(0, nameToBe);
    model.selectLayer(0);

    assertEquals(nameToBe, model.getActiveLayer().getName());

    final String temp2 = null;
    assertThrows(NullPointerException.class, () -> model.setLayerName(0, temp2));
    assertDoesNotThrow(() -> model.setLayerName(-1, temp));
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addCanvasUpdateListener(null));
    ICanvasUpdateListener listener = () -> {
    };
    model.addCanvasUpdateListener(listener);
    assertDoesNotThrow(() -> model.addCanvasUpdateListener(listener));
  }

  @Test
  void addLayerUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addLayerUpdateListener(null));
    ILayerUpdateListener listener = (e) -> {
    };
    model.addLayerUpdateListener(listener);
    assertDoesNotThrow(() -> model.addLayerUpdateListener(listener));
  }

  @Test
  void getLayers() {
    assertNotNull(model.getLayers());
  }

  @Test
  void getActiveLayer() {
    assertNull(model.getActiveLayer());
    ILayer layer = LayerFactory.createRasterLayer(10, 10);
    model.addLayer(layer);
    model.selectLayer(0);

    assertEquals(layer.getLayerType(), model.getActiveLayer().getLayerType());
    assertEquals(layer.getName(), model.getActiveLayer().getName());
    assertEquals(layer.getX(), model.getActiveLayer().getX());
    assertEquals(layer.getY(), model.getActiveLayer().getY());
    assertEquals(layer.getDepthIndex(), model.getActiveLayer().getDepthIndex());
  }

  @Test
  void setSelectedTool() {
    assertDoesNotThrow(() -> model.setSelectedTool(null));
  }

  @Test
  void moveSelectedLayer() {
    ILayer layer = LayerFactory.createRectangle(20, 15, 500, 70);
    model.addLayer(layer);

    model.selectLayer(0);

    assertEquals(20, model.getActiveLayer().getX());
    assertEquals(15, model.getActiveLayer().getY());

    model.moveActiveLayer(15, 10);

    assertEquals(35, model.getActiveLayer().getX());
    assertEquals(25, model.getActiveLayer().getY());
  }

  @Test
  void addUndoRedoListener() {
    assertThrows(NullPointerException.class, () -> model.addUndoRedoListener(null));
  }

  @Test
  void addModelSizeListener() {
    assertThrows(NullPointerException.class, () -> model.addModelSizeListener(null));
  }

  @Test
  void draw() {
    assertThrows(NullPointerException.class, () -> model.draw(null));
  }

  @Test
  void moveViewport() {
    int dx = 199;
    int dy = 795;

    model.moveViewport(dx, dy);

    // This works as the viewport should start at (0,0)
    assertEquals(dx, model.getViewport().getX());
    assertEquals(dy, model.getViewport().getY());
  }

  @Test
  void setViewportWidth() {
    int width = 472;
    model.setViewportWidth(width);

    assertEquals(width, model.getViewport().getWidth());
  }

  @Test
  void setViewportHeight() {
    int height = 8214;
    model.setViewportHeight(height);

    assertEquals(height, model.getViewport().getHeight());
  }

  @Test
  void getWidth() {
    assertDoesNotThrow(() -> model.getWidth());
  }

  @Test
  void getHeight() {
    assertDoesNotThrow(() -> model.getHeight());
  }

  @Test
  void getViewport() {
    assertNotNull(model.getViewport());
  }

  @Test
  void startMovingActiveLayer() {
    assertDoesNotThrow(() -> model.startMovingActiveLayer(0, 0));
  }

  @Test
  void updateMovingActiveLayer() {
    assertDoesNotThrow(() -> model.updateMovingActiveLayer(0, 0));
  }

  @Test
  void stopMovingActiveLayer() {
    assertDoesNotThrow(() -> model.stopMovingActiveLayer());
  }

  @Test
  void startStroke() {
    assertThrows(NullPointerException.class, () -> model.startStroke(null, 0, null));
    assertDoesNotThrow(() -> model.startStroke(PixelFactory.createPixel(0, 0), 1, Colors.BLACK));
  }

  @Test
  void updateStroke() {
    assertThrows(NullPointerException.class, () -> model.updateStroke(null));
    assertDoesNotThrow(() -> model.updateStroke(PixelFactory.createPixel(0, 0)));
  }

  @Test
  void endStroke() {
    assertThrows(NullPointerException.class, () -> model.endStroke(null));
    assertDoesNotThrow(() -> model.endStroke(PixelFactory.createPixel(0, 0)));
  }

  @Test
  void removeLayer() {
    assertDoesNotThrow(() -> model.removeLayer(0));
  }

  @Test
  void selectLayer() {
    assertDoesNotThrow(() -> model.selectLayer(0));
  }

  @Test
  void changeLayerDepthIndex() {
    assertDoesNotThrow(() -> model.changeLayerDepthIndex(24, 124));
  }

  @Test
  void setActiveLayerPixel() {
    // No active layer -> method has no effect
    assertDoesNotThrow(() -> model.setActiveLayerPixel(PixelFactory.createPixel(0, 0)));

    assertThrows(NullPointerException.class, () -> model.setActiveLayerPixel(null));
  }

  @Test
  void setLayerName() {
    assertDoesNotThrow(() -> model.setLayerName(-1, "Hello"));
  }

  @Test
  void setLayerVisibility() {
    assertDoesNotThrow(() -> model.setLayerVisibility(-1, true));
  }

  @Test
  void moveActiveLayer() {
    assertDoesNotThrow(() -> model.moveActiveLayer(0, 0));
  }

  @Test
  void startRotatingActiveLayer() {
    assertDoesNotThrow(() -> model.startRotatingActiveLayer(0, 0));
  }

  @Test
  void updateRotatingActiveLayer() {
    assertDoesNotThrow(() -> model.updateRotatingActiveLayer(0, 0));
  }

  @Test
  void stopRotatingActiveLayer() {
    assertDoesNotThrow(() -> model.stopRotatingActiveLayer());
  }

  @Test
  void rotateActiveLayer() {
    assertDoesNotThrow(() -> model.rotateActiveLayer(0));
  }

  @Test
  void selectedToolPressed() {
    assertThrows(NullPointerException.class, () -> model.selectedToolPressed(null));
  }

  @Test
  void selectedToolDragged() {
    assertThrows(NullPointerException.class, () -> model.selectedToolDragged(null));
  }

  @Test
  void selectedToolReleased() {
    assertThrows(NullPointerException.class, () -> model.selectedToolReleased(null));
  }

  @Test
  void setRenderer() {
    assertDoesNotThrow(() -> model.setRenderer(null));
  }

  @Test
  void isLayerVisible() {
    model.addLayer(LayerFactory.createRectangle(0, 0, 10, 10));
    assertTrue(model.isLayerVisible(0));
  }

  @Test
  void hasActiveLayer() {
    assertFalse(model.hasActiveLayer());

    model.addLayer(LayerFactory.createRectangle(0, 0, 10, 10));

    assertTrue(model.hasActiveLayer());
  }

  @Test
  void getLayerName() {
    assertNull(model.getActiveLayer());
  }

  @Test
  void getRenderer() {
    assertNull(model.getRenderer());
  }

  @Test
  void addColorChangeListener() {
    assertThrows(NullPointerException.class, () -> model.addColorChangeListener(null));
  }

  @Test
  void restore() {
    assertThrows(NullPointerException.class, () -> model.restore(null));
  }

  @Test
  void createSnapShot() {
    assertNotNull(model.createSnapShot());
  }

  @Test
  void undo() {
    assertDoesNotThrow(() -> model.undo());
  }

  @Test
  void redo() {
    assertDoesNotThrow(() -> model.redo());
  }

  @Test
  void setSelectedColor() {
    assertThrows(NullPointerException.class, () -> model.setSelectedColor(null));
  }

  @Test
  void getSelectedColor() {
    assertEquals(Colors.BLACK, model.getSelectedColor());
  }
}
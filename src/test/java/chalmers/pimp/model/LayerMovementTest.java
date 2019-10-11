package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerMovementTest {

  private LayerMovement layerMovement;
  private ModelMemento modelMemento;

  @BeforeEach
  void setUp() {
    layerMovement = new LayerMovement();

    IModel model = ModelFactory.createModel();
    modelMemento = model.createSnapShot();
  }

  @Test
  void start() {
    assertThrows(NullPointerException.class, () -> layerMovement.start(0, 0, null));
  }

  @Test
  void update() {
    // Values haven't been set yet, so expect default values
    assertEquals(0, layerMovement.getDx());
    assertEquals(0, layerMovement.getDy());

    int initialX = 12;
    int initialY = 912;
    layerMovement.start(initialX, initialY, modelMemento);

    int x = 8621;
    int y = 7712;

    layerMovement.update(x, y);
    assertEquals(x - initialX, layerMovement.getDx());
    assertEquals(y - initialY, layerMovement.getDy());
  }

  @Test
  void stop() {
    layerMovement.stop();

    // This should have no effect
    layerMovement.start(0, 0, modelMemento);
    layerMovement.update(12341, 7183);

    assertEquals(0, layerMovement.getDx());
    assertEquals(0, layerMovement.getDy());
  }

  @Test
  void getDx() {
    int initialX = 912841;
    layerMovement.start(initialX, 0, modelMemento);

    int x = 99124;

    layerMovement.update(x, 0);
    assertEquals(x - initialX, layerMovement.getDx());
  }

  @Test
  void getDy() {
    int initialY = -1294;
    layerMovement.start(0, initialY, modelMemento);

    int y = 71;

    layerMovement.update(0, y);
    assertEquals(y - initialY, layerMovement.getDy());
  }

  @Test
  void setEndX() {
    int endX = 1102;
    layerMovement.setEndX(endX);

    assertEquals(endX, layerMovement.getEndX());
  }

  @Test
  void setEndY() {
    int endY = 7382;
    layerMovement.setEndY(endY);

    assertEquals(endY, layerMovement.getEndY());
  }

  @Test
  void getModelMemento() {
    assertNull(layerMovement.getModelMemento());

    layerMovement.start(0, 0, modelMemento);
    assertEquals(modelMemento, layerMovement.getModelMemento());
  }

  @Test
  void getEndX() {
    int endX = 7382;
    layerMovement.setEndX(endX);

    assertEquals(endX, layerMovement.getEndX());
  }

  @Test
  void getEndY() {
    int endY = -912314;
    layerMovement.setEndY(endY);

    assertEquals(endY, layerMovement.getEndY());
  }
}
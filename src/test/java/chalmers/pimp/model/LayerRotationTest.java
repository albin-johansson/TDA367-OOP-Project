package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerRotationTest {

  private LayerRotation layerRotation;

  @BeforeEach
  void setUp() {
    layerRotation = new LayerRotation();
  }

  @Test
  void start() {
    Point tempPoint = new Point(143, 321);
    Point tempMousePoint = new Point(1, 1);
    IModel model = ModelFactory.createModel();
    ModelMemento tempMemento = model.createSnapShot();
    assertThrows(NullPointerException.class,
        () -> layerRotation.start(tempPoint, 0, tempMousePoint, null));
    assertThrows(NullPointerException.class,
        () -> layerRotation.start(tempPoint, 0, null, tempMemento));
    assertThrows(NullPointerException.class,
        () -> layerRotation.start(null, 0, tempMousePoint, tempMemento));
    assertDoesNotThrow(() -> layerRotation.start(tempPoint, 0, tempMousePoint, tempMemento));
  }

  @Test
  void update() {
    Point tempPoint = new Point(143, 321);
    IModel model = ModelFactory.createModel();
    ModelMemento tempMemento = model.createSnapShot();
    Point tempMousePoint = new Point(1, 1);

    //Should not change the currentDegree, since method starts haven't been called
    double tempDeg = layerRotation.getCurrentDegree();
    assertThrows(NullPointerException.class, () -> layerRotation.update(200, 200));
    assertEquals(tempDeg, layerRotation.getCurrentDegree());

    layerRotation.start(tempPoint, 0, tempMousePoint, tempMemento);

    //Should change the currentDegree
    layerRotation.update(200, 200);
    assertNotEquals(tempDeg, layerRotation.getCurrentDegree());
    tempDeg = layerRotation.getCurrentDegree();

    //Should not change the currentDegree after stop
    layerRotation.stop();
    layerRotation.update(32, 55);
    assertEquals(tempDeg, layerRotation.getCurrentDegree());
  }

  @Test
  void getModelMemento() {
    Point tempPoint = new Point(143, 321);
    Point tempMousePoint = new Point(1, 1);
    IModel model = ModelFactory.createModel();
    ModelMemento tempMemento = model.createSnapShot();
    assertThrows(NullPointerException.class,
        () -> Objects.requireNonNull(layerRotation.getModelMemento()));
    layerRotation.start(tempPoint, 0, tempMousePoint, tempMemento);
    assertEquals(tempMemento, layerRotation.getModelMemento());
  }

  @Test
  void getCurrentDegree() {
    Point tempPoint = new Point(143, 321);
    Point tempMousePoint = new Point(1, 1);
    IModel model = ModelFactory.createModel();
    ModelMemento tempMemento = model.createSnapShot();
    layerRotation.start(tempPoint, 0, tempMousePoint, tempMemento);
    layerRotation.update(43, 55);
    assertNotEquals(0, layerRotation.getCurrentDegree());
  }

  @Test
  void setCurrentDegree() {
    Point tempPoint = new Point(143, 321);
    Point tempMousePoint = new Point(1, 1);
    IModel model = ModelFactory.createModel();
    ModelMemento tempMemento = model.createSnapShot();
    assertEquals(0, layerRotation.getCurrentDegree());
    layerRotation.setCurrentDegree(40);
    assertEquals(40, layerRotation.getCurrentDegree());
  }
}
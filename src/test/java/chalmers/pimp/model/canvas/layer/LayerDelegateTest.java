package chalmers.pimp.model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerDelegateTest {

  private LayerDelegate delegate;
  private LayerType layerType;

  @BeforeEach
  private void setUp() {
    layerType = LayerType.SHAPE;
    delegate = new LayerDelegate(layerType);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new LayerDelegate((LayerType) null));
  }

  @Test
  void copyCtor() {
    assertThrows(NullPointerException.class, () -> new LayerDelegate((LayerDelegate) null));

    var copy = new LayerDelegate(delegate);

    assertEquals(delegate.getX(), copy.getX());
    assertEquals(delegate.getY(), copy.getY());
    assertEquals(delegate.getDepthIndex(), copy.getDepthIndex());
    assertEquals(delegate.getName(), copy.getName());
    assertEquals(delegate.isVisible(), copy.isVisible());
    assertEquals(delegate.getLayerType(), copy.getLayerType());
  }

  @Test
  void setX() {
    final int expectedX = 195;
    delegate.setX(expectedX);
    assertEquals(expectedX, delegate.getX());
  }

  @Test
  void setY() {
    final int expectedY = 883;
    delegate.setY(expectedY);
    assertEquals(expectedY, delegate.getY());
  }

  @Test
  void setVisible() {
    final boolean firstVisible = true;
    delegate.setVisible(firstVisible);
    assertEquals(firstVisible, delegate.isVisible());

    final boolean secondVisible = false;
    delegate.setVisible(secondVisible);
    assertEquals(secondVisible, delegate.isVisible());
  }

  @Test
  void isVisible() {
    assertEquals(LayerDelegate.DEFAULT_VISIBILITY_VALUE, delegate.isVisible());

    final boolean isVisible = false;
    delegate.setVisible(isVisible);

    assertEquals(isVisible, delegate.isVisible());
  }

  @Test
  void getX() {
    assertEquals(0, delegate.getX());

    final int expectedX = 9123;
    delegate.setX(expectedX);

    assertEquals(expectedX, delegate.getX());
  }

  @Test
  void getY() {
    assertEquals(0, delegate.getY());

    final int expectedY = -1235;
    delegate.setY(expectedY);

    assertEquals(expectedY, delegate.getY());
  }

  @Test
  void equalsTest() {
    assertNotEquals(null, delegate);
    assertEquals(delegate, delegate); // reflexive

    var copy = new LayerDelegate(delegate);
    var secondCopy = new LayerDelegate(copy);

    // Transitive
    assertEquals(copy, delegate);
    assertEquals(delegate, secondCopy);
    assertEquals(copy, secondCopy);

    // Symmetric
    assertEquals(delegate, copy);
    assertEquals(copy, delegate);
  }
}
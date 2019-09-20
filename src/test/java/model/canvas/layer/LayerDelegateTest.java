package model.canvas.layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayerDelegateTest {

  private LayerDelegate delegate;

  @BeforeEach
  private void setUp() {
    delegate = new LayerDelegate();
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
}
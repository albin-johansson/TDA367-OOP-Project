package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MouseStatusTest {


  @Test
  void getCoordinateTest() {
    MouseStatus mouseStatus = new MouseStatus(151, 231, 0);

    assertEquals(151, mouseStatus.getX());
    assertEquals(231, mouseStatus.getY());
  }

  @Test
  void getButtonTest() {
    MouseStatus mouseStatus = new MouseStatus(180, 150, 2);

    assertEquals(2, mouseStatus.getButton());
  }
}

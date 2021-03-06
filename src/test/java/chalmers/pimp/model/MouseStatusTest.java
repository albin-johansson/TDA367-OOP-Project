package chalmers.pimp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.MouseStatus.MouseButtonID;
import org.junit.jupiter.api.Test;

public class MouseStatusTest {

  @Test
  void getCoordinateTest() {
    MouseStatus mouseStatus = new MouseStatus(151, 231, MouseButtonID.NONE);

    assertEquals(151, mouseStatus.getX());
    assertEquals(231, mouseStatus.getY());
  }

  @Test
  void getButtonTest() {
    MouseStatus mouseStatus = new MouseStatus(180, 150, MouseButtonID.MIDDLE);

    assertEquals(MouseButtonID.MIDDLE, mouseStatus.getButton());
  }
}

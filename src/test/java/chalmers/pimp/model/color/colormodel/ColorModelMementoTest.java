package chalmers.pimp.model.color.colormodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColorModelMementoTest {

  private IColor color;
  private ColorModelMemento colorModelMemento;

  @BeforeEach
  void setUp() {
    color = Colors.WHITE;
    colorModelMemento = new ColorModelMemento(color);
  }

  @Test
  void ctor() {
    assertThrows(NullPointerException.class, () -> new ColorModelMemento(null));
  }

  @Test
  void getColor() {
    assertEquals(color, colorModelMemento.getColor());
  }
}
package view;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.ModelFactory;
import org.junit.jupiter.api.Test;

class ViewFactoryTest {

  @Test
  @SuppressWarnings("ResultOfMethodCallIgnored")
  void createView() {
    assertThrows(NullPointerException.class, () -> ViewFactory.createView(null));
    assertNotNull(ViewFactory.createView(ModelFactory.createModel()));
  }
}
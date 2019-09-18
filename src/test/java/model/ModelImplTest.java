package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.canvas.ICanvasUpdateListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelImplTest {

  private IModel model;

  @BeforeEach
  private void setUp() {
    model = ModelFactory.createModel();
  }

  @Test
  void addCanvasUpdateListener() {
    assertThrows(NullPointerException.class, () -> model.addCanvasUpdateListener(null));

    ICanvasUpdateListener listener = () -> {
    };

    model.addCanvasUpdateListener(listener);

    assertThrows(IllegalArgumentException.class, () -> model.addCanvasUpdateListener(listener));
  }
}
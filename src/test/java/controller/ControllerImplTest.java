package controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.IModel;
import model.ModelFactory;
import org.junit.jupiter.api.Test;
import view.IView;
import view.ViewFactory;

class ControllerImplTest {

  @Test
  void ctor() {
    IModel model = ModelFactory.createModel();
    IView view = ViewFactory.createView(model);

    // The constructor should check the parameters sequentially
    assertThrows(NullPointerException.class, () -> new ControllerImpl(null, null, null));
    assertThrows(NullPointerException.class, () -> new ControllerImpl(model, null, null));
    assertThrows(NullPointerException.class, () -> new ControllerImpl(model, view, null));
  }
}
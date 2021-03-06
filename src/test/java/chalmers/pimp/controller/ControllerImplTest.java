package chalmers.pimp.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import chalmers.pimp.view.IView;
import chalmers.pimp.view.ViewFactory;
import org.junit.jupiter.api.Test;

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
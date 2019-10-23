package chalmers.pimp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelFactory;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.MouseStatus.MouseButtonID;
import chalmers.pimp.model.mock.MouseEventMock;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;

class MouseStatusCreationServiceTest {

  @Test
  void createMouseStatus() {
    int x = 812;
    int y = 241;
    var event = new MouseEventMock(x, y, MouseButton.PRIMARY);

    IModel model = ModelFactory.createModel();
    MouseStatus status = MouseStatusCreationService.createMouseStatus(event, model);

    assertEquals(MouseButtonID.PRIMARY, status.getButton());
    assertEquals(x, status.getX());
    assertEquals(y, status.getY());
  }
}
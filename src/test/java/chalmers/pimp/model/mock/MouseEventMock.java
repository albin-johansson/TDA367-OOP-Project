package chalmers.pimp.model.mock;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public final class MouseEventMock extends MouseEvent {

  public MouseEventMock(int x, int y, MouseButton button) {
    super(null, x, y, 0, 0, button, 0, false, false, false, false, false, false,
        false, false, false, false, null);
  }
}

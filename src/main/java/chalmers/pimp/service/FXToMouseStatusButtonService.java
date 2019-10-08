package chalmers.pimp.service;

import chalmers.pimp.model.MouseStatus.MouseButtonID;
import java.util.Objects;
import javafx.scene.input.MouseButton;

/**
 * The {@code FXToMouseStatusButtonService} class is a service for converting the JavaFX MouseButton
 * enum to the models MouseStatus.MouseButtonID representation.
 */
public final class FXToMouseStatusButtonService {

  private FXToMouseStatusButtonService() {

  }

  /**
   * Converts the button pressed to an MouseButtonID representation.
   *
   * @param mouseButton the JavaFX enum value that describes the mouse button.
   * @return a MouseButtonID Enum representation of the supplied enum value.
   * @throws IllegalStateException if the supplied value isn't supported.
   * @throws NullPointerException  if the supplied value is {@code null}.
   */

  public static MouseButtonID getMouseButtonID(MouseButton mouseButton) {
    Objects.requireNonNull(mouseButton);
    switch (mouseButton) {
      case NONE:
        return MouseButtonID.NONE;
      case PRIMARY:
        return MouseButtonID.PRIMARY;
      case MIDDLE:
        return MouseButtonID.MIDDLE;
      case SECONDARY:
        return MouseButtonID.SECONDARY;
      default:
        throw new IllegalStateException("Invalid mouse button value: " + mouseButton);
    }
  }
}

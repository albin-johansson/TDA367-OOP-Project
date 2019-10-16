package chalmers.pimp.service;

import chalmers.pimp.model.MouseStatus.MouseButtonID;
import java.util.Objects;
import javafx.scene.input.MouseButton;

/**
 * The {@code MouseButtonConverterService} class is a service for converting values of the JavaFX
 * {@code MouseButton} enum to the corresponding {@code MouseButtonID} values.
 *
 * @see MouseButtonID
 */
final class MouseButtonConverterService {

  private MouseButtonConverterService() {
  }

  /**
   * Converts the button pressed to an MouseButtonID representation.
   *
   * @param mouseButton the JavaFX enum value that describes the mouse button.
   * @return a MouseButtonID Enum representation of the supplied enum value.
   * @throws IllegalStateException if the supplied value isn't supported.
   * @throws NullPointerException  if the supplied value is {@code null}.
   */
  static MouseButtonID getMouseButtonID(MouseButton mouseButton) {
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

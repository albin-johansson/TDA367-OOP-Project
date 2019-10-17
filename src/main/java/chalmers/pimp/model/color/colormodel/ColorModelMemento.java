package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.IColor;
import java.util.Objects;

/**
 * The {@code ColorModelMemento} class represents the state of a selected color.
 */
public final class ColorModelMemento {

  private final IColor color;

  /**
   * @param color the color state.
   * @throws NullPointerException if any reference are {@code null}.
   */
  ColorModelMemento(IColor color) {
    this.color = Objects.requireNonNull(color);
  }

  /**
   * Returns the color that the model was using when the memento was created.
   *
   * @return the color that the model was using when the memento was created.
   */
  IColor getColor() {
    return color;
  }
}

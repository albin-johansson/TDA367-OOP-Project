package chalmers.pimp.model;

import chalmers.pimp.model.canvas.Canvas;
import java.util.Objects;

/**
 * The {@code ModelMemento} class represents the state of the model at some point at time.
 *
 * @see IModel
 */
public final class ModelMemento {

  private final Canvas canvas;

  /**
   * Creates a {@code ModelMemento} instance. Note! All supplied references should refer to copies
   * of the actual data used by the model by the time this constructor is called. Otherwise,
   * unexpected behaviour might occur.
   *
   * @param canvas a copy of the canvas used by the model.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ModelMemento(Canvas canvas) {
    this.canvas = Objects.requireNonNull(canvas);
  }

  /**
   * Returns the saved canvas.
   *
   * @return the saved canvas.
   */
  Canvas getCanvas() {
    return canvas;
  }
}
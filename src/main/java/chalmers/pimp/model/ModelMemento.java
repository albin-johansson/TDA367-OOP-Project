package chalmers.pimp.model;

import chalmers.pimp.model.canvas.CanvasMemento;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IReadOnlyColor;
import java.util.Objects;

/**
 * The {@code ModelMemento} class represents the state of the model at some point at time.
 *
 * @see IModel
 */
public final class ModelMemento {

  private final CanvasMemento canvasMemento;
  private final IReadOnlyColor color;

  /**
   * @param canvasMemento a memento object for a canvas instance.
   * @param color         the selected color.
   * @throws NullPointerException if any references are {@code null}.
   */
  ModelMemento(CanvasMemento canvasMemento, IReadOnlyColor color) {
    this.canvasMemento = Objects.requireNonNull(canvasMemento);
    this.color = ColorFactory.createColor(
        Objects.requireNonNull(color)
    );
  }

  /**
   * Returns the canvas memento instance contained by this model memento instance.
   *
   * @return the canvas memento instance contained by this model memento instance.
   */
  CanvasMemento getCanvasMemento() {
    return canvasMemento;
  }

  IReadOnlyColor getColor() {
    return color;
  }
}
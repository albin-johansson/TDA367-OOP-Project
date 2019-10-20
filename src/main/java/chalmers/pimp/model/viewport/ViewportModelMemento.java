package chalmers.pimp.model.viewport;

import java.util.Objects;

/**
 * The {@code ViewportModelMemento} class represents the state of a {@code IViewportModel} instance
 * at some point in time.
 *
 * @see IViewportModel
 */
public final class ViewportModelMemento {

  private final Viewport viewport;

  /**
   * @param viewport a copy of the viewport that is used by the associated view port model.
   * @throws NullPointerException if any references are {@code null}.
   */
  ViewportModelMemento(Viewport viewport) {
    this.viewport = Objects.requireNonNull(viewport);
  }

  /**
   * Returns the viewport instance associated with the viewport model memento.
   *
   * @return the viewport instance associated with the viewport model memento.
   */
  Viewport getViewport() {
    return viewport;
  }
}
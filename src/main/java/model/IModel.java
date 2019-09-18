package model;

import model.canvas.ICanvasUpdateListener;

/**
 * The {@code IModel} interface specifies the facade for the main model component in the Pimp
 * application.
 */
public interface IModel {

  /**
   * Adds a canvas update listener to the model.
   *
   * @param listener the listener that will be added, may not be {@code null}.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IllegalArgumentException if the supplied listener has been added previously.
   */
  void addCanvasUpdateListener(ICanvasUpdateListener listener);

}
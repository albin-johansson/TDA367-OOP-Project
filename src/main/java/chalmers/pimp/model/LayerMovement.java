package chalmers.pimp.model;

import java.util.Objects;

/**
 * The {@code LayerMovement} class is useful when moving a layer instance. It's designed to be used
 * by the model <b>during</b> the time the user is moving a layer. After the user is done, an
 * instance of this class can be used to create a command that represents the movement.
 *
 * @see IModel
 */
public final class LayerMovement {

  private ModelMemento memento;
  private boolean isFinished;
  private int prevX;
  private int prevY;
  private int dx;
  private int dy;
  private int endX;
  private int endY;

  LayerMovement() {
    isFinished = false;
  }

  /**
   * Starts the layer movement. This method has no effect if the {@link LayerMovement#stop()} method
   * has been called on the layer movement instance.
   *
   * @param x       the current x-coordinate of the layer.
   * @param y       the current y-coordinate of the layer.
   * @param memento a model memento instance that represents the state of the model before the
   *                movement has begun.
   * @throws NullPointerException if the supplied model memento is {@code null}.
   */
  void start(int x, int y, ModelMemento memento) {
    Objects.requireNonNull(memento);
    if (!isFinished) {
      prevX = x;
      prevY = y;
      this.memento = Objects.requireNonNull(memento);
    }
  }

  /**
   * Updates the state of the layer movement. This method has no effect if the {@link
   * LayerMovement#stop()} method has been called on the layer movement instance.
   *
   * @param x the current x-coordinate of the layer.
   * @param y the current y-coordinate of the layer.
   */
  public void update(int x, int y) {
    if (!isFinished) {
      dx = x - prevX;
      dy = y - prevY;

      prevX = x;
      prevY = y;
    }
  }

  /**
   * Stops the layer movement. Invoking this method will render future calls to the {@link
   * LayerMovement#start(int, int, ModelMemento)} and {@link LayerMovement#update(int, int)} methods
   * useless.
   */
  void stop() {
    isFinished = true;
  }

  /**
   * Returns the current difference in x-axis movement since the last time the {@link
   * LayerMovement#update(int, int)} method was called.
   *
   * @return the current difference in x-axis movement since the last time the {@link
   * LayerMovement#update(int, int)} method was called.
   */
  int getDx() {
    return dx;
  }

  /**
   * Returns the current difference in y-axis movement since the last time the {@link
   * LayerMovement#update(int, int)} method was called.
   *
   * @return the current difference in y-axis movement since the last time the {@link
   * LayerMovement#update(int, int)} method was called.
   */
  int getDy() {
    return dy;
  }

  /**
   * Returns the model memento instance that represents the state of the model just before the layer
   * movement begun.
   *
   * @return the model memento instance that represents the state of the model just before the layer
   * movement begun.
   */
  public ModelMemento getModelMemento() {
    return memento;
  }

  /**
   * Returns the final x-coordinate of the layer, after movement seized.
   *
   * @return the final x-coordinate of the layer, after movement seized.
   */
  public int getEndX() {
    return endX;
  }

  /**
   * Sets the value of the property for the final x-coordinate of the layer.
   *
   * @param endX the value of the property for the final x-coordinate of the layer.
   */
  void setEndX(int endX) {
    this.endX = endX;
  }

  /**
   * Returns the final y-coordinate of the layer, after movement seized.
   *
   * @return the final y-coordinate of the layer, after movement seized.
   */
  public int getEndY() {
    return endY;
  }

  /**
   * Sets the value of the property for the final y-coordinate of the layer.
   *
   * @param endY the value of the property for the final y-coordinate of the layer.
   */
  void setEndY(int endY) {
    this.endY = endY;
  }
}
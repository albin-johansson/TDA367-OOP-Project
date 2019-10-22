package chalmers.pimp.model;

import static java.lang.Math.toDegrees;

import java.util.Objects;

/**
 * The {@code LayerRotation} class is useful when rotating a layer instance. It's designed to be
 * used by the model <b>during</b> the time the user is rotating a layer. After the user is done, an
 * instance of this class can be used to create a command that represents the rotation.
 *
 * @see IModel
 */
public final class LayerRotation {

  private ModelMemento memento;
  private boolean isFinished;
  private Point rotationAnchorPoint;
  private int mouseStartDegree;
  private int baseDegree;
  private int currentDegree;

  LayerRotation() {
    isFinished = false;
  }

  /**
   * Starts the layer rotation. This method has no effect if the {@link LayerMovement#stop()} method
   * has been called on the layer rotation instance.
   *
   * @param rotationAnchorPoint the center point for the layer to rotate.
   * @param baseDegree          the start rotation for the layer before the rotation has started.
   * @param mouseStartPoint     the start position of the mouse.
   * @param memento             a model memento instance that represents the state of the model
   *                            before the rotation has begun.
   * @throws NullPointerException if the supplied model memento is {@code null}.
   */
  void start(Point rotationAnchorPoint, int baseDegree, Point mouseStartPoint,
      ModelMemento memento) {
    Objects.requireNonNull(memento);
    Objects.requireNonNull(rotationAnchorPoint);
    Objects.requireNonNull(mouseStartPoint);
    if (!isFinished) {
      this.rotationAnchorPoint = rotationAnchorPoint;
      this.memento = Objects.requireNonNull(memento);
      //The new rotation is added to the old rotation of this layer, think of it as a mouse pad. Same movement can be repeated several times and add movement to the mouse position.
      this.baseDegree = baseDegree;
      currentDegree = baseDegree;
      //All rotation is based on the movement of the mouse, regardless of it's distance to the layer center.
      setMouseStartDegree(mouseStartPoint.getX(), mouseStartPoint.getY());
    }
  }

  /**
   * Updates the state of the layer rotation. This method has no effect if the {@link
   * LayerMovement#stop()} method has been called on the layer rotation instance.
   *
   * @param x the current x-coordinate of the mouse.
   * @param y the current y-coordinate of the mouse.
   */
  public void update(int x, int y) {
    if (isFinished) {
      return;
    }
    double dx = x - rotationAnchorPoint.getX();
    double dy = y - rotationAnchorPoint.getY();
    currentDegree = (int) toDegrees(Math.atan(dy / dx));
//    if (dx < 0) {
//      currentDegree = 180 + currentDegree;
//    }
    //Any rotation is based on the start position of the mouse.
    currentDegree = baseDegree + (currentDegree - mouseStartDegree);
  }

  /**
   * Sets the start mouse degree, since any change in the mouse movement is based on it's start
   * position.
   *
   * @param x the start x-coordinate for the mouse.
   * @param y the start y-coordinate for the mouse.
   */
  private void setMouseStartDegree(int x, int y) {
    double dx = x - rotationAnchorPoint.getX();
    double dy = y - rotationAnchorPoint.getY();
    mouseStartDegree = (int) toDegrees(Math.atan(dy / dx));
  }

  /**
   * Stops the layer rotation. Invoking this method will render future calls to the {@link
   * LayerMovement#start(int, int, ModelMemento)} and {@link LayerMovement#update(int, int)} methods
   * useless.
   */
  void stop() {
    isFinished = true;
  }

  /**
   * Returns the model memento instance that represents the state of the model just before the layer
   * rotation begun.
   *
   * @return the model memento instance that represents the state of the model just before the layer
   * rotation begun.
   */
  public ModelMemento getModelMemento() {
    return memento;
  }

  /**
   * Returns the current degree for this rotation.
   *
   * @return the current degree.
   */
  public int getCurrentDegree() {
    return currentDegree;
  }

  /**
   * Sets the current degree for this rotation.
   *
   * @param currentDegree the new current degree.
   */
  public void setCurrentDegree(int currentDegree) {
    this.currentDegree = currentDegree;
  }
}

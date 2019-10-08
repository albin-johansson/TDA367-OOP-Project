package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import java.util.Objects;

/**
 * A tool that moves the layer around by changing it's x and y coordinates
 */
final class MoveTool implements ITool {

  private final IModel model;

  /**
   * Creates a moveTool with no initial startX and startY values.
   *
   * @param model a reference to the model.
   * @throws NullPointerException if model is {@code null}.
   */
  MoveTool(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    model.startMovingLayer(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    model.updateMovingLayer(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    model.stopMovingLayer();
  }
}

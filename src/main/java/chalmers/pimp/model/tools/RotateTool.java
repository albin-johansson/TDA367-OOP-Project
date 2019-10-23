package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import java.util.Objects;

public class RotateTool implements ITool {

  private final IModel model;

  /**
   * Creates a rotateTool with no initial startX and startY values.
   *
   * @param model a reference to the model.
   * @throws NullPointerException if model is {@code null}.
   */
  RotateTool(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void pressed(MouseStatus mouseStatus) {
    model.startRotatingActiveLayer(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    model.updateRotatingActiveLayer(mouseStatus.getX(), mouseStatus.getY());
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    Objects.requireNonNull(mouseStatus); // required by specification
    model.stopRotatingActiveLayer();
  }
}

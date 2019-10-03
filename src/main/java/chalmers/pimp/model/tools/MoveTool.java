package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.command.CommandFactory;
import chalmers.pimp.model.command.ICommand;
import java.util.Objects;

/**
 * A tool that moves the layer around by changing it's x and y coordinates
 */
final class MoveTool implements ITool {

  private int prevX;
  private int prevY;
  private final IModel model;
  private ModelMemento startModelMemento;

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
    startModelMemento = model.createSnapShot();

    prevX = mouseStatus.getX();
    prevY = mouseStatus.getY();
  }

  @Override
  public void dragged(MouseStatus mouseStatus) {
    int dx = mouseStatus.getX() - prevX;
    int dy = mouseStatus.getY() - prevY;
    model.moveSelectedLayer(dx, dy);
    prevX = mouseStatus.getX();
    prevY = mouseStatus.getY();
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    int dx = mouseStatus.getX() - prevX;
    int dy = mouseStatus.getY() - prevY;
    model.moveSelectedLayer(dx, dy);

    // FIXME hard coded layer index
    ICommand command = CommandFactory.createMoveCommand(model, 0, dx, dy, startModelMemento);

    model.addCommand(command);
  }
}

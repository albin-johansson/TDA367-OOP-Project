package chalmers.pimp.model.tools;

import static chalmers.pimp.model.command.CommandFactory.createMoveCommand;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.MouseStatus;
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
    final int dx = mouseStatus.getX() - prevX;
    final int dy = mouseStatus.getY() - prevY;
    model.moveSelectedLayer(dx, dy);

    prevX = mouseStatus.getX();
    prevY = mouseStatus.getY();
  }

  @Override
  public void released(MouseStatus mouseStatus) {
    int layerIndex = model.getActiveLayer().getDepthIndex();
    int x = model.getActiveLayer().getX();
    int y = model.getActiveLayer().getY();

    ICommand cmd = createMoveCommand(model, layerIndex, x, y, startModelMemento);
    model.addCommand(cmd);
  }
}

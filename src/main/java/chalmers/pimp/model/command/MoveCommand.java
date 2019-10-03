package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import java.util.Objects;

final class MoveCommand implements ICommand {

  private final IModel model;
  private final int associatedLayerDepth;
  private final int dx;
  private final int dy;
  private ModelMemento modelMemento;

  MoveCommand(IModel model, int associatedLayerDepth, int dx, int dy, ModelMemento startMemento) {
    this.model = Objects.requireNonNull(model);
    this.associatedLayerDepth = associatedLayerDepth;
    this.dx = dx;
    this.dy = dy;
    modelMemento = startMemento;
  }

  @Override
  public void execute() {
    modelMemento = model.createSnapShot();

    model.selectLayer(associatedLayerDepth);
    model.moveSelectedLayer(dx, dy);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      model.restore(modelMemento);
    }
  }

  @Override
  public void setMemento(ModelMemento memento) {
    modelMemento = memento;
  }

  @Override
  public String getName() {
    return "Move Layer";
  }
}
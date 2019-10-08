package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.layer.ILayer;
import java.util.Objects;

final class AddLayerCommand implements ICommand {

  private final IMementoTarget<ModelMemento> mementoTarget;
  private final ICanvas canvas;
  private final ILayer layer;
  private ModelMemento modelMemento;

  AddLayerCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, ILayer layer) {
    this.canvas = Objects.requireNonNull(canvas);
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
    this.layer = Objects.requireNonNull(layer);
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();

    canvas.addLayer(layer);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Add Layer" /*+ layer.getName()*/; // TODO use the layer name
  }
}
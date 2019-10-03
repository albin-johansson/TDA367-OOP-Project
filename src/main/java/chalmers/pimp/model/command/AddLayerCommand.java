package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.layer.ILayer;

final class AddLayerCommand implements ICommand {

  private final IModel model;
  private final ILayer layer;
  private ModelMemento modelMemento;

  AddLayerCommand(IModel model, ILayer layer) {
    this.model = model;
    this.layer = layer;
  }

  @Override
  public void execute() {
    modelMemento = model.createSnapShot();

    model.getCanvas().addLayer(layer);
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      model.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Add Layer";
  }
}

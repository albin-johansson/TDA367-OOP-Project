package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.layer.ILayer;
import java.util.Objects;

/**
 * The {@code AddLayerCommand} class is a subclass of {@code AbstractCommand} that represents the
 * action of adding a layer.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class AddLayerCommand extends AbstractCommand {

  private final ILayer layer;

  /**
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param layer         the layer that will be added.
   * @throws NullPointerException if any references are {@code null}.
   */
  AddLayerCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, ILayer layer) {
    super(canvas, mementoTarget);
    this.layer = Objects.requireNonNull(layer);
  }

  @Override
  public void execute() {
    updateModelMemento();
    getCanvas().addLayer(layer.copy());
  }

  @Override
  public String getName() {
    return "Add Layer";
  }
}
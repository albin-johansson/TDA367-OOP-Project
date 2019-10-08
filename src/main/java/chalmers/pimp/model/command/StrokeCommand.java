package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.pixeldata.IPixel;
import java.util.Objects;

/**
 * The {@code StrokeCommand} class represents the action of a mouse stroke, which affects a varying
 * amount of pixels.
 */
final class StrokeCommand implements ICommand {

  private final ICanvas layerModel;
  private final IMementoTarget<ModelMemento> mementoTarget;
  private final Stroke stroke;
  private ModelMemento memento;

  /**
   * @param layerModel the associated model instance.
   * @param stroke     the associated stroke instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  StrokeCommand(ICanvas layerModel, IMementoTarget<ModelMemento> mementoTarget, Stroke stroke) {
    this.layerModel = Objects.requireNonNull(layerModel);
    this.mementoTarget = mementoTarget;
    this.stroke = Objects.requireNonNull(stroke);
    memento = stroke.getModelMemento();
  }

  @Override
  public void execute() {
    memento = mementoTarget.createSnapShot();

    for (IPixel pixel : stroke.getPixels()) {
      stroke.updatePixels(layerModel, pixel);
    }
  }

  @Override
  public void revert() {
    if (memento != null) {
      mementoTarget.restore(memento);
    }
  }

  @Override
  public String getName() {
    return "Stroke";
  }
}
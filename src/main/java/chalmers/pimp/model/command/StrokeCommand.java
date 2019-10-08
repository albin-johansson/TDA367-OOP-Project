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

  private final ICanvas canvas;
  private final IMementoTarget<ModelMemento> mementoTarget;
  private final Stroke stroke;
  private ModelMemento modelMemento;

  /**
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param stroke        the stroke instance that describes the stroke.
   * @throws NullPointerException if any references are {@code null}.
   */
  StrokeCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, Stroke stroke) {
    this.canvas = Objects.requireNonNull(canvas);
    this.mementoTarget = Objects.requireNonNull(mementoTarget);
    this.stroke = Objects.requireNonNull(stroke);

    modelMemento = stroke.getModelMemento();
  }

  @Override
  public void execute() {
    modelMemento = mementoTarget.createSnapShot();

    for (IPixel pixel : stroke.getPixels()) {
      stroke.updatePixels(canvas, pixel);
    }
  }

  @Override
  public void revert() {
    if (modelMemento != null) {
      mementoTarget.restore(modelMemento);
    }
  }

  @Override
  public String getName() {
    return "Stroke";
  }
}
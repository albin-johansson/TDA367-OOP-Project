package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.pixeldata.IPixel;
import java.util.Objects;

/**
 * The {@code StrokeCommand} class is a subclass of {@code AbstractCommand} that represents the
 * action of performing a stroke with a pixel-based "pen" tool.
 *
 * @see AbstractCommand
 * @see ICommand
 */
final class StrokeCommand extends AbstractCommand {

  private final Stroke stroke;

  /**
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param stroke        the stroke instance that describes the stroke.
   * @throws NullPointerException if any references are {@code null}.
   */
  StrokeCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget, Stroke stroke) {
    super(canvas, mementoTarget);
    this.stroke = Objects.requireNonNull(stroke);

    setModelMemento(stroke.getModelMemento());
  }

  @Override
  public void execute() {
    updateModelMemento();

    for (IPixel pixel : stroke.getPixels()) {
      stroke.updatePixels(getCanvas(), pixel, stroke.getColor());
    }
  }

  @Override
  public String getName() {
    return "Stroke";
  }
}
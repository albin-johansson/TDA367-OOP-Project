package chalmers.pimp.model.command;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.pixeldata.IPixel;
import java.util.Objects;

/**
 * The {@code StrokeCommand} class represents the action of a mouse stroke, which affects a varying
 * amount of pixels.
 */
final class StrokeCommand implements ICommand {

  private final IModel model;
  private final Stroke stroke;
  private ModelMemento memento;

  /**
   * @param model  the associated model instance.
   * @param stroke the associated stroke instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  StrokeCommand(IModel model, Stroke stroke) {
    this.model = Objects.requireNonNull(model);
    this.stroke = Objects.requireNonNull(stroke);
    memento = stroke.getModelMemento();
  }

  @Override
  public void execute() {
    memento = model.createSnapShot();

    for (IPixel pixel : stroke.getPixels()) {
      stroke.updatePixels(model, pixel);
    }
  }

  @Override
  public void revert() {
    if (memento != null) {
      model.restore(memento);
    }
  }

  @Override
  public String getName() {
    return "Stroke";
  }
}
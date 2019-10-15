package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IReadOnlyColor;

public class ChangeColorCommand extends AbstractCommand {

  private final IReadOnlyColor color;

  /**
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param color         the mew color.
   * @throws NullPointerException if any references are {@code null}.
   */
  ChangeColorCommand(ICanvas canvas, IMementoTarget<ModelMemento> mementoTarget,
      IReadOnlyColor color) {
    super(canvas, mementoTarget);
    this.color = ColorFactory.createColor(color);
  }

  @Override
  public void execute() {
    updateModelMemento();
  }

  @Override
  public String getName() {
    return "Change color";
  }
}

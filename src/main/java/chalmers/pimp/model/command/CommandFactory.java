package chalmers.pimp.model.command;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.LayerMovement;
import chalmers.pimp.model.LayerRotation;
import chalmers.pimp.model.ModelMemento;
import chalmers.pimp.model.Stroke;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorModel;

/**
 * The {@code CommandFactory} class is a factory for creating instances of the {@link ICommand}
 * interface.
 *
 * @see ICommand
 */
public final class CommandFactory {

  private CommandFactory() {
  }

  /**
   * Creates and returns a command that represents the action of performing a pixel stroke.
   *
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param stroke        the stroke instance that describes the stroke.
   * @return a command that represents the action of performing a pixel stroke.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createStrokeCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, Stroke stroke) {
    return new StrokeCommand(canvas, mementoTarget, stroke);
  }

  /**
   * Creates and returns a command that represents the action of moving a layer.
   *
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the affected layer.
   * @param movement        the layer movement instance that describes the movement.
   * @return a command that represents the action of moving a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createMoveCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex, LayerMovement movement) {
    return new MoveCommand(canvas, mementoTarget, layerDepthIndex, movement);
  }

  /**
   * Creates and returns a command that represents the action of rotating a layer.
   *
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the affected layer.
   * @param rotate          the layer rotate instance that describes the rotation.
   * @return a command that represents the action of rotating a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createRotateCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex, LayerRotation rotate) {
    return new RotateCommand(canvas, mementoTarget, layerDepthIndex, rotate);
  }

  /**
   * Creates and returns a command that represents the action of adding a layer.
   *
   * @param canvas        the associated canvas instance.
   * @param mementoTarget the memento target that will be used.
   * @param layer         the layer that will be added.
   * @return a command that represents the action of adding a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createAddLayerCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, ILayer layer) {
    return new AddLayerCommand(canvas, mementoTarget, layer);
  }

  /**
   * Creates and returns a command that represents the action of removing a layer.
   *
   * @param canvas          the associated canvas instance.
   * @param mementoTarget   the memento target that will be used.
   * @param layerDepthIndex the layer depth index of the layer that will be removed.
   * @return a command that represents the action of removing a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createRemoveLayerCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int layerDepthIndex) {
    return new RemoveLayerCommand(canvas, mementoTarget, layerDepthIndex);
  }

  /**
   * Creates and returns a command that represents the action of selecting a command.
   *
   * @param canvas           the associated canvas instance.
   * @param mementoTarget    the memento target that will be used.
   * @param targetLayerIndex the layer depth index of the layer that will be selected.
   * @return a command that represents the action of selecting a command.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createLayerSelectionCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int targetLayerIndex) {
    return new LayerSelectionCommand(canvas, mementoTarget, targetLayerIndex);
  }

  /**
   * Creates and returns a command that represents the action of changing the z-value of a layer.
   *
   * @param canvas         the associated canvas instance.
   * @param mementoTarget  the memento target that will be used.
   * @param baseDepthIndex the layer depth index of the layer that will be "moved".
   * @param dz             the delta z value (the offset), may be either negative or positive.
   * @return a command that represents the action of changing the z-value of a layer.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createChangeLayerDepthCommand(ICanvas canvas,
      IMementoTarget<ModelMemento> mementoTarget, int baseDepthIndex, int dz) {
    return new ChangeLayerDepthCommand(canvas, mementoTarget, baseDepthIndex, dz);
  }

  /**
   * Creates and returns a command that represents the action of changing the color.
   *
   * @param mementoTarget the memento target that will be used.
   * @param colorModel    the color model.
   * @param color         the new color.
   * @return a command that represents the action of changing the color.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static ICommand createChangeColorCommand(
      IColorModel colorModel,
      IMementoTarget<ModelMemento> mementoTarget,
      IColor color
  ) {
    return new ChangeColorCommand(colorModel, mementoTarget, color);
  }
}
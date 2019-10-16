package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colorchangeobserver.IColorChangeObservable;

/**
 * The {@code IColorModel} holds a color that the model uses.
 */
public interface IColorModel extends IMementoTarget<ColorModelMemento>, IColorChangeObservable {

  /**
   * Returns the color.
   *
   * @return the color.
   */
  IColor getColor();

  /**
   * Sets the color.
   *
   * @param color new color.
   */
  void setColor(IColor color);
}

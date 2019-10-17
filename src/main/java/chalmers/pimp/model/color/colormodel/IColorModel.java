package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.IMementoTarget;
import chalmers.pimp.model.color.IColor;

/**
 * The {@code IColorModel} interface specifies objects that represent the color model (information
 * about the selected color). The {@code IColorModel} interface extends {@code
 * IColorChangeObservable} interface and is thus observable and can notify its listeners when its
 * color has been modified.
 *
 * @see IColorChangeListener
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
   * @throws NullPointerException if the provided color is {@code null}.
   */
  void setColor(IColor color);
}

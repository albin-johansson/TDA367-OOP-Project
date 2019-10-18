package chalmers.pimp.model.canvas.layer;

import chalmers.pimp.model.color.IColor;

/**
 * The {@code IColorable} interface specifies something that is colorable.
 */
public interface IColorable {

  /**
   * The new color.
   *
   * @param color the new color.
   * @throws NullPointerException if the provided color is {@code null}.
   */
  void setColor(IColor color);

  /**
   * Returns the selected color.
   *
   * @return the selected color.
   */
  IColor getColor();
}

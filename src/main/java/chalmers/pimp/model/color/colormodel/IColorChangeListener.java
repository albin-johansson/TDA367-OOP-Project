package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.IColor;

/**
 * The {@code IColorChangeObserver} class represents something that listens for color changes.
 *
 * @see IColorChangeObservable
 */
public interface IColorChangeListener {

  /**
   * Notifies that there has been a color change.
   *
   * @param color the new color.
   */
  void colorChanged(IColor color);
}

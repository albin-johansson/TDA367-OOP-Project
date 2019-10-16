package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colorchangeobserver.IColorChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code ColorModelImpl} implements the IColorModel interface.
 *
 * @see IColorModel
 */
public class ColorModelImpl implements IColorModel {

  private IColor color;
  private final List<IColorChangeListener> colorChangeListeners;

  /**
   * @param color the color that the color model implementation should contain.
   * @throws NullPointerException if the supplied color is null.
   */
  ColorModelImpl(IColor color) {
    this.color = Objects.requireNonNull(color);
    colorChangeListeners = new ArrayList<>();
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public void setColor(IColor color) {
    this.color = color;
    notifyAllColorChangeListeners();
  }

  @Override
  public void restore(ColorModelMemento memento) {
    color = memento.getColor();
    notifyAllColorChangeListeners();
  }

  @Override
  public ColorModelMemento createSnapShot() {
    return new ColorModelMemento(color);
  }

  @Override
  public void addColorChangeListener(IColorChangeListener observer) {
    colorChangeListeners.add(Objects.requireNonNull(observer));
  }

  @Override
  public void notifyAllColorChangeListeners() {
    for (IColorChangeListener colorChangeListener : colorChangeListeners) {
      colorChangeListener.colorChanged(color);
    }
  }
}

package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;
import java.util.Objects;

/**
 * The {@code ColorModelImpl} class is an implementation of the {@code IColorModel} interface.
 *
 * @see IColorModel
 */
final class ColorModelImpl implements IColorModel {

  private final ColorChangeListenerComposite colorChangeListeners;
  private IColor color;

  ColorModelImpl() {
    colorChangeListeners = new ColorChangeListenerComposite();
    color = Colors.BLACK;
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public void setColor(IColor color) {
    this.color = Objects.requireNonNull(color);
    notifyAllColorChangeListeners();
  }

  @Override
  public void restore(ColorModelMemento memento) {
    Objects.requireNonNull(memento);
    color = memento.getColor();

    notifyAllColorChangeListeners();
  }

  @Override
  public ColorModelMemento createSnapShot() {
    return new ColorModelMemento(color);
  }

  @Override
  public void addColorChangeListener(IColorChangeListener listener) {
    colorChangeListeners.add(listener);
  }

  @Override
  public void notifyAllColorChangeListeners() {
    colorChangeListeners.colorChanged(color);
  }
}

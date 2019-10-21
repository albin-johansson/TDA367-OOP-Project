package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.AbstractComposite;
import chalmers.pimp.model.color.IColor;

/**
 * The {@code ColorChangeListenerComposite} class represents a composite of {@code
 * IColorChangeListener} instances.
 */
final class ColorChangeListenerComposite extends AbstractComposite<IColorChangeListener> implements
    IColorChangeListener {

  ColorChangeListenerComposite() {
  }

  @Override
  public void colorChanged(IColor color) {
    for (IColorChangeListener listener : this) {
      listener.colorChanged(color);
    }
  }
}

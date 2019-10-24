package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.canvas.LayerUpdateEvent;
import chalmers.pimp.model.canvas.layer.IColorable;
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

  /**
   * @param color the color.
   * @throws NullPointerException if the provided color is {@code null}.
   */
  ColorModelImpl(IColor color) {
    colorChangeListeners = new ColorChangeListenerComposite();
    this.color = Objects.requireNonNull(color);
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

  @Override
  public void layersUpdated(LayerUpdateEvent event) {
    // Do not update the color if there was a new layer created.
    // Only care about selections.
    if (event.wasLayerAdded() || !event.wasSelectionUpdated()) {
      return;
    }

    var selectedLayer = event.getSelectedLayer();

    // Update the selected color if the newly selected layer is colorable.
    if (selectedLayer instanceof IColorable) {
      setColor(((IColorable) selectedLayer).getColor());
      notifyAllColorChangeListeners();
    }
  }
}

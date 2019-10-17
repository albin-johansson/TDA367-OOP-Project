package chalmers.pimp.model.color.colormodel;

/**
 * The {@code IColorChangeObservable} class represents something that can be observed for when
 * colors are changed.
 */
public interface IColorChangeObservable {

  /**
   * Adds a listener.
   *
   * @param listener a listener that listens to color changes.
   * @throws NullPointerException if the provided observer is null.
   */
  void addColorChangeListener(IColorChangeListener listener);

  /**
   * Notifies all listeners what the new selected color is.
   */
  void notifyAllColorChangeListeners();
}

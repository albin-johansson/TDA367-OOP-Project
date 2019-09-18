package view;

import model.IModel;

/**
 * The {@code ViewFactory} class is a factory for creating instances of the {@code IView}
 * interface.
 */
public final class ViewFactory {

  private ViewFactory() {
  }

  /**
   * Creates and returns a view instance.
   *
   * @param model the associated model instance.
   * @return a view instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IView createView(IModel model) {
    return new ViewImpl(model);
  }
}
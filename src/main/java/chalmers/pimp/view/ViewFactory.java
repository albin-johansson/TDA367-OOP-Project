package chalmers.pimp.view;

import chalmers.pimp.model.IModel;

/**
 * The {@code ViewFactory} class is a factory for creating instances of the {@code IView}
 * interface.
 */
public final class ViewFactory {

  private ViewFactory() {
  }

  /**
   * Creates and returns a model instance.
   *
   * @param model the associated model instance.
   * @return a model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IView createView(IModel model) {
    return new ViewImpl(model);
  }
}
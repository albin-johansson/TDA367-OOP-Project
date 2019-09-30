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
   * Creates and returns a chalmers.pimp.view instance.
   *
   * @param model the associated chalmers.pimp.model instance.
   * @return a chalmers.pimp.view instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IView createView(IModel model) {
    return new ViewImpl(model);
  }
}
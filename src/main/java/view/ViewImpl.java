package view;

import java.util.Objects;
import model.IModel;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private final IModel model;

  /**
   * @param model the associated model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ViewImpl(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

}
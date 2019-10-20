package chalmers.pimp.model;

/**
 * The {@code ModelFactory} class is a factory for creating instances of the {@code IModel}
 * interface.
 */
public final class ModelFactory {

  private ModelFactory() {
  }

  /**
   * Creates and returns a model instance.
   *
   * @return a model instance.
   */
  public static IModel createModel() {
    return new ModelImpl();
  }
}
package chalmers.pimp.model.color.colormodel;

/**
 * The {@code ColorModelFactory} class is used to create instances of the IColorModel interface.
 *
 * @see IColorModel
 */
public final class ColorModelFactory {

  private ColorModelFactory() {
  }

  /**
   * Creates and returns a color model instance.
   *
   * @return a color model instance.
   */
  public static IColorModel createColorModel() {
    return new ColorModelImpl();
  }
}

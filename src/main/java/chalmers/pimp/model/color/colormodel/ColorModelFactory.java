package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.Colors;

/**
 * The {@code ColorModelFactory} class is used to create instances of the IColorModel interface.
 *
 * @see IColorModel
 */
public final class ColorModelFactory {

  private ColorModelFactory() {
  }

  /**
   * Creates and returns a color model instance. The color model will have black as the selected
   * color.
   *
   * @return a color model instance.
   */
  public static IColorModel createColorModel() {
    return new ColorModelImpl(Colors.BLACK);
  }
}

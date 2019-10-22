package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;

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
    return createColorModel(Colors.BLACK);
  }

  /**
   * Creates and returns a color model with the supplied color.
   * @param color the color.
   * @return a color model instance with the provided color.
   * @throws NullPointerException if the provided color is {@code null}.
   */
  public static IColorModel createColorModel(IColor color) {
    return new ColorModelImpl(color);
  }
}

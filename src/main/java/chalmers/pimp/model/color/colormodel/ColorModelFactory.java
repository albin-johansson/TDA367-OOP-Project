package chalmers.pimp.model.color.colormodel;

import chalmers.pimp.model.color.IColor;
import java.util.Objects;

/**
 * The {@code ColorModelFactory} class is used to create instances of the IColorModel interface.
 *
 * @see IColorModel
 */
public final class ColorModelFactory {

  private ColorModelFactory() {
  }

  /**
   * Creates and returns a implementation of the IColorModel interface.
   *
   * @param color the color the color model should hold.
   * @return a color model with the provided color.
   * @throws NullPointerException if the provided color is {@code null}.
   */
  public static IColorModel createColorModel(IColor color) {
    return new ColorModelImpl(Objects.requireNonNull(color));
  }
}

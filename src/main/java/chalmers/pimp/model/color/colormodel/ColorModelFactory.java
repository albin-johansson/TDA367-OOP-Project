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

  public static IColorModel createColorModel(IColor color) {
    Objects.requireNonNull(color);
    return new ColorModelImpl(color);
  }
}

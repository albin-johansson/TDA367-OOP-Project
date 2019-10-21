package chalmers.pimp.service;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import java.util.Objects;
import javafx.scene.paint.Color;

/**
 * The {@code ColorService} is responsible for converting colors between different
 * formats.
 */
public final class ColorConverterService {

  private ColorConverterService() {
  }

  /**
   * Converts the supplied color to a JavaFX color.
   *
   * @param color the color that will be converted.
   * @return a JavaFX color that is a copy of the supplied color.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static Color toFXColor(IColor color) {
    return new Color(color.getRedPercentage(),
        color.getGreenPercentage(),
        color.getBluePercentage(),
        color.getAlphaPercentage());
  }

  /**
   * Converts the supplied JavaFX color to a IColor.
   *
   * @param fxColor the color that will be converted.
   * @return a IColor that is a copy of the supplied color.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public static IColor fxToIColor(Color fxColor) {
    Objects.requireNonNull(fxColor);

    int max = 255;
    return ColorFactory.createColor(
        (int) (fxColor.getRed() * max),
        (int) (fxColor.getGreen() * max),
        (int) (fxColor.getBlue() * max),
        (int) (fxColor.getOpacity() * max)
    );
  }
}
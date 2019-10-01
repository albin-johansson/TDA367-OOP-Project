package chalmers.pimp.service;

import chalmers.pimp.model.color.IReadOnlyColor;
import javafx.scene.paint.Color;

/**
 * The {@code ColorConverterService} is responsible for converting colors between different
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
  public static Color toFXColor(IReadOnlyColor color) {
    return new Color(color.getRedPercentage(),
        color.getGreenPercentage(),
        color.getBluePercentage(),
        color.getAlphaPercentage());
  }
}
package chalmers.pimp.controller.components;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * The {@code ColorPickerPane} represents the anchor pane that holds the color picker.
 */
public class ColorPickerPane extends AnchorPane {

  @FXML
  private Color colorPicker;

  /**
   * Returns the current color of the color picker.
   *
   * @return the current color of the color picker.
   */
  public IColor getColor() {
    int max = 255;

    int red = (int) (colorPicker.getRed() * max);
    int green = (int) (colorPicker.getGreen() * max);
    int blue = (int) (colorPicker.getBlue() * max);
    int alpha = (int) (colorPicker.getOpacity() * max);

    return ColorFactory.createColor(red, green, blue, alpha);
  }
}

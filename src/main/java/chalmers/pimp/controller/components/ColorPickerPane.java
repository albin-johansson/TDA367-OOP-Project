package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.IColorChangeListener;
import chalmers.pimp.service.ColorConverterService;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * The {@code ColorPickerPane} represents the anchor pane that holds the color picker.
 */
final class ColorPickerPane extends AnchorPane implements IColorChangeListener {

  @FXML
  @SuppressWarnings("unused")
  private ColorPicker colorPicker;

  /**
   * @param model the model.
   * @throws IOException          if the color picker pane fxml file is not found.
   * @throws NullPointerException if the provided model is {@code null}.
   */
  ColorPickerPane(IModel model) throws IOException {
    Objects.requireNonNull(model);
    ControllerUtils.makeController(this, Resources.find(getClass(), "color_picker.fxml"));

    colorPicker.setOnAction(event -> {
      model.setSelectedColor(ColorConverterService.fxToIColor(colorPicker.getValue()));
    });
  }

  /**
   * Returns the current color of the color picker.
   *
   * @return the current color of the color picker.
   */
  public IColor getColor() {
    return ColorConverterService.fxToIColor(colorPicker.getValue());
  }

  @Override
  public void colorChanged(IColor color) {
    Color fxColor = ColorConverterService.toFXColor(color);
    colorPicker.setValue(fxColor);
  }
}

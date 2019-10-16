package chalmers.pimp.controller.components;

import chalmers.pimp.controller.ControllerUtils;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colorchangeobserver.IColorChangeListener;
import chalmers.pimp.service.ColorConverterService;
import chalmers.pimp.util.Resources;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * The {@code ColorPickerPane} represents the anchor pane that holds the color picker.
 */
public class ColorPickerPane extends AnchorPane implements IColorChangeListener {

  @FXML
  private ColorPicker colorPicker;
  private final IModel model;

  ColorPickerPane(IModel model) throws IOException {
    this.model = model;
    ControllerUtils.makeController(this, Resources.find(getClass(), "color_picker.fxml"));

    colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
      model.setSelectedColor(ColorConverterService.fxToIColor(newValue));
    });
    
    setColor(Colors.BLACK);
  }

  /**
   * Returns the current color of the color picker.
   *
   * @return the current color of the color picker.
   */
  public IColor getColor() {
    return ColorConverterService.fxToIColor(colorPicker.getValue());
  }

  /**
   * Sets the color of the color picker.
   *
   * @param color the new color.
   */
  public void setColor(IColor color) {
    Color fxColor = ColorConverterService.toFXColor(color);
    colorPicker.setValue(fxColor);
  }

  @Override
  public void colorChanged(IColor color) {
    setColor(color);
  }
}

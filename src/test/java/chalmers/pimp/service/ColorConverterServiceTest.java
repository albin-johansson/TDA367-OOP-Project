package chalmers.pimp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class ColorConverterServiceTest {

  @Test
  void toFXColor() {
    int red = 124;
    int green = 92;
    int blue = 59;
    int alpha = 12;

    IColor color = ColorFactory.createColor(red, green, blue, alpha);
    Color fxColor = ColorConverterService.toFXColor(color);

    assertEquals(red, fxColor.getRed() * 255, 0.01);
    assertEquals(green, fxColor.getGreen() * 255, 0.01);
    assertEquals(blue, fxColor.getBlue() * 255, 0.01);
    assertEquals(alpha, fxColor.getOpacity() * 255, 0.01);
  }

  @Test
  void fxToIColor() {
    int red = 249;
    int green = 12;
    int blue = 177;
    int alpha = 77;

    var fxColor = new Color(red / 255.0, green / 255.0, blue / 255.0, alpha / 255.0);
    IColor color = ColorConverterService.fxToIColor(fxColor);

    assertEquals(red, color.getRed(), 0.01);
    assertEquals(green, color.getGreen(), 0.01);
    assertEquals(blue, color.getBlue(), 0.01);
    assertEquals(alpha, color.getAlpha(), 0.01);
  }
}
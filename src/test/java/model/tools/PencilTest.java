package model.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import model.canvas.layer.ILayer;
import model.canvas.layer.LayerFactory;
import org.junit.jupiter.api.Test;

class PencilTest {

  @Test
  void drawTest() {
    Pencil pencil = new Pencil(2, Color.BLACK);
    ILayer layer = LayerFactory.createRasterLayer(100, 100);
    pencil.setTarget(layer);
    pencil.draw(10, 10);
    int nBlackPixels = 0;

    for (Iterable<Color> row : layer.getPixelData().getPixels()) {
      for (Color color : row) {
        if (color.getRGB() == Color.BLACK.getRGB()) {
          nBlackPixels++;
        }
      }
    }

    assertEquals(nBlackPixels, 4, "Should draw a 2x2 pixel grid.");
    assertEquals(layer.getPixelData().getPixel(10, 10).getRGB(), Color.BLACK.getRGB(),
        "Pixel should be colored black.");
  }
}
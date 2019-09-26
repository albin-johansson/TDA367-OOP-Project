package service;

import java.awt.Color;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import model.pixeldata.IReadOnlyPixelData;

/**
 * The {@code FXImageService} class is a service which takes pixeldata and returns an FX Image
 */
public class FXImageService {


  /**
   *
   * @param pixelData
   * @return
   */
  public static Image getFXImage(IReadOnlyPixelData pixelData) {
    int offsetX = 0;
    int offsetY = 0;

    WritableImage fxImage = new WritableImage(pixelData.getWidth(), pixelData.getHeight());

    PixelWriter pw = fxImage.getPixelWriter();

    for (Iterable<Color> pixelDataRow : pixelData.getPixels()) {
      for (Color c : pixelDataRow) {
        pw.setColor(offsetX, offsetY,
            new javafx.scene.paint.Color(c.getRed() / 255, c.getGreen() / 255, c.getBlue() / 255,
                c.getAlpha() / 255));
        offsetX++;
      }

      offsetY++;
      offsetX = 0;

    }
    return fxImage;
  }

}

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
   * Creates a new WriteableImage and sets the pixels to represent the pixelData entered.
   *
   * @param pixelData the pixelData that should be converted to an fxImage.
   * @return an FXImage representing the pixelData
   */
  public static Image getFXImage(IReadOnlyPixelData pixelData) {
    int offsetY = 0;

    WritableImage fxImage = new WritableImage(pixelData.getWidth(), pixelData.getHeight());

    PixelWriter pw = fxImage.getPixelWriter();

    for (Iterable<Color> pixelDataRow : pixelData.getPixels()) {
      setImagePixelRow(pixelDataRow, offsetY, pw);
      offsetY++;

    }
    return fxImage;
  }


  /**
   * Copies one row from the pixeldata to the new FXImage
   *
   * @param pixelDataRow the row of Pixeldata to be copied
   * @param offsetY The current row in the fx Image
   * @param pw the pixelwriter of the connected fx Image
   */
  //TODO change color representation to our own.
  private static void setImagePixelRow(Iterable<Color> pixelDataRow, int offsetY, PixelWriter pw) {
    int offsetX = 0;
    for (Color c : pixelDataRow) {
      pw.setColor(offsetX, offsetY,
          new javafx.scene.paint.Color(((double) c.getRed()) / 255, ((double) c.getGreen()) / 255,
              ((double) c.getBlue()) / 255,
              ((double) c.getAlpha())
                  / 255)); //Casts to double to change from Swing to FX, will be changed in future.
      offsetX++;
    }
  }

}

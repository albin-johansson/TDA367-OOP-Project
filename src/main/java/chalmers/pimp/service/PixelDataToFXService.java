package chalmers.pimp.service;

import chalmers.pimp.model.pixeldata.IReadOnlyPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * The {@code FXImageService} class is a service which converts pixel data to JavaFX images.
 */
public final class PixelDataToFXService {

  private PixelDataToFXService() {
  }

  /**
   * Creates and returns a JavaFX image based on the supplied pixel data.
   *
   * @param pixelData the pixel data that should be converted.
   * @return a JavaFX image based on the supplied pixel data.
   * @throws NullPointerException if any arguments are {@code null}.d
   */
  public static Image getFXImage(IReadOnlyPixelData pixelData) {
    Objects.requireNonNull(pixelData);

    var result = new WritableImage(pixelData.getWidth(), pixelData.getHeight());
    PixelWriter pixelWriter = result.getPixelWriter();

    int yOffset = 0;
    for (Iterable<? extends IReadOnlyPixel> pixelDataRow : pixelData.getPixels()) {
      setImagePixelRow(pixelDataRow, pixelWriter, yOffset);
      yOffset++;
    }

    return result;
  }

  /**
   * Copies a row of pixels to the image associated with the supplied pixel writer.
   *
   * @param pixelDataRow the row of Pixeldata to be copied.
   * @param pixelWriter  the pixel writer that will be used.
   * @param rowOffset    The row offset of the affected row.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  private static void setImagePixelRow(Iterable<? extends IReadOnlyPixel> pixelDataRow,
      PixelWriter pixelWriter, int rowOffset) {
    Objects.requireNonNull(pixelDataRow);
    Objects.requireNonNull(pixelWriter);

    int xOffset = 0;
    for (IReadOnlyPixel pixel : pixelDataRow) {
      Color fxColor = ColorConverterService.toFXColor(pixel.getColor());
      pixelWriter.setColor(xOffset, rowOffset, fxColor);
      xOffset++;
    }
  }
}

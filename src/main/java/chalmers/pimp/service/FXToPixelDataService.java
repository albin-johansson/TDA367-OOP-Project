package chalmers.pimp.service;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * The {@code FXToPixelDataService} class is a service for converting JavaFX images to pixel data
 * instances.
 */
public final class FXToPixelDataService {

  private FXToPixelDataService() {
  }

  /**
   * Creates a pixel data copy of the supplied JavaFX image.
   *
   * @param image the image that will be copied.
   * @return a pixel data copy of the supplied JavaFX image.
   * @throws NullPointerException if the supplied image is {@code null}.
   */
  public static PixelData createPixelDataCopy(Image image) {
    Objects.requireNonNull(image);

    final int imageWidth = (int) image.getWidth();
    final int imageHeight = (int) image.getHeight();

    var pixelData = new PixelData(imageWidth, imageHeight);
    PixelReader reader = image.getPixelReader();

    for (int row = 0; row < imageHeight; row++) {
      writeImageRow(pixelData, reader, imageWidth, row);
    }

    return pixelData;
  }

  /**
   * Writes a row in the supplied pixel data.
   *
   * @param pixelData  the pixel data to write to.
   * @param reader     the pixel reader used to obtain ARGB values from the JavaFX image.
   * @param imageWidth the width of the JavaFX image.
   * @param rowIndex   the row index in the JavaFX image.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  private static void writeImageRow(PixelData pixelData, PixelReader reader, int imageWidth,
      int rowIndex) {
    Objects.requireNonNull(pixelData);
    Objects.requireNonNull(reader);
    for (int col = 0; col < imageWidth; col++) {

      Color fxColor = reader.getColor(col, rowIndex);

      IColor color = ColorFactory.createColor()
          .setPercentageRed(fxColor.getRed())
          .setPercentageGreen(fxColor.getGreen())
          .setPercentageBlue(fxColor.getBlue())
          .setPercentageAlpha(fxColor.getOpacity());

      pixelData.setPixel(PixelFactory.createPixel(col, rowIndex, color));
    }
  }
}
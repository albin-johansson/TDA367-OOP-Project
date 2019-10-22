package chalmers.pimp.service;

import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IRasterData;
import chalmers.pimp.model.pixeldata.IReadOnlyPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyRasterData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import chalmers.pimp.model.pixeldata.RasterDataFactory;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * The {@code RasterDataService} class is a service for converting between JavaFX images and raster
 * data instances.
 *
 * @see IRasterData
 * @see IReadOnlyRasterData
 */
public final class RasterDataService {

  private RasterDataService() {
  }

  /**
   * Creates a pixel data copy of the supplied JavaFX image.
   *
   * @param image the image that will be copied.
   * @return a pixel data copy of the supplied JavaFX image.
   * @throws NullPointerException if the supplied image is {@code null}.
   */
  public static IRasterData createPixelDataCopy(Image image) {
    Objects.requireNonNull(image);

    final int imageWidth = (int) image.getWidth();
    final int imageHeight = (int) image.getHeight();

    IRasterData rasterData = RasterDataFactory.createRasterData(imageWidth, imageHeight);
    PixelReader reader = image.getPixelReader();

    for (int row = 0; row < imageHeight; row++) {
      writeImageRow(rasterData, reader, imageWidth, row);
    }

    return rasterData;
  }

  /**
   * Writes a row in the supplied pixel data.
   *
   * @param rasterData the raster data to write to.
   * @param reader     the pixel reader used to obtain ARGB values from the JavaFX image.
   * @param imageWidth the width of the JavaFX image.
   * @param rowIndex   the row index in the JavaFX image.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  private static void writeImageRow(IRasterData rasterData, PixelReader reader, int imageWidth,
      int rowIndex) {
    Objects.requireNonNull(rasterData);
    Objects.requireNonNull(reader);
    for (int col = 0; col < imageWidth; col++) {
      Color fxColor = reader.getColor(col, rowIndex);
      IColor color = ColorConverterService.fxToIColor(fxColor);

      rasterData.setPixel(PixelFactory.createPixel(col, rowIndex, color));
    }
  }

  /**
   * Creates and returns a JavaFX image based on the supplied pixel data.
   *
   * @param pixelData the pixel data that should be converted.
   * @return a JavaFX image based on the supplied pixel data.
   * @throws NullPointerException if any arguments are {@code null}.d
   */
  public static Image toFXImage(IReadOnlyRasterData pixelData) {
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
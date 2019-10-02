package chalmers.pimp.service;

import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

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
      IPixel pixel = createPixel(col, rowIndex, reader.getArgb(col, rowIndex));
      pixelData.setPixel(pixel);
    }
  }

  /**
   * Creates and returns a pixel based on the supplied coordinates and ARGB color mask.
   *
   * @param x    the x-coordinate of the pixel.
   * @param y    the y-coordinate of the pixel.
   * @param argb the integer that serves as a mask for the ARGB color.
   * @return a pixel instance.
   */
  private static IPixel createPixel(int x, int y, int argb) {
    double[] colorArray = convertARGBToArray(argb);
    double red = colorArray[0];
    double green = colorArray[1];
    double blue = colorArray[2];
    double alpha = colorArray[3];
    return PixelFactory.createPixel(x, y, red, green, blue, alpha);
  }

  /**
   * Converts the supplied ARGB-formatted integer to an array of doubles that represent each color
   * component (red, green, blue and alpha).
   *
   * @param argb the integer that contains the color information, according to ARGB format.
   * @return an array of doubles that contains values in the range [0, 1].
   */
  private static double[] convertARGBToArray(int argb) {
    var result = new double[4];

    for (int i = 0; i < 4; i++) {
      result[i] = (argb & 0xFF) / 255.0;
      argb >>= 8;
    }

    return result;
  }
}
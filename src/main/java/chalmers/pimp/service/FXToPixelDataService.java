package chalmers.pimp.service;

import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * Converts an javafx.Image to PixelData
 */
public final class FXToPixelDataService {

  private FXToPixelDataService() {
  }

  /**
   * Converts an javafx.Image to PixelData
   *
   * @param image the image to be converted
   * @return the resulting pixel data
   */
  public static PixelData getImage(Image image) {

    int width = (int) image.getWidth();
    int height = (int) image.getHeight();
    int argb;
    double[] argbArr;
    PixelData pixelData = new PixelData(width, height);
    PixelReader reader = image.getPixelReader();

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        argb = reader.getArgb(col, row);
        argbArr = argbToArgbArr(argb);
        pixelData.setPixel(
            PixelFactory.createPixel(col, row, argbArr[2], argbArr[1], argbArr[0], argbArr[3]));
      }
    }

    return pixelData;
  }

  /**
   * Converts a 32-bit int containing pixel color into an array of doubles with color info
   *
   * @param argb the 32-bit int to be converted. Each color channel is specified in each byte
   * @return [0] = blue percentage, [1] = green percentage, [2] = red percentage, [3] = alpha
   * percentage
   */
  private static double[] argbToArgbArr(int argb) {
    double[] percentage = new double[4];

    for (int i = 0; i < 4; i++) {
      percentage[i] = (argb & 0xFF) / 255.0;
      argb >>= 8;
    }
    return percentage;
  }
}

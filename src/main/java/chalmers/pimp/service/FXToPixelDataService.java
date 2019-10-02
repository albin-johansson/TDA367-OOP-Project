package chalmers.pimp.service;

import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public final class FXToPixelDataService {

  private FXToPixelDataService() {
  }

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

  private static double[] argbToArgbArr(int argb) {
    double[] arr = new double[4];

    for (int i = 0; i < 4; i++) {
      arr[i] = (argb & 0xFF) / 255.0;
      argb >>= 8;
    }
    return arr;
  }
}

package service.image;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * This service loads images
 */
public final class ImageService {

  private static ImageService instance;

  private ImageService() {
  }

  /**
   * Imports an image from the specified path
   *
   * @param path the full path to the specified image
   * @return an javafx.scene.image.Image
   */
  public static Image importImage(String path) throws IOException {
    try {
      return new Image("file://" + path, true);
    } catch (Exception e) {
      throw new IOException("The image at file://" + path + " was not found.");
    }
  }
}

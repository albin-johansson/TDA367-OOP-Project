package chalmers.pimp.service;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * The {@code ImageImportService} class is capable of importing JavaFX images.
 */
public final class ImageImportService {

  private ImageImportService() {
  }

  /**
   * Imports an image from the specified path.
   *
   * @param path the path to the desired image.
   * @return a javafx image.
   */
  public static Image importImage(String path) throws IOException {
    try {
      return new Image("file://" + path, false);
    } catch (Exception e) {
      throw new IOException("The image at file://" + path + " was not found.");
    }
  }
}

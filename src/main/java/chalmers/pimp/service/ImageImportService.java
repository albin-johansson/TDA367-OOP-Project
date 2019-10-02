package chalmers.pimp.service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 * The {@code ImageImportService} class is capable of importing JavaFX images.
 */
public final class ImageImportService {

  private ImageImportService() {
  }

  /**
   * Imports an image, represented by the supplied file.
   *
   * @param file the file that represents the desired image.
   * @return a JavaFX image.
   * @throws NullPointerException if the supplied file is {@code null}.
   * @throws IOException          if the image cannot be loaded.
   */
  public static Image importImage(File file) throws IOException {
    Objects.requireNonNull(file);
    try {
      return new Image(file.toURI().toString());
    } catch (Exception e) {
      throw new IOException("Failed to load the image at: " + file);
    }
  }
}

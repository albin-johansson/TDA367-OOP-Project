package chalmers.pimp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  /**
   * Returns the given file's filename (without extensions).
   *
   * @param file the file to have it's name returned.
   * @return the name of the supplied file.
   * @throws NullPointerException if the supplied file is {@code null}.
   */
  public static String getFileNameFromFile(File file) {
    Objects.requireNonNull(file);
    List<String> temp = new ArrayList<>(Arrays.asList(file.getName().split("\\.")));
    return file.getName().replace("." + temp.get(temp.size() - 1), "");
  }
}

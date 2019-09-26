package service.image;

import java.io.IOException;
import javafx.scene.image.Image;

public class ImageService {

  private static ImageService self;

  private ImageService() {
  }

  public static ImageService getInstance() {
    if (self == null) {
      self = new ImageService();
    }

    return self;
  }

  /**
   * Imports an image from the specified path
   *
   * @param path the full path to the specified image
   * @return an javafx.scene.image.Image
   */
  public Image importImage(String path) throws IOException {
    try{
      return new Image("file://" + path, true);
    } catch (Exception e){
      throw new IOException("The image at file://" + path + " was not found.");
    }
  }
}

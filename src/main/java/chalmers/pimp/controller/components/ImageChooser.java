package chalmers.pimp.controller.components;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.LayerFactory;
import chalmers.pimp.service.FXToPixelDataService;
import chalmers.pimp.service.ImageImportService;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * This component allows the user to select an image
 */
public class ImageChooser {

  FileChooser fileChooser;

  /**
   * Sets the default configuration of fileChooser
   */
  public ImageChooser() {
    fileChooser = new FileChooser();

    setTitle("Choose file to import");
    getExtensionFilters().addAll(
        new ExtensionFilter("JPG", "*.jpg"),
        new ExtensionFilter("PNG", "*.png"),
        new ExtensionFilter("All images", "*.*")
    );
  }

  /**
   * Sets the title of the file chooser window
   *
   * @param title the specified title
   */
  public void setTitle(String title) {
    fileChooser.setTitle(title);
  }

  /**
   * Returns a list of choosable filters of extension
   *
   * @return the list of extension filters
   */
  public ObservableList<ExtensionFilter> getExtensionFilters() {
    return fileChooser.getExtensionFilters();
  }

  /**
   * Shows the file chooser for the user
   *
   * @param window the window to show the chooser on
   * @return returns an ILayer containing the image selected, if no image was selected it returns
   * null
   */
  public ILayer show(Window window) {
    Image image;

    File file = fileChooser.showOpenDialog(window);
    if (file == null) {
      return null;
    }

    try {
      image = ImageImportService
          .importImage(file.getPath());
    } catch (IOException e) {
      System.out.println("Failed to load selected file");
      return null;
    }

    ILayer newLayer = LayerFactory.createPixelLayer(
        FXToPixelDataService.getImage(image));

    return newLayer;
  }
}

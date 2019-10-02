package chalmers.pimp.controller.components;

import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.service.FXToPixelDataService;
import chalmers.pimp.service.ImageImportService;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The {@code ImageChooser} class represents a file chooser which enables the user to import a
 * pre-existing image. The currently supported file formats are JPG and PNG.
 */
public final class ImageChooser {

  private final FileChooser fileChooser;

  public ImageChooser() {
    fileChooser = new FileChooser();
    fileChooser.setTitle("Import Image");

    var filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png");
    fileChooser.getExtensionFilters().add(filter);
  }

  /**
   * Attempts to let the user choose an existing image and subsequently import it.
   *
   * @param window the parent window that will contain the dialog.
   * @return a pixel data instance, that represents the existing image; {@code null} if no file was
   * selected.
   * @throws IOException          if an image cannot be loaded.
   * @throws NullPointerException if the supplied window is {@code null}.
   */
  public PixelData openDialog(Window window) throws IOException {
    Objects.requireNonNull(window);

    File file = fileChooser.showOpenDialog(window);
    if (file == null) {
      System.out.println("No image file was selected!");
      return null;
    }

    Image image = ImageImportService.importImage(file);
    return FXToPixelDataService.createPixelDataCopy(image);
  }
}
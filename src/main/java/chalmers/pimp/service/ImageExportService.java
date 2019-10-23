package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import chalmers.pimp.model.viewport.ViewportFactory;
import chalmers.pimp.view.renderer.RendererFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 * The {@code ImageExportService} class is a service for exporting the contents of a model canvas to
 * an image.
 */
public final class ImageExportService {

  private ImageExportService() {
  }

  /**
   * Creates and returns a file chooser for choosing PNG images.
   *
   * @return a file chooser for choosing PNG images.
   */
  private static FileChooser createFileChooser() {
    var fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
    return fileChooser;
  }

  /**
   * Renders the supplied layers on the supplied image.
   *
   * @param img    the image that will be rendered on.
   * @param layers the layers that will be rendered.
   * @throws NullPointerException if any references are {@code null}.
   */
  private static void renderToImage(BufferedImage img, Iterable<? extends IReadOnlyLayer> layers) {
    Objects.requireNonNull(img);
    Objects.requireNonNull(layers);

    int width = img.getWidth();
    int height = img.getHeight();
    IReadOnlyViewport viewport = ViewportFactory.createViewport(0, 0, width, height);
    IRenderer renderer = RendererFactory.createSwingRenderer(img.createGraphics(), viewport);

    for (IReadOnlyLayer layer : layers) {
      layer.draw(renderer, viewport);
    }
  }

  /**
   * Attempts to export the supplied layers into an image. This method has no effect if the
   * operation is unsuccessful.
   *
   * @param width  the width of the created image.
   * @param height the height of the created image.
   * @param layers the layers that will be used when creating the image.
   * @throws IllegalArgumentException if the supplied width/height aren't greater than zero.
   * @throws NullPointerException     if any references are {@code null}.
   */
  public static void exportImage(int width, int height, Iterable<? extends IReadOnlyLayer> layers) {
    if ((width < 1) || (height < 1)) {
      throw new IllegalArgumentException("Invalid width or height!");
    }
    Objects.requireNonNull(layers);

    FileChooser fileChooser = createFileChooser();
    File file = fileChooser.showSaveDialog(null);
    if (file == null) {
      return; // simply return if the user doesn't want to save the content
    }

    var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    renderToImage(img, layers);

    try {
      ImageIO.write(img, "png", file);
    } catch (Exception e) {
      System.err.println("Failed to export image! Exception: " + e);
    }
  }
}
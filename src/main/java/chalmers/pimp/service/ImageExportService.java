package chalmers.pimp.service;

import chalmers.pimp.model.IArea;
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

  private static IArea createArea(int width, int height) {
    return new IArea() {
      @Override
      public int getWidth() {
        return width;
      }

      @Override
      public int getHeight() {
        return height;
      }
    };
  }

  /**
   * Attempts to export the supplied layers into an image. This method has no effect if the
   * operation is unsuccessful.
   *
   * @param width  the width of the created image.
   * @param height the height of the created image.
   * @param layers the layers that will be used when creating the image.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static void exportImage(int width, int height, Iterable<? extends IReadOnlyLayer> layers) {
    Objects.requireNonNull(layers);

    var filter = new ExtensionFilter("PNG", "*.png");

    var fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(filter);

    File file = fileChooser.showSaveDialog(null);
    if (file == null) {
      return; // simply return if the user doesn't want to save the content
    }

    var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    // Since the renderer is temporary, this is OK
    IArea area = createArea(width, height);
    IRenderer renderer = RendererFactory.createSwingRenderer(img.createGraphics(), area);

    IReadOnlyViewport viewport = ViewportFactory.createViewport(0, 0, width, height);
    for (IReadOnlyLayer layer : layers) {
      layer.draw(renderer, viewport);
    }

    try {
      ImageIO.write(img, "png", file);
    } catch (Exception e) {
      System.err.println("Failed to export image! Exception: " + e);
    }
  }
}
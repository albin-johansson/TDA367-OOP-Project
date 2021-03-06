package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.viewport.ViewportFactory;
import chalmers.pimp.view.renderer.RendererFactory;
import java.util.Objects;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * The {@code LayerImageService} class is a service that generates a JavaFX image from a layer.
 */
public final class LayerImageService {

  private LayerImageService() {
  }

  /**
   * Returns the preview area size (the width and height of the preview area).
   *
   * @param layer the layer that will be used for the calculation.
   * @return the preview area size (the width and height of the preview area).
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  private static int getPreviewAreaSize(IReadOnlyLayer layer) {
    Objects.requireNonNull(layer);
    if (layer.getRotation() != 0) {
      return (int) Math.hypot(layer.getWidth(), layer.getHeight());
    } else {
      return Math.max(layer.getWidth(), layer.getHeight()) + 10; // creates a small margin
    }
  }

  /**
   * Creates a JavaFX Image representing a layer.
   *
   * @param layer the layer to be converted, the supplied layer will not be mutated.
   * @return a JavaFX Image of the target layer.
   * @throws NullPointerException if the supplied layer is {@code null}.
   */
  public static Image getLayerImage(ILayer layer) {
    Objects.requireNonNull(layer);
    ILayer copy = layer.clone();

    // Always show the layer so that the preview will be shown.
    copy.setVisible(true);

    int size = getPreviewAreaSize(copy);

    copy.setX(size);
    copy.setY(size);

    int cropX = copy.getX() - ((size - copy.getWidth()) / 2);
    int cropY = copy.getY() - ((size - copy.getHeight()) / 2);
    int width = copy.getX() + size;
    int height = copy.getY() + size;

    var canvas = new Canvas(width, height);
    IRenderer renderer = RendererFactory.createFXRenderer(canvas.getGraphicsContext2D());
    copy.draw(renderer, ViewportFactory.createViewport(0, 0, width, height));

    WritableImage image = canvas.snapshot(new SnapshotParameters(), null);

    // Crops the Image with the layer focused in the middle and a small margin
    return new WritableImage(image.getPixelReader(), cropX, cropY, size, size);
  }
}

package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.viewport.ViewportFactory;
import chalmers.pimp.view.renderer.RendererFactory;
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

  private static int getSize(IReadOnlyLayer layer) {
    if (layer.getRotation() != 0) {
      return (int) Math.hypot(layer.getWidth(), layer.getHeight());
    } else {
      return Math.max(layer.getWidth(), layer.getHeight()) + 10;
    }
  }

  /**
   * Creates a JavaFX Image representing a layer.
   *
   * @param layer the layer to be converted.
   * @return a JavaFX Image of the target layer.
   */
  public static Image getLayerImage(IReadOnlyLayer layer) {
    ILayer copy = layer.copy();

    int size = getSize(copy);

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

    //Crops the Image with the layer focused in the middle and a small margin
    return new WritableImage(image.getPixelReader(), cropX, cropY, size, size);
  }
}

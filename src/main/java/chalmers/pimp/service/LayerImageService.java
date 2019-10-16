package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.view.renderer.RendererFactory;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * The {@code LayerImageService} class is a service that generates a JavaFX Image a given layer.
 */
public final class LayerImageService {

  private LayerImageService() {

  }

  /**
   * Creates a JavaFX Image representing a layer.
   *
   * @param layer the layer to be converted.
   * @return a JavaFX Image of the target layer.
   */
  public static Image getLayerImage(IReadOnlyLayer layer) {

    int size = 20;

    if (layer.getPixelData().getHeight() > layer.getPixelData().getWidth()) {
      size += layer.getPixelData().getHeight();
    } else {
      size += layer.getPixelData().getWidth();
    }

    Canvas canvas = new Canvas(size, size);
    IRenderer renderer = RendererFactory.createFXRenderer(canvas.getGraphicsContext2D());

    int x = (size - layer.getPixelData().getWidth()) / 2;
    int y = (size - layer.getPixelData().getHeight()) / 2;

    renderer.drawImage(layer.getPixelData(), x, y, size, size);

    return canvas.snapshot(new SnapshotParameters(), null);
  }
}

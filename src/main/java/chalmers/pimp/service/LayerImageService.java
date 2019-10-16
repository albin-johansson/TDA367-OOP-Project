package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.view.renderer.RendererFactory;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

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
    size += Math.max(layer.getHeight(), layer.getWidth());

    int x = layer.getX() - (size - layer.getWidth()) / 2;
    int y = layer.getY() - (size - layer.getHeight()) / 2;

    if(x<0||y<0){
      size -= 20;
      if(x<0){
        x = 0;
      }
      if (y<0){
        y = 0;
      }
    }

    Canvas canvas = new Canvas(layer.getX()+layer.getWidth() + 200, layer.getY()+layer.getHeight() + 200);
    IRenderer renderer = RendererFactory.createFXRenderer(canvas.getGraphicsContext2D());
    layer.draw(renderer);
    WritableImage image = canvas.snapshot(new SnapshotParameters(), null);


    PixelReader reader = image.getPixelReader();
    System.out.println(image.getWidth());
    System.out.println(image.getHeight());

    WritableImage croppedImage = new WritableImage(reader, x, y, size, size);

    return croppedImage;
  }
}

package chalmers.pimp.service;

import chalmers.pimp.model.IRenderer;
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

    //Draws the Layer on Canvas and takes a snapshot
    Canvas canvas = new Canvas(layer.getX() + layer.getWidth(),
        layer.getY() + layer.getHeight());
    IRenderer renderer = RendererFactory.createFXRenderer(canvas.getGraphicsContext2D());
    layer.draw(renderer);
    WritableImage image = canvas.snapshot(new SnapshotParameters(), null);

    //Crops the Image so it's only portraying the layer.
    PixelReader reader = image.getPixelReader();
    WritableImage croppedImage = new WritableImage(reader, layer.getX(), layer.getY(), layer.getWidth(), layer.getHeight());
    PixelReader croppedReader = croppedImage.getPixelReader();

    //Creates a new Image slightly larger than the layer, and in the dimensions of the preview.
    int size = Math.max(layer.getHeight(), layer.getWidth());
    size += 20;
    WritableImage newImage = new WritableImage(size,size);

    //Draws the Layer centered on to the new canvas
    int x = (size - layer.getWidth()) / 2;
    int y = (size - layer.getHeight()) / 2;
    newImage.getPixelWriter().setPixels(x,y,layer.getWidth(),layer.getHeight(),croppedReader,0,0);

    return newImage;
  }


}

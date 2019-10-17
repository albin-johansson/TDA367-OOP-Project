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
    //Makes a Copy of the layer and changes X and Y values so it's well inside the canvas
    ILayer layerCopy = layer.copy();
    int size;

    if(layerCopy.getRotation() != 0 ){
      size = (int) Math.hypot(layerCopy.getWidth(), layerCopy.getHeight());
    }else{
      size = Math.max(layer.getWidth(),layer.getHeight()) + 10;
    }

    layerCopy.setX(size);
    layerCopy.setY(size);
    int cropX = layerCopy.getX() - ((size - layerCopy.getWidth()) / 2);
    int cropY = layerCopy.getY() - ((size - layerCopy.getHeight()) / 2);

    //Draws the Layer on Canvas and takes a snapshot
    Canvas canvas = new Canvas(layerCopy.getX() + size,
        layerCopy.getY() + size);
    IRenderer renderer = RendererFactory.createFXRenderer(canvas.getGraphicsContext2D());
    layerCopy.draw(renderer);
    WritableImage image = canvas.snapshot(new SnapshotParameters(), null);

    //Crops the Image with the layer focused in the middle and a small margin
    PixelReader reader = image.getPixelReader();
    WritableImage croppedImage = new WritableImage(reader, cropX, cropY, size, size);

    return croppedImage;
  }
}

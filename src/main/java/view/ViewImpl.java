package view;

import static service.FXImageService.getFXImage;

import java.awt.Color;
import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import model.IModel;
import model.canvas.layer.IReadOnlyLayer;
import model.pixeldata.IReadOnlyPixelData;
import service.FXImageService;


/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private final IModel model;
  private GraphicsContext graphics;

  /**
   * @param model the associated model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ViewImpl(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void canvasUpdated() {
    repaint();
  }

  @Override
  public void repaint() {
    for (IReadOnlyLayer layer : model.getLayers()) {
      paintLayer(layer);
    }
  }

  //TODO Change color to pixelData interface to gain abstraction

  /**
   * Paints a layer by fetching an JavaFX Image of the pixeldata and drawing on the GraphicContext.
   *
   * @param layer the layer the method is supposed to draw
   * @throws NullPointerException if layer = null
   */
  private void paintLayer(IReadOnlyLayer layer) {
    final int xPos = layer.getX();
    final int yPos = layer.getY();

    Image image = FXImageService.getFXImage(layer.getPixelData());

    graphics.drawImage(image, xPos, yPos);

  }

  @Override
  public void setGraphics(GraphicsContext graphics) {
    this.graphics = graphics;
  }
}
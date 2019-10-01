package chalmers.pimp.view;

import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import chalmers.pimp.model.IModel;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.service.FXImageService;
import javafx.scene.paint.Color;

/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private final IModel model;
  private GraphicsContext graphics;

  /**
   * @param model the associated chalmers.pimp.model instance.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  ViewImpl(IModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void canvasUpdated() {
    repaint();
  }

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

  //TODO Change color to pixelData interface to gain abstraction

  @Override
  public void setGraphics(GraphicsContext graphics) {
    this.graphics = graphics;
  }

  @Override
  public void repaint() {
    graphics.setFill(Color.WHITE);
    graphics.fillRect(0, 0, graphics.getCanvas().getWidth(), graphics.getCanvas().getHeight());

    for (IReadOnlyLayer layer : model.getLayers()) {
      paintLayer(layer);
    }
  }
}
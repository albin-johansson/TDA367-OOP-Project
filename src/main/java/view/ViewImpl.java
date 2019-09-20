package view;

import java.awt.Color;
import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import model.IModel;
import model.canvas.layer.IReadOnlyLayer;
import model.pixeldata.IReadOnlyPixelData;


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
   * Paints a layer by using a PixelWriter to update the GraphicContext.
   * Iterates over every List of Colors in pixel data and copies pixel information.
   *
   * @throws NullPointerException if layer = null
   * @param layer the layer the method is supposed to draw
   */
  private void paintLayer(IReadOnlyLayer layer) {
    final int startXPos = layer.getX();
    final int startYPos = layer.getY();
    int offsetX = 0;
    int offsetY = 0;

    PixelWriter pw = graphics.getPixelWriter();

    for (Iterable<Color> pixelDataRow : layer.getPixelData().getPixels()) {
      for (Color c : pixelDataRow) {
        pw.setColor(startXPos + offsetX, startYPos + offsetY,
            new javafx.scene.paint.Color(c.getRed()/255, c.getGreen()/255, c.getBlue()/255, c.getAlpha()/255));
        offsetX++;
      }

      offsetY++;
      offsetX = 0;

    }
  }

  @Override
  public void setGraphics(GraphicsContext graphics) {
    this.graphics = graphics;
  }
}
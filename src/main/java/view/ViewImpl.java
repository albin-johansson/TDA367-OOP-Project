package view;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import model.IModel;


/**
 * The {@code ViewImpl} class is an implementation of the {@code IView} interface.
 */
final class ViewImpl implements IView {

  private final IModel model;
  private GraphicsContext gc;


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

  /**
   * Repaints the canvas by fetching the layers in model.
   */
  @Override
  public void repaint() {
    PixelWriter pw = gc.getPixelWriter();

  }


  @Override
  public void setGraphics(GraphicsContext gc) {

  }
}
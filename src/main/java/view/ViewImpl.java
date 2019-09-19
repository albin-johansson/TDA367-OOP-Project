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
    PixelWriter pw = graphics.getPixelWriter();

  }

  @Override
  public void setGraphics(GraphicsContext graphics) {
    this.graphics = graphics;
  }
}
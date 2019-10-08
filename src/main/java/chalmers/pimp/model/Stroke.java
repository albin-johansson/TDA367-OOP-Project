package chalmers.pimp.model;

import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.pixeldata.PixelFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Stroke} class represents a mouse stroke that affects pixels in some way.
 */
public final class Stroke {

  private final List<IPixel> pixels;
  private final ModelMemento modelMemento;
  private final int diameter;

  /**
   * @param modelMemento the model memento object, that represents the current model state.
   * @param diameter     the diameter of the stroke, in pixels.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  Stroke(ModelMemento modelMemento, int diameter) {
    this.modelMemento = Objects.requireNonNull(modelMemento);
    this.diameter = diameter;
    pixels = new ArrayList<>(10);
  }

  /**
   * Adds the supplied pixel to the collection of affected pixels.
   *
   * @param pixel the pixel that will be added.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void add(IPixel pixel) {
    Objects.requireNonNull(pixel);
    pixels.add(pixel);
  }

  /**
   * Updates the state of the pixels in the model.
   *
   * @param model the associated model instance.
   * @param pixel the pixel affected by the stroke.
   * @throws NullPointerException if any arguments are {@code null}.
   */
  public void updatePixels(ICanvas model, IPixel pixel) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(pixel);

    var pixels = new PixelData(diameter, diameter);
    IReadOnlyColor color = pixel.getColor();

    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        IPixel p = PixelFactory.createPixel(col, row, color);
        pixels.setPixel(p);
      }
    }

    int radius = (int) (diameter / 2.0);
    model.setActiveLayerPixels(pixel.getX() - radius, pixel.getY() - radius, pixels);
  }

  /**
   * Returns the model memento associated with this stroke.
   *
   * @return the model memento associated with this stroke.
   */
  public ModelMemento getModelMemento() {
    return modelMemento;
  }

  /**
   * Returns all of the origin pixels contained in this stroke.
   *
   * @return all of the pixels contained in this stroke.
   */
  public Iterable<IPixel> getPixels() {
    return pixels;
  }
}
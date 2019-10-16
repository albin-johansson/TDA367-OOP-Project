package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.service.ColorConverterService;
import chalmers.pimp.service.PixelDataToFXService;
import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The {@code FXRenderer} is a implementation of the {@code IRenderer} interface.
 *
 * @see IRenderer
 */
final class FXRenderer implements IRenderer {

  private final GraphicsContext graphicsContext;

  /**
   * Creates and returns a FX renderer.
   *
   * @param graphicsContext the graphics context to manipulate.
   * @throws NullPointerException if the graphics context is null.
   */
  FXRenderer(GraphicsContext graphicsContext) {
    this.graphicsContext = Objects.requireNonNull(graphicsContext);
  }

  @Override
  public void clear() {
    graphicsContext.fillRect(0, 0, getCanvasWidth(), getCanvasHeight());
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    graphicsContext.rect(x, y, width, height);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    graphicsContext.fillRect(x, y, width, height);
  }

  @Override
  public void drawEllipse(int x, int y, int radiusX, int radiusY) {
  }

  @Override
  public void fillEllipse(int x, int y, int radiusX, int radiusY) {
  }

  @Override
  public void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y, int width, int height) {
    Image image = PixelDataToFXService.getFXImage(readOnlyPixelData);
    graphicsContext.drawImage(image, x, y);
  }

  @Override
  public void drawText(String content, int x, int y, int fontSize) {
    if (content != null) {
      graphicsContext.fillText(content, x, y, fontSize);
    }
  }

  @Override
  public void setRotation(int rotation) {

  }

  @Override
  public void setFillColor(IColor color) {
    graphicsContext.setFill(ColorConverterService.toFXColor(color));
  }

  @Override
  public void setBorderColor(IColor color) {
    graphicsContext.setStroke(ColorConverterService.toFXColor(color));
  }

  @Override
  public void setBorderWidth(int width) {
    graphicsContext.setLineWidth(width);
  }

  @Override
  public int getCanvasWidth() {
    return (int) graphicsContext.getCanvas().getWidth() + 1;
  }

  @Override
  public int getCanvasHeight() {
    return (int) graphicsContext.getCanvas().getHeight() + 1;
  }
}

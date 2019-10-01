package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.service.FXImageService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The {@code FXRenderer} is a implementation of the {@code IRenderer} interface.
 *
 * @see IRenderer
 */
final class FXRenderer implements IRenderer {

  private final GraphicsContext graphicsContext;

  FXRenderer(GraphicsContext graphicsContext) {
    this.graphicsContext = graphicsContext;
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
    Image image = FXImageService.getFXImage(readOnlyPixelData);
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
    graphicsContext.setFill(toPaint(color));
  }

  @Override
  public void setBorderColor(IColor color) {
    graphicsContext.setStroke(toPaint(color));
  }

  @Override
  public void setBorderWidth(int width) {
    graphicsContext.setLineWidth(width);
  }

  /**
   * TODO: Replace with service when implemented.
   */
  private Paint toPaint(IColor color) {
    return Color.color(color.getRedPercentage(), color.getGreenPercentage(),
        color.getBluePercentage());
  }
}

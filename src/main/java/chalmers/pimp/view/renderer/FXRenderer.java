package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IReadOnlyColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.service.ColorConverterService;
import chalmers.pimp.service.PixelDataToFXService;
import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

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
    graphicsContext.save(); // Save default transform
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
  public void startTransform(double rotation, Point startPoint, int width, int height) {
    endTransform();
    graphicsContext.save(); // Save default transform
    Affine rotate = new Affine();
    double centerX = startPoint.getX() + width / 2;
    double centerY = startPoint.getY() + height / 2;
    rotate.appendRotation(rotation, centerX, centerY);
    graphicsContext.setTransform(rotate);
  }

  @Override
  public void drawLine(Point p1, Point p2) {
    graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  @Override
  public void endTransform() {
    graphicsContext.restore();
  }

  @Override
  public void setGlobalAlpha(double alpha) {
    graphicsContext.setGlobalAlpha(alpha);
  }

  @Override
  public void setFillColor(IReadOnlyColor color) {
    graphicsContext.setFill(ColorConverterService.toFXColor(color));
  }

  @Override
  public void setBorderColor(IReadOnlyColor color) {
    graphicsContext.setStroke(ColorConverterService.toFXColor(color));
  }

  @Override
  public void setBorderWidth(int width) {
    graphicsContext.setLineWidth(width);
  }

  @Override
  public void setLineWidth(int width) {
    graphicsContext.setLineWidth(width);
  }

  @Override
  public int getCanvasWidth() {
    return (int) graphicsContext.getCanvas().getWidth();
  }

  @Override
  public int getCanvasHeight() {
    return (int) graphicsContext.getCanvas().getHeight();
  }
}

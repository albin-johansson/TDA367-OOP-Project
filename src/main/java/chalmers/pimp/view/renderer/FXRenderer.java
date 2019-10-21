package chalmers.pimp.view.renderer;

import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import chalmers.pimp.service.ColorConverterService;
import chalmers.pimp.service.PixelDataToFXService;
import java.util.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Affine;

/**
 * The {@code FXRenderer} is a implementation of the {@code IRenderer} interface.
 *
 * @see IRenderer
 */
final class FXRenderer implements IRenderer {

  private final GraphicsContext graphicsContext;
  private final BoxBlur lineBlurEffect;

  /**
   * Creates and returns a FX renderer.
   *
   * @param graphicsContext the graphics context to manipulate.
   * @throws NullPointerException if the graphics context is null.
   */
  FXRenderer(GraphicsContext graphicsContext) {
    this.graphicsContext = Objects.requireNonNull(graphicsContext);

    lineBlurEffect = new BoxBlur();
    lineBlurEffect.setWidth(1);
    lineBlurEffect.setHeight(1);
    lineBlurEffect.setIterations(3);

    graphicsContext.setLineJoin(StrokeLineJoin.ROUND); // should make strokes a little bit smoother
  }

  @Override
  public void clear() {
    graphicsContext.fillRect(0, 0, getCanvasWidth(), getCanvasHeight());
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    // Upper left -> Upper Right
    graphicsContext.strokeLine(x, y, (x + width), y);

    // Upper right -> Lower right
    graphicsContext.strokeLine((x + width), y, (x + width), (y + height));

    // Lower right -> lower left
    graphicsContext.strokeLine((x + width), (y + height), x, (y + height));

    // Lower left -> Upper left
    graphicsContext.strokeLine(x, (y + height), x, y);
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
  public void drawImage(IReadOnlyPixelData readOnlyPixelData, int x, int y) {
    if (readOnlyPixelData == null) {
      return;
    }
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
  public void startTransform(double rotation, Point centerPoint) {
    if (centerPoint == null) {
      return;
    }
    endTransform();
    graphicsContext.save(); // Save default transform

    var rotate = new Affine();
    rotate.appendRotation(rotation, centerPoint.getX(), centerPoint.getY());
    graphicsContext.setTransform(rotate);
  }

  @Override
  public void drawLine(Point p1, Point p2) {
    if ((p1 == null) || (p2 == null)) {
      return;
    }
    graphicsContext.setEffect(lineBlurEffect);
    graphicsContext.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    graphicsContext.setEffect(null);
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
  public void setLineWidth(int width) {
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

package chalmers.pimp.view.renderer;

import static chalmers.pimp.service.RasterDataService.toFXImage;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;

import chalmers.pimp.model.IArea;
import chalmers.pimp.model.IRenderer;
import chalmers.pimp.model.Point;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.pixeldata.IReadOnlyRasterData;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;

/**
 * The {@code SwingRenderer} class is an implementation of the {@code IRenderer} interface that uses
 * a Swing/AWT {@code Graphics2D} instance.
 *
 * @see IRenderer
 * @see Graphics2D
 */
final class SwingRenderer implements IRenderer {

  private final Graphics2D graphics;
  private final IArea area;
  private Color borderColor;
  private Color fillColor;
  private int rotation;
  private int lineWidth;

  /**
   * @param graphics the graphics instance that will be used internally.
   * @param area     the area that represents the rendering target.
   * @throws NullPointerException if any references are {@code null}.
   */
  SwingRenderer(Graphics2D graphics, IArea area) {
    this.graphics = Objects.requireNonNull(graphics);
    this.area = Objects.requireNonNull(area);

    graphics.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);

    borderColor = Color.BLACK;
    fillColor = Color.BLACK;
    rotation = 0;
  }

  /**
   * Creates and return a Swing/AWT equivalent of the supplied color.
   *
   * @param color the color that the created color will be based on.
   * @return a Swing/AWT equivalent of the supplied color.
   * @throws NullPointerException if the supplied color is {@code null}.
   */
  private Color toSwingColor(IColor color) {
    Objects.requireNonNull(color);
    return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  @Override
  public void clear() {
    fillRect(0, 0, area.getWidth(), area.getHeight());
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    graphics.setColor(borderColor);
    graphics.drawRect(0, 0, area.getWidth(), area.getHeight());
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    graphics.setColor(fillColor);
    graphics.fillRect(x, y, width, height);
  }

  @Override
  public void drawImage(IReadOnlyRasterData readOnlyPixelData, int x, int y) {
    if (readOnlyPixelData != null) {
      Image img = SwingFXUtils.fromFXImage(toFXImage(readOnlyPixelData), null);
      graphics.drawImage(img, x, y, null);
    }
  }

  @Override
  public void drawLine(Point p1, Point p2) {
    if ((p1 != null) && (p2 != null)) {
      graphics.setColor(fillColor);
      graphics.setStroke(new BasicStroke(lineWidth));
      graphics.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
  }

  @Override
  public void startTransform(double rotation, Point centerPoint) {

  }

  @Override
  public void endTransform() {

  }

  @Override
  public void setGlobalAlpha(double alpha) {

  }

  @Override
  public void setFillColor(IColor color) {
    if (color != null) {
      fillColor = toSwingColor(color);
    }
  }

  @Override
  public void setBorderColor(IColor color) {
    if (color != null) {
      borderColor = toSwingColor(color);
    }
  }

  @Override
  public void setLineWidth(int width) {
    lineWidth = width;
  }

  @Override
  public int getCanvasWidth() {
    return area.getWidth();
  }

  @Override
  public int getCanvasHeight() {
    return area.getHeight();
  }
}
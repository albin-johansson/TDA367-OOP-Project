package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import java.util.Objects;

/**
 * The {@code PixelImpl} class is an implementation of the {@code IPixel} interface.
 *
 * @see IPixel
 */
final class PixelImpl implements IPixel {

  private final int x;
  private final int y;
  private IColor color;

  /**
   * @param x the x-coordinate of the pixel (the column index).
   * @param y the y-coordinate of the pixel (the row index).
   */
  PixelImpl(int x, int y) {
    this.x = x;
    this.y = y;

    color = ColorFactory.createColor();
  }

  /**
   * @param x     the x-coordinate of the pixel (the column index).
   * @param y     the y-coordinate of the pixel (the row index).
   * @param color the color of the pixel.
   */
  PixelImpl(int x, int y, IColor color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  /**
   * Creates a copy of the supplied pixel.
   *
   * @param pixel the pixel that will be copied.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  PixelImpl(IReadOnlyPixel pixel) {
    Objects.requireNonNull(pixel);
    x = pixel.getX();
    y = pixel.getY();
    color = pixel.getColor();
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setRed(double red) {
    color = color.setPercentageRed(red);
  }

  @Override
  public void setGreen(double green) {
    color = color.setPercentageGreen(green);
  }

  @Override
  public void setBlue(double blue) {
    color = color.setPercentageBlue(blue);
  }

  @Override
  public void setAlpha(double alpha) {
    color = color.setPercentageAlpha(alpha);
  }

  @Override
  public IColor getColor() {
    return color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, color);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IPixel)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    var pixel = (IPixel) obj;
    return color.equals(pixel.getColor());
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "X: " + x + ", Y: " + y + ", Color: " + color;
    return "(" + id + " | " + state + ")";
  }
}
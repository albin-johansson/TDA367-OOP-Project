package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.IReadOnlyColor;
import java.util.Objects;

/**
 * The {@code PixelImpl} class is an implementation of the {@code IPixel} interface.
 *
 * @see IPixel
 */
final class PixelImpl implements IPixel {

  private final int x;
  private final int y;
  private final IColor color;

  /**
   * @param x the x-coordinate of the pixel (the column index).
   * @param y the y-coordinate of the pixel (the row index).
   */
  PixelImpl(int x, int y) {
    this.x = x;
    this.y = y;

    color = ColorFactory.createColor();
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
    color.setPercentageRed(red);
  }

  @Override
  public void setGreen(double green) {
    color.setPercentageGreen(green);
  }

  @Override
  public void setBlue(double blue) {
    color.setPercentageBlue(blue);
  }

  @Override
  public void setAlpha(double alpha) {
    color.setPercentageAlpha(alpha);
  }

  @Override
  public IReadOnlyColor getColor() {
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
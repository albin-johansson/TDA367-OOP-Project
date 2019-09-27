package model.pixeldata;

import java.util.Objects;
import model.color.ColorFactory;
import model.color.IColor;
import model.color.IReadOnlyColor;

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
  public double getRed() {
    return color.getRedPercentage();
  }

  @Override
  public void setRed(double red) {
    color.setPercentageRed(red);
  }

  @Override
  public double getGreen() {
    return color.getGreenPercentage();
  }

  @Override
  public void setGreen(double green) {
    color.setPercentageGreen(green);
  }

  @Override
  public double getBlue() {
    return color.getBluePercentage();
  }

  @Override
  public void setBlue(double blue) {
    color.setPercentageBlue(blue);
  }

  @Override
  public double getAlpha() {
    return color.getAlphaPercentage();
  }

  @Override
  public void setAlpha(double alpha) {
    color.setPercentageAlpha(alpha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, getRed(), getGreen(), getBlue());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IPixel)) {
      return false;
    }

    IPixel pixel = (IPixel) obj;

    return (pixel.getRed() == getRed()) && (pixel.getGreen() == getGreen()) && (pixel.getBlue()
        == getBlue()) && (
        pixel.getAlpha() == getAlpha());
  }

  @Override
  public IReadOnlyColor getColor() {
    return color;
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state =
        "Red: " + getRed() + ", Green: " + getGreen() + ", Blue: " + getBlue() + ", Alpha: "
            + getAlpha();
    return "(" + id + " | " + state + ")";
  }
}
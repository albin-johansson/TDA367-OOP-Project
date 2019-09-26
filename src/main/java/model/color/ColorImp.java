package model.color;

public class ColorImp implements IColor {

  private int red;
  private int green;
  private int blue;
  private int alpha;

  ColorImp(int red, int green, int blue, int alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  @Override
  public void setColor(String hex) {

  }

  @Override
  public void setRed(int red) {

  }

  @Override
  public void setGreen(int green) {

  }

  @Override
  public void setBlue(int blue) {

  }

  @Override
  public void setPercentageRed(double red) {

  }

  @Override
  public void setPercentageGreen(double green) {

  }

  @Override
  public void setPercentageBlue(double blue) {

  }

  @Override
  public int getRed() {
    return 0;
  }

  @Override
  public int getGreen() {
    return 0;
  }

  @Override
  public int getBlue() {
    return 0;
  }

  @Override
  public double getRedPercentage() {
    return 0;
  }

  @Override
  public double getGreenPercentage() {
    return 0;
  }

  @Override
  public double getBluePercentage() {
    return 0;
  }

  @Override
  public String getAlphaHexCode() {
    return null;
  }

  @Override
  public String getHexCode() {
    return null;
  }
}

package chalmers.pimp.model.pixeldata;

import chalmers.pimp.model.color.IReadOnlyColor;

public interface IReadOnlyPixel {

  /**
   * Returns the x-coordinate of this pixel.
   *
   * @return the x-coordinate of this pixel.
   */
  int getX();

  /**
   * Returns the y-coordinate of this pixel.
   *
   * @return the y-coordinate of this pixel.
   */
  int getY();

  /**
   * Returns the value of the red component of this pixel. By default this property is set to 0.
   *
   * @return the value of the red component of this pixel, in the range [0, 1].
   */
  double getRed();

  /**
   * Returns the value of the green component of this pixel. By default this property is set to 0.
   *
   * @return the value of the green component of this pixel, in the range [0, 1].
   */
  double getGreen();

  /**
   * Returns the value of the blue component of this pixel. By default this property is set to 0.
   *
   * @return the value of the blue component of this pixel, in the range [0, 1].
   */
  double getBlue();

  /**
   * Returns the value of the alpha (transparency) component of this pixel. By default this property
   * is set to 1.
   *
   * @return the value of the alpha component of this pixel, in the range [0, 1].
   */
  double getAlpha();

  /**
   * Indicates whether or not the supplied object is considered "equal" to this object. The
   * following statements must be true in order to qualify the objects as equal:
   * <ul>
   * <li>the supplied object is a pixel instance.</li>
   * <li>the objects have equal red component values.</li>
   * <li>the objects have equal green component values.</li>
   * <li>the objects have equal blue component values.</li>
   * <li>the objects have equal alpha component values.</li>
   * </ul>
   * <p>
   * Note! Two pixels instances do <b>not</b> need to have the same coordinates in order to be
   * considered equal.
   *
   * @param obj the object that will be checked.
   * @return {@code true} if the supplied object is considered equal to this object; {@code false}
   * otherwise.
   */
  @Override
  boolean equals(Object obj);

  /**
   * Returns a readable color of the pixel.
   *
   * @return a readable color.
   */
  IReadOnlyColor getColor();
}

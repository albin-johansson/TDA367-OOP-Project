package chalmers.pimp.model.pixeldata;

/**
 * The {@code IReadOnlyRasterData} interface specifies objects that represent some kind of pixel
 * data that.
 */
public interface IRasterData extends IReadOnlyRasterData {

  /**
   * Sets a specific pixel in the pixel matrix. Origin is positioned at top left corner and is zero
   * indexed.
   *
   * @param pixel the pixel to be set.
   * @throws NullPointerException if the supplied pixel is {@code null}.
   */
  void setPixel(IPixel pixel);
}

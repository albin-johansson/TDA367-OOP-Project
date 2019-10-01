package chalmers.pimp.model;

/**
 * The {@code IDrawable} interface specifies objects that can be drawn.
 */
public interface IDrawable {

  /**
   * Draw based on a specific render implementation.
   *
   * @param renderer the renderer that specifies the specific draw implementation.
   */
  void draw(IRenderer renderer);
}

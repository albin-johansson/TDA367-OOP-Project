package chalmers.pimp.model;

import chalmers.pimp.model.viewport.IReadOnlyViewport;

/**
 * The {@code IDrawable} interface specifies objects that can be drawn.
 */
public interface IDrawable {

  /**
   * Draw based on a specific render implementation.
   *
   * @param renderer the renderer that specifies the specific draw implementation.
   * @param viewport the the viewport that will be used.
   */
  void draw(IRenderer renderer, IReadOnlyViewport viewport);
}

package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import java.awt.Color;

/**
 * The {@code ToolFactory} class is a factory for creating instances of the {@code ITool}
 * interface.
 */
public class ToolFactory {

  /**
   * Creates and returns a pencil with the desired width and color.
   *
   * @param width the diameter of the pencils stroke.
   * @param color the color of the pencils stroke.
   * @return a pencil.
   */
  public static ITool createPencil(int width, Color color, IModel model) {
    return new Pencil(width, color, model);
  }

  /**
   * Creates and returns a black pencil with the stroke diameter of 2.
   *
   * @return a black pencil with the stroke of 2.
   */
  public static ITool createPencil(IModel model) {
    return createPencil(2, Color.BLACK, model);
  }
}

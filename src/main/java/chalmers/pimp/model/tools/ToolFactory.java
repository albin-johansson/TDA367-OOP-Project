package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.color.IColor;

/**
 * The {@code ToolFactory} class is a factory for creating instances of the {@code ITool}
 * interface.
 */
public final class ToolFactory {

  private ToolFactory() {
  }

  /**
   * Creates and returns a pencil with the desired diameter and color.
   *
   * @param diameter the diameter of the pencil.
   * @param color    the color of the pencil.
   * @return a tool that represents a pencil.
   */
  public static ITool createPencil(int diameter, IColor color, IModel model) {
    return new Pencil(diameter, color, model);
  }

  /**
   * Creates and returns a black pencil with the stroke diameter of 2.
   *
   * @param model a reference to the model.
   * @return a black pencil with the stroke of 2.
   */
  public static ITool createPencil(IModel model) {
    return createPencil(2, ColorFactory.createColor(0, 0, 0), model);
  }

  /**
   * Creates and returns a shape tool.
   *
   * @param model a reference to the model
   * @return a shape tool that appends rectangles.
   */
  public static ITool createShapeTool(IModel model) {
    return new ShapeTool(model);
  }
}

package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
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
   * Creates and returns a MoveTool.
   *
   * @param model a reference to the Model.
   * @return a MoveTool with no startX or startY.
   */
  public static ITool createMoveTool(IModel model) {
    return new MoveTool(model);
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

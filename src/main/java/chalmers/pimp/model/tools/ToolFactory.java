package chalmers.pimp.model.tools;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.color.Colors;

/**
 * The {@code ToolFactory} class is a factory for creating instances of the {@code ITool}
 * interface.
 */
public final class ToolFactory {

  private ToolFactory() {
  }

  /**
   * Creates and returns a raster pen. The created raster pen will use the currently selected model
   * color.
   *
   * @param diameter the diameter of the pencil.
   * @param model    the associated model instance.
   * @return a tool that represents a raster pen.
   * @throws NullPointerException     if the supplied model is {@code null}.
   * @throws IllegalArgumentException if the supplied diameter is less than 1.
   */
  public static ITool createRasterPen(int diameter, IModel model) {
    return new RasterPen(diameter, model);
  }

  public static ITool createRasterEraser(int diameter, IModel model) {
    return new RasterPen(diameter, model, Colors.TRANSPARENT);
  }

  /**
   * Creates and returns a move tool.
   *
   * @param model the associated model instance.
   * @return a move tool.
   * @throws NullPointerException if the supplied model is {@code null}.
   */
  public static ITool createMoveTool(IModel model) {
    return new MoveTool(model);
  }

  /**
   * Create and returns a rotation tool.
   *
   * @param model the associated model instance.
   * @return a rotation tool with a current rotation of 0 degrees.
   * @throws NullPointerException if the supplied model is {@code null}.
   */
  public static ITool createRotateTool(IModel model) {
    return new RotateTool(model);
  }

  /**
   * Creates and returns a shape tool.
   *
   * @param model the associated model instance.
   * @return a shape tool that appends rectangles.
   * @throws NullPointerException if the supplied model is {@code null}.
   */
  public static ITool createShapeTool(IModel model) {
    return new ShapeTool(model);
  }

  /**
   * Creates and returns a doodle tool.
   *
   * @param diameter the diameter of the stroke line.
   * @param model    the associated model instance.
   * @return a tool which represents a doodle tool.
   * @throws NullPointerException if the supplied model is {@code null}.
   */
  public static ITool createDoodleTool(int diameter, IModel model) {
    return new DoodleTool(diameter, model);
  }
}
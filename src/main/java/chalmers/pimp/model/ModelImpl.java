package chalmers.pimp.model;

import static chalmers.pimp.model.command.CommandFactory.createAddLayerCommand;
import static chalmers.pimp.model.command.CommandFactory.createChangeLayerDepthCommand;
import static chalmers.pimp.model.command.CommandFactory.createLayerSelectionCommand;
import static chalmers.pimp.model.command.CommandFactory.createMoveCommand;
import static chalmers.pimp.model.command.CommandFactory.createRemoveLayerCommand;
import static chalmers.pimp.model.command.CommandFactory.createStrokeCommand;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.command.CommandManager;
import chalmers.pimp.model.command.ICommand;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import chalmers.pimp.model.viewport.IViewportModel;
import chalmers.pimp.model.viewport.ViewportModelFactory;
import java.util.Objects;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 *
 * @see IModel
 * @see ModelFactory
 */
final class ModelImpl implements IModel {

  private final CommandManager commandManager;
  private final ICanvas canvas;
  private final IViewportModel viewportModel;

  private IRenderer renderer;
  private LayerMovement layerMovement;
  private Stroke stroke; // TODO remove
  private ITool selectedTool;
  private int width;
  private int height;

  ModelImpl() {
    commandManager = new CommandManager();

    canvas = CanvasFactory.createCanvas();
    viewportModel = ViewportModelFactory.createViewportModel();

    width = 800;
    height = 600;

    stroke = null;
    selectedTool = ToolFactory.createPencil(2, ColorFactory.createColor(0xFF, 0, 0xFF), this);
  }

  /**
   * Indicates whether or not there is a active tool.
   *
   * @return {@code true} if there is a selected tool; {@code false} otherwise.
   */
  private boolean hasSelectedTool() {
    return selectedTool != null;
  }

  @Override
  public void notifyCanvasUpdateListeners() {
    canvas.notifyCanvasUpdateListeners();
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }

  @Override
  public void addUndoRedoListener(IUndoRedoListener listener) {
    commandManager.addUndoRedoListener(listener);
  }

  @Override
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    canvas.addLayerUpdateListener(listener);
  }

  @Override
  public void draw(IRenderer renderer) {
    Objects.requireNonNull(renderer);
    for (IDrawable drawable : getLayers()) {
      drawable.draw(renderer, viewportModel.getViewport());
    }
  }

  @Override
  public void moveViewport(int dx, int dy) {
    viewportModel.moveViewport(dx, dy);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void centerViewport() {
    viewportModel.center(width, height);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setViewportWidth(int width) {
    viewportModel.setWidth(width);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setViewportHeight(int height) {
    viewportModel.setHeight(height);
    notifyCanvasUpdateListeners();
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public IReadOnlyViewport getViewport() {
    return viewportModel.getViewport();
  }

  @Override
  public void startMovingActiveLayer(int x, int y) {
    if (getActiveLayer() == null) {
      return;
    }

    layerMovement = new LayerMovement();
    layerMovement.start(x, y, createSnapShot());
  }

  @Override
  public void updateMovingActiveLayer(int x, int y) {
    if (layerMovement == null) {
      return;
    }

    layerMovement.update(x, y);
    moveActiveLayer(layerMovement.getDx(), layerMovement.getDy());
  }

  @Override
  public void stopMovingActiveLayer() {
    if (layerMovement == null) {
      return;
    }

    layerMovement.stop();
    layerMovement.setEndX(canvas.getActiveLayer().getX());
    layerMovement.setEndY(canvas.getActiveLayer().getY());

    ICommand cmd = createMoveCommand(canvas, this, getActiveLayer().getDepthIndex(), layerMovement);
    commandManager.insertCommand(cmd);
    layerMovement = null;
  }

  @Override
  public void startStroke(IPixel pixel, int diameter) {
    Objects.requireNonNull(pixel);
    stroke = new Stroke(createSnapShot(), diameter);
    updateStroke(pixel);
  }

  @Override
  public void updateStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(canvas, pixel);
      notifyCanvasUpdateListeners();
    }
  }

  @Override
  public void endStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(canvas, pixel);

      // We don't need to explicitly execute the created command, the effect is already present
      ICommand cmd = createStrokeCommand(canvas, this, stroke);

      commandManager.insertCommand(cmd);
      stroke = null;
    }
  }

  @Override
  public void addLayer(ILayer layer) {
    ICommand cmd = createAddLayerCommand(canvas, this, layer);
    cmd.execute();

    commandManager.insertCommand(cmd);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void removeLayer(int layerIndex) {
    ICommand cmd = createRemoveLayerCommand(canvas, this, layerIndex);
    cmd.execute();

    commandManager.insertCommand(cmd);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void selectLayer(int layerIndex) {
    ICommand cmd = createLayerSelectionCommand(canvas, this, layerIndex);
    cmd.execute();

    commandManager.insertCommand(cmd);
  }

  @Override
  public void changeLayerDepthIndex(int layerIndex, int dz) {
    ICommand cmd = createChangeLayerDepthCommand(canvas, this, layerIndex, dz);
    cmd.execute();

    commandManager.insertCommand(cmd);
  }

  @Override
  public void setActiveLayerPixel(IPixel pixel) {
    canvas.setActiveLayerPixel(pixel);
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    canvas.setLayerName(layerIndex, layerName);
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    canvas.setLayerVisibility(layerIndex, isVisible);
  }

  @Override
  public void moveActiveLayer(int dx, int dy) {
    canvas.moveActiveLayer(dx, dy);
  }

  @Override
  public void setSelectedTool(ITool selectedTool) {
    this.selectedTool = selectedTool;
  }

  @Override
  public void selectedToolPressed(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.pressed(mouseStatus);
    }
  }

  @Override
  public void selectedToolDragged(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.dragged(mouseStatus);
    }
  }

  @Override
  public void selectedToolReleased(MouseStatus mouseStatus) {
    if (hasSelectedTool()) {
      selectedTool.released(mouseStatus);
    }
  }

  @Override
  public void setRenderer(IRenderer renderer) {
    this.renderer = Objects.requireNonNull(renderer);
  }

  @Override
  public boolean isLayerVisible(int layerIndex) {
    return canvas.isLayerVisible(layerIndex);
  }

  @Override
  public String getLayerName(int layerIndex) {
    return canvas.getLayerName(layerIndex);
  }

  @Override
  public IRenderer getRenderer() {
    return renderer;
  }

  @Override
  public IReadOnlyLayer getActiveLayer() {
    return canvas.getActiveLayer();
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return canvas.getLayers();
  }

  @Override
  public void restore(ModelMemento modelMemento) {
    canvas.restore(modelMemento.getCanvasMemento());
    viewportModel.restore(modelMemento.getViewportModelMemento());
  }

  @Override
  public ModelMemento createSnapShot() {
    return new ModelMemento(canvas.createSnapShot(), viewportModel.createSnapShot());
  }

  @Override
  public void undo() {
    commandManager.undo();
  }

  @Override
  public void redo() {
    commandManager.redo();
  }
}
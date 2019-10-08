package chalmers.pimp.model;

import static chalmers.pimp.model.command.CommandFactory.createMoveCommand;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.ColorFactory;
import chalmers.pimp.model.command.CommandFactory;
import chalmers.pimp.model.command.CommandManager;
import chalmers.pimp.model.command.ICommand;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.PixelData;
import chalmers.pimp.model.tools.ITool;
import chalmers.pimp.model.tools.ToolFactory;
import java.util.Objects;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final ICanvas canvas;
  private final CommandManager commandManager;

  private IRenderer renderer;
  private Stroke stroke;
  private LayerMovement layerMovement;
  private ITool selectedTool;

  ModelImpl() {
    canvas = CanvasFactory.createCanvas();
    commandManager = new CommandManager();

    stroke = null;
    selectedTool = ToolFactory.createPencil(2, ColorFactory.createColor(0xFF, 0, 0xFF), this);
  }

  /**
   * Checks if there is a active tool selected.
   *
   * @return true if there is a tool selected.
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
  public void startMovingLayer(int x, int y) {
    layerMovement = new LayerMovement();
    layerMovement.start(x, y, createSnapShot());
  }

  @Override
  public void updateMovingLayer(int x, int y) {
    if (layerMovement == null) {
      return;
    }

    layerMovement.update(x, y);
    moveActiveLayer(layerMovement.getDx(), layerMovement.getDy());
  }

  @Override
  public void stopMovingLayer() {
    if (layerMovement == null) {
      return;
    }

    int depthIndex = getActiveLayer().getDepthIndex();
    int x = getActiveLayer().getX();
    int y = getActiveLayer().getY();

    ICommand cmd = createMoveCommand(canvas, this, depthIndex, x, y, layerMovement.getMemento());
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

      ICommand command = CommandFactory.createStrokeCommand(canvas, this, stroke);
      commandManager.insertCommand(command);
      stroke = null;
    }
  }

  @Override
  public void addLayer(ILayer layer) {
    ICommand addLayerCmd = CommandFactory.createAddLayerCommand(canvas, this, layer);
    addLayerCmd.execute();

    commandManager.insertCommand(addLayerCmd);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void removeLayer(ILayer layer) {
    canvas.removeLayer(layer); // TODO replace with command
  }

  @Override
  public void removeLayer(int layerIndex) {
    canvas.removeLayer(layerIndex);
  }

  @Override
  public void selectLayer(IReadOnlyLayer layer) {
    canvas.selectLayer(layer);
  }

  @Override
  public void selectLayer(int layerIndex) {
    canvas.selectLayer(layerIndex);
  }

  @Override
  public void moveLayer(IReadOnlyLayer layer, int steps) {
    canvas.changeDepthIndex(layer, steps);
  }

  @Override
  public void setActiveLayerPixel(IPixel pixel) {
    canvas.setActiveLayerPixel(pixel);
  }

  @Override
  public void setActiveLayerPixels(int x, int y, PixelData pixelData) {
    canvas.setActiveLayerPixels(x, y, pixelData);
  }

  @Override
  public void setLayerName(IReadOnlyLayer layer, String layerName) {
    canvas.setLayerName(layer, layerName);
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    canvas.setLayerName(layerIndex, layerName);
  }

  @Override
  public void setLayerVisibility(IReadOnlyLayer layer, boolean isVisible) {
    canvas.setLayerVisibility(layer, isVisible);
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
  }

  @Override
  public ModelMemento createSnapShot() {
    return new ModelMemento(canvas.createSnapShot());
  }

  @Override
  public void undo() {
    commandManager.undo();

    canvas.notifyLayerUpdateListeners();
    canvas.notifyCanvasUpdateListeners();
  }

  @Override
  public void redo() {
    commandManager.redo();

    canvas.notifyLayerUpdateListeners();
    canvas.notifyCanvasUpdateListeners();
  }
}
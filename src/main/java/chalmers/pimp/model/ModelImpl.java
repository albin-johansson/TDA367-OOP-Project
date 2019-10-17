package chalmers.pimp.model;

import static chalmers.pimp.model.command.CommandFactory.createAddLayerCommand;
import static chalmers.pimp.model.command.CommandFactory.createChangeLayerDepthCommand;
import static chalmers.pimp.model.command.CommandFactory.createLayerSelectionCommand;
import static chalmers.pimp.model.command.CommandFactory.createMoveCommand;
import static chalmers.pimp.model.command.CommandFactory.createRemoveLayerCommand;
import static chalmers.pimp.model.command.CommandFactory.createRotateCommand;
import static chalmers.pimp.model.command.CommandFactory.createStrokeCommand;

import chalmers.pimp.model.canvas.CanvasFactory;
import chalmers.pimp.model.canvas.ICanvas;
import chalmers.pimp.model.canvas.ICanvasUpdateListener;
import chalmers.pimp.model.canvas.ILayerUpdateListener;
import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.color.Colors;
import chalmers.pimp.model.color.IColor;
import chalmers.pimp.model.color.colormodel.ColorModelFactory;
import chalmers.pimp.model.color.colormodel.IColorChangeListener;
import chalmers.pimp.model.color.colormodel.IColorModel;
import chalmers.pimp.model.command.CommandFactory;
import chalmers.pimp.model.command.CommandManager;
import chalmers.pimp.model.command.ICommand;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.tools.ITool;
import java.util.Objects;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final ICanvas canvas;
  private final CommandManager commandManager;
  private IRenderer renderer;
  private LayerMovement layerMovement;
  private LayerRotation layerRotation;
  private Stroke stroke;
  private ITool selectedTool;
  private IColorModel colorModel;

  ModelImpl() {
    canvas = CanvasFactory.createCanvas();
    commandManager = new CommandManager();
    stroke = null;
    colorModel = ColorModelFactory.createColorModel(Colors.TRANSPARENT);
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
    layerMovement.setEndX(getActiveLayer().getX());
    layerMovement.setEndY(getActiveLayer().getY());

    ICommand cmd = createMoveCommand(canvas, this, getActiveLayer().getDepthIndex(), layerMovement);
    commandManager.insertCommand(cmd);
    layerMovement = null;
  }

  @Override
  public void startStroke(IPixel pixel, int diameter) {
    Objects.requireNonNull(pixel);
    stroke = new Stroke(createSnapShot(), diameter, colorModel.getColor());
    updateStroke(pixel);
  }

  @Override
  public void updateStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(canvas, pixel, colorModel.getColor());
      notifyCanvasUpdateListeners();
    }
  }

  @Override
  public void endStroke(IPixel pixel) {
    Objects.requireNonNull(pixel);
    if (stroke != null) {
      stroke.add(pixel);
      stroke.updatePixels(canvas, pixel, colorModel.getColor());

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
  public void startRotatingActiveLayer(int x, int y) {
    if (getActiveLayer() == null) {
      return;
    }

    layerRotation = new LayerRotation();
    Point tempPoint = new Point(x, y);
    layerRotation
        .start(canvas.getActiveLayer().getRotationAnchor(), canvas.getActiveLayer().getRotation(),
            tempPoint,
            createSnapShot());
  }

  @Override
  public void updateRotatingActiveLayer(int x, int y) {
    if (layerRotation == null) {
      return;
    }
    layerRotation.update(x, y);
    rotateActiveLayer(layerRotation.getCurrentDegree());
  }

  @Override
  public void stopRotatingActiveLayer() {
    if (layerRotation == null) {
      return;
    }
    layerRotation.stop();
    ICommand cmd = createRotateCommand(canvas, this, getActiveLayer().getDepthIndex(),
        layerRotation);
    commandManager.insertCommand(cmd);
    layerRotation = null;
  }

  @Override
  public void rotateActiveLayer(double alpha) {
    canvas.setActiveLayerRotation(alpha);
    notifyCanvasUpdateListeners();
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
  public void addColorChangeListener(IColorChangeListener listener) {
    colorModel.addColorChangeListener(listener);
  }

  @Override
  public void restore(ModelMemento modelMemento) {
    canvas.restore(modelMemento.getCanvasMemento());
    colorModel.restore(modelMemento.getColorModelMemento());
  }

  @Override
  public ModelMemento createSnapShot() {
    return new ModelMemento(canvas.createSnapShot(), colorModel.createSnapShot());
  }

  @Override
  public void undo() {
    commandManager.undo();
  }

  @Override
  public void redo() {
    commandManager.redo();
  }

  static int loops = 0;

  @Override
  public void setSelectedColor(IColor color) {
    ICommand cmd = CommandFactory
        .createChangeColorCommand(this, colorModel, Objects.requireNonNull(color));
    cmd.execute();
    commandManager.insertCommand(cmd);
  }

  @Override
  public IColor getSelectedColor() {
    return colorModel.getColor();
  }
}
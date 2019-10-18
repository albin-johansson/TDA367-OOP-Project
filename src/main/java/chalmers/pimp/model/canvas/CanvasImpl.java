package chalmers.pimp.model.canvas;

import chalmers.pimp.model.canvas.layer.ILayer;
import chalmers.pimp.model.canvas.layer.IReadOnlyLayer;
import chalmers.pimp.model.pixeldata.IPixel;
import chalmers.pimp.model.pixeldata.IReadOnlyPixelData;
import java.util.Objects;

/**
 * The {@code Canvas} class is an implementation of the {@link ICanvas} interface.
 *
 * @see ICanvas
 */
final class CanvasImpl implements ICanvas {

  private final CanvasUpdateListenerComposite canvasUpdateListeners;
  private LayerManager layerManager;

  CanvasImpl() {
    canvasUpdateListeners = new CanvasUpdateListenerComposite();
    layerManager = new LayerManager();
  }

  /**
   * Creates a copy of the supplied canvas.
   *
   * @param canvas the canvas that will be copied.
   * @throws NullPointerException if the supplied canvas is {@code null}.
   */
  private CanvasImpl(CanvasImpl canvas) {
    Objects.requireNonNull(canvas);
    canvasUpdateListeners = new CanvasUpdateListenerComposite(canvas.canvasUpdateListeners);
    layerManager = new LayerManager(canvas.layerManager);
  }

  @Override
  public void notifyCanvasUpdateListeners() {
    canvasUpdateListeners.canvasUpdated();
  }

  @Override
  public void notifyLayerUpdateListeners() {
    layerManager.notifyListeners();
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvasUpdateListeners.add(listener);
  }

  @Override
  public void addLayerUpdateListener(ILayerUpdateListener listener) {
    layerManager.addLayerUpdateListener(listener);
  }

  @Override
  public void addLayer(ILayer layer) {
    layerManager.addLayer(layer);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void removeLayer(int layerIndex) {
    layerManager.removeLayer(layerIndex);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void selectLayer(int layerIndex) {
    layerManager.selectLayer(layerIndex);
  }

  @Override
  public void changeDepthIndex(int layerIndex, int dz) {
    layerManager.changeDepthIndex(layerIndex, dz);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void moveActiveLayer(int dx, int dy) {
    layerManager.moveActiveLayer(dx, dy);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setActiveLayerRotation(double alpha) {
    layerManager.rotateActiveLayer(alpha);
  }

  @Override
  public void setLayerVisibility(int layerIndex, boolean isVisible) {
    layerManager.setLayerVisibility(layerIndex, isVisible);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setLayerName(int layerIndex, String layerName) {
    layerManager.setLayerName(layerIndex, layerName);
  }

  @Override
  public void setActiveLayerPixel(IPixel pixel) {
    layerManager.setActiveLayerPixel(pixel);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setActiveLayerPixels(int x, int y, IReadOnlyPixelData pixelData) {
    layerManager.setActiveLayerPixels(x, y, pixelData);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setActiveLayerX(int x) {
    layerManager.setActiveLayerX(x);
    notifyCanvasUpdateListeners();
  }

  @Override
  public void setActiveLayerY(int y) {
    layerManager.setActiveLayerY(y);
    notifyCanvasUpdateListeners();
  }

  @Override
  public int getAmountOfLayers() {
    return layerManager.getAmountOfLayers();
  }

  @Override
  public boolean isLayerVisible(int layerIndex) {
    return layerManager.isLayerVisible(layerIndex);
  }

  @Override
  public String getLayerName(int layerIndex) {
    return layerManager.getLayerName(layerIndex);
  }

  @Override
  public IReadOnlyLayer getActiveLayer() {
    return layerManager.getActiveLayer();
  }

  @Override
  public Iterable<? extends IReadOnlyLayer> getLayers() {
    return layerManager.getLayers();
  }

  @Override
  public void restore(CanvasMemento memento) {
    Objects.requireNonNull(memento);
    layerManager = memento.getLayerManager();

    notifyCanvasUpdateListeners();
    notifyLayerUpdateListeners();
  }

  @Override
  public CanvasMemento createSnapShot() {
    return new CanvasMemento(new LayerManager(layerManager));
  }

  @Override
  public String toString() {
    String id = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    String state = "Layer manager: " + layerManager;
    return "(" + id + " | " + state + ")";
  }

  @Override
  public ICanvas copy() {
    return new CanvasImpl(this);
  }
}
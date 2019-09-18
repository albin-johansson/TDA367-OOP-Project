package model;

import model.canvas.Canvas;
import model.canvas.ICanvasUpdateListener;

/**
 * The {@code ModelImpl} class is an implementation of the {@code IModel} interface.
 */
final class ModelImpl implements IModel {

  private final Canvas canvas;

  ModelImpl() {
    canvas = new Canvas();
  }

  @Override
  public void addCanvasUpdateListener(ICanvasUpdateListener listener) {
    canvas.addCanvasUpdateListener(listener);
  }
}
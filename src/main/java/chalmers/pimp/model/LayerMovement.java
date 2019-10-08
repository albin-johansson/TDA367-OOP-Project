package chalmers.pimp.model;

import java.util.Objects;

public class LayerMovement {

  private ModelMemento memento;
  private int prevX;
  private int prevY;
  private int dx;
  private int dy;

  void start(int x, int y, ModelMemento memento) {
    prevX = x;
    prevY = y;
    this.memento = Objects.requireNonNull(memento);
  }

  public void update(int x, int y) {
    dx = x - prevX;
    dy = y - prevY;

    prevX = x;
    prevY = y;
  }

  int getDx() {
    return dx;
  }

  int getDy() {
    return dy;
  }

  ModelMemento getMemento() {
    return memento;
  }
}

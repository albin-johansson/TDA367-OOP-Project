package chalmers.pimp.model.viewport;

import java.util.Objects;

/**
 * The {@code ViewportModelImpl} class is an implementation of the {@code IViewportModel}
 * interface.
 */
final class ViewportModelImpl implements IViewportModel {

  private Viewport viewport;

  ViewportModelImpl() {
    viewport = new Viewport();
    viewport.setWidth(800);
    viewport.setHeight(600);
  }

  @Override
  public void center(int areaWidth, int areaHeight) {
    viewport.center(areaWidth, areaHeight);
  }

  @Override
  public void moveViewport(int dx, int dy) {
    viewport.move(dx, dy);
  }

  @Override
  public void setX(int x) {
    viewport.setX(x);
  }

  @Override
  public void setY(int y) {
    viewport.setY(y);
  }

  @Override
  public void setWidth(int width) {
    viewport.setWidth(width);
  }

  @Override
  public void setHeight(int height) {
    viewport.setHeight(height);
  }

  @Override
  public int getX() {
    return viewport.getX();
  }

  @Override
  public int getY() {
    return viewport.getY();
  }

  @Override
  public int getWidth() {
    return viewport.getWidth();
  }

  @Override
  public int getHeight() {
    return viewport.getHeight();
  }

  @Override
  public IReadOnlyViewport getViewport() {
    return new Viewport(viewport);
  }

  @Override
  public void restore(ViewportModelMemento memento) {
    Objects.requireNonNull(memento);
    viewport = memento.getViewport();
  }

  @Override
  public ViewportModelMemento createSnapShot() {
    return new ViewportModelMemento(new Viewport(viewport));
  }
}
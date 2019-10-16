package chalmers.pimp.service;

import chalmers.pimp.model.IModel;
import chalmers.pimp.model.MouseStatus;
import chalmers.pimp.model.MouseStatus.MouseButtonID;
import chalmers.pimp.model.viewport.IReadOnlyViewport;
import java.util.Objects;
import javafx.scene.input.MouseEvent;

/**
 * The {@code MouseStatusCreationService} class is a service for creating instances of the {@code
 * MouseStatus} class.
 *
 * @see MouseStatus
 */
public final class MouseStatusCreationService {

  private MouseStatusCreationService() {
  }

  /**
   * Calculates and returns the translated equivalent of the supplied x-coordinate. The translation
   * is done relative to the model viewport. Use this method to convert a raw event-based
   * x-coordinate to the corresponding model x-coordinate.
   *
   * @param event    the event that contains the raw x-coordinate that will be translated.
   * @param viewport the viewport that will be used.
   * @return the translated equivalent of the raw x-coordinate in the supplied mouse event.
   * @throws NullPointerException if any references are {@code null}.
   */
  private static int getTranslatedX(MouseEvent event, IReadOnlyViewport viewport) {
    Objects.requireNonNull(event);
    Objects.requireNonNull(viewport);
    return (int) (event.getX() - (2 * viewport.getX()));
  }

  /**
   * Calculates and returns the translated equivalent of the supplied y-coordinate. The translation
   * is done relative to the model viewport. Use this method to convert a raw event-based
   * y-coordinate to the corresponding model y-coordinate.
   *
   * @param event    the event that contains the raw y-coordinate that will be translated.
   * @param viewport the viewport that will be used.
   * @return the translated equivalent of the raw y-coordinate in the supplied mouse event.
   * @throws NullPointerException if any references are {@code null}.
   */
  private static int getTranslatedY(MouseEvent event, IReadOnlyViewport viewport) {
    Objects.requireNonNull(event);
    Objects.requireNonNull(viewport);
    return (int) (event.getY() - (2 * viewport.getY()));
  }

  /**
   * Creates and returns a mouse status instance that describes the supplied mouse event.
   *
   * @param event the mouse event that will be "copied".
   * @param model the associated model instance.
   * @return a mouse status instance that describes the supplied mouse event.
   * @throws NullPointerException if any references are {@code null}.
   */
  public static MouseStatus createMouseStatus(MouseEvent event, IModel model) {
    Objects.requireNonNull(event);
    Objects.requireNonNull(model);

    MouseButtonID buttonID = MouseButtonConverterService.getMouseButtonID(event.getButton());

    int tx = getTranslatedX(event, model.getViewport());
    int ty = getTranslatedY(event, model.getViewport());

    return new MouseStatus(tx, ty, buttonID);
  }
}
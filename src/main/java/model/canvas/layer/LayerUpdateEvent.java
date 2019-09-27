package model.canvas.layer;

/**
 * Gives information the a listener when a layer is updated
 */
public final class LayerUpdateEvent {

  /**
   * EventType specifies what happened when the LayerUpdateEvent was sent
   */
  public enum EventType {
    CREATED,
    REMOVED,
    EDITED,
    SELECTED,
    POSITION_CHANGED,
    VISIBILITY_TOGGLED
  }

  private final IReadOnlyLayer layer;
  private final EventType type;

  /**
   * Gives ILayerUpdateListeners information about the event which accured
   *
   * @param type what happened to the layer which was affected.
   * @param layer the layer which was affected
   */
  public LayerUpdateEvent(EventType type, IReadOnlyLayer layer) {
    this.type = type;
    this.layer = layer;
  }

  /**
   * Returns the EventType
   *
   * @return what type of event which accured
   */
  public EventType getType() {
    return type;
  }

  /**
   * Returns the affected layer
   *
   * @return the layer which was affected
   */
  public IReadOnlyLayer getLayer() {
    return layer;
  }
}

package chalmers.pimp.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * The {@code AbstractComposite} class is useful for objects that represent some sort of composite.
 *
 * @param <T> the type of the children contained by the composite.
 */
public abstract class AbstractComposite<T> implements Iterable<T> {

  private static final int DEFAULT_CAPACITY = 5;

  private final Set<T> children;

  /**
   * @param capacity the intitial capacity of the composite.
   * @throws IllegalArgumentException if the supplied capacity is less than one.
   */
  protected AbstractComposite(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("Invalid capacity: " + capacity);
    }
    children = new HashSet<>(capacity);
  }

  /**
   * Creates a composite with the default capacity of {@value DEFAULT_CAPACITY}.
   */
  protected AbstractComposite() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Adds a child to this composite. Note! A child can only be added once. This method has no effect
   * if the supplied object is already contained in this composite.
   *
   * @param child the object that will be added.
   * @throws NullPointerException if the supplied argument is {@code null}.
   */
  public final void add(T child) {
    Objects.requireNonNull(child);
    children.add(child);
  }

  /**
   * Removes the supplied child from this composite. This method has no effect if the specified
   * object isn't contained in the composite.
   *
   * @param child the object that will be removed.
   * @throws NullPointerException if the supplied argument is {@code null}.
   */
  public final void remove(T child) {
    Objects.requireNonNull(child);
    children.remove(child);
  }

  /**
   * Indicates whether or not the supplied object is contained in this composite. This method will
   * simply return {@code false} if the supplied argument is {@code null}.
   *
   * @param obj the object to look for.
   * @return {@code true} if the supplied object is contained in this composite; {@code false}
   * otherwise.
   */
  public final boolean contains(T obj) {
    return (obj != null) && children.contains(obj);
  }

  @Override
  public final Iterator<T> iterator() {
    return children.iterator();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(children);
  }
}
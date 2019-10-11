package chalmers.pimp.model;

/**
 * The {@code IMementoTarget} interface specifies objects that can have their states restored and
 * "mirrored" by a memento object. The memento object is usually a public class that only features
 * package-private methods, that are only accessible by the class that implements this interface.
 *
 * @param <T> the type of the memento object.
 */
public interface IMementoTarget<T> {

  /**
   * Restores the state of this memento target to the one represented by the supplied memento
   * object.
   *
   * @param memento the memento object that contains the state.
   * @throws NullPointerException if the supplied reference is {@code null}.
   */
  void restore(T memento);

  /**
   * Creates and returns a snap shot of the current state of this memento target.
   *
   * @return a snap shot of the current state of this memento target.
   */
  T createSnapShot();
}
package chalmers.pimp.model;

/**
 * The {@code ICopiable} interface specifies objects that are copiable. This is especially useful
 * for classes that are package-private but have a common superinterface, as that interface could
 * then extend this interface.
 *
 * @param <T> the type of the object that will be copied.
 */
public interface ICopiable<T> {

  /**
   * Creates and returns a copy of this object.
   *
   * @return a copy of this object.
   */
  T copy();
}
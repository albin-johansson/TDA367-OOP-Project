package util;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * The {@code Resources} class is a utility class for locating resources in a fail-fast way.
 */
public final class Resources {

  private Resources() {
  }

  /**
   * Finds the resource at the given path and returns it.
   *
   * @param context the context class (the encapsulating class of the code that invoked this
   *                method).
   * @param path    the path of the desired resource.
   * @return the URL of the found resource.
   * @throws NullPointerException if any arguments are {@code null}.
   * @throws IOException          if the resource isn't found.
   */
  public static URL find(Class<?> context, String path) throws IOException {
    Objects.requireNonNull(context);
    Objects.requireNonNull(path);

    URL result = context.getClassLoader().getResource(path);
    if (result == null) {
      throw new IOException("Failed to find resource: " + path);
    }
    return result;
  }
}
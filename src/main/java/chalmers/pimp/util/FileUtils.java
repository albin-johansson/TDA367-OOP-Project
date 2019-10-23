package chalmers.pimp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * The {@code Files} class is a utility for dealing with files.
 *
 * @see File
 */
public final class FileUtils {

  private FileUtils() {
  }

  /**
   * Returns the supplied file's filename (without the extension).
   *
   * @param file the file to have it's name returned.
   * @return the name of the supplied file.
   * @throws NullPointerException if the supplied file is {@code null}.
   */
  public static String getSimpleFileName(File file) {
    Objects.requireNonNull(file);

    var temp = new ArrayList<>(Arrays.asList(file.getName().split("\\.")));
    var builder = new StringBuilder();

    builder.append(temp.get(0));
    for (int i = 1; i < temp.size() - 1; i++) {
      builder.append(".").append(temp.get(i));
    }

    return builder.toString();
  }
}

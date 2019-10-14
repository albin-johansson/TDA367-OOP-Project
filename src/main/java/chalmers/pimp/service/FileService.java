package chalmers.pimp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class FileService {

  /**
   * The {@code FileService} class is used to ease handling a file's properties.
   */
  private FileService() {

  }

  /**
   * Returns the given file's filename (without extensions).
   *
   * @param file the file to have it's name returned.
   * @return the name of the supplied file.
   * @throws NullPointerException if the supplied file is {@code null}.
   */
  public static String getFileNameFromFile(File file) {
    Objects.requireNonNull(file);
    List<String> temp = new ArrayList<>(Arrays.asList(file.getName().split("\\.")));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(temp.get(0));
    for (int i = 1; i < temp.size() - 1; i++) {
      stringBuilder.append("." + temp.get(i));
    }
    return stringBuilder.toString();
  }
}

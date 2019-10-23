package chalmers.pimp.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FileUtilsTest {

  @Test
  void getSimpleFileName() {
    assertThrows(NullPointerException.class, () -> FileUtils.getSimpleFileName(null));
  }
}
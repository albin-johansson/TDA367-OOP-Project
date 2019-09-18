package util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Test;

class ResourcesTest {

  @Test
  void find() throws IOException {
    assertThrows(NullPointerException.class, () -> Resources.find(null, ""));
    assertThrows(NullPointerException.class, () -> Resources.find(getClass(), null));

    URL url = Resources.find(getClass(), "find_me.txt");
    assertNotNull(url);
  }
}
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DummyClassTest {

  private DummyClass dc;

  @BeforeEach
  private void init() {
    dc = new DummyClass();
  }

  @Test
  void isTrue() {
    assertTrue(dc.isTrue(true));
    assertFalse(dc.isTrue(false));
  }

  @Test
  void crashIfNull() {
    assertThrows(NullPointerException.class, () -> dc.crashIfNull(null));
    assertDoesNotThrow(() -> dc.crashIfNull(new Object()));
  }
}
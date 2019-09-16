import java.util.Objects;

public class DummyClass {

  public boolean isTrue(boolean b) {
    return b;
  }

  public void crashIfNull(Object o) {
    Objects.requireNonNull(o);
  }

}

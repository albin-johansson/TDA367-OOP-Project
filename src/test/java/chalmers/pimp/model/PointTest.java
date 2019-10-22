package chalmers.pimp.model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {

  private int x;
  private int y;
  private Point point;

  @BeforeEach
  void setUp() {
    var random = new Random();
    x = random.nextInt(1000) + 5;
    y = random.nextInt(1000) + 5;
    point = new Point(x, y);
  }

  @Test
  void setX() {
    int expectedX = x + 125;
    Point newPoint = point.setX(expectedX);

    assertEquals(x, point.getX()); // should remain unaffected
    assertEquals(expectedX, newPoint.getX());
  }

  @Test
  void setY() {
    int expectedY = y + 849;
    Point newPoint = point.setY(expectedY);

    assertEquals(y, point.getY()); // should remain unaffected
    assertEquals(expectedY, newPoint.getY());
  }

  @Test
  void getX() {
    assertEquals(x, point.getX());
    point.setX(935);
    assertEquals(x, point.getX());
  }

  @Test
  void getY() {
    assertEquals(y, point.getY());
    point.setY(-235);
    assertEquals(y, point.getY());
  }

  @Test
  void add() {
    int dx = 12;
    int dy = 92;
    var other = new Point(x + dx, y + dy);

    Point result = point.add(other);

    assertEquals(x, point.getX());
    assertEquals(y, point.getY());
    assertEquals(x + (x + dx), result.getX());
    assertEquals(y + (y + dy), result.getY());
  }

  @Test
  void addX() {
    int dx = 812;

    Point result = point.addX(dx);

    assertEquals(x, point.getX());
    assertEquals(x + dx, result.getX());
  }

  @Test
  void addY() {
    int dy = 637;

    Point result = point.addY(dy);

    assertEquals(y, point.getY());
    assertEquals(y + dy, result.getY());
  }

  @Test
  void distance() {
    assertThrows(NullPointerException.class, () -> point.distance(null));

    int dx = 124;
    int dy = 95;
    var other = new Point(x + dx, y + dy);

    double distance = point.distance(other);
    double expected = sqrt(pow(x - (x + dx), 2) + pow(y - (y + dy), 2));

    assertEquals(expected, distance, 0.05);
  }

  @Test
  void testEquals() {
    assertNotEquals(point, null);

    var copy = new Point(point.getX(), point.getY());
    var secondCopy = new Point(copy.getX(), copy.getY());

    // Symmetric
    assertEquals(point, copy);
    assertEquals(copy, point);

    // Reflexive
    assertEquals(point, point);

    // Transitive
    assertEquals(point, copy);
    assertEquals(copy, secondCopy);
    assertEquals(point, secondCopy);
  }

  @Test
  void testToString() {
    System.out.println(point);
  }
}
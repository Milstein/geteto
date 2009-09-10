package factorial;
import junit.framework.*;

public class FactorialTestCase extends TestCase {
  private FactorialIter fi;

  public FactorialTestCase( String str ) {
    super( str );
  }

  public FactorialTestCase(  ) {
    this( "" );
  }

  public void setUp() {
    fi = new FactorialIter();
  }

  public void testNegativeNumber() {
    long f = fi.factorial(-34);
    assertEquals(-1, f);
  }

  public void testValidNumber() {
    long f = fi.factorial(10);
    assertEquals(3628800, f);
  }

  public void testOutOfBoundsNumber() {
    long f = fi.factorial(212);
    assertEquals(-2, f);
  }

  public void testOutOfBoundsLimitNumber() {
     long f = fi.factorial(21);
     assertEquals(-2, f);
  }

  public void testValidLimitNumber() {
     long f = fi.factorial(20);
     String s = "2432902008176640000";
     long l = Long.valueOf(s).longValue();
     assertEquals(l, f);
  }

  public void testValidLimitNumber2() {
     long f = fi.factorial(0);
     assertEquals(1, f);
  }

  public void testNegativeLimitNumber() {
     long f = fi.factorial(-1);
     assertEquals(-1, f);
  }

  protected void tearDown() {

  }
}
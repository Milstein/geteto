package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class FuncionalTestCase extends TestCase {

  public FuncionalTestCase() {
    this("");
  }

  public FuncionalTestCase(String s) {
    super(s);
  }

  public void testVerify1() {
    try {
      assertTrue(Identifier.verify("m"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify2() {
    try {
      assertTrue(Identifier.verify("ndfeas"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify3() {
    try {
      assertFalse(Identifier.verify("abcdefg"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify4() {
    try {
      assertFalse(Identifier.verify("1aB"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify5() {
    try {
      assertFalse(Identifier.verify("abc-d"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify6() {
    try {
      assertFalse(Identifier.verify(""));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  // protected void tearDown() {
 //     DefaultProber.dump();
 //  }
}

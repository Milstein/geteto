package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class EstruturalTestCase extends TestCase {

  public EstruturalTestCase() {
    this("");
  }

  public EstruturalTestCase(String s) {
    super(s);
  }

  public void testVerify1() {
    try {
      assertFalse(Identifier.verify(null));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify2() {
    try {
      assertTrue(Identifier.verify("aB3D5"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify3() {
    try {
      assertTrue(Identifier.verify("Ab3dE"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify4() {
    try {
      assertFalse(Identifier.verify("{bc}+"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify5() {
    try {
      Identifier ident = new Identifier();
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  // protected void tearDown() {
  //    DefaultProber.dump();
  // }
}

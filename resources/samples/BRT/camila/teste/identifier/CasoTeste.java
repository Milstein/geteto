package identifier;

import junit.framework.*;

public class CasoTeste extends TestCase {
	
  public CasoTeste( String str ) {
    super( str );
  }

  public CasoTeste(  ) {
    this( "" );
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
      assertTrue(Identifier.verify("nmopqr"));
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
      assertFalse(Identifier.verify("2cB"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify5() {
    try {
      assertFalse(Identifier.verify("cbd-f"));
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


public void testVerify7() {
    try {
      assertFalse(Identifier.verify(null));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify8() {
    try {
      assertTrue(Identifier.verify("cB6D8"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify9() {
    try {
      assertTrue(Identifier.verify("Ab7fE"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify10() {
    try {
      assertFalse(Identifier.verify("{ab}+"));
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

  public void testVerify11() {
    try {
      Identifier ident = new Identifier();
    }
    catch (Exception e) {
      fail("Exceção incorreta gerada");
    }
  }

}

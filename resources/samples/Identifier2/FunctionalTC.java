package identifier;

import junit.framework.*;

public class FunctionalTC extends TestCase {
	
  boolean b;

  ...

  public void testBeginsWithLetter() {		            	  
    b = Identifier.verify("abcde");
    assertEquals(true, b);
  }
  
  public void testDoesntBeginWithLetter() {		            	  
    b = Identifier.verify("1abc");
    assertEquals(false, b);
  }
  
  
  public void testContainsOnlyLettersDigits() {		            	  
    b = Identifier.verify("a12n3");
    assertEquals(true, b);
  }
  
  public void testDoesntContainOnlyLettersDigits() {		            	  
    b = Identifier.verify("a1n3_");
    assertEquals(false, b);
  }
  
  
  public void testNULL() {
    b = Identifier.verify(null);
    assertEquals(false, b);
  }
  
  public void testMinimumOK() {
    b = Identifier.verify("abc");
    assertEquals(true, b);
  }
  
  public void testMinimumNOTOK() {
    b = Identifier.verify("");
    assertEquals(false, b);
  }
  
  public void testMaximumOK() {
    b = Identifier.verify("ab12");
    assertEquals(true, b);
  }
  
  public void testMaximumNOTOK() {
    b = Identifier.verify("abcd1234");
    assertEquals(false, b);
  }
  
  public void testMimimumLimitOK() {
    b = Identifier.verify("a");
    assertEquals(true, b);
  }
  
  public void testMaximumLimitOK() {
    b = Identifier.verify("abc123");
    assertEquals(true, b);
  }  
  
  public void testMaximumLimitNOTOK() {
    b = Identifier.verify("abc1234");
    assertEquals(false, b);
  }

}
package identifier;

import junit.framework.*;

public class StructuralTC extends TestCase {
	
  boolean b;

  public StructuralTC( String str ) {
    super( str );
  }

  public StructuralTC(  ) {
    this( "" );
  }

  public void test1stValidIdFalse() {		            	  
    b = Identifier.verify("_");
    assertEquals(false, b);
  }
  
  public void testsValidIdFalseCaps() {		            	  
    b = Identifier.verify("B");
    assertEquals(true, b);
  }
  
  public void testsValidIdFalseGTNCaps() {		            	  
    b = Identifier.verify("{");
    assertEquals(false, b);
  }
  
  public void testfValidIdFalseCaps() {		            	  
    b = Identifier.verify("aB");
    assertEquals(true, b);
  }
  
  public void testfValidIdFalseGTNCaps() {		            	  
    b = Identifier.verify("a{");
    assertEquals(false, b);
  }
  
  public void testfValidIdFalseLTCaps() {		            	  
    b = Identifier.verify("a_");
    assertEquals(false, b);
  }
  
  public void testfValidIdFalseGTNumberCaps() {		            	  
    b = Identifier.verify("a#");
    assertEquals(false, b);
  }
  
  public void testDefUseValidID() {		            	  
    b = Identifier.verify("a#asd");
    assertEquals(false, b);
  }
  
}
package identifier;

import junit.framework.*;

public class CasosTestCase extends TestCase {
	
  boolean b;

  public CasosTestCase( String str ) {
    super( str );
  }

 // public CasosTeste(  ) {
 //   this( "" );
 // }
 
  public void testObjeto(){
  	Identifier i = new Identifier();
  }
 
  public void testComecaUmaLetra() {		            	  
    b = Identifier.verify("dead");
    assertEquals(true, b);
  }

  public void testNaoComecaUmaLetra() {		            	  
    b = Identifier.verify("2uio");
    assertEquals(false, b);
  }
  
  
  public void testContemSomenteLetrasDigitos() {		            	  
    b = Identifier.verify("p45w");
    assertEquals(true, b);
  }
  
  public void testContemOutrosCaracteres() {		            	  
    b = Identifier.verify("t5a8_");
    assertEquals(false, b);
  }
  
  
  public void testNULL() {
    b = Identifier.verify(null);
    assertEquals(true, b);
  }
  
  public void testMinimo() {
    b = Identifier.verify("b");
    assertEquals(true, b);
  }
  
  public void testVazio() {
    b = Identifier.verify("");
    assertEquals(false, b);
  }
  
  public void testMaximo() {
    b = Identifier.verify("abcd12");
    assertEquals(true, b);
  }
  
  public void testMaior() {
    b = Identifier.verify("a1234cdert");
    assertEquals(false, b);
  }
  
   public void testComecaUmaLetraMaiuscula() {		            	  
    b = Identifier.verify("A");
    assertEquals(true, b);
  }
  
   public void testMaximoMaiusculo() {
    b = Identifier.verify("AC");
    assertEquals(true, b);
  }
  
   public void testCaractereEspecial() {
    b = Identifier.verify("_");
    assertEquals(false, b);
  }
  
   public void testUmNumero() {
    b = Identifier.verify("2");
    assertEquals(false, b);
  }
   public void testTodasEspecial() {
    b = Identifier.verify("_%$^&");
    assertEquals(false, b);
}
   public void testvalido() {
    b = Identifier.verify("a1");
    assertEquals(true, b);
  }
   public void testinvalido() {
    b = Identifier.verify("2B3");
    assertEquals(false, b);
  }
  public void testinvalido2() {
    b = Identifier.verify("Z-12");
    assertEquals(false, b);
  } 
  public void testinvalido3() {
    b = Identifier.verify("A1b2C3d");
    assertEquals(false, b);
  }
    
}

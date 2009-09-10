package identifier;

import junit.framework.*;

public class TestIdentifier extends TestCase {
	
	public TestIdentifier() {
		this("");
	}
	
	public TestIdentifier(String s) {
		super(s);
	}
	
	// identificador vazio (inválido)
	public void testIndentVazio() {
		assertEquals(Identifier.verify(""),false);
	}
	
	// identificador nulo (inválido)
	public void testIndentNulo() {
		assertEquals(Identifier.verify(null),false);
	}	

  // identificador 2 caracteres (válido)	
	public void testIdent2CaracValido() {
		assertEquals(Identifier.verify("a1"),true);
	}
		
	// identificador 3 caracteres (inválido)	
	public void testIdent3CaracInvalido() {
		assertEquals(Identifier.verify("2B3"),false);
	}

	// identificador 4 caracteres (inválido)	
	public void testIdent4CaracInvalido() {
		assertEquals(Identifier.verify("Z-12"),false);
	}

	// identificador 7 caracteres (inválido)	
	public void testIdent7CaracInvalido() {
		assertEquals(Identifier.verify("A1b2C3d"),false);
	}	
	
	// *************************************************
	// identificador 2 caracteres (inválido)	
	public void testIdent2CaracInvalido2() {
		assertEquals(Identifier.verify("{1"),false);
	}
	
	public void testIdent7CaracInvalido2() {
		assertEquals(Identifier.verify("Aza2}3d"),false);
	}	
	
	public void testIdent1CaracValido() {
		assertEquals(Identifier.verify("A"),true);
	}	
	
	public void testIdent1CaracInvalido() {
		assertEquals(Identifier.verify("{"),false);
	}	

}

/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;

public class CasosTestCase extends TestCase {static class __CLOVER_1_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,80,105,110,115,116,84,97,46,100,98},1137689451139L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}
	
  boolean b;

  public CasosTestCase( String str ) {
    super( str );__CLOVER_1_0.cloverRec.S[482]++;__CLOVER_1_0.cloverRec.M[156]++;
  }

 // public CasosTeste(  ) {
 //   this( "" );
 // }
 
  public void testObjeto(){__CLOVER_1_0.cloverRec.M[157]++;
  	__CLOVER_1_0.cloverRec.S[483]++;Identifier i = new Identifier();
  }
 
  public void testComecaUmaLetra() {__CLOVER_1_0.cloverRec.M[158]++;		            	  
    __CLOVER_1_0.cloverRec.S[484]++;b = Identifier.verify("dead");
    __CLOVER_1_0.cloverRec.S[485]++;assertEquals(true, b);
  }

  public void testNaoComecaUmaLetra() {__CLOVER_1_0.cloverRec.M[159]++;		            	  
    __CLOVER_1_0.cloverRec.S[486]++;b = Identifier.verify("2uio");
    __CLOVER_1_0.cloverRec.S[487]++;assertEquals(false, b);
  }
  
  
  public void testContemSomenteLetrasDigitos() {__CLOVER_1_0.cloverRec.M[160]++;		            	  
    __CLOVER_1_0.cloverRec.S[488]++;b = Identifier.verify("p45w");
    __CLOVER_1_0.cloverRec.S[489]++;assertEquals(true, b);
  }
  
  public void testContemOutrosCaracteres() {__CLOVER_1_0.cloverRec.M[161]++;		            	  
    __CLOVER_1_0.cloverRec.S[490]++;b = Identifier.verify("t5a8_");
    __CLOVER_1_0.cloverRec.S[491]++;assertEquals(false, b);
  }
  
  
  public void testNULL() {__CLOVER_1_0.cloverRec.M[162]++;
    __CLOVER_1_0.cloverRec.S[492]++;b = Identifier.verify(null);
    __CLOVER_1_0.cloverRec.S[493]++;assertEquals(true, b);
  }
  
  public void testMinimo() {__CLOVER_1_0.cloverRec.M[163]++;
    __CLOVER_1_0.cloverRec.S[494]++;b = Identifier.verify("b");
    __CLOVER_1_0.cloverRec.S[495]++;assertEquals(true, b);
  }
  
  public void testVazio() {__CLOVER_1_0.cloverRec.M[164]++;
    __CLOVER_1_0.cloverRec.S[496]++;b = Identifier.verify("");
    __CLOVER_1_0.cloverRec.S[497]++;assertEquals(false, b);
  }
  
  public void testMaximo() {__CLOVER_1_0.cloverRec.M[165]++;
    __CLOVER_1_0.cloverRec.S[498]++;b = Identifier.verify("abcd12");
    __CLOVER_1_0.cloverRec.S[499]++;assertEquals(true, b);
  }
  
  public void testMaior() {__CLOVER_1_0.cloverRec.M[166]++;
    __CLOVER_1_0.cloverRec.S[500]++;b = Identifier.verify("a1234cdert");
    __CLOVER_1_0.cloverRec.S[501]++;assertEquals(false, b);
  }
  
   public void testComecaUmaLetraMaiuscula() {__CLOVER_1_0.cloverRec.M[167]++;		            	  
    __CLOVER_1_0.cloverRec.S[502]++;b = Identifier.verify("A");
    __CLOVER_1_0.cloverRec.S[503]++;assertEquals(true, b);
  }
  
   public void testMaximoMaiusculo() {__CLOVER_1_0.cloverRec.M[168]++;
    __CLOVER_1_0.cloverRec.S[504]++;b = Identifier.verify("AC");
    __CLOVER_1_0.cloverRec.S[505]++;assertEquals(true, b);
  }
  
   public void testCaractereEspecial() {__CLOVER_1_0.cloverRec.M[169]++;
    __CLOVER_1_0.cloverRec.S[506]++;b = Identifier.verify("_");
    __CLOVER_1_0.cloverRec.S[507]++;assertEquals(false, b);
  }
  
   public void testUmNumero() {__CLOVER_1_0.cloverRec.M[170]++;
    __CLOVER_1_0.cloverRec.S[508]++;b = Identifier.verify("2");
    __CLOVER_1_0.cloverRec.S[509]++;assertEquals(false, b);
  }
   public void testTodasEspecial() {__CLOVER_1_0.cloverRec.M[171]++;
    __CLOVER_1_0.cloverRec.S[510]++;b = Identifier.verify("_%$^&");
    __CLOVER_1_0.cloverRec.S[511]++;assertEquals(false, b);
}
   public void testvalido() {__CLOVER_1_0.cloverRec.M[172]++;
    __CLOVER_1_0.cloverRec.S[512]++;b = Identifier.verify("a1");
    __CLOVER_1_0.cloverRec.S[513]++;assertEquals(true, b);
  }
   public void testinvalido() {__CLOVER_1_0.cloverRec.M[173]++;
    __CLOVER_1_0.cloverRec.S[514]++;b = Identifier.verify("2B3");
    __CLOVER_1_0.cloverRec.S[515]++;assertEquals(false, b);
  }
  public void testinvalido2() {__CLOVER_1_0.cloverRec.M[174]++;
    __CLOVER_1_0.cloverRec.S[516]++;b = Identifier.verify("Z-12");
    __CLOVER_1_0.cloverRec.S[517]++;assertEquals(false, b);
  } 
  public void testinvalido3() {__CLOVER_1_0.cloverRec.M[175]++;
    __CLOVER_1_0.cloverRec.S[518]++;b = Identifier.verify("A1b2C3d");
    __CLOVER_1_0.cloverRec.S[519]++;assertEquals(false, b);
  }
    
}

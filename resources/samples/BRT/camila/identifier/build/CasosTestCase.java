/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;

public class CasosTestCase extends TestCase {static class __CLOVER_3_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,97,108,117,110,111,46,100,98},1144346081740L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}
	
  boolean b;

  public CasosTestCase( String str ) {
    super( str );__CLOVER_3_0.cloverRec.S[697]++;__CLOVER_3_0.cloverRec.M[295]++;
  }

 // public CasosTeste(  ) {
 //   this( "" );
 // }
 
  public void testObjeto(){__CLOVER_3_0.cloverRec.M[296]++;
  	__CLOVER_3_0.cloverRec.S[698]++;Identifier i = new Identifier();
  }
 
  public void testComecaUmaLetra() {__CLOVER_3_0.cloverRec.M[297]++;		            	  
    __CLOVER_3_0.cloverRec.S[699]++;b = Identifier.verify("dead");
    __CLOVER_3_0.cloverRec.S[700]++;assertEquals(true, b);
  }

  public void testNaoComecaUmaLetra() {__CLOVER_3_0.cloverRec.M[298]++;		            	  
    __CLOVER_3_0.cloverRec.S[701]++;b = Identifier.verify("2uio");
    __CLOVER_3_0.cloverRec.S[702]++;assertEquals(false, b);
  }
  
  
  public void testContemSomenteLetrasDigitos() {__CLOVER_3_0.cloverRec.M[299]++;		            	  
    __CLOVER_3_0.cloverRec.S[703]++;b = Identifier.verify("p45w");
    __CLOVER_3_0.cloverRec.S[704]++;assertEquals(true, b);
  }
  
  public void testContemOutrosCaracteres() {__CLOVER_3_0.cloverRec.M[300]++;		            	  
    __CLOVER_3_0.cloverRec.S[705]++;b = Identifier.verify("t5a8_");
    __CLOVER_3_0.cloverRec.S[706]++;assertEquals(false, b);
  }
  
  
  public void testNULL() {__CLOVER_3_0.cloverRec.M[301]++;
    __CLOVER_3_0.cloverRec.S[707]++;b = Identifier.verify(null);
    __CLOVER_3_0.cloverRec.S[708]++;assertEquals(true, b);
  }
  
  public void testMinimo() {__CLOVER_3_0.cloverRec.M[302]++;
    __CLOVER_3_0.cloverRec.S[709]++;b = Identifier.verify("b");
    __CLOVER_3_0.cloverRec.S[710]++;assertEquals(true, b);
  }
  
  public void testVazio() {__CLOVER_3_0.cloverRec.M[303]++;
    __CLOVER_3_0.cloverRec.S[711]++;b = Identifier.verify("");
    __CLOVER_3_0.cloverRec.S[712]++;assertEquals(false, b);
  }
  
  public void testMaximo() {__CLOVER_3_0.cloverRec.M[304]++;
    __CLOVER_3_0.cloverRec.S[713]++;b = Identifier.verify("abcd12");
    __CLOVER_3_0.cloverRec.S[714]++;assertEquals(true, b);
  }
  
  public void testMaior() {__CLOVER_3_0.cloverRec.M[305]++;
    __CLOVER_3_0.cloverRec.S[715]++;b = Identifier.verify("a1234cdert");
    __CLOVER_3_0.cloverRec.S[716]++;assertEquals(false, b);
  }
  
   public void testComecaUmaLetraMaiuscula() {__CLOVER_3_0.cloverRec.M[306]++;		            	  
    __CLOVER_3_0.cloverRec.S[717]++;b = Identifier.verify("A");
    __CLOVER_3_0.cloverRec.S[718]++;assertEquals(true, b);
  }
  
   public void testMaximoMaiusculo() {__CLOVER_3_0.cloverRec.M[307]++;
    __CLOVER_3_0.cloverRec.S[719]++;b = Identifier.verify("AC");
    __CLOVER_3_0.cloverRec.S[720]++;assertEquals(true, b);
  }
  
   public void testCaractereEspecial() {__CLOVER_3_0.cloverRec.M[308]++;
    __CLOVER_3_0.cloverRec.S[721]++;b = Identifier.verify("_");
    __CLOVER_3_0.cloverRec.S[722]++;assertEquals(false, b);
  }
  
   public void testUmNumero() {__CLOVER_3_0.cloverRec.M[309]++;
    __CLOVER_3_0.cloverRec.S[723]++;b = Identifier.verify("2");
    __CLOVER_3_0.cloverRec.S[724]++;assertEquals(false, b);
  }
   public void testTodasEspecial() {__CLOVER_3_0.cloverRec.M[310]++;
    __CLOVER_3_0.cloverRec.S[725]++;b = Identifier.verify("_%$^&");
    __CLOVER_3_0.cloverRec.S[726]++;assertEquals(false, b);
}
   public void testvalido() {__CLOVER_3_0.cloverRec.M[311]++;
    __CLOVER_3_0.cloverRec.S[727]++;b = Identifier.verify("a1");
    __CLOVER_3_0.cloverRec.S[728]++;assertEquals(true, b);
  }
   public void testinvalido() {__CLOVER_3_0.cloverRec.M[312]++;
    __CLOVER_3_0.cloverRec.S[729]++;b = Identifier.verify("2B3");
    __CLOVER_3_0.cloverRec.S[730]++;assertEquals(false, b);
  }
  public void testinvalido2() {__CLOVER_3_0.cloverRec.M[313]++;
    __CLOVER_3_0.cloverRec.S[731]++;b = Identifier.verify("Z-12");
    __CLOVER_3_0.cloverRec.S[732]++;assertEquals(false, b);
  } 
  public void testinvalido3() {__CLOVER_3_0.cloverRec.M[314]++;
    __CLOVER_3_0.cloverRec.S[733]++;b = Identifier.verify("A1b2C3d");
    __CLOVER_3_0.cloverRec.S[734]++;assertEquals(false, b);
  }
    
}

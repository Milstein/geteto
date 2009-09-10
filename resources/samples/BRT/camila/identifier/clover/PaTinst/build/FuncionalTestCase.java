/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class FuncionalTestCase extends TestCase {static class __CLOVER_1_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,80,97,84,105,110,115,116,46,100,98},1137689469323L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}

  public FuncionalTestCase() {
    this("");__CLOVER_1_0.cloverRec.S[347]++;__CLOVER_1_0.cloverRec.M[113]++;
  }

  public FuncionalTestCase(String s) {
    super(s);__CLOVER_1_0.cloverRec.S[348]++;__CLOVER_1_0.cloverRec.M[114]++;
  }

  public void testVerify1() {__CLOVER_1_0.cloverRec.M[115]++;
    __CLOVER_1_0.cloverRec.S[349]++;try {
      __CLOVER_1_0.cloverRec.S[350]++;assertTrue(Identifier.verify("m"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[351]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify2() {__CLOVER_1_0.cloverRec.M[116]++;
    __CLOVER_1_0.cloverRec.S[352]++;try {
      __CLOVER_1_0.cloverRec.S[353]++;assertTrue(Identifier.verify("ndfeas"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[354]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify3() {__CLOVER_1_0.cloverRec.M[117]++;
    __CLOVER_1_0.cloverRec.S[355]++;try {
      __CLOVER_1_0.cloverRec.S[356]++;assertFalse(Identifier.verify("abcdefg"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[357]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify4() {__CLOVER_1_0.cloverRec.M[118]++;
    __CLOVER_1_0.cloverRec.S[358]++;try {
      __CLOVER_1_0.cloverRec.S[359]++;assertFalse(Identifier.verify("1aB"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[360]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify5() {__CLOVER_1_0.cloverRec.M[119]++;
    __CLOVER_1_0.cloverRec.S[361]++;try {
      __CLOVER_1_0.cloverRec.S[362]++;assertFalse(Identifier.verify("abc-d"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[363]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify6() {__CLOVER_1_0.cloverRec.M[120]++;
    __CLOVER_1_0.cloverRec.S[364]++;try {
      __CLOVER_1_0.cloverRec.S[365]++;assertFalse(Identifier.verify(""));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[366]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  // protected void tearDown() {
 //     DefaultProber.dump();
 //  }
}

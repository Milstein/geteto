/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class FuncionalTestCase extends TestCase {static class __CLOVER_1_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,111,114,97,99,117,108,111,46,100,98},1137698834320L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}

  public FuncionalTestCase() {
    this("");__CLOVER_1_0.cloverRec.S[603]++;__CLOVER_1_0.cloverRec.M[155]++;
  }

  public FuncionalTestCase(String s) {
    super(s);__CLOVER_1_0.cloverRec.S[604]++;__CLOVER_1_0.cloverRec.M[156]++;
  }

  public void testVerify1() {__CLOVER_1_0.cloverRec.M[157]++;
    __CLOVER_1_0.cloverRec.S[605]++;try {
      __CLOVER_1_0.cloverRec.S[606]++;assertTrue(Identifier.verify("m"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[607]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify2() {__CLOVER_1_0.cloverRec.M[158]++;
    __CLOVER_1_0.cloverRec.S[608]++;try {
      __CLOVER_1_0.cloverRec.S[609]++;assertTrue(Identifier.verify("ndfeas"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[610]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify3() {__CLOVER_1_0.cloverRec.M[159]++;
    __CLOVER_1_0.cloverRec.S[611]++;try {
      __CLOVER_1_0.cloverRec.S[612]++;assertFalse(Identifier.verify("abcdefg"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[613]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify4() {__CLOVER_1_0.cloverRec.M[160]++;
    __CLOVER_1_0.cloverRec.S[614]++;try {
      __CLOVER_1_0.cloverRec.S[615]++;assertFalse(Identifier.verify("1aB"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[616]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify5() {__CLOVER_1_0.cloverRec.M[161]++;
    __CLOVER_1_0.cloverRec.S[617]++;try {
      __CLOVER_1_0.cloverRec.S[618]++;assertFalse(Identifier.verify("abc-d"));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[619]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify6() {__CLOVER_1_0.cloverRec.M[162]++;
    __CLOVER_1_0.cloverRec.S[620]++;try {
      __CLOVER_1_0.cloverRec.S[621]++;assertFalse(Identifier.verify(""));
    }
    catch (Exception e) {
      __CLOVER_1_0.cloverRec.S[622]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  // protected void tearDown() {
 //     DefaultProber.dump();
 //  }
}

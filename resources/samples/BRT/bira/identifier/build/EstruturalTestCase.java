/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class EstruturalTestCase extends TestCase {static class __CLOVER_2_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,111,114,97,99,117,108,111,46,100,98},1137698834320L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}

  public EstruturalTestCase() {
    this("");__CLOVER_2_0.cloverRec.S[623]++;__CLOVER_2_0.cloverRec.M[163]++;
  }

  public EstruturalTestCase(String s) {
    super(s);__CLOVER_2_0.cloverRec.S[624]++;__CLOVER_2_0.cloverRec.M[164]++;
  }

  public void testVerify1() {__CLOVER_2_0.cloverRec.M[165]++;
    __CLOVER_2_0.cloverRec.S[625]++;try {
      __CLOVER_2_0.cloverRec.S[626]++;assertFalse(Identifier.verify(null));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[627]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify2() {__CLOVER_2_0.cloverRec.M[166]++;
    __CLOVER_2_0.cloverRec.S[628]++;try {
      __CLOVER_2_0.cloverRec.S[629]++;assertTrue(Identifier.verify("aB3D5"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[630]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify3() {__CLOVER_2_0.cloverRec.M[167]++;
    __CLOVER_2_0.cloverRec.S[631]++;try {
      __CLOVER_2_0.cloverRec.S[632]++;assertTrue(Identifier.verify("Ab3dE"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[633]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify4() {__CLOVER_2_0.cloverRec.M[168]++;
    __CLOVER_2_0.cloverRec.S[634]++;try {
      __CLOVER_2_0.cloverRec.S[635]++;assertFalse(Identifier.verify("{bc}+"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[636]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify5() {__CLOVER_2_0.cloverRec.M[169]++;
    __CLOVER_2_0.cloverRec.S[637]++;try {
      __CLOVER_2_0.cloverRec.S[638]++;Identifier ident = new Identifier();
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[639]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  // protected void tearDown() {
  //    DefaultProber.dump();
  // }
}

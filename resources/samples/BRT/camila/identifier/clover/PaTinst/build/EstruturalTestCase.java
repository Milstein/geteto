/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

import junit.framework.*;
//import probe.DefaultProber;

public class EstruturalTestCase extends TestCase {static class __CLOVER_2_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,80,97,84,105,110,115,116,46,100,98},1137689469323L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}

  public EstruturalTestCase() {
    this("");__CLOVER_2_0.cloverRec.S[367]++;__CLOVER_2_0.cloverRec.M[121]++;
  }

  public EstruturalTestCase(String s) {
    super(s);__CLOVER_2_0.cloverRec.S[368]++;__CLOVER_2_0.cloverRec.M[122]++;
  }

  public void testVerify1() {__CLOVER_2_0.cloverRec.M[123]++;
    __CLOVER_2_0.cloverRec.S[369]++;try {
      __CLOVER_2_0.cloverRec.S[370]++;assertFalse(Identifier.verify(null));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[371]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify2() {__CLOVER_2_0.cloverRec.M[124]++;
    __CLOVER_2_0.cloverRec.S[372]++;try {
      __CLOVER_2_0.cloverRec.S[373]++;assertTrue(Identifier.verify("aB3D5"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[374]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify3() {__CLOVER_2_0.cloverRec.M[125]++;
    __CLOVER_2_0.cloverRec.S[375]++;try {
      __CLOVER_2_0.cloverRec.S[376]++;assertTrue(Identifier.verify("Ab3dE"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[377]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify4() {__CLOVER_2_0.cloverRec.M[126]++;
    __CLOVER_2_0.cloverRec.S[378]++;try {
      __CLOVER_2_0.cloverRec.S[379]++;assertFalse(Identifier.verify("{bc}+"));
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[380]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  public void testVerify5() {__CLOVER_2_0.cloverRec.M[127]++;
    __CLOVER_2_0.cloverRec.S[381]++;try {
      __CLOVER_2_0.cloverRec.S[382]++;Identifier ident = new Identifier();
    }
    catch (Exception e) {
      __CLOVER_2_0.cloverRec.S[383]++;fail("Exce\u00e7\u00e3o incorreta gerada");
    }
  }

  // protected void tearDown() {
  //    DefaultProber.dump();
  // }
}

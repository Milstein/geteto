/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

public class Identifier {static class __CLOVER_0_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,80,105,110,115,116,84,97,46,100,98},1137689451139L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}
  public static boolean verify(String id) {__CLOVER_0_0.cloverRec.M[154]++;
    __CLOVER_0_0.cloverRec.S[455]++;if (((((id == null) || (id.length() == 0)) && (++__CLOVER_0_0.cloverRec.CT[77]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[77]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[456]++;return false;
    }

    }__CLOVER_0_0.cloverRec.S[457]++;int length = 1;
    __CLOVER_0_0.cloverRec.S[458]++;boolean valid_id = valid_char(id.charAt(0), true);

    __CLOVER_0_0.cloverRec.S[459]++;for (int i = 1; (((i < id.length()) && (++__CLOVER_0_0.cloverRec.CT[78]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[78]==0&false)); i++) {{
      __CLOVER_0_0.cloverRec.S[460]++;if ((((!(valid_char(id.charAt(i), false))) && (++__CLOVER_0_0.cloverRec.CT[79]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[79]==0&false))) {{
        __CLOVER_0_0.cloverRec.S[461]++;valid_id = false;
        __CLOVER_0_0.cloverRec.S[462]++;break;
      }
      }__CLOVER_0_0.cloverRec.S[463]++;length++;
    }
    }__CLOVER_0_0.cloverRec.S[464]++;if ((((valid_id) && (++__CLOVER_0_0.cloverRec.CT[80]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[80]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[465]++;if ((((length <= 6) && (++__CLOVER_0_0.cloverRec.CT[81]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[81]==0&false))) {{
	__CLOVER_0_0.cloverRec.S[466]++;return true;
      }
      }else
	{__CLOVER_0_0.cloverRec.S[467]++;return false;
    }}
    }else
      {__CLOVER_0_0.cloverRec.S[468]++;return false;
  }}

  private static boolean valid_char(char ch, boolean first) {__CLOVER_0_0.cloverRec.M[155]++;
    __CLOVER_0_0.cloverRec.S[469]++;if ((((first) && (++__CLOVER_0_0.cloverRec.CT[82]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[82]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[470]++;if (((((ch >= 'A') && (ch <= 'Z')) && (++__CLOVER_0_0.cloverRec.CT[83]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[83]==0&false)))
        {__CLOVER_0_0.cloverRec.S[471]++;return true;
      }else
        {__CLOVER_0_0.cloverRec.S[472]++;if (((((ch >= 'a') && (ch <= 'z')) && (++__CLOVER_0_0.cloverRec.CT[84]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[84]==0&false)))
          {__CLOVER_0_0.cloverRec.S[473]++;return true;
	}else
	  {__CLOVER_0_0.cloverRec.S[474]++;return false;
    }}}
    }else {{
      __CLOVER_0_0.cloverRec.S[475]++;if (((((ch >= 'A') && (ch <= 'Z')) && (++__CLOVER_0_0.cloverRec.CT[85]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[85]==0&false)))
        {__CLOVER_0_0.cloverRec.S[476]++;return true;
      }else
	{__CLOVER_0_0.cloverRec.S[477]++;if (((((ch >= 'a') && (ch <= 'z')) && (++__CLOVER_0_0.cloverRec.CT[86]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[86]==0&false)))
	  {__CLOVER_0_0.cloverRec.S[478]++;return true;
	}else
	  {__CLOVER_0_0.cloverRec.S[479]++;if (((((ch >= '0') && (ch <= '9')) && (++__CLOVER_0_0.cloverRec.CT[87]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[87]==0&false)))
	    {__CLOVER_0_0.cloverRec.S[480]++;return true;
	  }else
            {__CLOVER_0_0.cloverRec.S[481]++;return false;
    }}}}
  }}
}
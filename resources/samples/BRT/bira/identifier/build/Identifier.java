/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ */package identifier;

public class Identifier {static class __CLOVER_0_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,111,114,97,99,117,108,111,46,100,98},1137698834320L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}
  public static boolean verify(String id) {__CLOVER_0_0.cloverRec.M[153]++;
    __CLOVER_0_0.cloverRec.S[576]++;if (((((id == null) || (id.length() == 0)) && (++__CLOVER_0_0.cloverRec.CT[99]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[99]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[577]++;return false;
    }

    }__CLOVER_0_0.cloverRec.S[578]++;int length = 1;
    __CLOVER_0_0.cloverRec.S[579]++;boolean valid_id = valid_char(id.charAt(0), true);

    __CLOVER_0_0.cloverRec.S[580]++;for (int i = 1; (((i < id.length()) && (++__CLOVER_0_0.cloverRec.CT[100]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[100]==0&false)); i++) {{
      __CLOVER_0_0.cloverRec.S[581]++;if ((((!(valid_char(id.charAt(i), false))) && (++__CLOVER_0_0.cloverRec.CT[101]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[101]==0&false))) {{
        __CLOVER_0_0.cloverRec.S[582]++;valid_id = false;
        __CLOVER_0_0.cloverRec.S[583]++;break;
      }
      }__CLOVER_0_0.cloverRec.S[584]++;length++;
    }
    }__CLOVER_0_0.cloverRec.S[585]++;if ((((valid_id) && (++__CLOVER_0_0.cloverRec.CT[102]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[102]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[586]++;if ((((length <= 6) && (++__CLOVER_0_0.cloverRec.CT[103]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[103]==0&false))) {{
	__CLOVER_0_0.cloverRec.S[587]++;return true;
      }
      }else
	{__CLOVER_0_0.cloverRec.S[588]++;return false;
    }}
    }else
      {__CLOVER_0_0.cloverRec.S[589]++;return false;
  }}

  private static boolean valid_char(char ch, boolean first) {__CLOVER_0_0.cloverRec.M[154]++;
    __CLOVER_0_0.cloverRec.S[590]++;if ((((first) && (++__CLOVER_0_0.cloverRec.CT[104]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[104]==0&false))) {{
      __CLOVER_0_0.cloverRec.S[591]++;if (((((ch >= 'A') && (ch <= 'Z')) && (++__CLOVER_0_0.cloverRec.CT[105]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[105]==0&false)))
        {__CLOVER_0_0.cloverRec.S[592]++;return true;
      }else
        {__CLOVER_0_0.cloverRec.S[593]++;if (((((ch >= 'a') && (ch <= 'z')) && (++__CLOVER_0_0.cloverRec.CT[106]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[106]==0&false)))
          {__CLOVER_0_0.cloverRec.S[594]++;return true;
	}else
	  {__CLOVER_0_0.cloverRec.S[595]++;return false;
    }}}
    }else {{
      __CLOVER_0_0.cloverRec.S[596]++;if (((((ch >= 'A') && (ch <= 'Z')) && (++__CLOVER_0_0.cloverRec.CT[107]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[107]==0&false)))
        {__CLOVER_0_0.cloverRec.S[597]++;return true;
      }else
	{__CLOVER_0_0.cloverRec.S[598]++;if (((((ch >= 'a') && (ch <= 'z')) && (++__CLOVER_0_0.cloverRec.CT[108]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[108]==0&false)))
	  {__CLOVER_0_0.cloverRec.S[599]++;return true;
	}else
	  {__CLOVER_0_0.cloverRec.S[600]++;if (((((ch >= '0') && (ch <= '9')) && (++__CLOVER_0_0.cloverRec.CT[109]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[109]==0&false)))
	    {__CLOVER_0_0.cloverRec.S[601]++;return true;
	  }else
            {__CLOVER_0_0.cloverRec.S[602]++;return false;
    }}}}
  }}
}
/* $$ This file has been instrumented by Clover 1.3.5#20050221143834272 $$ *//*
 * Identifier.java
 *
 * ESPECIFICACAO: O programa deve determinar  se  um  identificador  eh  ou  nao
 * valido em \u00b4Silly Pascal\u00b4 (uma estranha variante do Pascal). Um  identificador
 * valido deve comecar com uma letra e conter  apenas  letras  ou  digitos. Alem
 * disso,  deve  ter  no  minimo  1  caractere  e  no  maximo  6  caracteres  de
 * comprimento.
 *
 */

 package identifier;
 
 public class Identifier {static class __CLOVER_0_0{static com_cenqua_clover.g cloverRec;static{try{if(20050221143834272L!=com_cenqua_clover.CloverVersionInfo.getBuildMagic()){java.lang.System.err.println("[CLOVER] WARNING: The Clover version used in instrumentation does not match the runtime version. You need to run instrumented classes against the same version of Clover that you instrumented with.");java.lang.System.err.println("[CLOVER] WARNING: Instr=1.3.5,Runtime="+com_cenqua_clover.CloverVersionInfo.getReleaseNum());}cloverRec = com_cenqua_clover.f.getRecorder(new char[] {68,58,92,99,97,109,105,108,97,92,101,120,101,114,99,105,99,105,111,92,99,97,109,105,108,97,92,105,100,101,110,116,105,102,105,101,114,92,99,108,111,118,101,114,92,80,97,84,105,110,115,116,46,100,98},1137689469323L,50, true);}catch (Throwable t) {java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+t.getClass()+":"+ t.getMessage()+")");}}}
 	
 	/* Method to verify if the identifier is a valid one */	
	public static boolean verify(String id) {__CLOVER_0_0.cloverRec.M[112]++;
		
		__CLOVER_0_0.cloverRec.S[336]++;boolean valid_id = true;
		__CLOVER_0_0.cloverRec.S[337]++;int i = 1;

		/* Verifies the length of the identifier */
		__CLOVER_0_0.cloverRec.S[338]++;if((((id != null && id.length() >= 1 && id.length() <= 6) && (++__CLOVER_0_0.cloverRec.CT[35]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[35]==0&false))) {{

			/* Verifies the first character of the identifier */
			__CLOVER_0_0.cloverRec.S[339]++;if ((((!(((id.charAt(0) >= 'A') && (id.charAt(0) <= 'Z')) ||
			      ((id.charAt(0) >= 'a') && (id.charAt(0) <= 'z')))) && (++__CLOVER_0_0.cloverRec.CT[36]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[36]==0&false)))
				{__CLOVER_0_0.cloverRec.S[340]++;valid_id = false;

			/* Verifies the remaining characters of the identifier */
			}__CLOVER_0_0.cloverRec.S[341]++;while((((i <= id.length()-1) && (++__CLOVER_0_0.cloverRec.CT[37]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[37]==0&false))) {{
				__CLOVER_0_0.cloverRec.S[342]++;if ((((!(((id.charAt(i) >= 'A') && (id.charAt(i) <= 'Z')) ||
				      ((id.charAt(i) >= 'a') && (id.charAt(i) <= 'z')) ||
			    	  ((id.charAt(i) >= '0') && (id.charAt(i) <= '9')))) && (++__CLOVER_0_0.cloverRec.CT[38]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[38]==0&false)))
					{__CLOVER_0_0.cloverRec.S[343]++;valid_id = false;
				}__CLOVER_0_0.cloverRec.S[344]++;i++;
			}

			}__CLOVER_0_0.cloverRec.S[345]++;return (((((valid_id == true) ) && (++__CLOVER_0_0.cloverRec.CT[39]!=0|true)) || (++__CLOVER_0_0.cloverRec.CF[39]==0&false))? true : false);
		} }else
			{__CLOVER_0_0.cloverRec.S[346]++;return false;
	}}
 	
 	/* Main for running purposes */
 /*	public static void main (String args[]) { 
 		Identifier i = new Identifier();
 		System.out.println("a1" + i.Verify("a1"));
 		System.out.println("2B3" + i.Verify("2B3"));
 		System.out.println("Z-12" + i.Verify("Z-12"));
 		System.out.println("A1b2C3d" + i.Verify("A1b2C3d"));
 		System.out.println("null" + i.Verify(""));
 	}
 */}
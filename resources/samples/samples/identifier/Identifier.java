/*
 * Identifier.java
 *
 * ESPECIFICACAO: O programa deve determinar  se  um  identificador  eh  ou  nao
 * valido em ´Silly Pascal´ (uma estranha variante do Pascal). Um  identificador
 * valido deve comecar com uma letra e conter  apenas  letras  ou  digitos. Alem
 * disso,  deve  ter  no  minimo  1  caractere  e  no  maximo  6  caracteres  de
 * comprimento.
 *
 */

 package samples.identifier;
 
 public class Identifier {
 	
 	/* Method to verify if the identifier is a valid one */	
	public static boolean verify(String id) {
		
		boolean valid_id = true;
		int i = 1;

		/* Verifies the length of the identifier */
		if(id != null && id.length() >= 1 && id.length() <= 6) {

			/* Verifies the first character of the identifier */
			if (!(((id.charAt(0) >= 'A') && (id.charAt(0) <= 'Z')) ||
			      ((id.charAt(0) >= 'a') && (id.charAt(0) <= 'z'))))
				valid_id = false;

			/* Verifies the remaining characters of the identifier */
			while(i <= id.length()-1) {
				if (!(((id.charAt(i) >= 'A') && (id.charAt(i) <= 'Z')) ||
				      ((id.charAt(i) >= 'a') && (id.charAt(i) <= 'z')) ||
			    	  ((id.charAt(i) >= '0') && (id.charAt(i) <= '9'))))
					valid_id = false;
				i++;
			}

			return ((valid_id == true) ? true : false);
		} else
			return false;
	}
 	
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

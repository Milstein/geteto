package identifier;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Identifier {

  public static void validateIdentifier(String s) {
    char achar;
    boolean valid_id;
    valid_id = true;
    achar = s.charAt(0);
    valid_id = valid_s(achar);
    achar = s.charAt(1);
    int i = 1;
    while (i < s.length()) {
      achar = s.charAt(i);
      if (!valid_f(achar))
       valid_id = false;
      i++;
    }

    if (valid_id &&
       (s.length() >=1) && (s.length() <= 6))
       System.out.println("Válido\n");
    else
       System.out.println("Inválido\n");
  }

  public static boolean valid_s(char ch) {
    if (((ch >= 'A') &&
         (ch <= 'Z')) ||
        ((ch >= 'a') &&
         (ch <= 'z')))
      return true;
    else
      return false;
  }

  public static boolean valid_f(char ch) {
    if (((ch >= 'A') &&
         (ch <= 'Z')) ||
        ((ch >= 'a') &&
         (ch <= 'z')) ||
        ((ch >= '0') &&
         (ch <= '9')))
      return true;
    else
      return false;
  }

  public static void main(String[] args) {
    validateIdentifier("a1");
    validateIdentifier("2B3");
    validateIdentifier("Z-12");
    validateIdentifier("A1b2C3d");
  }
}

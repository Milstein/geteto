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

  public static boolean verify(String s) {
  	if (s == null || s.length() == 0) 
  	  return false;
  	
    char achar;
    boolean valid_id;
    valid_id = true;
    achar = s.charAt(0);
    valid_id = valid_s(achar);
    
    if (s.length() == 1 && valid_id)
      return true;    
    
    int i = 1;
    while (i < s.length()) {
      achar = s.charAt(i);
      if (!valid_f(achar))
       valid_id = false;
      i++;
    }

    if (valid_id && (s.length() <= 6))
       return true;
    
    return false;
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

}

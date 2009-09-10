          public static boolean verify(String s) {
/* 01 */  	if (s == null || s.length() == 0) 
/* 02 */  	  return false;
/* 03 */    char achar;
/* 03 */    boolean valid_id;
/* 03 */    valid_id = true;
/* 03 */    achar = s.charAt(0);
/* 03 */    valid_id = valid_s(achar);
/* 03 */    if (s.length() == 1 && valid_id)
/* 04 */      return true;
/* 05 */    int i = 1;
/* 06 */    while (i < s.length()) {
/* 07 */      achar = s.charAt(i);
/* 07 */      if (!valid_f(achar))
/* 08 */       valid_id = false;
/* 09 */      i++;
/* 09 */    }
/* 10 */    if (valid_id && (s.length() <= 6))
/* 11 */       return true;
/* 12 */    return false;
/* 12 */  }
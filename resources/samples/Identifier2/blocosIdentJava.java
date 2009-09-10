public static void validateIdentifier(String s) {
/* 00 */    char achar;
/* 00 */    boolean valid_id;
/* 00 */    valid_id = true;
/* 00 */    achar = s.charAt(0);
/* 00 */    valid_id = valid_s(achar);
/* 00 */    achar = s.charAt(1);
/* 00 */    int i = 1;
/* 01 */    while (i < s.length()) {
/* 02 */      achar = s.charAt(i);
/* 02 */      if (!valid_f(achar))
/* 03 */       valid_id = false;
/* 04 */      i++;
/* 04 */    }
/* 05 */    if (valid_id &&
               (s.length() >=1) && (s.length() <= 6))
/* 06 */       System.out.println("Valid\n");
/* 07 */    else
/* 07 */       System.out.println("Invalid\n");
/* 08 */  }


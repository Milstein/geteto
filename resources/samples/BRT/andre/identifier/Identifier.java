package identifier;

public class Identifier {
	public static boolean verify (String id) {
		int length = 0;
		boolean valid_id = false;
		
		if ((id == null) || (id.length() == 0))
		  return false;
		else {
			if (valid_id = valid_s(id.charAt(0)))
				length = 1;
		
			for (int i = 1; i < id.length(); i++) {
				if (!(valid_f(id.charAt(i)))) {
					valid_id = false;
					break;
				}
				length++;			
			}
		}
		return (valid_id && (length <= 6));
	}

	private static boolean valid_s(char ch) {
		return (((ch >= 'A') && (ch <= 'Z')) || 
		       ((ch >= 'a') && (ch <= 'z')));
	}
	
	private static boolean valid_f(char ch) {
		return (((ch >= 'A') && (ch <= 'Z')) ||
			     ((ch >= 'a') && (ch <= 'z')) ||
			     ((ch >= '0') && (ch <= '9')));
	}

}

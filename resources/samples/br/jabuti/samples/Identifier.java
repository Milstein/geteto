package br.jabuti.samples;

public class Identifier
{
	public static boolean verify(String id)
	{
		if ((id == null) || (id.length() == 0)) {
			return false;
		}

		int length = 1;
		boolean valid_id = valid_char(id.charAt(0), true);

		for (int i = 1; i < id.length(); i++) {
			if (!(valid_char(id.charAt(i), false))) {
				valid_id = false;
				break;
			}
			length++;
		}
		if (valid_id) {
			if (length <= 6) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private static boolean valid_char(char ch, boolean first)
	{
		if (first) {
			if ((ch >= 'A') && (ch <= 'Z'))
				return true;
			else if ((ch >= 'a') && (ch <= 'z'))
				return true;
			else
				return false;
		} else {
			if ((ch >= 'A') && (ch <= 'Z'))
				return true;
			else if ((ch >= 'a') && (ch <= 'z'))
				return true;
			else if ((ch >= '0') && (ch <= '9'))
				return true;
			else
				return false;
		}
	}
}

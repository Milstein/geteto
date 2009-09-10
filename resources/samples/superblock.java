
package samples;


public class superblock {
    static int j;
	
    public static void main(String args[]) {
        int i = 0; 
		
        while (i == j) {
            switch (i) {
            case 1:
                i++;
                break;

            case 2:
                i--; 
                while (i > 0) {
                    ++i;
                }

            default:
                if (i == 0) { 

                    i++;
                    continue;
                }
                do {
                    i = i + 2;
                    main(null);
					  
                } while (i < 10);

                i--;
            }
            i--;
        }
        i++;
		
    }
}

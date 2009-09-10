package samples;


import java.io.*;


public class finaly3 {
	
    public static void main(String args[]) {
        int i = 0;
		
        try {
            try {
                FileInputStream  fi = new FileInputStream(args[1]);
            } finally { 
                FileInputStream  fi = new FileInputStream(args[1]);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            i++;
        }
        i++; 
    }
}

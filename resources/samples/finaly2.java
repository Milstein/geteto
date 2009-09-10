package samples;


import java.io.*;


public class finaly2 {
	
    public static void main(String args[]) {
        int i = 0;
		
        try {
            FileInputStream  fi = new FileInputStream(args[0]);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            i = 0;
        }
        i++; 
    }
}

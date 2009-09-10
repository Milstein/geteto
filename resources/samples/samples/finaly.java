package samples;


import java.io.*;


public class finaly {
	
    public static void main(String args[]) {
        int i = 0;
		
        try {
            FileInputStream  fi = new FileInputStream(args[0]);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                FileInputStream  fi = new FileInputStream(args[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        i++; 
    }
}

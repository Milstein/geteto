package br.jabuti.util;


import java.io.*;


/**
 <p>Class to help emit some debugging message. To activate the class
 run your program whith <BR></p>

 <p>java -DDEBUG <your Prog><BR></p>

 <p>and the messages will go to standard output<BR>
 Run with <BR></p>

 <p>java -DDEBUG=xxxx <your Prog><BR></p>

 <p>and the messages will go to file xxxx<BR></p>

 @version: 0.00001
 @author: Marcio Delamaro


 */
public class Debug {
	
    private static PrintStream out;
	
    static {
        try {
            String dout = System.getProperty("DEBUG");
            if (dout != null) {
                if (dout.length() == 0) {
                    out = System.out;
                } else {
                    out = new PrintStream(
                            new FileOutputStream(
                            new File(dout)
                            )
                            );
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
	
    /** Prints a message to the "debug device"

     @param x Sends <code>x.toString()</code> to the debug device
     */
    static public void D(Object x) {
        if (out != null) {
            out.println(x);
        }
    }
	 
    static public long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
	
}

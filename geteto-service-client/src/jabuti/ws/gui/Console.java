package jabuti.ws.gui;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Console {
    PipedInputStream piOut;
    PipedInputStream piErr;
    PipedOutputStream poOut;
    PipedOutputStream poErr;
    JTextArea textArea;

    public Console(JTextArea textArea) throws IOException {
    	
    	this.textArea = textArea; 
        // Set up System.out
        piOut = new PipedInputStream();
        poOut = new PipedOutputStream(piOut);
        System.setOut(new PrintStream(poOut, true));

        // Set up System.err
        piErr = new PipedInputStream();
        poErr = new PipedOutputStream(piErr);
        System.setErr(new PrintStream(poErr, true));


        // Create reader threads
        new ReaderThread(piOut).start();
        new ReaderThread(piErr).start();
        
    }

    class ReaderThread extends Thread {
        PipedInputStream pi;

        ReaderThread(PipedInputStream pi) {
            this.pi = pi;
        }

        public void run() {
            final byte[] buf = new byte[1024];
            try {
                while (true) {
                    final int len = pi.read(buf);
                    if (len == -1) {
                        break;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            textArea.append(new String(buf, 0, len));

                            // Make sure the last line is always visible
                            textArea.setCaretPosition(textArea.getDocument().getLength());

                            // Keep the text area down to a certain character size
                            int idealSize = 1000;
                            int maxExcess = 500;
                            int excess = textArea.getDocument().getLength() - idealSize;
                            if (excess >= maxExcess) {
                                textArea.replaceRange("", 0, excess);
                            }
                        }
                    });
                }
            } catch (IOException e) {
            }
        }
    }
    
    public static void main(String args[]) throws IOException{
    	JTextArea a = new JTextArea();
    	new Console(a);
    }
	
    
}
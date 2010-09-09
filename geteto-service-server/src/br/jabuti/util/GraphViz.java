

/**
 * This class store general configuration information
 * for using GraphViz Tool inside our application
 */

package br.jabuti.util;


import java.io.*;


public class GraphViz {
    static public String EXEC = "dot";
    static public String BIN = "C:\\Program Files\\ATT\\Graphviz\\bin\\";
    static public String FLAGS = "-Tgif";
		
    static public int callGraphViz(String dotFile, String targetFile) {
        Runtime command;
        // Criando um objeto RunTim
        command = Runtime.getRuntime();
		
        try {
            command.exec(EXEC + " " + FLAGS + " " + dotFile + " -o " + targetFile);
        } catch (IOException ioe) {
            System.err.println("Error executing GraphViz:\n" + ioe.getMessage() + "\n");
            return -1;
        }
        return 0;
    }
}

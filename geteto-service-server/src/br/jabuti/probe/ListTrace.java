package br.jabuti.probe;


import java.util.*;
import java.io.*;


public class ListTrace {
    public static void main(String args[]) throws Throwable {
        TraceReader dtr = null;

        try {
            String filename = args[0];

            System.out.println("TRACE FILE: " + filename);
			
            dtr = new DefaultTraceReader(new File(filename));
        	
            Hashtable trace = (Hashtable) dtr.getPaths();
            int cont = 0;        	

            while (trace != null) {
                System.out.println("**************************************");
                System.out.println("Path number " + (++cont));
                System.out.println("**************************************");
                Iterator it = trace.keySet().iterator();

                while (it.hasNext()) {
                    ProbedNode pn = (ProbedNode) it.next();

                    System.out.println(pn.toString());
        			
                    String[][] nodes = (String[][]) trace.get(pn);
        			
                    for (int i = 0; i < nodes.length; i++) {
                        System.out.println("Path len: " + nodes[i].length + "\n");
                        for (int j = 0; j < nodes[i].length; j++) {
                            String n = nodes[i][j];

                            System.out.print(" " + n);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
                trace = (Hashtable) dtr.getPaths();
            }
        } catch (IOException ioe) {
            System.out.println("Cannot find trace file");
            return;
        }
    }
}

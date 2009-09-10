package identifier;

import junit.framework.*;

public class Main {
  public static void main(String[] args) {
    junit.swingui.TestRunner.run(FunctionalTC.class);    
    junit.swingui.TestRunner.run(StructuralTC.class);    
  }	
}
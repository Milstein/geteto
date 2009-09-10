package samples;

import java.io.*;

public class ExHandler {

    public Object exceptionHandlerExample(String s)
    {
       FileInputStream fis = null;
       try
       {
           fis = new FileInputStream(s);
       }
       catch (FileNotFoundException e1)
       {
            e1.printStackTrace();
       }
       catch (IOException e2)
       {
            e2.printStackTrace();
       }
       return fis;
    }
    
    static public void main(String[] args)
    {
    }
}



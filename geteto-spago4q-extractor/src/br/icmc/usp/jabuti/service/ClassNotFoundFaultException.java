
/**
 * ClassNotFoundFaultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package br.icmc.usp.jabuti.service;

public class ClassNotFoundFaultException extends java.lang.Exception{
    
    private br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ClassNotFoundFaultE faultMessage;

    
        public ClassNotFoundFaultException() {
            super("ClassNotFoundFaultException");
        }

        public ClassNotFoundFaultException(java.lang.String s) {
           super(s);
        }

        public ClassNotFoundFaultException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ClassNotFoundFaultException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ClassNotFoundFaultE msg){
       faultMessage = msg;
    }
    
    public br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ClassNotFoundFaultE getFaultMessage(){
       return faultMessage;
    }
}
    
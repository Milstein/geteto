
/**
 * InvalidExpressionFaultException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package br.icmc.usp.jabuti.service;

public class InvalidExpressionFaultException extends java.lang.Exception{
    
    private br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.InvalidExpressionFaultE faultMessage;

    
        public InvalidExpressionFaultException() {
            super("InvalidExpressionFaultException");
        }

        public InvalidExpressionFaultException(java.lang.String s) {
           super(s);
        }

        public InvalidExpressionFaultException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidExpressionFaultException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.InvalidExpressionFaultE msg){
       faultMessage = msg;
    }
    
    public br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.InvalidExpressionFaultE getFaultMessage(){
       return faultMessage;
    }
}
    
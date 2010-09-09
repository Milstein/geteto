
/**
 * ParserConfigurationExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package br.icmc.usp.jabuti.service;

public class ParserConfigurationExceptionException extends java.lang.Exception{
    
    private br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ParserConfigurationException faultMessage;

    
        public ParserConfigurationExceptionException() {
            super("ParserConfigurationExceptionException");
        }

        public ParserConfigurationExceptionException(java.lang.String s) {
           super(s);
        }

        public ParserConfigurationExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ParserConfigurationExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ParserConfigurationException msg){
       faultMessage = msg;
    }
    
    public br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.ParserConfigurationException getFaultMessage(){
       return faultMessage;
    }
}
    
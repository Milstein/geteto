/**
 * QPSProjectManagerSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.spago4q.sdk.stub;

public class QPSProjectManagerSoapBindingSkeleton implements it.eng.spago4q.sdk.stub.QPSProjectManager, org.apache.axis.wsdl.Skeleton {
    private it.eng.spago4q.sdk.stub.QPSProjectManager impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("addProject", _params, new javax.xml.namespace.QName("", "addProjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:qpsprojectmanager", "addProject"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("addProject") == null) {
            _myOperations.put("addProject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("addProject")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deleteProject", _params, new javax.xml.namespace.QName("", "deleteProjectReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:qpsprojectmanager", "deleteProject"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deleteProject") == null) {
            _myOperations.put("deleteProject", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deleteProject")).add(_oper);
    }

    public QPSProjectManagerSoapBindingSkeleton() {
        this.impl = new it.eng.spago4q.sdk.stub.QPSProjectManagerSoapBindingImpl();
    }

    public QPSProjectManagerSoapBindingSkeleton(it.eng.spago4q.sdk.stub.QPSProjectManager impl) {
        this.impl = impl;
    }
    public boolean addProject(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException
    {
        boolean ret = impl.addProject(in0, in1);
        return ret;
    }

    public boolean deleteProject(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.deleteProject(in0);
        return ret;
    }

}

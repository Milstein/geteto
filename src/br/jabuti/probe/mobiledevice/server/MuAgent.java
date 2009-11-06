package br.jabuti.probe.mobiledevice.server;

import java.io.IOException;
import java.io.Serializable;


public  abstract  class  MuAgent extends  Thread  implements  GroupHandler , Serializable

{

 /***
 **Class Fields
 ***/
 private  transient  MuServer server ;
 private  static  final  String AGENTLABEL ="_MuAgent_";


//  CLASS: mucode.abstractions.MuAgent:
 public     MuAgent(  MuServer server) 
 {
    super();
    this.server =null;
    this.server =server;
    return;

 }

//  CLASS: mucode.abstractions.MuAgent:
 public     MuAgent( ) 
 {
    super();
    this.server =null;
    return;

 }

//  CLASS: mucode.abstractions.MuAgent:
 public  final    void go(  String destination,  String [] classNames,  String dynLink,  boolean synch) 	 
 throws IOException  ,ClassNotFoundException 
 {
    Group group= null;
    String agentClassName= null;
    if(this.server== null)
    {
      this.server =MuServer.getServer(this);
   
    }
    agentClassName=this.getClass().getName();
    group=this.server.createGroup(agentClassName,agentClassName);
    group.addObject("_MuAgent_",this);
    group.addClasses(classNames);
    group.setDynamicLinkSource(dynLink);
    group.setSynchronousTransfer(synch);
    group.ship(destination);
    this.stop();
    return;

 }

//  CLASS: mucode.abstractions.MuAgent:
 public  final    void go(  String destination) 	 
 throws IOException  ,ClassNotFoundException 
 {
    if(this.server== null)
    {
      this.server =MuServer.getServer(this);
   
    }
    this.go(destination,ClassInspector.getClassClosure(this.getClass().getName(),this.server,5),null,false);
    return;

 }

//  CLASS: mucode.abstractions.MuAgent:
 public  final    Thread unpack(  Group group) 	 
 throws MuCodeException 
 {
    MuAgent agent= null;
    agent=(MuAgent)group.getObject("_MuAgent_");
    agent.server =group.getServer();
    return agent;

 }

//  CLASS: mucode.abstractions.MuAgent:
 public  abstract    void run( );


}


package br.jabuti.mobility.abstractions;


import mucode.*;
import br.jabuti.mobility.mobile.*;

/** 
*	This is a re-implementation of <code>mucode.abstractions.MuAgent</code>.
*	It replaces that class for code instrumentation proposes. The comments
*	in this doc. reproduces the documentatio of that class
*/
public abstract class MuAgent extends mucode.abstractions.MuAgent 
{
  protected String hostDestino, projectName;
  protected int delay;
  static final public String runName = "jabutiRun";

  	public MuAgent(MuServer server) 
  	{ 
  		super(server);
		hostDestino = new String(HostProber.getHostDestino());
		projectName = new String(HostProber.getProjectName());
		delay = HostProber.getTimeout();
  	}

  	public MuAgent() 
  	{ 
  		super();
  	} 


	public void init(String host, String proj)
	{
		hostDestino = host;
		projectName = proj;
	}
 
 /* 	public void run()
  	{
  		System.out.println("Rodando agente " + hostDestino + projectName);
  		try
  		{
  			HostProber.init(hostDestino, projectName, delay);
  			HostProber.start();
  			jabutiRun();
  		}
  		finally
  		{
  			HostProber.stop();
  		}
  	}
  	
  	abstract public void jabutiRun();
*/
}


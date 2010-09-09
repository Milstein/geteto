package br.jabuti.mobility.mobile.agent;

import java.util.*;
import java.io.*;
import mucode.abstractions.*;
import mucode.*;
import br.jabuti.mobility.HostProberServer;
import br.jabuti.mobility.mobile.*;

public class HostProberAgent extends MuAgent
{
	/**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 5213032578170757706L;
	String host, destino, DelaProject;
	long timeStamp;
	Hashtable hs;
	boolean migrated;
	private int op;
	static final public int TRACE_REPORT = 0,
							START_TRACES = 1,
							END_TRACES = 2;
	
	
  	public HostProberAgent(String host, long stamp,
  							Hashtable traces,
  	                       	String destino, String proj,
  	                       	MuServer ms) 
  	{
     	super(ms);
     	this.host = host;
     	this.destino = destino;
     	DelaProject = proj;
     	hs = traces;
     	migrated = false;
     	op = TRACE_REPORT;
     	timeStamp = stamp;
  	}

  	public HostProberAgent(String host, long stamp, int oper,
  	                       String destino, String proj,
  	                       MuServer ms) 
  	{
     	super(ms);
     	this.host = host;
     	this.destino = destino;
     	DelaProject = proj;
     	hs = null;
     	migrated = false;
     	op = oper;
     	timeStamp = stamp;
  	}


  	public HostProberAgent() { super(); }  

  	public void run() 
  	{
  		if ( ! migrated )
  		{
  			boolean ok = true;
			try 
			{
				migrated = true;
				go(destino);
			}
			catch (Exception e)
			{
				System.err.println(e.getClass().getName() + " reason: " +
					e.getMessage());
				e.printStackTrace();
				System.err.println("Could not send data to " + destino +
					". Check the reason...");
				System.err.println(this);
				ok = false;
			}	  			
			finally
			{
				if (ok) HostProber.signalAgentSent();
			}
  		}
  		else
  		{
  			try
  			{
  				HostProberServer hps = (HostProberServer) MuServer.getServer();
  				
  				// verifica qual o tipo de operacao o agente quer realizar
  				switch (op)
  				{
  					case START_TRACES: // inicio da execucao de um agente
  						hps.startTraces(host, timeStamp, DelaProject);
  						break;
  					case TRACE_REPORT: // chegada de uma porcao do trace de um agente
		  				hps.addTraces(host, timeStamp, DelaProject, hs);
  						break;
  					case END_TRACES: // final da execucao de todos os agentes em um server
  						hps.endTraces(host, timeStamp, DelaProject);
  						break;
  				}
  				
  			}
  			catch (FileNotFoundException e)
  			{
  				System.err.println(e.getClass().getName() + " " + e.getMessage());
  			}
  			catch (IOException e)
  			{
  				System.err.println(e.getClass().getName() + " " + e.getMessage());
  			}
  		}
  	}
  	
  	public String toString()
  	{
  		String s = "";
  		switch (op)
  		{
  			case START_TRACES:
  				s = "START_TRACES" ;
  				break;
  			case TRACE_REPORT:
  				s = "TRACE_REPORT" ;
  				break;
  			case END_TRACES:
  				s = "END_TRACES" ;
  				break;
  		}
  		return this.getClass().getName() + ":" + s + " " + host + 
  		 		" " + timeStamp + " " + destino + " " + DelaProject;
  	}
}

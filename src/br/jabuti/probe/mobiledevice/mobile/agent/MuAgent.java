/*
Copyright (C) 2006 Auri Vicenzi and Marcio Delamaro

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/

package br.jabuti.probe.mobiledevice.mobile.agent;

import br.jabuti.probe.mobiledevice.mobile.HostProber;
import br.jabuti.probe.mobiledevice.server.MuServer;


/** 
*	This is a re-implementation of <code>mucode.abstractions.MuAgent</code>.
*	It replaces that class for code instrumentation proposes. The comments
*	in this doc. reproduces the documentatio of that class
*/
public abstract class MuAgent extends br.jabuti.probe.mobiledevice.server.abstractions.MuAgent 
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


package samples;

import mobility.abstractions.MuAgent;
import mobility.HostProber;

public class instru extends MuAgent
{
	
	
	public void run()
	{
		HostProber.init(hostDestino, projectName);
	}
}
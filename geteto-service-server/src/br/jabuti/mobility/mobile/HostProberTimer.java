package br.jabuti.mobility.mobile;


public class HostProberTimer extends Thread
{
	private int sleepTime;
	private volatile boolean fim, resetFlag;
	
	public HostProberTimer(int t)
	{
		sleepTime = t;
		fim = false;
		resetFlag = true;
	}
	
	
	public void run()
	{
		while (! fim )
		{
			synchronized (this)
			{
				if (resetFlag)
				{
					resetFlag = false;
				}
				else
				{
					HostProber.dump();
				}
			}
			try
			{
				sleep(sleepTime);
			}
			catch (InterruptedException e)
			{
				reset();
			}
		}
	}
	
	synchronized public void stopTimer()
	{
		fim = true;
		reset();
	}

	synchronized public void reset()
	{
		resetFlag = true;
	}
	
	public int getTimeout()
	{
		return sleepTime;
	}
}

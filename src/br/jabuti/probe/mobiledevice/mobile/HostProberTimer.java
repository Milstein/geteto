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

package br.jabuti.probe.mobiledevice.mobile;


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

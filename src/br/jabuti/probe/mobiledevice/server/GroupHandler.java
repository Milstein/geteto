package br.jabuti.probe.mobiledevice.server;


public interface GroupHandler 
{
	Thread unpack(Group group) throws MuCodeException;
}
package br.jabuti.lookup.api;

public interface JavaProfile
{
	boolean has(String className);
	
	String getId();
	
	String getName();
}

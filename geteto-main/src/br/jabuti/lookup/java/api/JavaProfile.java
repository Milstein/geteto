package br.jabuti.lookup.java.api;

public interface JavaProfile
{
	boolean has(String className);
	
	String getId();
	
	String getName();
}

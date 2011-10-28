package br.icmc.labes.testing;

public class Dummy
{
	public static final String message = "Hello world";
	
	public Dummy()
	{
		System.out.println(message);
	}
	
	public static void main(String[] args)
	{
		Dummy dummy = new Dummy();
	}
}

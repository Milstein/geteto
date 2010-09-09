/*
 * Created on 18/10/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.jabuti.criteria;

abstract public class Requirement implements Comparable {

	private int weight;
	
	public void setWeight(int x)
	{
		weight = x;
	}
	
	public int getWeight()
	{
		return weight;
	}
}

package Tipos;

public class DadosEmpresa
{
	private int empID;
	private String nomeEmpresa;
	private String CGC;
	private float txDesconto;
	
	public DadosEmpresa()
	{
		empID=0;
		nomeEmpresa="";
		CGC="";
		txDesconto=0;
	}
	
	public void setEmpID(int newEmpID)
	{
		empID = newEmpID;
	}
	
	public int getEmpID()
	{
		return empID;
	}
	
	public void setNomeEmpresa(String newNomeEmpresa)
	{
		nomeEmpresa = newNomeEmpresa;
	}
	
	public String getNomeEmpresa()
	{
		return nomeEmpresa;
	}
	
	public void setCGC(String newCGC)
	{
		CGC = newCGC;
	}
	
	public String getCGC()
	{
		return CGC;
	}
	
	public void setTxDesconto(float newTxDesconto)
	{
		txDesconto = newTxDesconto;
	}
	
	public float getTxDesconto()
	{
		return txDesconto;
	}
}
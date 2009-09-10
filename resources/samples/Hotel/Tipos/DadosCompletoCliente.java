package Tipos;

public class DadosCompletoCliente extends DadosCliente
{
	int cliID;
	String RG;
	String CPF;
	String endereco;
	String cidade;
	String bairro;
	String CEP;
	String fone;
	
	public void setCliID(int newCliID)		
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
	}
	
	public void setRG(String newRG)
	{
		RG = newRG;
	}
	
	public String getRG()
	{
		return RG;
	}
	
	public void setCPF(String newCPF)
	{
		CPF = newCPF;
	}
	
	public String getCPF()
	{
		return CPF;
	}
	
	public void setEndereco(String newEndereco)
	{
		endereco = newEndereco;
	}
	
	public String getEndereco()
	{
		return endereco;
	}
	
	public void setCidade(String newCidade)
	{
		cidade = newCidade;
	}
	
	public String getCidade()
	{
		return cidade;
	}
			
	public void setBairro(String newBairro)
	{
		bairro = newBairro;
	}
	
	public String getBairro()
	{
		return bairro;
	}
	
	public void setCEP(String newCEP)
	{
		CEP = newCEP;
	}
	
	public String getCEP()
	{
		return CEP;
	}
	
	public void setFone(String newFone)
	{
		fone = newFone;
	}
	
	public String getFone()
	{
		return fone;
	}		
	
}		

package Tipos;

public class DadosCliente
{
	private int CliID;
	private String nome;
	private String contato;
	private String email;
	
	public DadosCliente()
	{
	}
	
	public void setCliID(int newCliID)
	{
		CliID = newCliID;
	}
	
	public int getCliID()
	{
		return CliID;
	}
	
	public void setNome(String newNome)
	{
		nome = newNome;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setContato(String newContato)
	{
		contato = newContato;
	}
	
	public String getContato()
	{
		return contato;
	}
	
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	
	public String getEmail()
	{
		return email;
	}
}	
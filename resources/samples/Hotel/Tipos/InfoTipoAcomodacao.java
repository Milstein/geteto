package Tipos;

public class InfoTipoAcomodacao
{
	int qtdePessoas;
	float preco;
	
	public InfoTipoAcomodacao()
	{
	}
	
	public InfoTipoAcomodacao(int newQtdePessoas, float newPreco)
	{
		qtdePessoas = newQtdePessoas;
		preco = newPreco;
	}
	
	public void setQtdePessoas(int newQtdePessoas)
	{
		qtdePessoas = newQtdePessoas;
	}
	
	public int getQtdePessoas()
	{
		return qtdePessoas;
	}
	
	public void setPreco(float newPreco)
	{
		preco = newPreco;
	}
	
	public float getPreco()
	{
		return preco;
	}
}
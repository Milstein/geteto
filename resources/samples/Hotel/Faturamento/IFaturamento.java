package Faturamento;

public interface IFaturamento 
{
	public void abrirConta(int cliID);
	public void abrirContaEstada(int cliID, float valor, float desconto);
	public int obterNumContaCliente(int cliID);
	public void addLancamento(int numConta, String desc, String tipo, float valor, float desconto);		
	public float obterValorConta(int numConta);
	public float obterValorContaCliente(int cliID);
	public float obterValorDescontoContaCliente(int cliID);
}
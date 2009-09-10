package Faturamento;

public class GesFaturamento implements IFaturamento
{
	public void abrirConta(int cliID)
	{
		Conta ct = new Conta(cliID);
		ct.salva();
	}
	
	public void abrirContaEstada(int cliID, float valor, float desconto)
	{
		Conta ct = new Conta(cliID);
		ct.salva();
		ct.addLancamento("add", "Estada no Hotel", valor, desconto);
	}
	
	public int obterNumContaCliente(int cliID)
	{
		Conta ct = Conta.obterContaCliente(cliID);
		return ct.getNumConta();
	}
	
	public void addLancamento(int numConta, String desc, String tipo, float valor, float desconto)
	{
		Lancamento lc= new Lancamento(numConta, tipo, desc, valor, desconto);
		lc.salva();
	}
	
	public float obterValorConta(int numConta)
	{
		Conta ct = Conta.obterConta(numConta);
		return ct.calcularValorConta();
	}
	
	public float obterValorDescontoConta(int numConta)
	{
		Conta ct = Conta.obterConta(numConta);
		return ct.calcularDescontoConta();
	}
	
	public float obterValorDescontoContaCliente(int cliID)
	{
		Conta ct = Conta.obterContaCliente(cliID);
		return ct.calcularValorConta();
	}
	
	public float obterValorContaCliente(int cliID)
	{
		Conta ct = Conta.obterContaCliente(cliID);
		return ct.calcularDescontoConta();
	}
}
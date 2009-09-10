package GesEmpresa;

import java.util.Vector;
import Tipos.*;

public class GesEmpresa implements IGesEmpresa
{
	Empresa empresaAtual;
	
	public GesEmpresa()
	{
	}
	
	public void associaClienteEmpresa(DadosCliente cli, String nomeEmpresa)
	{
		empresaAtual = obterEmpresa(nomeEmpresa);
		empresaAtual.associaCliente(cli);				
	}
	
	public Vector obterEmpresas()
	{		
		return Empresa.getEmpresas();
	}
	
	public Empresa selecionaEmpresa(String nomeEmpresa)
	{
		//return Empresa.obterEmpresa(nomeEmpresa);		
		return Empresa.obterEmpresa(nomeEmpresa);
	}
	
	public Vector obterClientesEmpresa(String nomeEmpresa)
	{			    		
		empresaAtual = selecionaEmpresa(nomeEmpresa);		
		return empresaAtual.obterClientes();
	}
	
	public float obterTXDescontoEmpresa(String nomeEmpresa)
	{
		empresaAtual = selecionaEmpresa(nomeEmpresa);		
		return empresaAtual.getTxDesconto();
	}
	
	public Empresa obterEmpresa(int empID)
	{
		empresaAtual = Empresa.obterEmpresa(empID);
		return empresaAtual;
	}
	
	public Empresa obterEmpresa(String nomeEmpresa)
	{
		empresaAtual = Empresa.obterEmpresa(nomeEmpresa);
		return empresaAtual;
	}
	
	public void addEmpresa(DadosEmpresa emp)
	{
		Empresa empresa = new Empresa();
		empresa.setNomeEmpresa(emp.getNomeEmpresa());
		empresa.setCGC(emp.getCGC());
		empresa.setTxDesconto(emp.getTxDesconto());
		empresa.salva();
	}
	
	public String obterEmpresaCliente(int cliID)
	{
		Cliente c = Cliente.obterCliente(cliID);
		if (c.getEmpID()!=0)
		   {		   
		      empresaAtual = Empresa.obterEmpresa(c.getEmpID());
		      return empresaAtual.getNomeEmpresa();
		   }
		   else return "";
	}
}
package GesCliente;

import java.util.Vector;
import Tipos.*;

public class GesCliente implements IGesCliente
{	
	Cliente clienteAtual;
	
	
	public Vector obterClientes()
	{
		return Cliente.getClientes();
	}
	
	public int obterCliente(DadosCliente cli) //: int {cliID}
	{
		Cliente c = Cliente.obterCliente(cli.getNome());
		if (c!=null)
		   return c.getCliID();
		   else return 0;		
	}
	
	public int criarCliente(DadosCliente cli)//: int {cliID}
	{
		Cliente c = new Cliente();
		c.setNome(cli.getNome());
		c.setContato(cli.getContato());
		c.setEmail(cli.getEmail());
		c.salva();						
		return c.getCliID();
	}
	
	public void notificarCliente (int cliID, String res)
	{
	}
	
	public DadosCliente obterDadosCliente(int cliID)
	{	
		selecionaCliente(cliID);	
		Cliente c = clienteAtual;
		DadosCliente cli = new DadosCliente();
		cli.setNome(c.getNome());
		cli.setContato(c.getContato());
		cli.setEmail(c.getEmail());		
		return cli;
	}
	
	public DadosCompletoCliente obterDadosCompletoCliente(int cliID)
	{
		selecionaCliente(cliID);
		Cliente c = clienteAtual;
		DadosCompletoCliente cli = new DadosCompletoCliente();
		cli.setCliID(c.getCliID());
		cli.setNome(c.getNome());
		cli.setContato(c.getContato());
		cli.setEmail(c.getEmail());
		cli.setFone(c.getFone());
		cli.setEndereco(c.getEndereco());
		cli.setCidade(c.getCidade());
		cli.setBairro(c.getBairro());
		cli.setCEP(c.getCEP());
		cli.setRG(c.getRG());
		cli.setCPF(c.getCPF());
		return cli;
	}
	
	
	public void completarDadosCliente(DadosCompletoCliente cliComp)
	{
		selecionaCliente(cliComp.getCliID());								
		Cliente cli = clienteAtual;
		cli.setFone(cliComp.getFone());
		cli.setEndereco(cliComp.getEndereco());
		cli.setCidade(cliComp.getCidade());
		cli.setBairro(cliComp.getBairro());
		cli.setCEP(cliComp.getCEP());
		cli.setRG(cliComp.getRG());
		cli.setCPF(cliComp.getCPF());
		cli.salva();
	}
	
	public void selecionaCliente(int cliID)
	{						
		
		Cliente c = Cliente.obterCliente(cliID);		
		clienteAtual = c;
	}
	
	public String obterNomeCliente(int cliID)
	{
		selecionaCliente(cliID);
		return clienteAtual.getNome();
	}
	
	public DadosCliente obterCliente(String nomeCliente)
	{
		DadosCliente dc = new DadosCliente();
		dc.setCliID(0);	
		Cliente c = Cliente.obterCliente(nomeCliente);
		if (c!=null)
		{			
			dc.setCliID(c.getCliID());
			dc.setNome(c.getNome());
			dc.setContato(c.getContato());
			dc.setEmail(c.getEmail());
		}
		return dc;
	}
}
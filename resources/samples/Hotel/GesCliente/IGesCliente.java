package GesCliente;

import java.util.Vector;

import Tipos.*;

public interface IGesCliente
{
	public int obterCliente(DadosCliente cli); //: int {cliID}	
	public DadosCliente obterCliente(String nomeCliente);
	public int criarCliente(DadosCliente cli);//: int {cliID}
	public void notificarCliente (int cliID, String res);
	public DadosCliente obterDadosCliente(int cliID);
	public DadosCompletoCliente obterDadosCompletoCliente(int cliID);
	public void completarDadosCliente(DadosCompletoCliente cliComp);
	
	//OUTROS:
	public Vector obterClientes();
	public String obterNomeCliente(int cliID);
}			
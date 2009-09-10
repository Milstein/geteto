package GesEmpresa;

import Tipos.*;
import java.util.Vector;



public interface IGesEmpresa
{
	public void associaClienteEmpresa(DadosCliente cli, String nomeEmpresa);
	public Vector obterEmpresas();
	public Vector obterClientesEmpresa(String nomeEmpresa); // vetor de Nomes
	public float obterTXDescontoEmpresa(String nomeEmpresa);
	public String obterEmpresaCliente(int cliID);
	public void addEmpresa(DadosEmpresa emp);
}
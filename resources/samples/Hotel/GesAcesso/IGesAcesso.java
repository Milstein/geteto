package GesAcesso;

import java.util.Vector;

public interface IGesAcesso
{
	public void addUsuario(String login, String senha, String papel);
	
	public void addPapel(String papel, String descricao);
	
	public void addOperacao(String operacao, String descricao);
	
	public void atribuirOperacaoPapel(String papel, String operacao);
	
	public String obterUsuarioRegistrado();
	
	public String obterPapelUsuario(String login);
	
	public Vector obterOperacoesPapel(String papel);		
	
	
	public Vector obterPapeis(); //Strings
	public Vector obterOperacoes();
	public Vector obterUsuarios();
	
	public void setUsuarioRegistrado(String userLogin);			
	
	public String obterSenhaUsuario(String login);
	
	public void setSenhaUsuario(String login, String senha);
	
}
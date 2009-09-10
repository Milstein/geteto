package GesAcesso;

import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;

public class GesAcesso implements IGesAcesso
{	
	static int i = 0;
	
	Usuario usuarioRegistrado = new Usuario();
	
	public GesAcesso()
	{
		
		
	}
	
	public void addUsuario(String login, String senha, String papel)
	{
		Usuario us = new Usuario(login,senha,papel);
		us.salva();
		//usuarios.add(us);
	}
	
	public void setSenhaUsuario(String login, String senha)
	{
		Usuario us = obterUsuario(login);
		//System.out.println("Senha no GesAcesso.setSenhaUsuario : " + senha);
		us.setSenha(senha);
		us.atualiza();
	}
	
	public void addPapel(String papel, String descricao)
	{
		Papel pp = new Papel(papel, descricao);
		pp.salva();
		//papeis.add(pp);
	}
	
	
	public void addOperacao(String operacao, String descricao)
	{
		Operacao op = new Operacao(operacao, descricao);
		op.salva();
	//	operacoes.add(op);
	}
				
	public void atribuirOperacaoPapel(String papel, String operacao)
	{
		Papel pp = obterPapel(papel);
	//	Operacao op = obterOperacao(operacao);
		pp.associaOperacao(operacao);
	}		
	
	public String obterPapelUsuario(String login)
	{
		Usuario us = obterUsuario(login);
		return us.getPapelUsuario();
	}			
	
	public Vector obterOperacoesPapel(String papel)
	{
		Papel pp = obterPapel(papel);
		return pp.getOperacoes();
	}
	
	
	public Papel obterPapel(String papel)
	{
		Papel pp = Papel.obterPapel(papel);
		return pp;		
	}
	
	
	public Operacao obterOperacao(String operacao)
	{
		Operacao op = Operacao.obterOperacao(operacao);		
		return op;
	}	
	
	public Usuario obterUsuario(String login)
	{
		Usuario us = Usuario.obterUsuario(login);
		return us;		
	}	
	
	public Vector obterPapeis()
	{
		Vector stPapeis = Papel.getPapeis();		
		return stPapeis;
	}
	
	public Vector obterUsuarios()
	{
		Vector stUsuarios = Usuario.getUsuarios();		
		return stUsuarios;
	}
	
	public Vector obterOperacoes()
	{
		Vector stOp = Operacao.getOperacoes();		
		return stOp;
	}
	
	public void setUsuarioRegistrado(String userLogin)
	{
		//usuarioRegistrado = obterUsuario(userLogin);
		infoAcesso.setUsuarioRegistrado(userLogin);
	}	
	
	public String obterUsuarioRegistrado()
	{
		//return usuarioRegistrado.getLogin();
		return infoAcesso.obterUsuarioRegistrado();
	}
	
	public String obterSenhaUsuario(String login)
	{
		Usuario us = Usuario.obterUsuario(login);
		return us.getSenha();
	}
	
}
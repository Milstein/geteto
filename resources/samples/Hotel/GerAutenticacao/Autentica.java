package GerAutenticacao;

import GesAcesso.*;
import java.util.Vector;

public class Autentica
{
	
	IGesAcesso IGA = new GesAcesso();
	
	public Autentica()
	{}
	
	public boolean verificarLogin(String login)
	{
		Vector vLog = IGA.obterUsuarios();
		boolean achou = false;
		String st = "";
		int i=0;
		//System.out.println("Login: " + login);
		while ((i<vLog.size()) && (!achou))
		{
			st = (String) vLog.get(i);
		//	System.out.println("Login buscado: " + st);
			if (login.equals(st))			
				achou = true;			
			i++;
		}
		
		return achou;
	}
	
	public boolean verificarSenha(String login, String senha)
	{				
		if (verificarLogin(login))
		{
		  //  System.out.println("Senha: " + senha);
			String sn = IGA.obterSenhaUsuario(login);
			//System.out.println("Senha buscada: " + sn);
			if (senha.equals(sn))
			   return true;
		}		
		return false;		
	}
	
	public boolean efetuarLogin(String login, String senha)
	{
		if (verificarSenha(login,senha))
		   IGA.setUsuarioRegistrado(login);
		   else return false;
		return true;
	}
	
	public String obterUsuarioRegistrado()
	{
		return IGA.obterUsuarioRegistrado();
	}
	
}
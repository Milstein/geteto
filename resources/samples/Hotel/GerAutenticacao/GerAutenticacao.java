package GerAutenticacao;

import GesAcesso.*;
import javax.swing.JFrame;
import java.awt.Frame;

public abstract aspect GerAutenticacao
{
	IGesAcesso IGA = new GesAcesso();
	
	public String obterUsuarioRegistrado()
	{
		return IGA.obterUsuarioRegistrado();
	}
	
	public void deslogar()
	{
		System.out.println("USUARIO DESLOGADO");
		IGA.setUsuarioRegistrado("nulo");
	}
		
	public abstract pointcut OperacaoInicial();
	public abstract pointcut OperacaoFinal();
		
	Object around(): OperacaoInicial() 
	{
	 	String usuario = obterUsuarioRegistrado();
		System.out.println("USUÁRIO LOGADO NO SISTEMA: " + 	usuario);
		if (usuario.equals("nulo"))
		{
			
			FrameLogin FL = new FrameLogin(new Frame(), "Login de Usuário", true);
			if (FL.getStatusLogin())
			   return proceed();			
			//throw new RuntimeException("ERRO");
			return null;
		}
		else return proceed();
	}	
	

	before(): OperacaoFinal()
	{
		deslogar();
	}
	
	
}
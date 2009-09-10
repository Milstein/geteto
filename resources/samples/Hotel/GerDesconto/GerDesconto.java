package GerDesconto;

import GesEmpresa.*;
import Tipos.*;

public abstract aspect GerDesconto 
{							
	
	public float recuperaTaxaDesconto(int cliID)
	{
	   IGesEmpresa IGE = new GesEmpresa();
	   String nomeEmpresa = IGE.obterEmpresaCliente(cliID);
	   float taxa;
	   if (!nomeEmpresa.equals(""))
	      taxa = IGE.obterTXDescontoEmpresa(nomeEmpresa);
	      else taxa = 0;
	   return taxa;
	}
	
	public abstract pointcut PontoDeDesconto();
	
		
	float around(DetalhesReserva res):PontoDeDesconto() && args(res)
	{
	    System.out.println("ACAO DO ASPECTO DE DESCONTO ");						    
		System.out.println(res.getCliID());
		if (res.getCliID()==0)
		    return 0;//new Float(0);
		float tx = recuperaTaxaDesconto(res.getCliID());
		System.out.println("TAXA DESCONTO: " + tx);
		//return (Object) global.recuperaTaxaDesconto(res.getCliID());
		//return proceed(tx);
		//Float ft = new Float(tx);			
		System.out.println("DESCONTO _ ASPECTO ");				
		return tx;		
	}				
	
}
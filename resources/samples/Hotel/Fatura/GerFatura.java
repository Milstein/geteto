package Fatura;

import GerReserva.*;
import Faturamento.*;
import Tipos.*;

public abstract aspect GerFatura
{				   

	IOcuparReserva IOR = new GerReserva();	                
	IFaturamento IFT = new GesFaturamento();
		
		
	public abstract pointcut AcessoFatura();
	
	after(String codReserva, DadosCompletoCliente cli): AcessoFatura() 
	                                                      && args(codReserva, cli)
	{						 
		    System.out.println("After Iniciar Estada");
			DetalhesReserva res = IOR.obterReserva(codReserva);
			float valor = IOR.obterPrecoReserva(res);
			float txdesconto = IOR.obterTXDescontoReserva(res);
			IFT.abrirContaEstada(res.getCliID(), valor, txdesconto*valor);
			System.out.println("GEROU FATURA - ASPECT OK");		 		
	}
	
}
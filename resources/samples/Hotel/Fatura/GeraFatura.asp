package Fatura;

import GerReserva.*;
import Faturamento.*;
import Tipos.*;

class GeraFatura
{				   

	IOcuparReserva IOR = new GerReserva();	                
	IFaturamento IFT = new GesFaturamento();
		
	hook IGerarFatura
	{
	
		IGerarFatura(method(String codReserva, DadosCompletoCliente cli))
		{
			execute(method);
		}
						
		after()
		{		 
		    System.out.println("After Iniciar Estada");
			DetalhesReserva res = global.IOR.obterReserva(codReserva);
			float valor = global.IOR.obterPrecoReserva(res);
			float txdesconto = global.IOR.obterTXDescontoReserva(res);
			global.IFT.abrirContaEstada(res.getCliID(), valor, txdesconto*valor);
			System.out.println("GEROU FATURA - ASPECT OK");
		} 
		
	}

	
}
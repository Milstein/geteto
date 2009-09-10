package GerRegistroOp;

import GerReserva.*;

public aspect AplicaRegistroOp extends GerRegistroOp
{
	public pointcut OperacoesEntrada(): 
		execution(* GerReserva.fazerReserva(..)) || 	 						
		execution(* GerReserva.alterarReserva(..)) ||						
		execution(* GerReserva.cancelarReserva(..)) ||
		execution(* GerReserva.iniciarEstada(..)) ||
		execution(* GerReserva.completarDadosCliente(..)) ||
		execution(* GerReserva.gerarFaturasReservasVencidas(..)) ||
		execution(* GerReserva.cancelarReservasVencidas(..));	     
}


                          
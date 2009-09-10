package Fatura;

import GerReserva.*;
import Tipos.*;

public aspect AplicaFatura extends GerFatura
{
	public pointcut AcessoFatura(): call(* *.iniciarEstada(..));	
}
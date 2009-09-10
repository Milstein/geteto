package GerDesconto;

import Tipos.*;
import GerReserva.*;

public aspect AplicaDesconto extends GerDesconto
{
	public pointcut PontoDeDesconto(): call(float *.obterTXDescontoReserva(DetalhesReserva));
}
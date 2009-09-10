package GerControleAcesso;

import GesHotel.*;
import GesAcesso.*;
import GesCliente.*;
import GerReserva.*;
import GesEmpresa.*;

public aspect AplicaControleAcesso extends GerControleAcesso
{
    public pointcut OperacoesControladas():
		execution(* GesHotel.*(..)) ||
		execution(* GesCliente.*(..)) ||		
		execution(* GerReserva.*(..)) ||
		execution(* GesEmpresa.*(..)) ||
		execution(* GesAcesso.addPapel(..)) ||
		execution(* GesAcesso.addUsuario(..)) ||
		execution(* GesAcesso.atribuirOperacaoPapel(..));            
}

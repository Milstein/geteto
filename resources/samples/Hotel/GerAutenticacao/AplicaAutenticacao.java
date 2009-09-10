package GerAutenticacao;

import exFrame.*;

public aspect AplicaAutenticacao extends GerAutenticacao
{
    public pointcut OperacaoInicial(): 
          call(* FrameEntrar.iniciarAplicacao());                                
   
    public pointcut OperacaoFinal():
          call(* FramePrincipal.sairSistema());    
}

package GerCriptografia;

import GesAcesso.*;

public aspect AplicaCriptografia extends GerCriptografia
{

   public pointcut OperacaoCriptografar():
       call(* GesAcesso.setSenhaUsuario(String,String));
   
   
   public pointcut OperacaoDescriptografar():
       call(String GesAcesso.obterSenhaUsuario(String));
   
}

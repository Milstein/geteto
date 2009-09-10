package GerControleAcesso;

import GesAcesso.*;
import java.util.Vector;

public abstract aspect GerControleAcesso
{				   	                
		String user = "";
		IGesAcesso IGA = new GesAcesso();
		
		public boolean contemSubstring(String st, String subst)
		{
			
			String aux;
			boolean achou =false;
			int ini,fim;
			ini=0;
			fim = subst.length();
			
			while ((fim<st.length()) && (!achou))
			{				
			    aux = st.substring(ini,fim);
			    if (aux.equals(subst))
			       achou=true;
			    ini++; fim++;
			}
			
			return achou;			
		}
		
		public void setUsuarioRegistrado(String newUser)
		{
		    user = newUser;
		}
		
		public boolean verificarOperacaoControlada(String metodo)
		{
		   Vector todasOp = IGA.obterOperacoes();
			
		   String opst="";
           boolean achou = false;           
           int i=0;
           
           while ((!achou) && (i<todasOp.size()))
           {                      
               opst = (String) todasOp.get(i); 
           
           	   if (contemSubstring(metodo, opst))
               {
           	       achou=true;
               }
               
               i++;
           }
           
           return achou;			
		}
		
		
		public boolean verificarDireitoAcesso(String metodo)
		{		                                        
		
		   System.out.println("USER: " + IGA.obterUsuarioRegistrado());		   
           setUsuarioRegistrado(IGA.obterUsuarioRegistrado());
           
           String papel = IGA.obterPapelUsuario(user);
           
           Vector ops = IGA.obterOperacoesPapel(papel);                      
           
           String opst="";
           boolean achou = false;           
           int i=0;
           
           while ((!achou) && (i<ops.size()))
           {                      
               opst = (String) ops.get(i);                
           	   if (contemSubstring(metodo, opst))
               {
           	       achou=true;
               }
               
               i++;
           }
           
           return achou;
        }
        
        
       public abstract pointcut OperacoesControladas();
		
			
		Object around():OperacoesControladas()
		{		   
		
		   boolean operacaoControlada = verificarOperacaoControlada(thisJoinPoint.toString());
		   
		   if (operacaoControlada)
		     {
		       	   System.out.println("ACAO DO ASPECTO DE CONTROLE DE ACESSO");
		           System.out.println("INTERCEPTOU MSG: " + thisJoinPoint);
				   boolean autorizado = verificarDireitoAcesso(thisJoinPoint.toString());
				   if (autorizado)
				     {
				        System.out.println("OK - USUARIO AUTORIZADO PARA EXECUTAR OPERACAO");
				        return proceed();
				      }
				     else 
				       { 
				          System.out.println("!!! ERRO - USUARIO NAO AUTORIZADO PARA EXECUTAR OPERACAO !!!");
				          return null;
				          //throw new RuntimeException("ERRO - USUÁRIO NÃO AUTORIZADO");
				          //throw new Exception("ERRO");	
				        }
		      }
		      else 
		        {		           	                 
		           //System.out.println(thisJoinPoint + " - OPERACAO NAO CONTROLADA");
		           return proceed();
		        }
		        
		        
		} 
		
}
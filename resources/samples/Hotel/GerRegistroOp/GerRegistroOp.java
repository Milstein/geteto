package GerRegistroOp;

import GesAcesso.*;
import GesRegistroOp.*;
import java.util.Date;
import java.sql.Time;
import java.util.Vector;

public abstract aspect GerRegistroOp
{				   	                
	IGesAcesso IGA = new GesAcesso();
	IGesRegistroOp IGRP = new GesRegistroOp();					
			
	public void registrarOperacaoExecutada(String msg)
	{
		Date dt = new Date();
		Time hora = new Time(dt.getHours(), dt.getMinutes(), dt.getSeconds());
		String usuario = IGA.obterUsuarioRegistrado();
		IGRP.registrarOperacaoExecutada(dt,hora,usuario,msg);
	}
	
	public abstract pointcut OperacoesEntrada();
	
	before():OperacoesEntrada()
	{		   		    
	    System.out.println("#BEFORE# ACAO DO ASPECTO DE REGISTRAR OPERACAO #BEFORE#");
	    String msg = "";
	    msg = msg + "BFR::" + thisJoinPoint;				   		   
	    System.out.println("REGISTRO DA OPERACAO: "+ msg);
	   	registrarOperacaoExecutada(msg);
	} 
		
	after():OperacoesEntrada()
	{		   				   		 	
		System.out.println("#AFTER# ACAO DO ASPECTO DE REGISTRAR OPERACAO #AFTER#");    
        String msg = "";
	    msg = msg + "AFTR::" + thisJoinPoint;				   		   
	    System.out.println("REGISTRO DA OPERACAO: "+ msg);
	  	registrarOperacaoExecutada(msg);  				   	
	}
						
}
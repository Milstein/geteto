package GerRegistroOp;

import GesRegistroOp.*;
import java.util.Vector;

public class RegistroOp
{				   	                
	IGesRegistroOp IGRP = new GesRegistroOp();					

		
	public Vector obterRegistrosOperacao()
	{
		return IGRP.obterRegistrosOperacao();
	}				
				
	public Vector obterOperacoesRegistradas()
	{
	    return IGRP.obterRegistrosOperacao();
	}			
}
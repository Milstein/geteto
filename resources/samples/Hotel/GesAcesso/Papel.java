package GesAcesso;

import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;



public class Papel
{
	String papel;
	String descricao;
	Vector operacoes;
		
	public Papel()
	{
		papel = "";
		descricao = "";
		operacoes = new Vector();
	}
	
	public Papel(String newPapel, String newDescricao)
	{
		papel = newPapel;
		descricao = newDescricao;
		operacoes = new Vector();
	}
	
	public void salva()
	{
		TableManager TM = new TableManager();
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("papel");
		colunas.add("descricao");		
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(papel);
		valoresColunas.add(descricao);		
		
		boolean ok = TM.addRegistro("Papel", colunas, valoresColunas);	
	}
	
	public void addOperacao(String nomeOp)
	{		
		operacoes.add(nomeOp);		
	}
	
	public void associaOperacao(String nomeOp)
	{
		TableManager TM = new TableManager();
		
		Vector colunas = new Vector();
		colunas.add("papel");
		colunas.add("nomeOp");		
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(papel);
		valoresColunas.add(nomeOp);		
		
		boolean ok = TM.addRegistro("OperacaoPapel", colunas, valoresColunas);
				
		operacoes.add(nomeOp);
		//criar registro de relacionamento entre operacoesPapel
	}
	
	public Vector getOperacoes()
	{
		return operacoes;
	}
	
	public String getPapelUsuario()
	{
		return papel;
	}
	
	public void setPapel(String newPapel)
	{
		papel = newPapel;
	}
	
		public void setDescricao(String newDescricao)
	{
		descricao = newDescricao;
	}
	
	public String getDescricao()
	{
		return descricao;
	}	
	
	public static Papel obterPapel(String papel)
	{				
		TableManager TM = new TableManager();				
		
			
		//recupera dados do hotel
		  ResultSet reg = TM.buscaPor("Papel","papel",papel);				
		  Papel p = Papel.setRSToObject(reg);	
		  		  
		  //REcupera operaçõs do papel
		  boolean OK = true;
	  	  //recupera Tipos Acomodacao do hotel	  	  
	      reg = TM.buscaPor("OperacaoPapel","papel",papel);
	      try{	      
		      OK = reg.next();	    	    	    
		      Object o = null;
		      while (OK)
		      {
		      	  o = reg.getObject("nomeOp");
		     	  p.addOperacao(o.toString());
				  OK = reg.next();
		      }	 
	      }
	      catch(SQLException sqle)
	      {
	      }
	      return p;   	
	}
	
	
	private static Papel setRSToObject(ResultSet reg)
	{
		Papel p = new Papel();
		
		//Object o = null;
		try{				
				
		 Object            o = null;				
         reg.next();
         o = reg.getObject("papel");         
         if (o!=null)
         p.setPapel(o.toString());                  
                  
         o = reg.getObject("descricao");         
         if (o!=null)
         p.setDescricao(o.toString());                                             
                          		           
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT PAPEL");
        	return null;
        }
        
        return p;		
	}
	
	public static Vector getPapeis()
	{
		Vector vt = new Vector();
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Papel");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("papel"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETPAPEIS");			
		}
		return vt;
	}	
}
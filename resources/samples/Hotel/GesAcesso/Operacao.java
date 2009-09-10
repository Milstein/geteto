package GesAcesso;

import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;

public class Operacao
{
	String nomeOp;
	String descricao;
	
	public Operacao()
	{
		nomeOp = "";
		descricao = "";
	}
	
	public Operacao(String newOperacao, String newDescricao)
	{
		nomeOp = newOperacao;
		descricao = newDescricao;
	}
	
	
	public void salva()
	{
		TableManager TM = new TableManager();
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("nomeOp");
		colunas.add("descricao");		
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(nomeOp);
		valoresColunas.add(descricao);		
		
		boolean ok = TM.addRegistro("Operacao", colunas, valoresColunas);			
	}
	
	
	public void setOperacao(String newNome)
	{
		nomeOp = newNome;
	}
	
	public String getNome()
	{
		return nomeOp;
	}
	
	public void setDescricao(String newDescricao)
	{
		descricao = newDescricao;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public static Vector getOperacoes()
	{
		Vector vt = new Vector();
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Operacao");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("nomeOp"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETOperacoes");			
		}
		return vt;
	}
	
	
	public static Operacao obterOperacao(String nomeOp)
	{				
		TableManager TM = new TableManager();				
		
			
		//recupera dados do hotel
		  ResultSet reg = TM.buscaPor("Operacao","nomeOp",nomeOp);				
		  Operacao op = Operacao.setRSToObject(reg);	
		  
		  return op;
		  //REcupera operaçõs do papel
		  //PRocurar na tabela operacoesPapel ()
	
	}
	
	private static Operacao setRSToObject(ResultSet reg)
	{
		Operacao op = new Operacao();
		
		//Object o = null;
		try{				
				
		 Object            o = null;				
         reg.next();
         o = reg.getObject("nomeOp");         
         if (o!=null)
         op.setOperacao(o.toString());                  
                  
         o = reg.getObject("descricao");         
         if (o!=null)
         op.setDescricao(o.toString());                                             
                          		           
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT Operacao");
        	return null;
        }
        
        return op;		
	}
	
}
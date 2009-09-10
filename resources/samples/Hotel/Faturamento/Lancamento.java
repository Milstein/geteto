package Faturamento;

import Persistencia.*;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lancamento
{
	private int numConta;
	private int numLancamento;
	private String tipo;
	private String desc;
	private float valor;
	private float desconto;
	
	
	public Lancamento()
	{
		numConta=0;
		numLancamento=0;
		tipo="";
		desc="";
		valor=0;
		desconto=0;
	}
	
	public Lancamento(int newNumConta, String newTipo, String newDesc,
	                  float newValor, float newDesconto)
	{
		numConta = newNumConta;
		numLancamento = 0;
		tipo = newTipo;
		desc=newDesc;
		valor=newValor;
		desconto = newDesconto;
	}
	
	public void setNumConta(int newNumConta)
	{
		numConta = newNumConta;
	}
	
	public int getNumConta()
	{
		return numConta;
	}
	
	public void setNumLancamento(int newNumLancamento)
	{
		numLancamento = newNumLancamento;
	}
	
	public int getNumLancamento()
	{
		return numLancamento;
	}
	
	public void setTipo(String newTipo)
	{
		tipo = newTipo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public void setDesc(String newDesc)
	{
		desc = newDesc;
	}
	
	public String getDesc()
    {
		return desc;
	}
	
	public void setValor(float newValor)
	{
		valor = newValor;
	}
	
	public float getValor()
	{
		return valor;
	}
	
	public void setDesconto(float newDesconto)
	{
		desconto = newDesconto;
	}
	
	public float getDesconto()
	{
		return desconto;
	}
	
	public void salva()
	{
		TableManager TM = new TableManager();
		numLancamento = TM.obterUltimoCodigo("Lancamentos","numLancamento")+1;
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("numConta");
		colunas.add("numLancamento");
		colunas.add("tipo");
		colunas.add("desc");
		colunas.add("valor");
		colunas.add("desconto");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(numConta));
		valoresColunas.add(String.valueOf(numLancamento));
		valoresColunas.add(tipo);		
		valoresColunas.add(desc);		
		valoresColunas.add(String.valueOf(valor));
		valoresColunas.add(String.valueOf(desconto));
		
		boolean ok = TM.addRegistro("Lancamentos", colunas, valoresColunas);	
	}
	
	public static Lancamento setRSToObject(ResultSet reg)
	{
		Lancamento lc = new Lancamento();
				
		try{				
				
		 Object            o = null;				        
         
         o = reg.getObject("numConta");
         Integer it = new Integer(o.toString());
		 lc.setNumConta(it.intValue());		 
		 
		 o = reg.getObject("numLancamento");
         it = new Integer(o.toString());
		 lc.setNumLancamento(it.intValue());		 
		 
		 o = reg.getObject("tipo");
         lc.setTipo(o.toString());
         
         o = reg.getObject("desc");
         lc.setDesc(o.toString()); 
         
         o = reg.getObject("valor");
         Float ft = new Float(o.toString());
         lc.setValor(ft.floatValue());
         
         o = reg.getObject("desconto");
         ft = new Float(o.toString());
         lc.setDesconto(ft.floatValue());
    	 
    	 return lc;		           
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT Lancamento");
        	return null;
        }                
	}
	
	
	/*
	public static Vector obterLancamento(int numConta)
	{
		TableManager TM = new TableManager();				
		
		try{				
		//recupera dados da Conta
		
		Vector clauses = new Vector();
		clauses.add("cliID");
		clauses.add("status");
		
		Vector parametros = new Vector();
		parametros.add(String.valueOf(cliID));
		parametros.add("false");
		
		
		ResultSet reg = TM.buscaPorCondicao("Conta",clauses, parametros);
		Conta ct = Conta.setRSToObject(reg);		
		
		boolean OK = true;
		//recupera Lancamentos da Conta
	    reg = TM.buscaPor("Lancamentos","numConta",String.valueOf(ct.getNumConta()));
	    OK = reg.next();	    	    	    
	    while (OK)
	    {
	    	Lancamento lc = Lancamento.setRSToObject(reg);
	    	if (lc!=null)
			   ct.addLancamento(lc);	    	
			OK = reg.next();
	    }	    
	    		
		//recupera Pagamento da Conta
		reg = TM.buscaPor("Pagamento","numConta",String.valueOf(ct.getNumConta()));	    
	    OK = reg.next();	    	    	    
	    while (OK)
	    {	    
	    	Pagamento pg = Pagamento.setRSToObject(reg);	    		    	
	    	if (pg!=null)
	    	    ct.setPagamento(pg);
	    	OK = reg.next();	    		    
	    }	    	    	    	    
	    
	    return ct;		
	    
	    }
	    catch (SQLException sqle)
	    {
	    	return null;
	    }
	}*/	
}
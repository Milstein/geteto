package Faturamento;

import Persistencia.*;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conta
{
	private int numConta;
	private int cliID;
	private Vector lancamentos;
	private Pagamento pgto;
	private boolean status;
	
	public Conta()
	{
		numConta = 0;
		cliID=0;
		lancamentos = new Vector();
		pgto = null;
		status = false;
	}
	
	public Conta(int newCliID)
	{
		numConta = 0;		
		cliID = newCliID;
		lancamentos = new Vector();
		pgto = null;
		status = false;
	}
	
	public void setNumConta(int newNumConta)
	{
		numConta = newNumConta;
	}
	
	public int getNumConta()
	{
		return numConta;
	}
	
	public void setCliID(int newCliID)
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
	}
	
	public void addLancamento(String newTipo, String newDesc, float newValor, float newDesconto)
	{
		Lancamento lc = new Lancamento(numConta, newTipo, newDesc, newValor, newDesconto);
		lc.salva();
		lancamentos.add(lc);
	}
	
	public void addLancamento(Lancamento newLc)
	{
		lancamentos.add(newLc);
	}
	
	public Vector getLancamentos()
	{
		return lancamentos;
	}
	
	public void setPagamento(Pagamento newPgto)
	{
		pgto = newPgto;
	}
	
	public Pagamento getPagamento()
	{
		return pgto;
	}
	
	public void setStatus(boolean newStatus)
	{
		status = newStatus;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void pagarConta(float valorPago)
	{
		float valorTotalConta = calcularValorConta();
		float valorDescontosConta = calcularDescontoConta();
		if (valorPago==valorTotalConta-valorDescontosConta)
			{
				pgto = new Pagamento(numConta, valorTotalConta, valorDescontosConta);
				status = true;
			}
			else System.out.println("Valor Insuficiente para pagar conta");
	}
	
	public float calcularValorConta()
	{
		float somaTotal = 0;
		Lancamento lc;
		for (int i=0; i<lancamentos.size(); i++)
		{
			lc = (Lancamento) lancamentos.get(i);
			if (lc.getTipo().equals("add"))
			   somaTotal+=(lc.getValor());
			   else somaTotal-=lc.getValor();
		}
		return somaTotal;
	}		
	
	public float calcularDescontoConta()
	{
		float somaTotal = 0;
		Lancamento lc;
		for (int i=0; i<lancamentos.size(); i++)
		{
			lc = (Lancamento) lancamentos.get(i);			
			somaTotal+=(lc.getDesconto());			
		}
		return somaTotal;
	}
	
	//Persistencia
	
	public void salva()
	{
		TableManager TM = new TableManager();
		numConta = TM.obterUltimoCodigo("Conta","numConta")+1;
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("numConta");
		colunas.add("cliID");
		colunas.add("status");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(numConta));
		valoresColunas.add(String.valueOf(cliID));
		valoresColunas.add(String.valueOf(status));		
		
		boolean ok = TM.addRegistro("Conta", colunas, valoresColunas);	
	}
	
	
	private static Conta setRSToObject(ResultSet reg)
	{
		Conta ct = new Conta();
				
		try{				
				
		 Object o = null;				
         if (reg.next())
         {                           
         o = reg.getObject(1);         
         Integer it = new Integer(o.toString());
		 ct.setNumConta(it.intValue());		 		 
		 o = reg.getObject(2);
         it = new Integer(o.toString());
		 ct.setCliID(it.intValue());			 
		 o = reg.getObject(3);
    	 Boolean bl = new Boolean(o.toString());	    
    	 ct.setStatus(bl.booleanValue());		 
    	 }
    	 return ct;		           
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT CONTA");
        	return null;
        }                
	}
	
	
	public static Conta obterContaCliente(int cliID)
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
	}	
	
	
	public static Conta obterConta(int numConta)
	{
		TableManager TM = new TableManager();				
		
		try{				
		//recupera dados da Conta
		
		Vector clauses = new Vector();
		clauses.add("numConta");
		clauses.add("status");
		
		Vector parametros = new Vector();
		parametros.add(String.valueOf(numConta));
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
	}	
}
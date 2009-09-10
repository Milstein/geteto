package Faturamento;

import Persistencia.*;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pagamento
{
	private int numConta;
	private int numPagamento;	
	private float valorTotal;	
	private float descontoTotal;
	
	public Pagamento()
	{
		numPagamento = 0;
		numConta = 0;
		valorTotal=0;		
		descontoTotal = 0;
	}
	
	public Pagamento(int newNumConta, float newValorTotal, float newDesconto)
	{
		numPagamento = 0;
		numConta = newNumConta;
		valorTotal=newValorTotal;	
		descontoTotal = newDesconto;	
	}
	
	public void setNumPagamento(int newNumPagamento)
	{
		numPagamento = newNumPagamento;
	}
	
	public int getNumPagamento()
	{
		return numPagamento;
	}
	
		public void setNumConta(int newNumConta)
	{
		numConta = newNumConta;
	}
	
	public int getNumConta()
	{
		return numConta;
	}
	
	public void setValorTotal(float newValor)
	{
		valorTotal = newValor;
	}
	
	public float getValorTotal()
	{
		return valorTotal;
	}		
	
	public void setDescontoTotal(float newDesconto)
	{
		descontoTotal = newDesconto;
	}
	
	public float getDescontoTotal()
	{
		return descontoTotal;
	}
	
	public void salva()
	{
		TableManager TM = new TableManager();
		numPagamento = TM.obterUltimoCodigo("Pagamento","numPagamento")+1;
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("numConta");
		colunas.add("numPagamento");		
		colunas.add("valorTotal");
		colunas.add("descontoTotal");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(numConta));
		valoresColunas.add(String.valueOf(numPagamento));		
		valoresColunas.add(String.valueOf(valorTotal));
		valoresColunas.add(String.valueOf(descontoTotal));
		
		boolean ok = TM.addRegistro("Pagamento", colunas, valoresColunas);	
	}
	
	public static Pagamento setRSToObject(ResultSet reg)
	{
		Pagamento pg = new Pagamento();
				
		try{				
				
		 Object            o = null;				
         
         o = reg.getObject("numConta");
         Integer it = new Integer(o.toString());
		 pg.setNumConta(it.intValue());		 
		 
		 o = reg.getObject("numPagamento");
         it = new Integer(o.toString());
		 pg.setNumPagamento(it.intValue());		 

         o = reg.getObject("valorTotal");
         Float ft = new Float(o.toString());
         pg.setValorTotal(ft.floatValue());
         
         o = reg.getObject("descontoTotal");
         ft = new Float(o.toString());
         pg.setDescontoTotal(ft.floatValue());
    	 
    	 return pg;		           
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT PAGAMENTO");
        	return null;
        }                
	}
}
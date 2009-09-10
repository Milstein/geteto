package GesHotel;

import java.sql.ResultSet;
import Persistencia.*;
import java.util.Vector;
import java.sql.SQLException;

class TipoAcomodacao
{	
    private int hotelID;    
	private String tipo;
	private int qtdePessoas;
	private float preco;
	
	public TipoAcomodacao()
	{
	}

	public TipoAcomodacao(int newHotelID, String newTipo, int newQtdePessoas, float newPreco)
	{
		hotelID = newHotelID;
		tipo = newTipo;
		qtdePessoas = newQtdePessoas;
		preco = newPreco;						
	}
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("hotelID");
		colunas.add("tipo");
		colunas.add("qtdePessoas");
		colunas.add("preco");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(hotelID));
		valoresColunas.add(tipo);
		valoresColunas.add(String.valueOf(qtdePessoas));
		valoresColunas.add(String.valueOf(preco));
		
		TableManager TM = new TableManager();
		
		boolean ok = TM.addRegistro("TipoAcomodacao", colunas, valoresColunas);
	}
		
	public String getTipo()
	{
		return tipo;
	}
	
	public void setTipo(String newTipo)
	{
		tipo = newTipo;
	}

	
	public int getQtdePessoas()
	{
		return qtdePessoas;
	}
	
	public void setQtdePessoas(int newQtdePessoas)
	{
		qtdePessoas = newQtdePessoas;
	}
	
	public float getPreco()
	{
		return preco;
	}		
	
	public void setPreco(float newPreco)
	{
		preco = newPreco;
	}
	
	public void setHotelID(int newHotelID)
	{
		hotelID = newHotelID;
	}
	
	public int getHotelID()
	{
		return hotelID;
	}
	
	public static TipoAcomodacao setRSToObject(ResultSet reg)
	{
	    TipoAcomodacao ta = new TipoAcomodacao();		
	    
	    try{	    
	    
	    Object o = null;	        	    	           
       	    		    		    
	    o = reg.getObject(1);
	    Integer it = new Integer(o.toString());
	    ta.setHotelID(it.intValue());	    
	    
	    o = reg.getObject(2);
        ta.setTipo(o.toString());        
        	        
        o = reg.getObject(3);
        it = new Integer(o.toString());
        ta.setQtdePessoas(it.intValue());        
        
        o = reg.getObject(4);
        Float ft = new Float(o.toString());
        ta.setPreco(ft.floatValue());                            	        
	      
	    }
    	catch (SQLException sqle)
    	{
    		System.out.println("ERRO SETDBOBJECT - TipoAcomod");
    		return null;
    	}
	
    	return ta;
	}
}
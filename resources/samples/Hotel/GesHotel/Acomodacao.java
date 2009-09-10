package GesHotel;

import java.sql.ResultSet;
import Persistencia.*;
import java.util.Vector;
import java.sql.SQLException;

class Acomodacao
{
	private int hotelID;
	private int numero;
	private String tipo;
	private boolean status;
	
	public Acomodacao()
	{
		numero = -1;
	}	
	
	public Acomodacao(int newHotelID,int newNumero, String newTipo)
	{
		hotelID = newHotelID;
		numero = newNumero;
		tipo = newTipo;
		status = false;			
	}
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("hotelID");
		colunas.add("numero");
		colunas.add("tipo");
		colunas.add("status");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(hotelID));
		valoresColunas.add(String.valueOf(numero));
		valoresColunas.add(tipo);
		valoresColunas.add(String.valueOf(status));
		
		TableManager TM = new TableManager();
		
		boolean ok = TM.addRegistro("Acomodacao", colunas, valoresColunas);	
	}
	
	public void atualiza()
	{
		TableManager TM = new TableManager();
		
		Vector colunas = new Vector();
		Vector valoresColunas = new Vector();
		Vector clausulas = new Vector();
		Vector parametros = new Vector();
		
		colunas.add("status");
		valoresColunas.add(String.valueOf(status));
		clausulas.add("hotelID");
		clausulas.add("numero");
		parametros.add(String.valueOf(hotelID));
		parametros.add(String.valueOf(numero));
		
	    System.out.println("Atualização:"+ TM.atualizaRegistro("Acomodacao",colunas,valoresColunas,clausulas,parametros));
	}
	
	public int getNumero()
	{
		return numero;
	}
	
	public void setNumero(int newNumero)
	{
		numero = newNumero;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public void setTipo(String newTipo)
	{
		tipo = newTipo;
	}
	
	public String getDescricaoTipo()
	{
		return tipo;
	}
	
	public void setStatus(boolean newStatus)
	{
		status = newStatus;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setHotelID(int newHotelID)
	{
		hotelID = newHotelID;
	}
	
	public int getHotelID()
	{
		return hotelID;
	}
	
	public static Acomodacao setRSToObject(ResultSet reg)
	{
		Acomodacao ac = new Acomodacao();
		
		try
		{
		Object o = null;
		
					
		o = reg.getObject(1);
	    Integer it = new Integer(o.toString());
	    ac.setHotelID(it.intValue());
					
    	o = reg.getObject(2);
	    it = new Integer(o.toString());
    	ac.setNumero(it.intValue());
    	
    	o = reg.getObject(3);    	
    	ac.setTipo(o.toString());
    	
    	o = reg.getObject(4);
    	Boolean bl = new Boolean(o.toString());	    
    	ac.setStatus(bl.booleanValue());
	      
    	    	
    	}
    	catch(SQLException sqle)
    	{
    		System.out.println("Erro SETDBOBJECT Acomod");
    		return null;
    	}
    	
    	return ac;
	}
	
}
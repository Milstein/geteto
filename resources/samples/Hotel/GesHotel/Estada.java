package GesHotel;

import java.sql.ResultSet;
import Persistencia.*;
import java.util.Vector;
import java.sql.SQLException;

class Estada
{	
	private int hotelID;
	private int cliID;	
	private String codReserva;	
	private int numAcomodacao;	
	private boolean status;
	
	public Estada()
	{
		status = false;
	}
	
	public Estada(int newHotelID, String newCodReserva, int newNumAcomodacao, int newCliID)
	{
		hotelID = newHotelID;
		cliID = newCliID;
		codReserva = newCodReserva;
		numAcomodacao = newNumAcomodacao;		
		status = false;
		
		
	}	
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("hotelID");
		colunas.add("cliID");
		colunas.add("codReserva");
		colunas.add("numAcomodacao");
		colunas.add("status");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(hotelID));
		valoresColunas.add(String.valueOf(cliID));
		valoresColunas.add(codReserva);
		valoresColunas.add(String.valueOf(numAcomodacao));
		valoresColunas.add(String.valueOf(status));
		
		TableManager TM = new TableManager();
		
		boolean ok = TM.addRegistro("Estada", colunas, valoresColunas);
	}
	
	public void setCodReserva(String newCodReserva)
	{
		codReserva = newCodReserva;
	}
	
	public String getCodReserva()
	{
		return codReserva;
	}
	
	public void setCliID(int newCliID)
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
	}
	
	public void setNumAcomodacao(int newNumAcomodacao)
	{
		numAcomodacao = newNumAcomodacao;
	}
	
	public int getNumAcomodacao()
	{
		return numAcomodacao;
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
	
	public static Estada setRSToObject(ResultSet reg)
	{
		Estada est = new Estada();	 
		
		try{			
		
		Object o = null;
	  	Integer it;
	  	  	
	    o = reg.getObject(1);
	  	if (o!=null)
	  	{it = new Integer(o.toString());
	    est.setHotelID(it.intValue());	 }
	    
	    
	    o = reg.getObject(2);
	    if (o!=null)
        {it = new Integer(o.toString());
	    est.setCliID(it.intValue());}		
	    
	    ;
	    o = reg.getObject(3);
	    if (o!=null)
    	est.setCodReserva(o.toString());
    	    	
    
    	o = reg.getObject(4);
    	if (o!=null)
        {it = new Integer(o.toString());
	  	est.setNumAcomodacao(it.intValue());}
	  	    		   		    
	    		    
		o = reg.getObject(5);
		if (o!=null)
    	{Boolean bl = new Boolean(o.toString());	    
    	est.setStatus(bl.booleanValue());}
    	

    		    	
    	}
    	catch (SQLException sqle)
    	{
    		System.out.println("ERRO SETDBOBJECT ESTADA");
    		return null;
    	}
    	
    	return est;
	}
}

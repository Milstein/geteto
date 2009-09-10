package GesHotel;

import Persistencia.*;
import java.util.Vector;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

class Reserva
{
	private int hotelID;	
	private int cliID;
	private String codReserva;
	private java.util.Date dataIni;
	private java.util.Date dataFim;
	private String tipo;
	private boolean status;
	private static int contador=0;
	
	public Reserva()
	{
		status =false;
		contador++;
	}
	
	public Reserva(int newHotelID, java.util.Date newDataIni,
	                          java.util.Date newDataFim, String newTipo,
	                          int newCliID)
	{				
		String st;	
		contador++;
		Integer it = new Integer(contador);
		st = it.toString();
		if (st.length()<4)
			{
				for (int i=0; i<=(4-st.length());i++)
				     st = "0"+st;
			}
						
		hotelID = newHotelID;
		cliID = newCliID;
		codReserva = "RES" + st;
		dataIni = newDataIni;
		dataFim = newDataFim;
		tipo = newTipo;						
		status = false;					
	}
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("hotelID");
		colunas.add("cliID");
		colunas.add("codReserva");
		colunas.add("dataIni");
		colunas.add("dataFim");				
		colunas.add("tipo");				
		colunas.add("status");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(hotelID));
		valoresColunas.add(String.valueOf(cliID));
		valoresColunas.add(codReserva);		
		
		
		String dI = "";
		dI = String.valueOf(dataIni.getYear()+1900) + "-" + String.valueOf(dataIni.getMonth()+1) + 
		     "-" + String.valueOf(dataIni.getDate());
		
		valoresColunas.add(dI);//String.valeuOf(status));
		
		
		String dF = "";
		dF = String.valueOf(dataFim.getYear()+1900) + "-" + String.valueOf(dataFim.getMonth()+1) + 
		     "-" + String.valueOf(dataFim.getDate());
		
		valoresColunas.add(dF);
		
		valoresColunas.add(tipo);
		valoresColunas.add(String.valueOf(status));
		
		TableManager TM = new TableManager();
		
		boolean ok = TM.addRegistro("Reserva", colunas, valoresColunas);
	}
	
	public void setCodReserva(String newCodReserva)
	{
		codReserva = newCodReserva;
	}
		
	public String getCodReserva()
	{
		return codReserva;
	}
	
	public void setHotelID(int newHotelID)
	{
		hotelID = newHotelID;
	}
	
	public int getHotelID()
	{
		return hotelID;
	}
	
	public void setDataIni(java.util.Date newDataIni)
	{
		dataIni = newDataIni;
	}
	
	public java.util.Date getDataIni()
	{
		return dataIni;
	}
	
	public void setDataFim(java.util.Date newDataFim)
	{
		dataFim = newDataFim;
	}
	
	public java.util.Date getDataFim()
	{
		return dataFim;
	}
	
	public void setTipo(String newTipo)
	{
		tipo = newTipo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public void setCliID(int newCliID)
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
	}
	
	public void setStatus(boolean newStatus)
	{
		status = newStatus;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public static Reserva setRSToObject(ResultSet reg)
	{
		Reserva res = new Reserva();
		try{		
		
		  	Object o = null;
		  	
		  	o = reg.getObject(1);
            Integer it = new Integer(o.toString());
		    res.setHotelID(it.intValue());		 		    		    
		    
		    o = reg.getObject(2);
            it = new Integer(o.toString());
		    res.setCliID(it.intValue());		 		   			   	
		   	
		    o = reg.getObject(3);
	    	res.setCodReserva(o.toString());	    	
	    	
	    	o = reg.getObject(4);  //(Data: ano-mes-dia)	    			    	    	
	    	res.setDataIni(obterData(o.toString()));	    		    	
	    	
	    	o = reg.getObject(5);  //(Data: ano-mes-dia)	    			    	    	
	    	res.setDataFim(obterData(o.toString()));	    		    	
	    	
	    	java.util.Date  dt = obterData(o.toString());
	    	
	        o = reg.getObject(6);
	    	res.setTipo(o.toString());            
	    		    	
	    	o = reg.getObject(7);
	    	Boolean bl = new Boolean(o.toString());	    
	    	res.setStatus(bl.booleanValue());
	          	      	  
    	}
    	catch(SQLException sqle)
    	{
    		System.out.println("ERRO SETDBOBJECT RESERVA");
    		return null;
    	}
    	return res;
	}
	
	public static Date obterData(String stDataAnoMesDia)
	{
		char[] data = stDataAnoMesDia.toCharArray();
		
		String ano = "";
		
		ano = ano + data[0];
		ano = ano + data[1];
		ano = ano + data[2];
		ano = ano + data[3];
		             
		String mes = "";
		mes = mes + data[5];
		mes = mes + data[6];			    
	    
		String dia=  "";
		
		dia = dia + data[8];
		dia = dia + data[9];
		
		Integer ita = new Integer(ano);
		Integer itm = new Integer(mes);
		Integer itd = new Integer(dia);
		
		return new java.util.Date(ita.intValue()-1900, itm.intValue(), itd.intValue());		
	}
	
	public void atualiza()
	{
		
		TableManager TM = new TableManager();
		
		Vector colunas = new Vector();
		Vector valoresColunas = new Vector();
		Vector clausulas = new Vector();
		Vector parametros = new Vector();
		
		colunas.add("dataIni");
		colunas.add("dataFim");
		colunas.add("tipo");
		colunas.add("status");
				
		
		String dI = "";
		dI = String.valueOf(dataIni.getYear()+1900) + "-" + String.valueOf(dataIni.getMonth()+1) + 
		     "-" + String.valueOf(dataIni.getDate());
	    String dF = "";
		dF = String.valueOf(dataFim.getYear()+1900) + "-" + String.valueOf(dataFim.getMonth()+1) + 
		     "-" + String.valueOf(dataFim.getDate());
		
		valoresColunas.add(dI);
		valoresColunas.add(dF);
		valoresColunas.add(tipo);
		valoresColunas.add(String.valueOf(status));
				
		clausulas.add("hotelID");
		clausulas.add("codReserva");		
		
		parametros.add(String.valueOf(hotelID));
		parametros.add(codReserva);				
				
	    System.out.println("Atualização Reserva:"+ TM.atualizaRegistro("Reserva",colunas,valoresColunas,clausulas,parametros));
	}
	
	public static void removeReserva(String codReserva)
	{
		
	}
	
}
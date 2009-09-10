package GesHotel;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;

class Hotel
{
//	IGesHotel.DetalhesHotel dh = new IGesHotel.DetalhesHotel();		
	private int hotelID;
	private String nomeHotel;	
	
	private Vector acomodacoes;
	private Vector tiposAcomodacao;	
	private Vector reservas = new Vector();
	private Vector estadas = new Vector();

	public Hotel()
	{
		hotelID=0;
		nomeHotel="";
		
		acomodacoes = new Vector();			
		tiposAcomodacao = new Vector();
		reservas = new Vector();
	}

	public Hotel(String newNomeHotel)
	{						
	    TableManager TM = new TableManager();
		hotelID = TM.obterUltimoCodigo("Hotel","hotelID")+1;
		nomeHotel = newNomeHotel;			
		acomodacoes = new Vector();			
		tiposAcomodacao = new Vector();
		reservas = new Vector();					
	}
	
	public void salva()
	{
		TableManager TM = new TableManager();
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("hotelID");
		colunas.add("nomeHotel");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(hotelID));
		valoresColunas.add(nomeHotel);
		
		boolean ok = TM.addRegistro("Hotel", colunas, valoresColunas);	
	}
	
	public void setNomeHotel(String newNomeHotel)
	{
		nomeHotel = newNomeHotel;
	}
	
	public String getNomeHotel()
	{
		return nomeHotel;
	}
	
	public int getHotelID()
	{
		return hotelID;
	}
	
	public void setHotelID(int newHotelID)
	{
		hotelID = newHotelID;
	}
	
	public TipoAcomodacao getTipoAcomodacao(String tipo)
	{	
		TipoAcomodacao ta = null;
		boolean achou = false;
		int i=0;	
		while ((i<tiposAcomodacao.size()) && (!achou))
		{
			ta = (TipoAcomodacao) tiposAcomodacao.get(i);
			//System.out.println("ta: " + ta.getTipo());
			if (tipo.equals(ta.getTipo()))
			   achou=true;
			i++;
		}
		return ta;
	}
	
	public void addAcomodacao(int numero, String tipo)
	{				
		Acomodacao ac = new Acomodacao(this.getHotelID(),numero, tipo);
		ac.salva();
		acomodacoes.add(ac);		
	}
	
	public void addTipoAcomodacao(String tipo, int qtde, float preco)
	{
		TipoAcomodacao ta = new TipoAcomodacao(this.getHotelID(),tipo,qtde,preco);
		ta.salva();
		tiposAcomodacao.add(ta);
	}
	

	public Vector getTiposAcomodacao()
	{
		TipoAcomodacao ta;
		Vector tipos = new Vector();
		
		for (int i=0; i<tiposAcomodacao.size(); i++)
		{
			ta = (TipoAcomodacao) tiposAcomodacao.get(i);
			tipos.add(ta.getTipo());
		}
		return tipos;
	}
	
	
	public Vector getAcomodacoes()
	{
		return acomodacoes;
	}
				
	public float obterPrecoTipoAcomodacao(String tipo)
	{
		TipoAcomodacao ta = getTipoAcomodacao(tipo);		
		if (ta!=null)
		   return ta.getPreco();
		   else return 0;
	}
	
	public int obterQtdePessoasTipoAcomodacao(String tipo)
	{
		TipoAcomodacao ta = getTipoAcomodacao(tipo);		
		if (ta!=null)
		   return ta.getQtdePessoas();
		   else return 0;	   	 		   
	}
	
	public int getQtdeAcomodacoesTipo(String tipoAcomodacao)
	{
		Acomodacao ac;
		int cont=0;
		for (int i=0; i<acomodacoes.size(); i++)
		{
			ac = (Acomodacao) acomodacoes.get(i);
			if (ac.getDescricaoTipo().equals(tipoAcomodacao))
			   cont++;
		}
		return cont;
	}
	
	public Vector getReservas()
	{
		return reservas;
	}
	
	public void addReserva(Reserva rsv)
	{
		reservas.add(rsv);
	}
	
	public void addEstada(Estada est)
	{
		estadas.add(est);
	}
	
	public boolean removeReserva(String codReserva)
	{		
	    TableManager TM = new TableManager();
	    
	    Vector clause = new Vector();
	    clause.add("hotelID");
	    clause.add("codReserva");
	    
	    Vector parameter = new Vector();
	    parameter.add(String.valueOf(hotelID));
	    parameter.add(codReserva);
	    				
		return TM.delete("Reserva",clause,parameter);
	}
	
	public Reserva obterReserva(String codReserva)
	{
		Reserva res = null;		
		boolean achou = false;
		int i=0;		
		while ((!achou) && (i<reservas.size()))		
		{						
			res = (Reserva) reservas.get(i);			
			if (res.getCodReserva().equals(codReserva))			
				achou = true;			
			i++;
		}				
		return res;
	}
	
	public int alocarAcomodacao(String codReserva)
	{		
		Acomodacao ac = new Acomodacao();
		Reserva res = obterReserva(codReserva);
		res.setStatus(true);
		res.atualiza();			
		int i=0;		
		boolean achou = false;
		while ((!achou) && (i<acomodacoes.size()))
		{						
			ac = (Acomodacao) acomodacoes.get(i);			
			if ((ac.getTipo().equals(res.getTipo())) && (ac.getStatus()==false))
				achou = true;
			i++;
		}
		
		Estada est = new Estada(this.getHotelID(), codReserva, ac.getNumero(),res.getCliID());
		ac.setStatus(true);
		res.setStatus(true);
		est.salva();
		ac.atualiza();
		addEstada(est);
		
		return ac.getNumero();
	}
	
	public void addAcomodacao(Acomodacao ac)
	{
		acomodacoes.add(ac);
	}
	
	public void addTipoAcomodacao(TipoAcomodacao tipoAc)
	{
		tiposAcomodacao.add(tipoAc);
	}		
	
	
	public static Hotel obterHotel(String nomeHotel)
	{
		TableManager TM = new TableManager();				
		
		try{			
		//recupera dados do hotel
		ResultSet reg = TM.buscaPor("Hotel","nomeHotel",nomeHotel);				
		Hotel h = Hotel.setRSToObject(reg);		
		
		boolean OK = true;
		//recupera Tipos Acomodacao do hotel
	    reg = TM.buscaPor("TipoAcomodacao","hotelID",String.valueOf(h.getHotelID()));
	    OK = reg.next();	    	    	    
	    while (OK)
	    {
	    	TipoAcomodacao ta = TipoAcomodacao.setRSToObject(reg);
	    	if (ta!=null)
			   h.addTipoAcomodacao(ta);	    	
			OK = reg.next();
	    }	    
	    		
		//recupera Acomodacoes do hotel
		reg = TM.buscaPor("Acomodacao","hotelID",String.valueOf(h.getHotelID()));	    
	    OK = reg.next();	    	    	    
	    while (OK)
	    {	    
	    	Acomodacao ac = Acomodacao.setRSToObject(reg);	    		    	
	    	if (ac!=null)
	    	    h.addAcomodacao(ac);
	    	OK = reg.next();	    		    
	    }
	    	    
	    //recupera Reservas do hotel
		reg = TM.buscaPor("Reserva","hotelID",String.valueOf(h.getHotelID()));	    	    
	    OK = reg.next();	    	    	    
	    
	    while (OK)
	    {	    
	    	Reserva res =  Reserva.setRSToObject(reg);	    	
	    	if (res!=null)
	    	   h.addReserva(res);	  
	    	OK = reg.next();  		    	
	    }		
	    
	    
		
		//recupera Estadas do hotel
		reg = TM.buscaPor("Estada","hotelID",String.valueOf(h.getHotelID()));	    
		OK = reg.next();
		
	    while (OK)		
	    {	    
	    	Estada est = Estada.setRSToObject(reg);	    		    	
	    	if (est!=null)
	    	   h.addEstada(est);	    		    	
	    	OK = reg.next();
	    }	    
	    
	    return h;		
	    
	    }
	    catch (SQLException sqle)
	    {
	    	return null;
	    }
	}	
	
	
	
	public static Hotel obterHotel(int hotelID)
	{
		TableManager TM = new TableManager();				
		
		try{				
		//recupera dados do hotel
		ResultSet reg = TM.buscaPor("Hotel","hotelID", String.valueOf(hotelID));		
		Hotel h = Hotel.setRSToObject(reg);		
		
		boolean OK = true;
		//recupera Tipos Acomodacao do hotel
	    reg = TM.buscaPor("TipoAcomodacao","hotelID",String.valueOf(h.getHotelID()));
	    OK = reg.next();	    	    	    
	    while (OK)
	    {
	    	TipoAcomodacao ta = TipoAcomodacao.setRSToObject(reg);
	    	if (ta!=null)
			   h.addTipoAcomodacao(ta);	    	
			OK = reg.next();
	    }	    
	    		
		//recupera Acomodacoes do hotel
		reg = TM.buscaPor("Acomodacao","hotelID",String.valueOf(h.getHotelID()));	    
	    OK = reg.next();	    	    	    
	    while (OK)
	    {	    
	    	Acomodacao ac = Acomodacao.setRSToObject(reg);	    		    	
	    	if (ac!=null)
	    	    h.addAcomodacao(ac);
	    	OK = reg.next();	    		    
	    }
	    	    
	    //recupera Reservas do hotel
		reg = TM.buscaPor("Reserva","hotelID",String.valueOf(h.getHotelID()));	    	    
	    OK = reg.next();	    	    	    
	    while (OK)
	    {	    
	    	Reserva res =  Reserva.setRSToObject(reg);	    	
	    	if (res!=null)
	    	   h.addReserva(res);	  
	    	OK = reg.next();  		    	
	    }		
		
		//recupera Estadas do hotel
		reg = TM.buscaPor("Estada","hotelID",String.valueOf(h.getHotelID()));	    
		OK = reg.next();
	    while (reg.next());		
	    {	    
	    	Estada est = Estada.setRSToObject(reg);	    		    	
	    	if (est!=null)
	    	   h.addEstada(est);	    		    	
	    	OK = reg.next();
	    }	    
	    
	    return h;		
	    
	    }
	    catch (SQLException sqle)
	    {
	    	return null;
	    }
	}	
			
	private static Hotel setRSToObject(ResultSet reg)
	{
		Hotel ht = new Hotel();
		
		//Object o = null;
		try{				
				
		 Object            o = null;				
         reg.next();
         o = reg.getObject("hotelID");
         Integer it = new Integer(o.toString());
		 ht.setHotelID(it.intValue());		 
		 o = reg.getObject("nomeHotel");					 
		 ht.setNomeHotel(o.toString());                  
          
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT HOTEL");
        	return null;
        }
        
        return ht;		
	}
	
	public static Vector getHoteis()
	{
		Vector vt = new Vector();
		
		try{		
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Hotel");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("nomeHotel"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETHOTEIS");			
		}
		return vt;
	}	
		
}
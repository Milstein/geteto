package GesHotel;

import java.util.Vector;
import java.util.Date;


import Tipos.*;
import Persistencia.*;

public class GesHotel implements IGesHotel
{	
		
	//private Vector hoteis = new Vector();
	Hotel hotelAtual;
	
	public GesHotel()
	{							
		hotelAtual = new Hotel();
	}	
	
	public void selecionaHotel(String nomeHotel)
	{													
		hotelAtual = Hotel.obterHotel(nomeHotel);			
	}
	
	public void selecionaHotel(int hotelID)
	{
		hotelAtual = Hotel.obterHotel(hotelID);					
	}
	
	
	public void addHotel(String nomeHotel)
	{
		Hotel h = new Hotel (nomeHotel);
		h.salva();		
	}
	
	public void addAcomodacao(int numero, String tipo)
	{
		hotelAtual.addAcomodacao(numero, tipo);
	}
	
	public void addTipoAcomodacao(String tipo, int qtde, float preco)
	{
		hotelAtual.addTipoAcomodacao(tipo,qtde,preco);
	}
	
	public Vector obterHoteis()
	{				
		return Hotel.getHoteis();		
	}		
	
	//public DetalhesHotel[] obterDetalheHotel(String nomeHotel)		
	public DetalhesHotel obterDetalheHotel(String nomeHotel) //vetor do tipo DetalhesHotel
	{
		selecionaHotel(nomeHotel);
		DetalhesHotel dh = new DetalhesHotel();
		dh.setHotelID(hotelAtual.getHotelID());
		dh.setNomeHotel(hotelAtual.getNomeHotel());
		dh.setTiposAcomodacao(hotelAtual.getTiposAcomodacao());
		//System.out.println("Qtde tipos acomodacao: " + dh.getTiposAcomodacao().size());
		return dh;				
	}
	
	
	public InfoTipoAcomodacao obterInfoTipoAcomodacao(String tipoAcomodacao)
	{		
		return (new InfoTipoAcomodacao(hotelAtual.obterQtdePessoasTipoAcomodacao(tipoAcomodacao),
			                      hotelAtual.obterPrecoTipoAcomodacao(tipoAcomodacao)));		                                
	}
	
	public boolean verificarDisponibilidadePeriodo(DetalhesReserva res)
	{
		//para cada dia verifica se tem quarto disponivel +1
        //um dia em milissegundos: 86400000
       // System.out.println("ANTES:  hotelAtual = getHotelReserva(res.getCodReserva());" );
        if (!res.getCodReserva().equals(""))
	        hotelAtual = getHotelReserva(res.getCodReserva());
	        else selecionaHotel(res.getHotelID());
       // System.out.println("DEPOIS:  hotelAtual = getHotelReserva(res.getCodReserva());" );
        //selecionaHotel(res.getHotelID());
        
        boolean disp = true;
        long diaIni, diaFim;
        int A, B, TA, TB;             
        
        A = hotelAtual.getQtdeAcomodacoesTipo(res.getTipoAcomodacao());
        //System.out.println("A: " + A);
        TA = hotelAtual.getAcomodacoes().size();
        //System.out.println("TA: " + TA);
        TB = hotelAtual.getReservas().size();                
        //System.out.println("TB: " + TB);
        
        diaIni = res.getDataIni().getTime();
        diaFim = res.getDataFim().getTime();
        
        while ((disp) && (diaIni<=diaFim))
        {
        	B = getQtdeReservasTipoDia(res.getTipoAcomodacao(),new Date(diaIni));        	
        	//System.out.println("\nB: " + B);
        	if ((A-B>0) && (TA-TB>1))
        	   diaIni = diaIni+86400000;        	           	
        	   else disp=false;
        }
        		
		return disp;
	}
	
	private int getQtdeReservasTipoDia(String tipoAcomodacao, Date data)
	{
		int cont =0;
		Vector reservas = hotelAtual.getReservas();
		Reserva res;
		
		for (int i=0; i<reservas.size(); i++)
		{
			//se o tipo de acomodacao for o mesmo
			//se o dia estiver entre o dia inicial da reserva e o dia final
			res = (Reserva) reservas.get(i);
			if ( (res.getTipo().equals(tipoAcomodacao)) &&
			    ( (data.compareTo(res.getDataIni())>=0) && 
			        (data.compareTo(res.getDataFim())<= 0)) )
				cont ++;							
		}				
		
		return cont;
	}
	
	
	public float obterPrecoReserva(DetalhesReserva res)
	{		
	
	  //  System.out.println("obter PRECO");
	    if (!res.getCodReserva().equals(""))
	        hotelAtual = getHotelReserva(res.getCodReserva());
	        else selecionaHotel(res.getHotelID());
	  //  System.out.println("HotelATual: " + hotelAtual);
	 //   System.out.println("obter PRECO1");
	//	selecionaHotel(res.getHotelID());
		InfoTipoAcomodacao itpa = obterInfoTipoAcomodacao(res.getTipoAcomodacao());
		float preco = itpa.getPreco();
	  //  System.out.println("obter PRECO2");
		long ini, fim, dias;
		ini = res.getDataIni().getTime();		
		fim = res.getDataFim().getTime();				
		dias = (long)  (fim-ini)/86400000;
		int qtde_dias = (int) dias;							
		
		return (qtde_dias*preco);	
	}
	
	
	public String fazerReserva(DetalhesReserva res, DadosCliente cliDados)
	{
		selecionaHotel(res.getHotelID());
		Reserva rsv = new Reserva(res.getHotelID() ,res.getDataIni(), res.getDataFim(), res.getTipoAcomodacao(), cliDados.getCliID());
		rsv.salva();
		hotelAtual.addReserva(rsv);
		//reservas.add(rsv);
		return rsv.getCodReserva();
		//System.out.println("\n\n codReserva: " + res.getCodReserva());
	}	
	
	public int iniciarEstada(String codReserva)
	{
		hotelAtual = getHotelReserva(codReserva);
		return hotelAtual.alocarAcomodacao(codReserva);		
	}
	
	public boolean cancelarReserva(String codReserva)	
	{
		hotelAtual = getHotelReserva(codReserva);
		//hotelAtual.removeReserva(codReserva);
		return hotelAtual.removeReserva(codReserva);		
	}	
	
	public Hotel getHotelReserva(String codReserva)
	{			
		Vector nomeHoteis = Hotel.getHoteis();				
		
	    Vector reservas;
		
		boolean achou = false;
		int hotelID = 0;
		int i=0,j=0;
		Hotel ht = new Hotel();
		Reserva res = new Reserva();
		
		while ((i<nomeHoteis.size()) && (!achou))  
		{
		//	System.out.println("Dentro do while - " + i+1 + ": " + nomeHoteis.get(i));
			ht = Hotel.obterHotel((String)nomeHoteis.get(i));
			j = 0;			
			reservas = ht.getReservas();
		//	System.out.println("Qtde Reservas: " + reservas.size());
			while ((j<reservas.size()) && (!achou))			 
			{
				res = (Reserva) reservas.get(j);
			//	System.out.println("CodReserva " + j+1 + ": " + res.getCodReserva());
				if (res.getCodReserva().equals(codReserva))	
				{				
					achou = true;				
				//	System.out.println("achou");
				}
				j++;
			}
			i++;
		}
		
		if (achou)
		    {
		    	//System.out.println("ACHOU HOTEL DA RESERVA");
		    	return ht;
		    	
		    }			
			else 
			{
				System.out.println("NÂAAAAOOOOOO ACHOU HOTEL DA RESERVA");
				return null;		
			}
	}
	
	/*
	public Reserva obterReserva2(String codReserva)
	{
		Vector reservas = hotelAtual.getReservas();
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
	}*/
	
	public boolean alterarReserva(String codReserva, DetalhesReserva novaRes)
	{
		hotelAtual = getHotelReserva(codReserva);
	    Reserva res = hotelAtual.obterReserva(codReserva);	   
	    res.setTipo(novaRes.getTipoAcomodacao());
	    res.setDataIni(novaRes.getDataIni());
	    res.setDataFim(novaRes.getDataFim());	    
	    res.atualiza();
	    return true;	    
	}
	
	public String[] obterReservasVencidas(int hotelID)
	{
		return null;
	}
	
	public void cancelarReservas(String[] reservas)
	{
	}

	public String obterNomeHotel(int hotelID)
	{
		selecionaHotel(hotelID);
		return hotelAtual.getNomeHotel();
	}
	
	public DetalhesReserva obterReserva(String codReserva)
	{							
		hotelAtual = getHotelReserva(codReserva);			
		Reserva res = hotelAtual.obterReserva(codReserva);		
		DetalhesReserva detRes = new DetalhesReserva();
		
		if (res!=null)
		{
			detRes.setHotelID(res.getHotelID());
			detRes.setCliID(res.getCliID());
			detRes.setDataIni(res.getDataIni());
			detRes.setDataFim(res.getDataFim());
			detRes.setTipoAcomodacao(res.getTipo());
			detRes.setStatus(res.getStatus());
			detRes.setCodReserva(res.getCodReserva());
		}			
		return detRes;	
		
	}

}
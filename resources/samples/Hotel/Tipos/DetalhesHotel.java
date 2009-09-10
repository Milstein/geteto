package Tipos;

import java.util.Vector;

public class DetalhesHotel
{
	int hotelID;
	String nomeHotel;
	Vector tiposAcomodacao; //Vetor de strings
	
	public DetalhesHotel()
	{
		tiposAcomodacao = new Vector();
	}
							
	public void setHotelID(int newHotelID)
	{
		hotelID = newHotelID;
	}
	
	public void setNomeHotel(String newNomeHotel)
	{
		nomeHotel = newNomeHotel;
	}
	
	public void addTiposAcomodacao(String nomeTipo)
	{
		tiposAcomodacao.add(nomeTipo);
	}
	
	public void setTiposAcomodacao(Vector newTiposAcomodacao)
	{
		tiposAcomodacao = newTiposAcomodacao;
	}
	
	public int getHotelID()
	{
		return hotelID;
	}
	
	public String getNomeHotel()
	{
		return nomeHotel;
	}
	
	public Vector getTiposAcomodacao()
	{
		return tiposAcomodacao;
	}
}
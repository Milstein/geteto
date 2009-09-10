package Tipos;	
	
public class DetalhesReserva
{
	String codReserva;
	int hotelID;
	int cliID;
	java.util.Date dataIni;
	java.util.Date dataFim;
	String tipoAcomodacao;
	boolean status;
	
	public String toString()
	{
		String s = "";
		s = s + "###############################################";			
		s = s + "\n";
		s = s + "HOTEL ID: ";
		s = s + String.valueOf(hotelID);
		s = s + " \n ";
		s = s + "Datas: ";
		s = s + dataIni.toGMTString();
		s = s + " ateh ";
		s = s + dataFim.toGMTString();
		s = s + "\n";
		s = s + "TipoAcomod: ";
		s = s +  tipoAcomodacao;
		s = s + "\n";
		s = s + "##############################################";			
		return s;
	}
	
	public DetalhesReserva()
	{
		status = false;
		codReserva = "";
	}
	
	public void setCliID(int newCliID)
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
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
	
	public void setTipoAcomodacao(String newTipoAcomodacao)
	{
		tipoAcomodacao = newTipoAcomodacao;
	}
	
	public String getTipoAcomodacao()
	{
		return tipoAcomodacao;
	}
	
		public void setStatus(boolean newStatus)
	{
		status = newStatus;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setCodReserva (String newCodReserva)
	{
		codReserva = newCodReserva;
	}
	
	public String getCodReserva()
	{
		return codReserva;
	}
}


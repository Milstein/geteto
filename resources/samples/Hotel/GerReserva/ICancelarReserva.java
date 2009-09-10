package GerReserva;

import Tipos.*;

public interface ICancelarReserva
{
	public DetalhesReserva obterReserva(String codReserva); 
	public String obterNomeHotel(int hotelID);
	public String obterNomeCliente(int cliID);
	public boolean cancelarReserva(String codReserva);	
}

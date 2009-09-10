package GerReserva;

import Tipos.*;

public interface IAlterarReserva
{
	public DetalhesReserva obterReserva(String codReserva);
	public DetalhesHotel obterDetalheHotel(int hotelID);
	public InfoTipoAcomodacao obterInfoTipoAcomodacao(String tipoAcomodacao);
	public float obterPrecoReserva(DetalhesReserva novaRes);
	public boolean verificarDisponibilidadePeriodo(DetalhesReserva res); // true - disponivel	
	public boolean alterarReserva(String codReserva, DetalhesReserva novaRes);	
	
	public float obterTXDescontoReserva(DetalhesReserva res);
}
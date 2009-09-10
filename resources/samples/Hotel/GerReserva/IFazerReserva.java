package GerReserva;


import java.util.Vector;
import Tipos.*;

public interface IFazerReserva
{
	public Vector obterHoteis(); //vetor de strings
	public void selecionaHotel(String nomeHotel);
	public DetalhesHotel obterDetalheHotel(String nomeHotel);
	public InfoTipoAcomodacao obterInfoTipoAcomodacao(String tipoAcomodacao);
	public boolean verificarDisponibilidadePeriodo(DetalhesReserva res); // true - disponivel
	public float obterPrecoReserva(DetalhesReserva res);

    public float obterTXDescontoReserva(DetalhesReserva res);

	public String fazerReserva(DetalhesReserva res, DadosCliente cli); //retorna codigo da reserva	
	
	public DadosCliente obterCliente(String nomeCliente);
}
package GesHotel;

import java.util.Vector;

import Tipos.*;

public interface IGesHotel
{

	public Vector obterHoteis();
	//public DetalhesHotel[] obterDetalheHotel(String nomeHotel);
	public DetalhesHotel obterDetalheHotel(String nomeHotel);
	
	
	public InfoTipoAcomodacao obterInfoTipoAcomodacao(String tipoAcomodacao);
	//public float obterPrecoTipoAcomodacao(String tipoAcomodacao);
	//public int obterQtdePessoasTipoAcomodacao(String tipoAcomodacao);
	
	public boolean verificarDisponibilidadePeriodo(DetalhesReserva res);
	public float obterPrecoReserva(DetalhesReserva res);
	public String fazerReserva(DetalhesReserva res, DadosCliente cliDados);
	public DetalhesReserva obterReserva(String codReserva);
	public int iniciarEstada(String codReserva);
	public boolean cancelarReserva(String codReserva);
	public boolean alterarReserva(String codReserva, DetalhesReserva novaRes);
	public String[] obterReservasVencidas(int hotelID);
	
	//outros
	public void selecionaHotel(String nomeHotelAtual);		
	public void selecionaHotel(int hotelID);	
	
	public void addHotel(String nomeHotel);
	public void addAcomodacao(int numero, String tipo);
	public void addTipoAcomodacao(String tipo, int qtde, float preco);	
	
	public String obterNomeHotel(int hotelID);

}		
	
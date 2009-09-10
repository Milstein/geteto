package GerReserva;

import Tipos.*;

public interface IOcuparReserva
{
	public DetalhesReserva obterReserva(String codReserva);
	public DadosCompletoCliente obterClienteReserva(String codReserva);
	public void completarDadosCliente(DadosCompletoCliente cli);
	public int iniciarEstada(String codReserva, DadosCompletoCliente cli);
	
	public float obterPrecoReserva(DetalhesReserva res);
	public String obterNomeHotel(int hotelID);
	
	public float obterTXDescontoReserva(DetalhesReserva res);
}
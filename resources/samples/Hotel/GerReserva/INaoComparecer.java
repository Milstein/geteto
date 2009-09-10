package GerReserva;

import java.util.Vector;
import Tipos.*;

public interface INaoComparecer
{
	public Vector obterReservasVencidas(int hotelID);
	public void cancelarReservasVencidas(Vector reservasVencidas);
	public void gerarFaturasReservas(Vector reservas);
	
	public float obterTXDescontoReserva(DetalhesReserva res);
	
}
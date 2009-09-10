package GerReserva;

import java.util.Vector;
import Tipos.*;
import GesHotel.*;
import GesCliente.*;

public class GerReserva implements IFazerReserva, IOcuparReserva, ICancelarReserva,
 									IAlterarReserva, INaoComparecer
{
	private IGesHotel IGH;
	private IGesCliente IGC;
	
	public GerReserva()
	{
		IGH = new GesHotel();
		IGC = new GesCliente();		
	}			
	
	public Vector obterHoteis() //IFazerReserva
	{		
		return IGH.obterHoteis();
	}
	
	public void selecionaHotel(String nomeHotel) //IFazerReserva
	{
		IGH.selecionaHotel(nomeHotel);
	}
	
	public DetalhesHotel obterDetalheHotel(String nomeHotel) //IFazerReserva
	{
		return IGH.obterDetalheHotel(nomeHotel);
	}
	
	public DetalhesHotel obterDetalheHotel(int hotelID) //IAlterarReserva
	{		
		return IGH.obterDetalheHotel(obterNomeHotel(hotelID));
	}
	
	public InfoTipoAcomodacao obterInfoTipoAcomodacao(String tipoAcomodacao) //IFazerReserva
	{
		return IGH.obterInfoTipoAcomodacao(tipoAcomodacao);
	}
	
	public boolean verificarDisponibilidadePeriodo(DetalhesReserva res) //IFazerReserva, IAlterarReserva
	{
		return IGH.verificarDisponibilidadePeriodo(res);
	}
		
	public float obterPrecoReserva(DetalhesReserva res) //IFazerReserva, IAlterarReserva
	{
		return IGH.obterPrecoReserva(res);
	}
	
	public String fazerReserva(DetalhesReserva res, DadosCliente cli) //IFazerReserva
	{
		IGH.selecionaHotel(res.getHotelID());
		
		String cod = "";
		
		int cliID = IGC.obterCliente(cli);
		if (cliID==0)
			{
				cliID = IGC.criarCliente(cli);
			}
		cli.setCliID(cliID);
		res.setCliID(cliID);
		
		boolean disp = IGH.verificarDisponibilidadePeriodo(res);			
		
		
		if (disp==true)		
			cod = IGH.fazerReserva(res,cli);
		
		String st = res.toString();
		st = "\n" + st;
		st = cod + st;		
		st = "Cod Reserva : " + st;
				
		if (!cod.equals(""))
			IGC.notificarCliente(cliID, st);
		return cod;		
	}
	
	public DadosCompletoCliente obterClienteReserva(String codReserva) //ICancelarReserva, IOcuparReserva
	{
		DetalhesReserva res = obterReserva(codReserva);
		return IGC.obterDadosCompletoCliente(res.getCliID());
	}

	public void completarDadosCliente(DadosCompletoCliente cli) //IOcuparReserva
	{
		IGC.completarDadosCliente(cli);
	}

	public DetalhesReserva obterReserva(String codReserva) //ICancelarReserva, IOcuparReserva, IAlterarReserva
	{
		return IGH.obterReserva(codReserva);
	}
	
	public int iniciarEstada(String codReserva, DadosCompletoCliente cli) //IOcuparReserva
	{
		DetalhesReserva res = IGH.obterReserva(codReserva);
		if (res.getStatus()==false)
		{		
			float valor = IGH.obterPrecoReserva(res);
			int NUM = IGH.iniciarEstada(codReserva);
		
			//IFAT.abrirContaEstada(res,cli,valor);
			return NUM;
		}
		return 0;
	}
	
	public boolean cancelarReserva(String codReserva) //ICancelarReserva
	{
		DetalhesReserva res = IGH.obterReserva(codReserva);
		if (res.getStatus()==false)
		{		
			return IGH.cancelarReserva(codReserva);
		}
		else return false;
	}
	
	public String obterNomeHotel(int hotelID) //ICancelarReserva
	{
		return IGH.obterNomeHotel(hotelID);
	}
	
	public String obterNomeCliente(int cliID) //ICancelarReserva
	{
		return IGC.obterNomeCliente(cliID);
	}
	
	public boolean alterarReserva(String codReserva, DetalhesReserva novaRes)
	{
		DetalhesReserva res = IGH.obterReserva(codReserva);
		if (res.getStatus()==false)
		{		
			return IGH.alterarReserva(codReserva, novaRes);
		}
		else return false;
	}		
	
	public Vector obterReservasVencidas(int hotelID)
	{
		return null;
	}
	
	public void cancelarReservasVencidas(Vector reservasVencidas)
	{
		
	}
	
	public void gerarFaturasReservas(Vector reservas)
	{
		
	}
	
	public float obterTXDescontoReserva(DetalhesReserva res)
	{
		return 0;
	}
	
	public DadosCliente obterCliente(String nomeCliente)
	{
		return IGC.obterCliente(nomeCliente);
	}
	
}
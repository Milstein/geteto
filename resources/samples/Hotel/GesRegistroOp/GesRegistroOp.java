package GesRegistroOp;

import java.util.Date;
import java.sql.Time;
import java.util.Vector;

public class GesRegistroOp implements IGesRegistroOp
{
	public void registrarOperacaoExecutada(Date data, Time hora, String usuario, String operacao)
	{
		RegistroOperacao ro = new RegistroOperacao(data, hora, usuario, operacao);
		System.out.println("REGISTRO DE OPERAÇÃO ARMAZENADO");
		ro.salva();
	}
	
	public Vector obterRegistrosOperacao()
	{
		return RegistroOperacao.getRegistrosOperacao();
	}
}
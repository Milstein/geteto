package GesRegistroOp;

import java.util.Date;
import java.sql.Time;
import java.util.Vector;

public interface IGesRegistroOp
{
	public void registrarOperacaoExecutada(Date data, Time hora, String usuario, String operacao);
	public Vector obterRegistrosOperacao();
}
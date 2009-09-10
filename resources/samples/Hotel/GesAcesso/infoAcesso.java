package GesAcesso;

import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;

public class infoAcesso
{
	private static String usuarioRegistrado = "";	
		
	public static void atualiza()
	{
		String sql = "update infoAcesso set usuarioRegistrado = '" + usuarioRegistrado + "'";
		TableManager TM = new TableManager();
		TM.executaSQL(sql);		
	}		
	
	public static String obterUsuarioRegistrado()
	{				
		TableManager TM = new TableManager();				
        Object o = new Object();							
		//recupera dados do hotel
		try{		
	  	  ResultSet reg = TM.buscaTodos("infoAcesso");				
	  	  //System.out.println("111");
		  reg.next();
		  //System.out.println("222");
		  o = reg.getObject(1);
		  //System.out.println("333");
		  //System.out.println("no infocesso: " + o.toString());
		  return o.toString();
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO OBTER USUARIO REGISTRADO");
			return o.toString();
		}
		
	//	return o.toString();
	}
		
	public static void setUsuarioRegistrado(String login)
	{
		usuarioRegistrado = login;
		atualiza();
	}
}
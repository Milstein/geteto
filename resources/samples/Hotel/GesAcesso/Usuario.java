
package GesAcesso;

import java.sql.ResultSet;
import java.util.Vector;
import Persistencia.*;
import java.sql.SQLException;

public class Usuario
{
	private String login;
	private String senha;
	private String papel;
	
	public Usuario()
	{
		login = "";
		senha = "";		
		papel = "";
	}
	
	public Usuario(String newLogin, String newSenha, String newPapel)
	{
		login = newLogin;
		senha = newSenha;
		papel = newPapel;
	}
	
	public void salva()
	{
		TableManager TM = new TableManager();
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("login");
		colunas.add("senha");
		colunas.add("papel");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(login);
		valoresColunas.add(senha);
		valoresColunas.add(papel);
		
		boolean ok = TM.addRegistro("Usuario", colunas, valoresColunas);	
	}
	
	public void setLogin(String newLogin)
	{
		login = newLogin;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setSenha(String newSenha)
	{
		senha = newSenha;
	}
	
	public String getSenha()
	{
		return senha;
	}
	
	public void setPapel(String newPapel)
	{
		papel = newPapel;
	}
	
	public String getPapelUsuario()
	{
		return papel;
	}
	
	public static Usuario obterUsuario(String login)
	{				
		TableManager TM = new TableManager();				
		
					
		//recupera dados do hotel
		  ResultSet reg = TM.buscaPor("Usuario","login",login);				
		  Usuario u = Usuario.setRSToObject(reg);	
		  
		  return u;
	
		
	}
	
	
	private static Usuario setRSToObject(ResultSet reg)
	{
		Usuario u = new Usuario();
		
		//Object o = null;
		try{				
				
		 Object            o = null;				
         reg.next();
         o = reg.getObject("login");         
         if (o!=null)
         u.setLogin(o.toString());                  
                  
         o = reg.getObject("senha");         
         if (o!=null)
         u.setSenha(o.toString());                           
         
         o = reg.getObject("papel");         
         if (o!=null)
         u.setPapel(o.toString());                           		 
          
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT USUARIO");
        	return null;
        }
        
        return u;		
	}
	
	public static Vector getUsuarios()
	{
		Vector vt = new Vector();
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Usuario");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("login"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETUSUARIOS");			
		}
		return vt;
	}		
	
	
	public void atualiza()
	{
		TableManager TM = new TableManager();
		
		Vector colunas = new Vector();
		Vector valoresColunas = new Vector();
		Vector clausulas = new Vector();
		Vector parametros = new Vector();
		
		colunas.add("senha");
		valoresColunas.add(senha);
		clausulas.add("login");		
		parametros.add(login);		
		
	    System.out.println("Atualização:"+ TM.atualizaRegistro("Usuario",colunas,valoresColunas,clausulas,parametros));
	}
	
}

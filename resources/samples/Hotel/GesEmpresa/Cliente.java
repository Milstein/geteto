package GesEmpresa;

import Persistencia.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;

class Cliente
{
	private int cliID;
	private String nome;
	private String contato;
	private String email;	
	private int empID;	
		
	public Cliente()
	{			
	//	cliID = TM.obterUltimoCodigo("Cliente","cliID")+1;
		nome = "";
		contato="";
		email = "";
	}
	
	
	
	public void setCliID(int newCliID)		
	{
		cliID = newCliID;
	}
	
	public int getCliID()
	{
		return cliID;
	}
	
	public void setNome(String newNome)
	{
		nome = newNome;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setContato(String newContato)
	{
		contato = newContato;
	}
	
	public String getContato()
	{
		return contato;
	}
	
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmpID(int newEmpID)		
	{
		empID = newEmpID;
	}
	
	public int getEmpID()
	{
		return empID;
	}
	
	public static Cliente setRSToObject(ResultSet reg)
	{
		Cliente c = new Cliente();
		
		//Object o = null;
		try{				
				
		 Object o = null;				
	    // reg.next();
	     
	     o = reg.getObject("cliID");
	     Integer it = new Integer(o.toString());
	     c.setCliID(it.intValue());
	     		 
		 o = reg.getObject("nome");	
		 if (o!=null)				 
		 c.setNome(o.toString());                  
		 		 
		 o = reg.getObject("contato");					 
		 if (o!=null)
		 c.setContato(o.toString());                  
		 
		 o = reg.getObject("email");					 
		 if (o!=null)
		 c.setEmail(o.toString());                  
		 
	     o = reg.getObject("empID");	     
	     if (o!=null)
	       { it = new Integer(o.toString());
	         c.setEmpID(it.intValue()); }
	    }     
	    catch(SQLException sqle)  
	    {
	    	System.out.println("ERRO SETDBOBJECT Cliente da EMPRESA");
	    	return null;
	    }
	    
	    return c;	
	}
	
	public static Cliente obterCliente(int cliID)
	{
		TableManager TM = new TableManager();
	    ResultSet reg = TM.buscaPor("Cliente","cliID", String.valueOf(cliID));
	    try{reg.next();}catch(SQLException sqle){ }
		Cliente c = Cliente.setRSToObject(reg);	
		return c;
	}
	
	public void atualiza()
	{
		TableManager TM = new TableManager();
		
		Vector colunas = new Vector();
		Vector valoresColunas = new Vector();
		Vector clausulas = new Vector();
		Vector parametros = new Vector();
		
		colunas.add("empID");
		valoresColunas.add(String.valueOf(empID));
		clausulas.add("cliID");
		parametros.add(String.valueOf(cliID));
		
		
	    TM.atualizaRegistro("Cliente",colunas,valoresColunas,clausulas,parametros);
	}
	
	public static Vector getClientes()
	{	
		Vector vt = new Vector();
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Cliente");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("nome"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETCLIENTES");			
		}
		return vt;
	}		
			
}
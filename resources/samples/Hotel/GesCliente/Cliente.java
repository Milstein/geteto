package GesCliente;

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
	private String RG;
	private String CPF;
	private String endereco;
	private String cidade;
	private String bairro;
	private String CEP;
	private	String fone;	
	
	
	public Cliente()
	{
		TableManager TM = new TableManager();
		
		cliID = TM.obterUltimoCodigo("Cliente","cliID")+1;
		CPF = "";
		RG = "";
		nome = "";
		email = "";
		endereco = "";
		cidade = "";
		bairro = "";
		CEP = "";
		fone = "";
	}
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("cliID");
		colunas.add("nome");
		colunas.add("contato");
		colunas.add("email");
		colunas.add("RG");
		colunas.add("CPF");
		colunas.add("endereco");
		colunas.add("cidade");
		colunas.add("bairro");
		colunas.add("CEP");
		colunas.add("fone");
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(cliID));
		valoresColunas.add(nome);
		valoresColunas.add(contato);
		valoresColunas.add(email);
		valoresColunas.add(RG);
		valoresColunas.add(CPF);
		valoresColunas.add(endereco);
		valoresColunas.add(cidade);
		valoresColunas.add(bairro);
		valoresColunas.add(CEP);
		valoresColunas.add(fone);
		
		TableManager TM = new TableManager();
		
		boolean ok = TM.addRegistro("Cliente", colunas, valoresColunas);
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
	
	public void setRG(String newRG)
	{
		RG = newRG;
	}
	
	public String getRG()
	{
		return RG;
	}
	
	public void setCPF(String newCPF)
	{
		CPF = newCPF;
	}
	
	public String getCPF()
	{
		return CPF;
	}
	
	public void setEndereco(String newEndereco)
	{
		endereco = newEndereco;
	}
	
	public String getEndereco()
	{
		return endereco;
	}
	
	public void setCidade(String newCidade)
	{
		cidade = newCidade;
	}
	
	public String getCidade()
	{
		return cidade;
	}
			
	public void setBairro(String newBairro)
	{
		bairro = newBairro;
	}
	
	public String getBairro()
	{
		return bairro;
	}
	
	public void setCEP(String newCEP)
	{
		CEP = newCEP;
	}
	
	public String getCEP()
	{
		return CEP;
	}
	
	public void setFone(String newFone)
	{
		fone = newFone;
	}
	
	public String getFone()
	{
		return fone;
	}		
	
	public static Cliente setRSToObject(ResultSet reg)
	{
		Cliente c = new Cliente();
		
		//Object o = null;
		try{				
				
		 Object o = null;				
         reg.next();
         
         o = reg.getObject("cliID");
         Integer it = new Integer(o.toString());
         c.setCliID(it.intValue());
         		 		 
		 o = reg.getObject("nome");					 
		 if (o!=null)
		 c.setNome(o.toString());                  
		 
		 o = reg.getObject("contato");					 
		 if (o!=null)
		 c.setContato(o.toString());                  
		 
		 o = reg.getObject(4);					 
		 if (o!=null)
		 c.setEmail(o.toString());                  
		 
         o = reg.getObject(5);	
         if (o!=null)				 
		 c.setRG(o.toString());                  
		 
		 o = reg.getObject(6);					 
		 if (o!=null)
		    c.setCPF(o.toString());                  
		 
		 o = reg.getObject(7);					 
		 if (o!=null)
		 c.setEndereco(o.toString());                  
		 
		 o = reg.getObject(8);					 
		 if (o!=null)
		 c.setCidade(o.toString());                  
		 
		 o = reg.getObject(9);					 
		 if (o!=null)
		 c.setBairro(o.toString());                  
		 
		 o = reg.getObject(10);					 
		 if (o!=null)
		 c.setCEP(o.toString()); 
		 
		 o = reg.getObject(11);					 
		 if (o!=null)
		 c.setFone(o.toString()); 		
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT Cliente");
        	return null;
        }
        
        return c;	
	}
	
	public static Cliente obterCliente(int cliID)
	{
		TableManager TM = new TableManager();
	    ResultSet reg = TM.buscaPor("Cliente","cliID", String.valueOf(cliID));		
		Cliente c = Cliente.setRSToObject(reg);	
		return c;
	}
	
	public static Cliente obterCliente(String nomeCliente)
	{
		TableManager TM = new TableManager();
	    ResultSet reg = TM.buscaPor("Cliente","nome", nomeCliente);		
		Cliente c = Cliente.setRSToObject(reg);	
		return c;
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
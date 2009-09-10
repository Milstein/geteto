package GesEmpresa;

import Persistencia.*;
import java.util.Vector;	
import java.sql.ResultSet;
import java.sql.SQLException;
import Tipos.*;
	
public class Empresa
{
	private int empID;
	private String nomeEmpresa;
	private String CGC;
	private float txDesconto;
	private Vector clientes;
	
	public Empresa()
	{
		clientes = new Vector();
		empID=0;
		nomeEmpresa="";
		CGC="";
		txDesconto=0;
	}
	
	public void setEmpID(int newEmpID)
	{
		empID = newEmpID;
	}
	
	public int getEmpID()
	{
		return empID;
	}
	
	public void setNomeEmpresa(String newNomeEmpresa)
	{
		nomeEmpresa = newNomeEmpresa;
	}
	
	public String getNomeEmpresa()
	{
		return nomeEmpresa;
	}
	
	public void setCGC(String newCGC)
	{
		CGC = newCGC;
	}
	
	public String getCGC()
	{
		return CGC;
	}
	
	public void setTxDesconto(float newTxDesconto)
	{
		txDesconto = newTxDesconto;
	}
	
	public float getTxDesconto()
	{
		return txDesconto;
	}
	
	public void salva()
	{
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("empID");
		colunas.add("nomeEmpresa");
		colunas.add("CGC");
		colunas.add("txDesconto");		
		
		TableManager TM = new TableManager();		
		empID = TM.obterUltimoCodigo("Empresa","empID")+1;
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(empID));
		valoresColunas.add(nomeEmpresa);
		valoresColunas.add(CGC);
		valoresColunas.add(String.valueOf(txDesconto));		
				
		
		boolean ok = TM.addRegistro("Empresa", colunas, valoresColunas);
	}
	
	public static Empresa setRSToObject(ResultSet reg)
	{
		Empresa emp = new Empresa();
		
		//Object o = null;
		try{				
				
		 Object o = null;				
         reg.next();
                  
         o = reg.getObject(1);
         Integer it = new Integer(o.toString());
         emp.setEmpID(it.intValue());
         
		 o = reg.getObject(2);					 
		 emp.setNomeEmpresa(o.toString());                  
		 
		 o = reg.getObject(3);					 
		 emp.setCGC(o.toString());                  
		 
		 o = reg.getObject(4);					 
		 Float ft = new Float(o.toString());
		 emp.setTxDesconto(ft.floatValue());                  		          
		 
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT EMPRESA");
        	return null;
        }                
        
        return emp;	
	}
	
	public void associaCliente(DadosCliente cli)
	{
		Cliente cl = Cliente.obterCliente(cli.getCliID());
		cl.setCliID(cli.getCliID());
		cl.setEmpID(empID);
		cl.setNome(cli.getNome());
		cl.setContato(cli.getContato());
		cl.setEmail(cli.getEmail());
		cl.atualiza();				
		this.addCliente(cl);
	}
	
	public void addCliente(Cliente cli)
	{
		//cli.setEmpID(empID);
		clientes.add(cli);			
	}		
	
	public static Empresa obterEmpresa(int empID)
	{
		TableManager TM = new TableManager();
	    ResultSet reg = TM.buscaPor("Empresa","empID", String.valueOf(empID));		
		Empresa emp = Empresa.setRSToObject(reg);
		
		boolean OK = true;
		//recupera cLIENTES DA EMPRESA
		try{
		
		    ResultSet regCli = TM.buscaPor("Cliente","empID",String.valueOf(emp.getEmpID()));
		    
		    OK = regCli.next();		    
		    while (OK)
		    {		    	
		    	Cliente cli = Cliente.setRSToObject(regCli);
		    	if (cli!=null)
				   {
				   	    emp.addCliente(cli);	    	
				   	}
		
				OK = regCli.next();
		    }	
	    }
	    catch(SQLException sqle)   
	    {
	    	System.out.println("ERRO OBTER EMPRESA _ BUSCANDO CLIENTES");
	    }
			
		return emp;
	}
	
	public static Empresa obterEmpresa(String nomeEmpresa)
	{
		TableManager TM = new TableManager();
	    ResultSet reg = TM.buscaPor("Empresa","nomeEmpresa", nomeEmpresa);		
		Empresa emp = Empresa.setRSToObject(reg);			
		
		boolean OK = true;
		//recupera cLIENTES DA EMPRESA
		try{
		
		    ResultSet regCli = TM.buscaPor("Cliente","empID",String.valueOf(emp.getEmpID()));
		    
		    OK = regCli.next();		    
		    while (OK)
		    {		    	
		    	Cliente cli = Cliente.setRSToObject(regCli);
		    	if (cli!=null)
				   {
				   	    emp.addCliente(cli);	    	
		
				   	}
		
				OK = regCli.next();
		    }	
	    }
	    catch(SQLException sqle)   
	    {
	    	System.out.println("ERRO OBTER EMPRESA _ BUSCANDO CLIENTES");
	    }
			
		return emp;
	}
	
	public static Vector getEmpresas()
	{	
		Vector vt = new Vector();
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("Empresa");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				vt.add(reg.getString("nomeEmpresa"));
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GETEMPRESA");			
		}
		return vt;
	}
	
	public Vector obterClientes()
	{
		Vector vt = new Vector();
		Cliente c;
		for (int i=0; i<clientes.size(); i++)
		{	
			c = (Cliente) clientes.get(i);
			vt.add(c.getNome());
		}
		return vt;
	}
}
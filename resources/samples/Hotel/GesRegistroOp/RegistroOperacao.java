package GesRegistroOp;

import java.util.Date;
import java.sql.Time;
import java.sql.SQLException;
import Persistencia.*;
import java.util.Vector;
import java.sql.ResultSet;

public class RegistroOperacao
{
	private int codRegistro;
	private Date data;
	private Time hora;
	private String usuario;
	private String operacao;
	
	public RegistroOperacao()
	{
		data = new Date();		
		hora = new Time(data.getTime());
		usuario = "";
		operacao = "";
		codRegistro = 0;
	}
	
	public RegistroOperacao(Date newData, Time newHora, String newUsuario, String newOperacao)
	{
		data = newData;
		hora = newHora;
		usuario = newUsuario;
		operacao = newOperacao;
	}
	
	public void setCodRegistro(int newCodRegistro)
	{
		codRegistro = newCodRegistro;
	}
	
	public int getCodRegistro()
	{
		return codRegistro;
	}
	
	public void setData(Date newData)
	{
		data = newData;
	}
	
	public Date getData()
	{
		return data;
	}
	
	public void setHora(Time newHora)
	{
		hora = newHora;
	}
	
	public Time getHora()
	{
		return hora;
	}
	
	public void setUsuario(String newUsuario)
	{
		usuario = newUsuario;
	}
	
	public String getUsuario()
	{
		return usuario;
	}
	
	public void setOperacao(String newOperacao)
	{
		operacao = newOperacao;
	}
	
	public String getOperacao()
	{
		return operacao;
	}
	
	
	public void salva()
	{
		TableManager TM = new TableManager();
		//Persistencia
		Vector colunas = new Vector();
		colunas.add("codRegistro");
		colunas.add("data");
		colunas.add("hora");
		colunas.add("usuario");
		colunas.add("operacao");
				
		codRegistro = TM.obterUltimoCodigo("RegistroOperacao","codRegistro")+1;
		
		Vector valoresColunas = new Vector();
		valoresColunas.add(String.valueOf(codRegistro));
		//valoresColunas.add(data.toGMTString());		
	    String dI = "";
		dI = String.valueOf(data.getYear()+1900) + "-" + String.valueOf(data.getMonth()+1) + 
		     "-" + String.valueOf(data.getDate());		
		valoresColunas.add(dI);					
		valoresColunas.add(hora.toString());
		valoresColunas.add(usuario);
		valoresColunas.add(operacao);
		System.out.println("Add Registro");
		boolean ok = TM.addRegistro("RegistroOperacao", colunas, valoresColunas);	
	}
	
	
	public static RegistroOperacao obterRegistroOperacao(int codRegistro)
	{				
		TableManager TM = new TableManager();						
					
		//recupera dados do registro de operação
		  ResultSet reg = TM.buscaPor("RegistroOperacao","codRegistro",String.valueOf(codRegistro));				
		  RegistroOperacao ro = RegistroOperacao.setRSToObject(reg);	
		  
		  return ro;
	}
	
	
	private static RegistroOperacao setRSToObject(ResultSet reg)
	{
		RegistroOperacao ro = new RegistroOperacao();
		
		//Object o = null;
		try{				
				
		 Object            o = null;				
         //reg.next();                                            
         
         o = reg.getObject("codRegistro");         
         if (o!=null)
         {
         	Integer it = new Integer(o.toString());
         	ro.setCodRegistro(it.intValue());                  
         }
                           
         o = reg.getObject("data");         
         if (o!=null)
         {
         //	System.out.println("ANTES REcuperando data do banco - Registro Operacao");
         	Date dt = new Date();
         	//Date dt = new Date();
         //	System.out.println("DEPOIS REcuperando data do banco - Registro Operacao");
         	ro.setData(dt);
         }         
         
         o = reg.getObject("hora");
         
         o = reg.getObject("usuario");         
         if (o!=null)
            ro.setUsuario(o.toString());                           		 
            
         o = reg.getObject("operacao");         
         if (o!=null)
            ro.setOperacao(o.toString());                           		 
          
        }
        catch(SQLException sqle)  
        {
        	System.out.println("ERRO SETDBOBJECT REGISTRO OPERACAO");
        	return null;
        }
        
        return ro;		
	}
	
	public static Vector getRegistrosOperacao()
	{
		Vector vt = new Vector();		
		
		try{
			TableManager TM = new TableManager();
			ResultSet reg = TM.buscaTodos("RegistroOperacao");
			
			boolean OK = reg.next();
			
			while (OK)
			{
				String st = "";
				
				RegistroOperacao ro = RegistroOperacao.setRSToObject(reg);
				if (ro!=null)
				{
					st = st + String.valueOf(ro.getCodRegistro()) + " # " ;
					st = st + ro.getData().toGMTString() + " # ";
					st = st + ro.getHora().toString() + " # ";
					st = st + ro.getUsuario() + " # ";
					st = st + ro.getOperacao();
				}				
				
				vt.add(st);
				OK = reg.next();
			}
				
		}
		catch(SQLException sqle)
		{
			System.out.println("ERRO GET_REGISTROS_OPERACAO");			
		}
		return vt;
	}	
}
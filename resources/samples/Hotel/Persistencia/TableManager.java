package Persistencia;

import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


    public class TableManager
    {
        private TableManager tablemanager;

        //métodos que tratam da implementação no BD
        public TableManager ()
        {
          tablemanager = null;
        }
        
        public boolean addRegistro(String nomeTabela, Vector colunas, Vector valoresColunas)
        {
        	String insert = "insert into " + nomeTabela + " (";
        	String col="",val="";
        	
        	for (int i=0; i<colunas.size(); i++)
        	{
        		col = (String) colunas.get(i);
        		insert = insert + col;
        		if (i!=colunas.size()-1)
        		    insert = insert + ", ";
        		   else insert = insert + ") ";
        	}
        	
        	insert = insert + " values (";
        	
        	for (int j=0; j<valoresColunas.size(); j++)
        	{
        		val = (String) valoresColunas.get(j);
        		insert = insert + "'" + val + "'";
        		if (j!=valoresColunas.size()-1)
        		   insert = insert + ", ";
        		   else insert = insert + ")";
        	}        	        	
        	
        	try
        	{
        		//System.out.println("sql insert: " + insert);
        		ConnectionManager.stmt = ConnectionManager.con.createStatement();
        		ConnectionManager.stmt.executeUpdate(insert);         	
            	ConnectionManager.con.commit();
                return true;        	
        	}
        	catch (SQLException e)
        	{
        		return false;
        	}
            
        
        }
        
        public ResultSet buscaPor(String nomeTabela, String coluna, String valorColuna)
        {
    		  String buscaSQL = "";
    		  buscaSQL = "SELECT * FROM " + nomeTabela + " WHERE " + coluna + " = " + "'" + valorColuna + "'";
    		 // System.out.println("BUSCAPOR _ SQL: " + buscaSQL);
    		  ResultSet reg;    		  
    		  try
    		  {
    		     reg = ConnectionManager.stmt.executeQuery(buscaSQL);   
    		     return reg;
    		  }
    		  catch(SQLException sqle)
    		  {
    		  	System.out.println("ERRO BUSCAR POR");
    		  	return null;
    		  }    		      		  
        }
        
        
        public ResultSet buscaPorCondicao(String nomeTabela, Vector clausulas, Vector parametros)                                          
        {
    		  String buscaSQL = "";
    		  buscaSQL = "SELECT * FROM " + nomeTabela + " WHERE ";
    		  // + coluna + " = " + "'" + valorColuna + "'";    		      		   		      		      		      		  
    		  int i;
    		  for (i=0; i<clausulas.size(); i++)
	        	{	        		
	        		buscaSQL = buscaSQL + ((String) clausulas.get(i)) + " = '" + 
	        		                      ((String) parametros.get(i)+"'");
	        		if (i!=clausulas.size()-1)
	        		   buscaSQL = buscaSQL + " and ";
	        	}    		  
    		  
    		  //System.out.println("BUSCA POR CONDIÇÂO: " + buscaSQL);
    		  ResultSet reg;    		  
    		  try
    		  {
    		     reg = ConnectionManager.stmt.executeQuery(buscaSQL);   
    		     return reg;
    		  }
    		  catch(SQLException sqle)
    		  {
    		  	System.out.println("ERRO BUSCAR POR CONDIÇÃO");
    		  	return null;
    		  }    		      		  
        }
        
        
        public ResultSet buscaTodos(String nomeTabela)
        {
    		  String buscaSQL = "";
    		  buscaSQL = "select * from " + nomeTabela;        		  
    		  //System.out.println("SQLBUSCA Todos: " + buscaSQL);
    		  
    		  ResultSet reg;
    		  
    		  try{    		   
    		    //System.out.println("aaa");
    		  	reg = ConnectionManager.stmt.executeQuery(buscaSQL);  		  		      		  	
    		  	//System.out.println("Bbbb");
    		  	return reg;
    		  }	
    		  catch(SQLException sqle)
    		  {
    		  	System.out.println("ERRO BUSCAR TODOS");
    		  	return null;
    		  }    		  
    		  
        }
        
        public int obterUltimoCodigo(String nomeTabela, String coluna)
        {        	
        	String SQL = "";
    		SQL = "select max("+coluna+") from " + nomeTabela;        		  
    	//	System.out.println("SQLMAX: " + SQL);
    		ResultSet reg;    		
    		try{    		   
    		  	 reg = ConnectionManager.stmt.executeQuery(SQL);        		  		      		  	
     		  	 //return reg;
    		    }	
    		 catch(SQLException sqle)
    		  {
    		  	System.out.println("ERRO BUSCA MAX");
    		  	return -1;
    		  }    		  
        	
        	try
        	{   
	        	Object o = null;
	        	if (reg.next())
	        	   {	   
	        	      //System.out.println("MAX TEM REGISTRO");     	   	       	   	  
	        	   	  o = reg.getObject(1);
	        	   	  if (o==null)
	        	   	     {
	        	   	    // 	System.out.println("Objeto NULO");
	        	   	     	return 0;
	        	   	     }
	        	   	  Integer it = new Integer(o.toString());
	        	   	  return it.intValue();	        	   	        	   	  
	        	   }
	        	   else 
	        	     { 
	        	     //   System.out.println("MAX NAO TEM REGISTRO");
	        	        return 0;        	
	        	        
	        	      }
	         }	         	  
    	   	  catch(SQLException sqle)
    	   	  {
    	   	  	System.out.println("EERRRO MAX");
    	   	  	return -1;
    	   	  }
        }
        
        public ResultSet processaSQL(String SQL)
        {
        	ResultSet reg;
        	try{    		   
    		  	 reg = ConnectionManager.stmt.executeQuery(SQL);        		  		      		  	
     		  	 return reg;
    		    }	
    		 catch(SQLException sqle)
    		  {
    		  	System.out.println("ERRO BUSCA MAX");
    		  	return null;
    		  }    		  
        }
        
        public boolean atualizaRegistro(String nomeTabela, Vector colunas, Vector valoresColunas, 
                                        Vector clausulas, Vector parametros)
        {
        	
        	try{        	
	        	String updateSQL = "update "+nomeTabela + " set ";
	        	
	        	int i=0;
	        	for (i=0; i<colunas.size();i++)
	        	{
	        		updateSQL = updateSQL + ((String) colunas.get(i)) + " = '" + ((String) valoresColunas.get(i))+"'";
	        		if (i!=colunas.size()-1)
	        		   updateSQL = updateSQL + ", ";        		   
	        	}
	        	
	        	updateSQL = updateSQL + " where ";
	        	
	        	for (i=0; i<clausulas.size(); i++)
	        	{
	        		//System.out.println("TAM Clausulas: " + clausulas.size());
	        	//	System.out.println("i: " + i);
	        		updateSQL = updateSQL + ((String) clausulas.get(i)) + " = '" + ((String) parametros.get(i)+"'");
	        		if (i!=clausulas.size()-1)
	        		   updateSQL = updateSQL + " and ";
	        	}
	        	
	        	//System.out.println("UpdateSQL: " + updateSQL);
	        	
	        	ConnectionManager.stmt.executeUpdate (updateSQL);
	            ConnectionManager.con.commit();
	           // System.out.println("Registro atualizado corretamente!");
	            return true;        
	           }
	         catch(SQLException sqle)
	         {
	         	System.out.println("FALHA NO UPDATE");
	         	return false;
	         }

		}
		
		
		
		public boolean delete (String tableName, Vector clausulas, Vector parametros)
        {
          String tipo;
          String deleteSQL;
          int contador;
          try
          {
          	    deleteSQL = "delete from " + tableName + " where ";                
	        	int i;
	        	for (i=0; i<clausulas.size(); i++)
	        	{
	        		deleteSQL = deleteSQL + ((String) clausulas.get(i)) + " = '" + ((String) parametros.get(i)+"'");
	        		if (i!=clausulas.size()-1)
	        		   deleteSQL = deleteSQL + " and ";
	        	}
           
	        // System.out.println("DeleteSQL: " + deleteSQL);
		        	
		     ConnectionManager.stmt.executeUpdate (deleteSQL);
		     ConnectionManager.con.commit();
		    // System.out.println("Registro apagado corretamente!");
		     return true;        
	       }
	         catch(SQLException sqle)
	         {
	         	System.out.println("FALHA NO DELETE");
	         	return false;
	         }
		}
		
		
		public void executaSQL(String sql)
        {          
           try{           
		     ConnectionManager.stmt.executeUpdate(sql);
		     ConnectionManager.con.commit();		     		     
	         }
	         catch(SQLException sqle)
	         {
	         	System.out.println("FALHA NO executaSQL");	         	
	         }
		}
				
		
		
/*
        public boolean insertDB (String tableName, Vector colNames, Vector colValues, int cols)
        {
           int contador;
           String insertSQL;
           Object tipo;
           Object tipoNull;

           try
           {

              System.out.println ("col names  :" + colNames + "col values: "  + colValues);

              contador = cols - 1;
              insertSQL = "INSERT INTO " + tableName + " (";
              for (int i = 0; i <= contador; i++)
              {
                  insertSQL = insertSQL + (String)colNames.elementAt(i);
                  if (i != contador)
                    insertSQL = insertSQL + ",";
              }
              insertSQL = insertSQL + ") VALUES (";

              for (int i = 0; i <= contador; i++)
              {
                //System.out.println ("passou aqui pela vez : " + i);

                //obtém o tipo de dado do objeto que está no vetor colValues,
                //na posição i
                //tipo = (colValues.elementAt(i)).getClass ().getName ();
                tipo = colValues.elementAt(i); //System.out.println("Tipo: "+ tipo);
                //compara o tipo do objeto lido com String
                if (tipo == null)
                {
                   System.out.println ("achou tipo = null");
                   insertSQL = insertSQL + null;
                }
                else if (tipo.getClass().getName ().equals ("java.lang.String"))
                {
                  insertSQL = insertSQL + "'" + colValues.elementAt(i) + "'";
                }
                else if (tipo.getClass().getName().equals ("java.lang.Integer"))
                {
                  Integer elemento1 = (Integer)colValues.elementAt (i);
                  int elemento2 = elemento1.intValue ();
                  insertSQL = insertSQL + elemento2;
                }
                else
                {
                  insertSQL = insertSQL + colValues.elementAt(i);
                }
                if (i != contador)
                   insertSQL = insertSQL + "," ;
               }
               insertSQL = insertSQL + ")";
               System.out.println("clausulas sql: " + insertSQL);
               ConnectionManager.stmt.executeUpdate (insertSQL);
               ConnectionManager.con.commit();
               System.out.println ("registro inserido corretamente!");
               return true;
           }
           catch (SQLException e)
           {
             try
             {
               if (e.getErrorCode() == -193)
               {
                  System.out.println ("já cadastrado" + e.getErrorCode ());
                  return false;
               }
             }
             catch (Exception ex)
             {
                System.out.println ("problema com tratamento de erro");
                return false;
             }
            }
            return false;
         }

         public boolean updateDB (String tableName, Vector colNames, Vector colValues, int cols,
         Vector clause, Vector parameter, Vector colNamesException)
         {

              System.out.println ("vetor colNames:" + colNames);
              System.out.println ("vetor colValues:" + colValues);
              System.out.println ("vetor de clausulas " + clause);
              System.out.println ("vetor de parametros :" + parameter);

               int contador;
               String updateSQL;
               String tipo;
               try
               {
                  //verifica se os dois vetores devem ter o mesmo tamanho
                  if (clause.size() != parameter.size())
                  {
                      System.out.println("tamanho dos vetores de parâmetros e cláusula incompatíveis");
                      return false;
                  }
                  //remove as colunas e valores que estão no
                  //vetor colNamesException, isto é, que são
                  //key ou foreign key
                  contador = colNamesException.size() - 1;
                  for (int i = 0; i<=contador; i++)
                  {
                    int aux = colNames.indexOf (colNamesException.elementAt(i));
                    if (aux >= 0)
                    {
                        colNames.removeElementAt(aux);
                        colValues.removeElementAt (aux);
                    }
                   }
                   contador = colNames.size() - 1;
                   updateSQL = "UPDATE " + tableName + " SET ";
                   for (int i = 0; i <= contador; i++)
                   {
                      //verifica se o nome da coluna não está em colNamesException,
                      //isto é, não é uma key ou foreign key para que não sejam inseridas
                      // na SQL da atualização.
                      if (!colNamesException.contains (colNames.elementAt(i)))
                      {
                          updateSQL = updateSQL + (String)colNames.elementAt(i);
                          updateSQL = updateSQL + " = ";
                          //obtém o tipo de dado que está no vetor colValues;
                          // na posicao i
                          tipo = (colValues.elementAt (i).getClass ().getName ());
                          //compara o tipo do objeto lido co String
                          if (tipo.equals ("java.lang.String"))
                            updateSQL = updateSQL + "'" + colValues.elementAt(i) + "'" ;
                          else
                             updateSQL = updateSQL + colValues.elementAt(i);
                             //verifica se nã é a últimacoluna
                             if (i != contador)
                                 updateSQL = updateSQL + ",";
                       }

                    }
                    contador = clause.size() - 1;
                    updateSQL = updateSQL + "WHERE ";
                    for (int i =0; i <=contador; i++)
                    {
                        //obtém o tipo de objeto que está armazenado em parameter
                        tipo = (parameter.elementAt(i).getClass().getName ());
                        //compara o tipo de objeto lido com String
                        if (tipo.equals("java.lang.String"))
                           updateSQL = updateSQL  + clause.elementAt(i) + "'" + parameter.elementAt(i) + "'";
                        else
                           updateSQL = updateSQL + clause.elementAt(i) + parameter.elementAt(i);

                        if (i != contador)
                           updateSQL = updateSQL + "AND ";
                     }
                     System.out.println ("updateSQL : " + updateSQL);
                     ConnectionManager.stmt.executeUpdate (updateSQL);
                     ConnectionManager.con.commit ();
                     System.out.println("Registro atualizado corretamente!");
                     return true;
                   }
                   catch (SQLException e)
                   {
                      System.out.println ("Problema na atuailzação do registro!" + e.getErrorCode ());
                      return false;
                   }
                 }

              public boolean deleteDB (String tableName, Vector clause, Vector parameter)
              {
                  String tipo;
                  String deleteSQL;
                  int contador;
                  try
                  {
                      //verifica se os dois vetores devem ter o mesmo tamanho
                      if (clause.size() != parameter.size())
                      {
                          System.out.println ("Tamanho dos vetores de cláusulas e parâmetros incompatíveis ");
                          return false;
                       }
                       contador = clause.size() -1;
                       deleteSQL = "DELETE FROM " + tableName + " WHERE ";
                       for (int i = 0; i <= contador; i++)
                       {
                          //obtém o tipo de objeto que está armazenado em parameter
                          tipo = (parameter.elementAt(i).getClass ().getName ());
                          // compara o tipo do objeto lido, com String
                          if (tipo.equals ("java.lang.String"))
                              deleteSQL = deleteSQL + clause.elementAt(i) + "'"  + parameter.elementAt(i) + "'";
                          else
                              deleteSQL = deleteSQL + clause.elementAt(i) + parameter.elementAt(i);
                          if (i != contador)
                              deleteSQL = deleteSQL + " AND ";
                        }
                        //System.out.println (deleteSQL);
                        ConnectionManager.stmt.executeUpdate(deleteSQL);
                        ConnectionManager.con.commit ();
                        System.out.println("registro removido corretamente");
                        return true;
                      }
                      catch (SQLException e)
                      {
                          System.out.println ("problema na remoção do registro! " + e.getErrorCode());
                          return false;
                      }
                 }

        public ResultSet findallDB (String tableName)
        {
        try
        {
            String findallSQL = "SELECT * FROM " + tableName;
            ResultSet rs = ConnectionManager.stmt.executeQuery(findallSQL);
            return rs;
         }
         catch (SQLException e)
         {
             System.out.println ("Problema na consulta dos registros!"  + e.getErrorCode ());
             return null;
          }
         }

          public ResultSet findlikeDB(String tableName, Vector clause, Vector parameter)
          {
              String findlikeSQL;
              String tipo;
              int contador;
              try
              {
                  //verifica se os dois vetores devem ter o mesmo tamanho
                  if (clause.size() != parameter.size())
                  {
                    System.out.println ("Tamamho dos vetores de cláusula e parâmetros incompativeis");
                    return null;
                  }
                  contador = clause.size() - 1;
                  findlikeSQL = "SELECT * FROM " + tableName + " WHERE ";
                  for (int i = 0;i <= contador; i++)
                  {
                    //obtém o tipo de objeto que está armazenado em parameter
                    tipo = (parameter.elementAt(i)).getClass ().getName ();
                    System.out.println("Tipo: " + tipo);
                    //compara o tipo do objeto lido com String
                    if (tipo.equals ("java.lang.String"))
                        findlikeSQL = findlikeSQL + clause.elementAt(i) + "'" + parameter.elementAt(i) + "'";
                    else
                        findlikeSQL = findlikeSQL + clause.elementAt(i) + parameter.elementAt (i);
                    if (i != contador)
                      findlikeSQL = findlikeSQL + " AND ";
                   }
                   System.out.println("sql para findlike: " + findlikeSQL);
                   ResultSet rs = ConnectionManager.stmt.executeQuery (findlikeSQL);
                   return rs;
                }
                catch (SQLException e)
                {
                  System.out.println ("Problema na consulta do registro" + e.getErrorCode ());
                  return null;
                 }
              }

            public int getlastDB (String tableName, String keyName)
            {
                int codigo = 0;
                ResultSet rs;
                String findlastSQL;
                try
                {
                  //neste caso sempre só terá uma cláusula para calcular o máximo
                  // que é a key
                  findlastSQL = "SELECT MAX(" + keyName + ")";
                  findlastSQL = findlastSQL + " FROM " + tableName;
                  System.out.println ("findlastSQL = " + findlastSQL);
                  rs = ConnectionManager.stmt.executeQuery (findlastSQL);
                  if (rs.next());
                  {
                    keyName = "MAX(" + keyName + ")";
                    codigo = rs.getInt(keyName);
                  }
                   return codigo;
                 }
                 catch (SQLException e)
                 {
                    System.out.println ("Problema na consulta do registro, numero do erro: " + e.getErrorCode ());
                    System.out.println ("mensagem de erro: " + e.getMessage ());
                    return codigo;
                 }
             }

             public int getlastDB (String tableName, String keyName, int cod, String tipoFuncionario)
             {
                 int codigo = 0;
                 ResultSet rs;
                 String findlastSQL;
                 try
                 {
                   //neste caso sempre só terá uma cláusula para calcular o máximo
                   // que é a key
                   findlastSQL = "SELECT MAX(" + keyName + ")";
                   findlastSQL = findlastSQL + " FROM " + tableName;
                   findlastSQL = findlastSQL + " WHERE cod_funcionario = " + cod + " AND Tipo = '" + tipoFuncionario + "'";

                   System.out.println ("findlastSQL = " + findlastSQL);
                   rs = ConnectionManager.stmt.executeQuery (findlastSQL);
                   if (rs.next());
                   {
                     keyName = "MAX(" + keyName + ")";
                     codigo = rs.getInt(keyName);
                   }
                    return codigo;
                  }
                  catch (SQLException e)
                  {
                     System.out.println ("Problema na consulta do registro, numero do erro: " + e.getErrorCode ());
                     System.out.println ("mensagem de erro: " + e.getMessage ());
                     return codigo;
                  }
              }



        public int getlastDB (String tableName, String keyName, Vector clause, Vector parameter)
        {
          int codigo = 0;
          int contador;
          String tipo;
          ResultSet rs;
          String findlastSQL;
          try
          {
            //verifica se os dois vetores devem ter o mesmo tamanho
            if (clause.size() != parameter.size())
            {
              System.out.println ("tamanho dos vetores de cláusulas e parâmetros incompatíveis");
              return codigo;
            }
            //neste caso sempre só terá uma cláusula para calcular o máximo
            // que é a key
            findlastSQL = "SELECT MAX( " + keyName + " )";
            findlastSQL = findlastSQL + " FROM " + tableName + " WHERE ";
            //obtém a quantidade de ítens do vetor, iniciando pelo indice 0
            contador = clause.size() - 1;
            for  (int i = 0;i<=contador; i++)
            {
              //obtém o tipo de objeto que etá armazenado em parameter
              tipo = (parameter.elementAt (i).getClass().getName ());
              System.out.println("Tipo: " + tipo);
              //compaa o tipo do objto lido com String
              if (tipo.equals ("java.lang.String"))
                findlastSQL = findlastSQL + clause.elementAt (i) + "'" + parameter.elementAt(i) + "'";
              else
                findlastSQL = findlastSQL + clause.elementAt (i) + parameter.elementAt (i);
              if (i != contador)
                findlastSQL = findlastSQL + " AND ";
             }
             rs = ConnectionManager.stmt.executeQuery (findlastSQL);
             if (rs.next())
             {
                keyName = "Max (" + keyName + ")";
                codigo = rs.getInt(keyName);
             }
             return codigo;
           }
           catch (SQLException e)
           {
               System.out.println ("problema na consulta do registro!" + e.getErrorCode ());
               return codigo;
           }
        }*/
    }


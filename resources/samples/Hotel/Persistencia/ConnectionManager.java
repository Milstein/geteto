package Persistencia;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectionManager {

  static Connection con;
  static Statement stmt;

  // Efetua a conexão com o banco de dados.
  public static void ConnectDB () {
    try {
      //Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      //con = DriverManager.getConnection ("jdbc:odbc:FuncionariosDB", "dba", "sql");
      //stmt = con.createStatement ();
      
      //Class.forName ("com.mysql.jdbc.Driver");
      Class.forName("org.hsqldb.jdbcDriver" );
     // con = DriverManager.getConnection ("jdbc:mysql:///test?user=&password=");
      
      con = DriverManager.getConnection("jdbc:hsqldb:hotel","sa","");
      stmt = con.createStatement();        
      
    }
    catch (Exception e) {
      System.out.println ( "problemas para abrir database" );
    }
  }

  // Elimina a conexão com o banco de dados.
  public static void CloseDB () {
    try {
      stmt.close ();
      con.close ();
    }
    catch (Exception e) {
      System.out.println ( "problemas para fechar database" );
    }
  }
}
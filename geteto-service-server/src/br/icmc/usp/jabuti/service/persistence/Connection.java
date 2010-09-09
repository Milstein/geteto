package br.icmc.usp.jabuti.service.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
    private static java.sql.Connection CONN;
       
    public Connection() {
    }
    
    public static java.sql.Connection getConnection(Properties props)
    {
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
    	
    	if(CONN == null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                CONN = DriverManager.getConnection(url + "?" + "user=" + user + "&password=" + password);
                return CONN;
                
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        else
            return CONN;
    }
    
    public static void main(String args[])
    {
    	System.out.println("test");
    	//java.sql.Connection conn = Connection.getConnection();
    }
}

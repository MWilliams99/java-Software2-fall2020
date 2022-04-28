package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnection class is used to create a connection to the database.
 * @author Mary Williams
 * @version 1
 */
public class DBConnection {
    //url parts
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ip = "host-placeholder";
    //full jdbc url
    private static final String dbURL = protocol+vendor+ip;
    //connection interface reference
    private static Connection conn = null;
    //db username + pass
    private static final String dbUsername = "username-placeholder";
    private static final String dbPassword = "password-placeholder";

    /**
     * The startConnection method starts a connection to the database.
     * @return The Connection object that was created.
     */
    public static Connection startConnection(){
        try{
            conn = DriverManager.getConnection(dbURL,dbUsername, dbPassword);
            //System.out.println("Connection successful");
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * The closeConnection method closes a connection to the database.
     */
    public static void closeConnection(){
        try{
            conn.close();
            //System.out.println("Connection closed");
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
    }
}

package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DBQuery class is used to create a Statement for querying the database.
 * @author Mary Williams
 * @version 1
 */
public class DBQuery {
    //statement object
    private static Statement statement;

    /**
     * The setStatement method creates a Statement object using the Connection.
     * @param conn The Connection object to use in the create of the Statement.
     */
    public static void setStatement(Connection conn){
        try{
            statement = conn.createStatement();
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
    }

    /**
     * The getStatement method returns the created Statement.
     * @return The Statement object in the statement variable.
     */
    public static Statement getStatement(){
        return statement;
    }
}

package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * DivisionImp implements DivisionDao and holds methods to perform CRUD operations on divisions in the database.
 * @author Mary Williams
 * @version 1
 */
public class DivisionImp implements DivisionDao{
    /**
     * The getDivision method is used to retrieve a single division from the database that corresponds to the divisionID parameter.
     * @param divisionID The ID of the division to retrieve from the database.
     * @return The Division object with data retrieved from the database.
     */
    @Override
    public Division getDivision(int divisionID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select division by ID
        String selectStatement = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, divisionID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("COUNTRY_ID");

                Division division = new Division(divisionID,divisionName,countryID);
                DBConnection.closeConnection();
                return division;
            }
            else{
                DBConnection.closeConnection();
                return null;
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            DBConnection.closeConnection();
            return null;
        }
    }
    /**
     * The getDivsByCountry method is used to retrieve all divisions from the database that correspond to the countryID parameter.
     * @param countryID The ID of the country to get related divisions from the database.
     * @return The Observable List of Division in the database that correspond to the country ID.
     */
    @Override
    public ObservableList<Division> getDivsByCountry(int countryID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create observable list to hold returned divisions
        ObservableList<Division> divisionsByCountry = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, countryID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Division division = new Division(divisionID,divisionName,countryID);
                divisionsByCountry.add(division);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return divisionsByCountry;
    }
}

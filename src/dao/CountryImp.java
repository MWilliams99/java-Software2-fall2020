package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * CountryImp implements CountryDao and holds methods to perform CRUD operations on countries in the database.
 * @author Mary Williams
 * @version 1
 */
public class CountryImp implements CountryDao{
    /**
     * The getAllCountries method is used to retrieve all countries from the database.
     * @return The Observable list of all Countries in the database.
     */
    @Override
    public ObservableList<Country> getAllCountries() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create observablelist to hold returned countries
        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM countries";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Country country = new Country(countryID,countryName);
                allCountries.add(country);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return allCountries;
    }
    /**
     * The getCountry method is used to retrieve a single country from the database that corresponds to the countryID parameter.
     * @param countryID The ID of the country to retrieve from the database.
     * @return The Country object with data retrieved from the database.
     */
    @Override
    public Country getCountry(int countryID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select country by ID
        String selectStatement = "SELECT * FROM countries WHERE Country_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, countryID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String countryName = rs.getString("Country");

                Country country = new Country(countryID,countryName);
                DBConnection.closeConnection();
                return country;
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
}

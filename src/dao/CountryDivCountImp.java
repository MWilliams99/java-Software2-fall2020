package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CountryDivCount;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * CountryDivCountImp implements CountryDivCountDao and holds a method to gather a count of customers in each division for a given country for use in the main form 'Number of Customers by Country and Division' Table.
 * @author Mary Williams
 * @version 1
 */
public class CountryDivCountImp implements CountryDivCountDao {
    /**
     * The getReportCustomerCountTable method is used to get a Count of customers in each division for the selected country, for use in the 'Number of Customers by Country and Division' Table in the main form.
     * @param countryID The ID of the selected country to retrieve a count of customers in each division related to it.
     * @return The Observable List of CountryDivCount that hold the data retrieved from the database.
     */
    @Override
    public ObservableList<CountryDivCount> getReportCustomerCountTable(int countryID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<CountryDivCount> monthTypeCountRecord = FXCollections.observableArrayList();

        //Select count of customers by country and division
        String selectStatement = "SELECT Division, COUNT(customers.Division_ID) as 'Count', Country " +
                "FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
                "INNER JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID " +
                "WHERE first_level_divisions.COUNTRY_ID = ? GROUP BY Division";

        //try..catch run sqlstatement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, countryID);
            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                String country = rs.getString("Country");
                String division = rs.getString("Division");
                int count = rs.getInt("Count");

                CountryDivCount countryDivCount = new CountryDivCount(country,division,count);
                monthTypeCountRecord.add(countryDivCount);
            }
        }
        catch(SQLException e){
            //System.out.println(e);
        }
        DBConnection.closeConnection();
        return monthTypeCountRecord;
    }
}

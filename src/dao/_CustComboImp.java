package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.Division;
import model._CustDivCountryCombo;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;
/**
 * _CustComboImp implements _CustComboDao and holds a method to gather Customers to display in the main form Customers Table.
 * @author Mary Williams
 * @version 1
 */
public class _CustComboImp implements _CustComboDao{
    /**
     * The getCustomerTable method is used to return all Customers for use in the main form Customers Table.
     * @return The Observable List of _CustDivCountryCombo that correlate to all Customers.
     */
    @Override
    public ObservableList<_CustDivCountryCombo> getCustomerTable() {
        ObservableList<_CustDivCountryCombo> customerTableQueryResults = FXCollections.observableArrayList();

        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String selectStatement = "SELECT * "+
                "FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID "+
                "INNER JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID "+
                "ORDER BY Customer_ID ASC";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int customerID = rs.getInt("customers.Customer_ID");
                String customerName = rs.getString("customers.Customer_Name");
                String customerAddress = rs.getString("customers.Address");
                String customerPostCode = rs.getString("customers.Postal_Code");
                String customerPhone = rs.getString("customers.Phone");
                int divisionID = rs.getInt("customers.Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);

                String divisionName = rs.getString("first_level_divisions.Division");
                int countryID = rs.getInt("first_level_divisions.COUNTRY_ID");

                Division division = new Division(divisionID,divisionName,countryID);

                String countryName = rs.getString("countries.Country");

                Country country = new Country(countryID,countryName);


                _CustDivCountryCombo customerRecord = new _CustDivCountryCombo(customer,division,country);
                customerTableQueryResults.add(customerRecord);

            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return customerTableQueryResults;
    }
}

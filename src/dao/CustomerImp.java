package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * CustomerImp implements CustomerDao and holds methods to perform CRUD operations on customers in the database.
 * @author Mary Williams
 * @version 1
 */
public class CustomerImp implements CustomerDao{
    /**
     * The createCustomer method is used to create a customer in the database.
     * @param customerCreate The customer to create in the database.
     */
    @Override
    public void createCustomer(Customer customerCreate) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String customerName = customerCreate.getCustomerName();
        String customerAddress = customerCreate.getCustomerAddress();
        String customerPostCode = customerCreate.getCustomerPostCode();
        String customerPhone = customerCreate.getCustomerPhone();
        String createBy = User.getCurrentUserName();
        String updateBy = User.getCurrentUserName();
        int divisionID = customerCreate.getDivisionID();

        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Created_By, Last_Updated_By, Division_ID) "+
                "VALUES(?,?,?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(insertStatement)){
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostCode);
            ps.setString(4, customerPhone);
            ps.setString(5, createBy);
            ps.setString(6, updateBy);
            ps.setInt(7, divisionID);

            ps.execute();

            //confirm row insert -- just for testing? -- if keep, change to a pop up window.
            if(ps.getUpdateCount() > 0){
                //System.out.println("Insert "+ps.getUpdateCount()+"row(s)");
                //System.out.println(insertStatement);
            }
            else{
                //System.out.println("No change");
            }
            DBConnection.closeConnection();
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            DBConnection.closeConnection();
        }
    }
    /**
     * The getAllCustomers method is used to retrieve all customers from the database.
     * @return The Observable List of all Customers in the database.
     */
    @Override
    public ObservableList<Customer> getAllCustomers() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create an observablelist to hold return
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        //Select all from customers
        String selectStatement = "SELECT * FROM customers";

        //try..catch run sql statement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();
            //while next in rs - make new customer and put into observable list
            while(rs.next()){
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);
                allCustomers.add(customer);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return allCustomers;
    }
    /**
     * The getCustomer method is used to retrieve a single customer from the database that corresponds to the customerID parameter.
     * @param customerID The ID of the customer to retrieve from the database.
     * @return The Customer object with data retrieved from the database.
     */
    @Override
    public Customer getCustomer(int customerID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select customer by ID
        String selectStatement = "SELECT * FROM customers WHERE Customer_ID = ?";

        //try..catch run the select statement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, customerID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);

                DBConnection.closeConnection();
                return customer;
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
     * The updateCustomer method is used to update a customer in the database.
     * @param customerID The ID of the customer to update in the database.
     * @param customerUpdate The customer with updated data to use in the database.
     */
    @Override
    public void updateCustomer(int customerID, Customer customerUpdate) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Get customer info into vars
        String customerName = customerUpdate.getCustomerName();
        String customerAddress = customerUpdate.getCustomerAddress();
        String customerPostCode = customerUpdate.getCustomerPostCode();
        String customerPhone = customerUpdate.getCustomerPhone();
        String updateBy = User.getCurrentUserName();
        int divisionID = customerUpdate.getDivisionID();

        //Update customer by ID
        String updateStatement = "UPDATE customers SET Customer_Name = ?"+
                ", Address = ?"+
                ", Postal_Code = ?"+
                ", Phone = ?"+
                ", Last_Update = now(), Last_Updated_By = ?"+
                ", Division_ID = ?"+
                " WHERE Customer_ID = ?";

        //try..catch to run updateStatement
        try(PreparedStatement ps = conn.prepareStatement(updateStatement)){
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostCode);
            ps.setString(4, customerPhone);
            ps.setString(5, updateBy);
            ps.setInt(6, divisionID);
            ps.setInt(7, customerID);

            ps.execute();

            //Confirm update count -- for testing purposes -- if keep change to popup not in console.
            if(ps.getUpdateCount() > 0){
                //System.out.println("Updated "+ps.getUpdateCount()+"row(s).");
                //System.out.println(updateStatement);
            }
            else{
                //System.out.println("No change.");
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
    }
    /**
     * The deleteCustomer method is used to delete a customer in the database.
     * @param customerID The ID of the customer to delete in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    @Override
    public boolean deleteCustomer(int customerID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(deleteStatement)){
            ps.setInt(1, customerID);
            ps.execute();

            if(ps.getUpdateCount() > 0){
                //System.out.println("Deleted "+ps.getUpdateCount()+"row(s)");
                //System.out.println(deleteStatement);
                DBConnection.closeConnection();
                return true;
            }
            else{
                //System.out.println("No change");
                DBConnection.closeConnection();
                return false;
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            DBConnection.closeConnection();
            return false;
        }
    }
}

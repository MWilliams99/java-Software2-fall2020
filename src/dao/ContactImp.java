package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * ContactImp implements ContactDao and holds methods to perform CRUD operations on contacts in the database.
 * @author Mary Williams
 * @version 1
 */
public class ContactImp implements ContactDao{
    /**
     * The getAllContacts method is used to retrieve all contacts from the database.
     * @return The Observable List of all Contacts in the database.
     */
    @Override
    public ObservableList<Contact> getAllContacts() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create observablelist to hold returned contacts
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM contacts";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contact contact = new Contact(contactID,contactName,contactEmail);
                allContacts.add(contact);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return allContacts;
    }

    /**
     * The getContact method is used to retrieve a single contact from the database that corresponds to the contactID parameter.
     * @param contactID The ID of the contact to retrieve from the database.
     * @return The Contact object with data retrieved from the database.
     */
    @Override
    public Contact getContact(int contactID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select contact by ID
        String selectStatement = "SELECT * FROM contacts WHERE Contact_ID = "+contactID;

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();
            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contact contact = new Contact(contactID,contactName,contactEmail);
                DBConnection.closeConnection();
                return contact;
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

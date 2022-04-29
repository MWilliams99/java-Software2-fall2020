package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.*;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * _AppComboImp implements _AppComboDao and holds methods to gather Appointments to display in the main form Appointments Table.
 * @author Mary Williams
 * @version 1
 */
public class _AppComboImp implements _AppComboDao{
    /**
     * The getAppointmentTable method is used to return all Appointments for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to all Appointments.
     */
    @Override
    public ObservableList<_AppContactCustCombo> getAppointmentTable() {
        ObservableList<_AppContactCustCombo> appointmentsTableQueryResults = FXCollections.observableArrayList();

        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String selectStatement = "SELECT * "+
                "FROM appointments INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID "+
                "INNER JOIN users ON appointments.User_ID = users.User_ID "+
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "ORDER BY Appointment_ID ASC";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int appointmentID = rs.getInt("appointments.Appointment_ID");
                String appointmentTitle = rs.getString("appointments.Title");
                String appointmentDesc = rs.getString("appointments.Description");
                String appointmentLocation = rs.getString("appointments.Location");
                String appointmentType = rs.getString("appointments.Type");
                LocalDateTime appointmentStart = rs.getTimestamp("appointments.Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("appointments.End").toLocalDateTime();
                int userID = rs.getInt("appointments.User_ID");
                int customerID = rs.getInt("appointments.Customer_ID");
                int contactID = rs.getInt("appointments.Contact_ID");

                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);

                String customerName = rs.getString("customers.Customer_Name");
                String customerAddress = rs.getString("customers.Address");
                String customerPostCode = rs.getString("customers.Postal_Code");
                String customerPhone = rs.getString("customers.Phone");
                int divisionID = rs.getInt("customers.Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);

                String userName = rs.getString("users.User_Name");
                String userPass = rs.getString("users.Password");

                User user = new User(userID,userName,userPass);

                String contactName = rs.getString("contacts.Contact_Name");
                String contactEmail = rs.getString("contacts.Email");

                Contact contact = new Contact(contactID,contactName,contactEmail);


                _AppContactCustCombo appointmentRecord = new _AppContactCustCombo(appointment,customer,contact,user);
                appointmentsTableQueryResults.add(appointmentRecord);

            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsTableQueryResults;
    }

    /**
     * The getWeekAppointmentTable method is used to return Appointments between now and a week from now for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to Appointments between now and a week from now.
     */
    @Override
    public ObservableList<_AppContactCustCombo> getWeekAppointmentTable() {
        ObservableList<_AppContactCustCombo> appointmentsTableQueryResults = FXCollections.observableArrayList();

        //Create connection and statement
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Get current time, and time a week from now - rounded to midnight day of.
        LocalDateTime nowWeek = LocalDateTime.now().plusWeeks(1).withHour(23).withMinute(59).withSecond(59).withNano(0);

        //Select appointments WHERE start time between now and a week from now.

        String selectStatement = "SELECT * "+
                "FROM appointments INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID "+
                "INNER JOIN users ON appointments.User_ID = users.User_ID "+
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "WHERE Start >= now() AND Start <= ?" +
                "ORDER BY Appointment_ID ASC";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setObject(1, nowWeek);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int appointmentID = rs.getInt("appointments.Appointment_ID");
                String appointmentTitle = rs.getString("appointments.Title");
                String appointmentDesc = rs.getString("appointments.Description");
                String appointmentLocation = rs.getString("appointments.Location");
                String appointmentType = rs.getString("appointments.Type");
                LocalDateTime appointmentStart = rs.getTimestamp("appointments.Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("appointments.End").toLocalDateTime();
                int userID = rs.getInt("appointments.User_ID");
                int customerID = rs.getInt("appointments.Customer_ID");
                int contactID = rs.getInt("appointments.Contact_ID");

                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);

                String customerName = rs.getString("customers.Customer_Name");
                String customerAddress = rs.getString("customers.Address");
                String customerPostCode = rs.getString("customers.Postal_Code");
                String customerPhone = rs.getString("customers.Phone");
                int divisionID = rs.getInt("customers.Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);

                String userName = rs.getString("users.User_Name");
                String userPass = rs.getString("users.Password");

                User user = new User(userID,userName,userPass);

                String contactName = rs.getString("contacts.Contact_Name");
                String contactEmail = rs.getString("contacts.Email");

                Contact contact = new Contact(contactID,contactName,contactEmail);


                _AppContactCustCombo appointmentRecord = new _AppContactCustCombo(appointment,customer,contact,user);
                appointmentsTableQueryResults.add(appointmentRecord);

            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsTableQueryResults;
    }

    /**
     * The getMonthAppointmentTable method is used to return Appointments between now and a month from now for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to Appointments between now and a month from now.
     */
    @Override
    public ObservableList<_AppContactCustCombo> getMonthAppointmentTable() {
        ObservableList<_AppContactCustCombo> appointmentsTableQueryResults = FXCollections.observableArrayList();

        //Create connection and statement
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Get current time, and time a month from now - rounded to midnight day of.
        LocalDateTime nowMonth = LocalDateTime.now().plusMonths(1).withHour(23).withMinute(59).withSecond(59).withNano(0);

        //Select appointments WHERE start time between now and a week from now.
        String selectStatement = "SELECT * "+
                "FROM appointments INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID "+
                "INNER JOIN users ON appointments.User_ID = users.User_ID "+
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "WHERE Start >= now() AND Start <= ?" +
                "ORDER BY Appointment_ID ASC";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setObject(1,nowMonth);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int appointmentID = rs.getInt("appointments.Appointment_ID");
                String appointmentTitle = rs.getString("appointments.Title");
                String appointmentDesc = rs.getString("appointments.Description");
                String appointmentLocation = rs.getString("appointments.Location");
                String appointmentType = rs.getString("appointments.Type");
                LocalDateTime appointmentStart = rs.getTimestamp("appointments.Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("appointments.End").toLocalDateTime();
                int userID = rs.getInt("appointments.User_ID");
                int customerID = rs.getInt("appointments.Customer_ID");
                int contactID = rs.getInt("appointments.Contact_ID");

                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);

                String customerName = rs.getString("customers.Customer_Name");
                String customerAddress = rs.getString("customers.Address");
                String customerPostCode = rs.getString("customers.Postal_Code");
                String customerPhone = rs.getString("customers.Phone");
                int divisionID = rs.getInt("customers.Division_ID");

                Customer customer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);

                String userName = rs.getString("users.User_Name");
                String userPass = rs.getString("users.Password");

                User user = new User(userID,userName,userPass);

                String contactName = rs.getString("contacts.Contact_Name");
                String contactEmail = rs.getString("contacts.Email");

                Contact contact = new Contact(contactID,contactName,contactEmail);


                _AppContactCustCombo appointmentRecord = new _AppContactCustCombo(appointment,customer,contact,user);
                appointmentsTableQueryResults.add(appointmentRecord);

            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsTableQueryResults;
    }
}

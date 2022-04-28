package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * AppointmentImp implements AppointmentDao and holds methods to perform CRUD operations on appointments in the database.
 * @author Mary Williams
 * @version 2
 */
public class AppointmentImp implements AppointmentDao{
    /**
     * The createAppointment method is used to create an appointment in the database.
     * @param appointmentCreate The appointment to create in the database.
     */
    @Override
    public void createAppointment(Appointment appointmentCreate) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String appointmentTitle = appointmentCreate.getAppointmentTitle();
        String appointmentDesc = appointmentCreate.getAppointmentDesc();
        String appointmentLocation = appointmentCreate.getAppointmentLocation();
        String appointmentType = appointmentCreate.getAppointmentType();


        ZoneId localZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime appointmentStartLocal = appointmentCreate.getAppointmentStart();
        LocalDateTime appointmentStart = appointmentStartLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();

        LocalDateTime appointmentEndLocal = appointmentCreate.getAppointmentEnd();
        LocalDateTime appointmentEnd = appointmentEndLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();


        String createBy = User.getCurrentUserName();
        String updateBy = User.getCurrentUserName();
        int userID = appointmentCreate.getUserID();
        int customerID = appointmentCreate.getCustomerID();
        int contactID = appointmentCreate.getContactID();

        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) "+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";


        try(PreparedStatement ps = conn.prepareStatement(insertStatement)){
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDesc);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setObject(5, appointmentStart);
            ps.setObject(6, appointmentEnd);
            ps.setString(7, createBy);
            ps.setString(8, updateBy);
            ps.setInt(9, customerID);
            ps.setInt(10, userID);
            ps.setInt(11, contactID);

            ps.execute();

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
     * The getAllAppointments method is used to retrieve all appointments from the database.
     * @return The Observable List of all Appointments in the database.
     */
    @Override
    public ObservableList<Appointment> getAllAppointments() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create an observablelist to hold return
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        //Select all from appointments
        String selectStatement = "SELECT * FROM appointments";

        //try..catch run sql statement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();
            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int userID = rs.getInt("User_ID");
                int customerID = rs.getInt("Customer_ID");
                int contactID = rs.getInt("Contact_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
                allAppointments.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return allAppointments;
    }

    /**
     * The getAppointment method is used to retrieve a single appointment from the database that corresponds to the appointmentID parameter.
     * @param appointmentID The ID of the appointment to retrieve from the database.
     * @return The Appointment object with data retrieved from the database.
     */
    @Override
    public Appointment getAppointment(int appointmentID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select appointment by ID
        String selectStatement = "SELECT * FROM appointments WHERE Appointment_ID = ?";

        //try..catch run the select statement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, appointmentID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int userID = rs.getInt("User_ID");
                int customerID = rs.getInt("Customer_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
                DBConnection.closeConnection();
                return appointment;
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
     * The getUserAppointmentAlert method is used to get all appointments from the database that correspond with the userID parameter.
     * @param userID The ID of the user to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the user ID.
     */
    @Override
    public ObservableList<Appointment> getUserAppointmentAlert(int userID){
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create an observablelist to hold return
        ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();

        //Get username, login time, and login time +15 for use in select statement
        LocalDateTime loginLocalTime = User.getLoginTime();
        LocalDateTime loginLocalTime15 = User.getLoginTime15();

        //Change Times for accurate selection
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime loginTime = loginLocalTime.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();
        LocalDateTime loginTime15 = loginLocalTime15.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();

        //Select from appointments where current user is userID and Start > now and Start < now+15
        String selectStatement = "SELECT * FROM appointments WHERE User_ID = ?"+
                " AND Start >= ? AND Start <= ? ORDER BY Start ASC";


        //try..catch run sql statement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, userID);
            ps.setObject(2, loginTime);
            ps.setObject(3, loginTime15);

            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();
            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int contactID = rs.getInt("Contact_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
                userAppointments.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return userAppointments;
    }

    /**
     * The getAppsByCustomer method is used to get all appointments from the database that correspond with the customerID parameter.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the customer ID.
     */
    @Override
    public ObservableList<Appointment> getAppsByCustomer(int customerID){
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<Appointment> appointmentsByCustomer = FXCollections.observableArrayList();

        //Select appointments by customer id
        String selectStatement = "SELECT * FROM appointments WHERE Customer_ID = ?";

        //try..catch run sqlstatement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, customerID);

            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();

            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
                appointmentsByCustomer.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsByCustomer;
    }

    /**
     * The getReportScheduleTable method is used to get all appointments from the database that correspond with the contactID parameter, for use in the 'Appointments Schedule by Contact' Table in the main form.
     * @param contactID The ID of the contact to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the contact ID.
     */
    @Override
    public ObservableList<Appointment> getReportScheduleTable(int contactID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();

        //Select appointments by contact id
        String selectStatement = "SELECT * FROM appointments WHERE Contact_ID = ? ORDER BY Start ASC";

        //try..catch run sqlstatement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, contactID);

            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();

            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
                appointmentsByContact.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsByContact;
    }

    /**
     * The getConflictingAppointments method is used to get any existing appointments for the customerID parameter that conflict with input appointment times appStart and appEnd parameters.
     * In version 2 this has been changed to include greater-than-or-equal-to and less-than-or-equal-to, to better fit the A.3.d. project requirement:
     * "Implement input validation and logical error checks to prevent scheduling overlapping appointments for customers."
     * Previously when using greater-than and less-than it would allow, for example, one appointment to end at 10am while the next one starts at 10am.
     * With greater-than-or-equal-to and less-than-or-equal-to signs, this now will not allow appointments start and end to overlap in the same one minute, for example, an appointment must now end at 9:59am if the next starts at 10am.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @param appointmentStartLocal The suggested Start time for a new appointment, to check if it overlaps with any existing appointments for the given customer.
     * @param appointmentEndLocal suggested End time for a new appointment, to check if it overlaps with any existing appointments for the given customer.
     * @return The Observable List of Appointments that conflict with the suggested times for the customer's new appointment.
     */
    @Override
    public ObservableList<Appointment> getConflictingAppointments(int customerID, LocalDateTime appointmentStartLocal, LocalDateTime appointmentEndLocal){
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<Appointment> appointmentsConflicting = FXCollections.observableArrayList();

        //Change Times for accurate selection
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime appointmentStart = appointmentStartLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();
        LocalDateTime appointmentEnd = appointmentEndLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();

        //Select appoints where conflicting times for newApp.
        String selectStatement = "SELECT * FROM appointments WHERE Customer_ID = ?"+
                " AND End >= ? AND Start <= ?";

        //try..catch run sqlstatement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, customerID);
            ps.setObject(2, appointmentStart);
            ps.setObject(3, appointmentEnd);

            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();

            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = rs.getTimestamp("End").toLocalDateTime();
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appStart,appEnd,userID,customerID,contactID);
                appointmentsConflicting.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsConflicting;
    }

    /**
     * The getConflictingAppointmentsUpdate method is used to get any existing appointments, outside of the one being updated, for the customerID parameter that conflict with input appointment times appStart and appEnd parameters.
     * In version 2 this has been changed to include greater-than-or-equal-to and less-than-or-equal-to, to better fit the A.3.d. project requirement:
     * "Implement input validation and logical error checks to prevent scheduling overlapping appointments for customers."
     * Previously when using greater-than and less-than it would allow, for example, one appointment to end at 10am while the next one starts at 10am.
     * With greater-than-or-equal-to and less-than-or-equal-to signs, this now will not allow appointments start and end to overlap in the same one minute, for example, an appointment must now end at 9:59am if the next starts at 10am.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @param appointmentID The ID of the appointment being updated, to not allow it to count as conflicting with itself.
     * @param appointmentStartLocal The suggested Start time for an updated appointment, to check if it overlaps with any other existing appointments for the given customer.
     * @param appointmentEndLocal The suggested End time for an updated appointment, to check if it overlaps with any other existing appointments for the given customer.
     * @return The Observable List of Appointments that conflict with the suggested times for the customer's updated appointment.
     */
    @Override
    public ObservableList<Appointment> getConflictingAppointmentsUpdate(int customerID, int appointmentID, LocalDateTime appointmentStartLocal, LocalDateTime appointmentEndLocal){
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<Appointment> appointmentsConflicting = FXCollections.observableArrayList();

        //Change Times for accurate selection
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime appointmentStart = appointmentStartLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();
        LocalDateTime appointmentEnd = appointmentEndLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();

        //Select appoints where conflicting times for newApp.
        String selectStatement = "SELECT * FROM appointments WHERE Customer_ID = ?"+
                " AND End >= ? AND Start <= ? AND NOT Appointment_ID = ?";

        //try..catch run sqlstatement
        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, customerID);
            ps.setObject(2, appointmentStart);
            ps.setObject(3, appointmentEnd);
            ps.setInt(4, appointmentID);

            ps.execute();

            //Gather result into resultset
            ResultSet rs = ps.getResultSet();

            //while next in rs - make new appointment and put into observable list
            while(rs.next()){
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = rs.getTimestamp("End").toLocalDateTime();
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");


                Appointment appointment = new Appointment(appointmentID,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appStart,appEnd,userID,customerID,contactID);
                appointmentsConflicting.add(appointment);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return appointmentsConflicting;
    }

    /**
     * The updateAppointment method is used to update an appointment in the database.
     * @param appointmentID The ID of the appointment to update in the database.
     * @param appointmentUpdate The appointment with updated data to use in the database.
     */
    @Override
    public void updateAppointment(int appointmentID, Appointment appointmentUpdate) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Get appointment info into vars
        String appointmentTitle = appointmentUpdate.getAppointmentTitle();
        String appointmentDesc = appointmentUpdate.getAppointmentDesc();
        String appointmentLocation = appointmentUpdate.getAppointmentLocation();
        String appointmentType = appointmentUpdate.getAppointmentType();


        ZoneId localZone = ZoneId.systemDefault();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime appointmentStartLocal = appointmentUpdate.getAppointmentStart();
        LocalDateTime appointmentStart = appointmentStartLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();

        LocalDateTime appointmentEndLocal = appointmentUpdate.getAppointmentEnd();
        LocalDateTime appointmentEnd = appointmentEndLocal.atZone(localZone).withZoneSameInstant(utcZone).toLocalDateTime();


        String updateBy = User.getCurrentUserName();
        int userID = appointmentUpdate.getUserID();
        int customerID = appointmentUpdate.getCustomerID();
        int contactID = appointmentUpdate.getContactID();

        //Update appointment by ID
        String updateStatement = "UPDATE appointments SET Title = ?"+
                ", Description = ?"+
                ", Location = ?"+
                ", Type = ?"+
                ", Start = ?"+
                ", End = ?"+
                ", Last_Update = now(), Last_Updated_By = ?"+
                ", Customer_ID = ?"+
                ", User_ID = ?"+
                ", Contact_ID = ?"+
                " WHERE Appointment_ID = ?";

        //try..catch to run updateStatement
        try(PreparedStatement ps = conn.prepareStatement(updateStatement)){
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDesc);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setObject(5, appointmentStart);
            ps.setObject(6, appointmentEnd);
            ps.setString(7, updateBy);
            ps.setInt(8, customerID);
            ps.setInt(9, userID);
            ps.setInt(10, contactID);
            ps.setInt(11, appointmentID);

            ps.execute();

            //Confirm update count -- for testing purposes --
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
     * The deleteAppointment method is used to delete an appointment in the database.
     * @param appointmentID The ID of the appointment to delete in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    @Override
    public boolean deleteAppointment(int appointmentID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(deleteStatement)){
            ps.setInt(1, appointmentID);
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

    /**
     * The deleteAppsByCustomer method is used to delete all appointments in the database that correspond with the customerID parameter.
     * @param customerID The ID of the customer to delete related appointments of in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    @Override
    public boolean deleteAppsByCustomer(int customerID){
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        String deleteStatement = "Delete FROM appointments WHERE Customer_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(deleteStatement)){
            ps.setInt(1, customerID);
            ps.execute();

            if(ps.getUpdateCount() > 0){
                //System.out.println("Deleted "+ps.getUpdateCount()+" rows(s)");
                //System.out.println(deleteStatement);
                DBConnection.closeConnection();
                return true;
            }
            else{
                //System.out.println("No Change");
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

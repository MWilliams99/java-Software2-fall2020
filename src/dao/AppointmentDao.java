package dao;

import javafx.collections.ObservableList;
import model.Appointment;

import java.time.LocalDateTime;

/**
 * AppointmentDao is the Interface for AppointmentImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface AppointmentDao {
    /**
     * The createAppointment method is used to create an appointment in the database.
     * @param appointmentCreate The appointment to create in the database.
     */
    void createAppointment(Appointment appointmentCreate);

    /**
     * The getAllAppointments method is used to retrieve all appointments from the database.
     * @return The Observable List of all Appointments in the database.
     */
    ObservableList<Appointment> getAllAppointments();

    /**
     * The getAppointment method is used to retrieve a single appointment from the database that corresponds to the appointmentID parameter.
     * @param appointmentID The ID of the appointment to retrieve from the database.
     * @return The Appointment object with data retrieved from the database.
     */
    Appointment getAppointment(int appointmentID);

    /**
     * The getUserAppointmentAlert method is used to get all appointments from the database that correspond with the userID parameter.
     * @param userID The ID of the user to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the user ID.
     */
    ObservableList<Appointment> getUserAppointmentAlert(int userID);

    /**
     * The getAppsByCustomer method is used to get all appointments from the database that correspond with the customerID parameter.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the customer ID.
     */
    ObservableList<Appointment> getAppsByCustomer(int customerID);

    /**
     * The getReportScheduleTable method is used to get all appointments from the database that correspond with the contactID parameter, for use in the 'Appointments Schedule by Contact' Table in the main form.
     * @param contactID The ID of the contact to get related appointments from the database.
     * @return The Observable List of Appointments in the database that correspond to the contact ID.
     */
    ObservableList<Appointment> getReportScheduleTable(int contactID);

    /**
     * The getConflictingAppointments method is used to get any existing appointments for the customerID parameter that conflict with input appointment times appStart and appEnd parameters.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @param appStart The suggested Start time for a new appointment, to check if it overlaps with any existing appointments for the given customer.
     * @param appEnd The suggested End time for a new appointment, to check if it overlaps with any existing appointments for the given customer.
     * @return The Observable List of Appointments that conflict with the suggested times for the customer's new appointment.
     */
    ObservableList<Appointment> getConflictingAppointments(int customerID, LocalDateTime appStart, LocalDateTime appEnd);

    /**
     * The getConflictingAppointmentsUpdate method is used to get any existing appointments, outside of the one being updated, for the customerID parameter that conflict with input appointment times appStart and appEnd parameters.
     * @param customerID The ID of the customer to get related appointments from the database.
     * @param appointmentID The ID of the appointment being updated, to not allow it to count as conflicting with itself.
     * @param appointmentStartLocal The suggested Start time for an updated appointment, to check if it overlaps with any other existing appointments for the given customer.
     * @param appointmentEndLocal The suggested End time for an updated appointment, to check if it overlaps with any other existing appointments for the given customer.
     * @return The Observable List of Appointments that conflict with the suggested times for the customer's updated appointment.
     */
    ObservableList<Appointment> getConflictingAppointmentsUpdate(int customerID, int appointmentID, LocalDateTime appointmentStartLocal, LocalDateTime appointmentEndLocal);

    /**
     * The updateAppointment method is used to update an appointment in the database.
     * @param appointmentID The ID of the appointment to update in the database.
     * @param appointmentUpdate The appointment with updated data to use in the database.
     */
    void updateAppointment(int appointmentID, Appointment appointmentUpdate);

    /**
     * The deleteAppointment method is used to delete an appointment in the database.
     * @param appointmentID The ID of the appointment to delete in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    boolean deleteAppointment(int appointmentID);

    /**
     * The deleteAppsByCustomer method is used to delete all appointments in the database that correspond with the customerID parameter.
     * @param customerID The ID of the customer to delete related appointments of in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    boolean deleteAppsByCustomer(int customerID);
}

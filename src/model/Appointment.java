package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Appointment class simulates an Appointment.
 * It allows for data to be held about the appointment, as well as an associated User ID, Customer ID, and Contact ID.
 * @author Mary Williams
 * @version 1
 */
public class Appointment {
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDesc;
    private String appointmentLocation;
    private String appointmentType;

    private LocalDateTime appointmentStart;
    private String appointmentStartStr;
    private LocalDateTime appointmentEnd;
    private String appointmentEndStr;

    private int userID;
    private int customerID;
    private int contactID;

    //Constructor
    /**
     * The constructor creates an appointment.
     * @param appointmentID The integer to be given to the ID of the Appointment.
     * @param appointmentTitle The string to be given to the title of the Appointment.
     * @param appointmentDesc The string to be given to the description of the Appointment.
     * @param appointmentLocation The string to be given to the location of the Appointment.
     * @param appointmentType The string to be given to the type of the Appointment.
     * @param appointmentStart The LocalDateTime to be given to the start time of the Appointment.
     * @param appointmentEnd The LocalDateTime to be given to the end time of the Appointment.
     * @param userID The integer to be given to the User ID associated with the Appointment.
     * @param customerID The integer to be given to the Customer ID associated with the Appointment.
     * @param contactID The integer to be given to the Contact ID associated with the Appointment.
     */
    public Appointment(int appointmentID, String appointmentTitle, String appointmentDesc, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int userID, int customerID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDesc = appointmentDesc;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentStartStr = appointmentStart.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        this.appointmentEnd = appointmentEnd;
        this.appointmentEndStr = appointmentEnd.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        this.userID = userID;
        this.customerID = customerID;
        this.contactID = contactID;
    }

    //Getters and Setters
    /**
     * The getAppointmentID method returns the appointmentID.
     * @return The value in the appointmentID variable.
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    /**
     * The setAppointmentID method sets the ID of the Appointment.
     * @param appointmentID The integer to be given to the ID of the Appointment.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * The getAppointmentTitle method returns the title of the Appointment.
     * @return The value in the appointmentTitle variable.
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    /**
     * The setAppointmentTitle method sets the Title of the Appointment.
     * @param appointmentTitle The string to be given to the title of the Appointment.
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * The getAppointmentDesc method returns the Description of the Appointment.
     * @return The value in the appointmentDesc variable.
     */
    public String getAppointmentDesc() {
        return appointmentDesc;
    }
    /**
     * The setAppointmentDesc method sets the Description of the Appointment.
     * @param appointmentDesc The string to be given to the description of the Appointment.
     */
    public void setAppointmentDesc(String appointmentDesc) {
        this.appointmentDesc = appointmentDesc;
    }

    /**
     * The getAppointmentLocation method returns the Location of the Appointment.
     * @return The value in the appointmentLocation variable.
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    /**
     * The setAppointmentLocation method sets the Location of the Appointment.
     * @param appointmentLocation The string to be given to the Location of the Appointment.
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * The getAppointmentType method returns the Type of the Appointment.
     * @return The value in the appointmentType variable.
     */
    public String getAppointmentType() {
        return appointmentType;
    }
    /**
     * The setAppointmentType method sets the Type for the Appointment.
     * @param appointmentType The string to be given to the Type of the Appointment.
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * The getAppointmentStart method returns the Start Time of the Appointment.
     * @return The value in the appointmentStart variable.
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }
    /**
     * The setAppointmentStart method sets the Start Time for the Appointment.
     * @param appointmentStart The LocalDateTime to be given to the Start Time of the Appointment.
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * The getAppointmentStartStr method returns the Start Time of the Appointment in the form of a String.
     * @return The value in the appointmentStartStr variable.
     */
    public String getAppointmentStartStr() {
        return appointmentStartStr;
    }
    /**
     * The setAppointmentStartStr method sets the Start Time for the Appointment in the form of a String.
     * @param appointmentStartStr The string to be given to the String version of the Start Time of the Appointment.
     */
    public void setAppointmentStartStr(String appointmentStartStr) {
        this.appointmentStartStr = appointmentStartStr;
    }

    /**
     * The getAppointmentEnd method returns the End Time of the Appointment.
     * @return The value in the appointmentEnd variable.
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }
    /**
     * The setAppointmentEnd method sets the End Time of the Appointment.
     * @param appointmentEnd The LocalDateTime to be given to the End Time of the Appointment.
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * The getAppointmentEndStr method returns the End Time of the Appointment in the form of a String.
     * @return The value in the appointmentEndStr variable.
     */
    public String getAppointmentEndStr() {
        return appointmentEndStr;
    }
    /**
     * The setAppointmentEndStr method sets the End Time for the Appointment in the form of a String.
     * @param appointmentEndStr The string to be given to the String version of the End Time of the Appointment.
     */
    public void setAppointmentEndStr(String appointmentEndStr) {
        this.appointmentEndStr = appointmentEndStr;
    }

    /**
     * The getUserID method returns the ID of the Appointment's associated User.
     * @return The value in the userID variable.
     */
    public int getUserID() {
        return userID;
    }
    /**
     * The setUserID method sets the User ID associated with the Appointment.
     * @param userID The integer to be given to the user ID associated with the Appointment.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * The getCustomerID method returns the ID of the Appointment's associated Customer.
     * @return The value in the customerID variable.
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * The setCustomerID method sets the Customer ID associated with the Appointment.
     * @param customerID The integer to be given to the customer ID associated with the Appointment.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The getContactID method returns the ID of the Appointment's associated Contact.
     * @return The value in the contactID variable.
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * The setContactID method sets the Contact ID associated with the Appointment.
     * @param contactID The integer to be given to the contact ID associated with the Appointment.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}

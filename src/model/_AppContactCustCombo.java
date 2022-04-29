package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The _AppContactCustCombo class holds a combination of Appointment, Contact, Customer, and User objects and is used in the main form Appointments Table and for sending data to appointmentModifyController.
 * @author Mary Williams
 * @version 1
 */
public class _AppContactCustCombo {
    private Appointment appointment;
    private Contact contact;
    private Customer customer;
    private User user;
    //Appointment id, title, description, location, type, start and end
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDesc;
    private String appointmentLocation;
    private String appointmentType;
    //!!!Change to zoned date times?? to display correct !!!
    private LocalDateTime appointmentStart;
    private String appointmentStartStr;
    private LocalDateTime appointmentEnd;
    private String appointmentEndStr;
    //Contact id, name, email
    private int contactID;
    private String contactName;
    private String contactEmail;
    //Customer id
    private int customerID;
    private String customerName;

    //Constructor

    /**
     * The constructor creates a combination class of Appointment, Contact, Customer, and User, as well as setting variables to make it easier to put data into the Appointments Table.
     * @param appointment The Appointment object to be given to the Appointment/Contact/Customer/User Combo.
     * @param customer The Customer object to be given to the Appointment/Contact/Customer/User Combo.
     * @param contact The Contact object to be given to the Appointment/Contact/Customer/User Combo.
     * @param user The User object to be given to the Appointment/Contact/Customer/User Combo.
     */
    public _AppContactCustCombo(Appointment appointment, Customer customer, Contact contact, User user){
        this.appointment = appointment;
        this.customer = customer;
        this.contact = contact;
        this.user = user;

        this.appointmentID = appointment.getAppointmentID();
        this.appointmentTitle = appointment.getAppointmentTitle();
        this.appointmentDesc = appointment.getAppointmentDesc();
        this.appointmentLocation = appointment.getAppointmentLocation();
        this.appointmentType = appointment.getAppointmentType();
        this.appointmentStart = appointment.getAppointmentStart();
        this.appointmentStartStr = appointmentStart.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        this.appointmentEnd = appointment.getAppointmentEnd();
        this.appointmentEndStr = appointmentEnd.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        this.contactID = contact.getContactID();
        this.contactName = contact.getContactName();
        this.contactEmail = contact.getContactEmail();
        this.customerID = customer.getCustomerID();
        this.customerName = customer.getCustomerName();
    }

    //Getters and Setters
    /**
     * The getAppointment method returns the Appointment object from the combination.
     * @return The Appointment object in the appointment variable.
     */
    public Appointment getAppointment() {
        return appointment;
    }
    /**
     * The setAppointment method sets the Appointment object in the combination.
     * @param appointment The Appointment object to be give to the combination.
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * The getContact method returns the Contact object from the combination.
     * @return The Contact object in the contact variable.
     */
    public Contact getContact() {
        return contact;
    }
    /**
     * The setContact method sets the Contact object in the combination.
     * @param contact The Contact object to be given to the combination.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * The getCustomer method returns the Customer object from the combination.
     * @return The Customer object in the customer variable.
     */
    public Customer getCustomer() {
        return customer;
    }
    /**
     * The setCustomer method sets the Customer object in the combination.
     * @param customer The Customer object to be given to the combination.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * The getUser method returns the User object from the combination.
     * @return The User object in the user variable.
     */
    public User getUser() {
        return user;
    }
    /**
     * The setUser method sets the User in the combination.
     * @param user The User object to be given to the combination.
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * The getAppointmentID method returns the associated Appointment ID.
     * @return The value in the appointmentID variable.
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    /**
     * The setAppointmentID method sets the associated Appointment ID.
     * @param appointmentID The integer to be given to the appointmentID.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * The getAppointmentTitle method returns the associated Appointment Title.
     * @return The value in the appointmentTitle variable.
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    /**
     * The setAppointmentTitle method sets the associated Appointment Title.
     * @param appointmentTitle The string to be given to the appointmentTitle.
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * The getAppointmentDesc method returns the associated Appointment Description.
     * @return The value in the appointmentDesc variable.
     */
    public String getAppointmentDesc() {
        return appointmentDesc;
    }
    /**
     * The setAppointmentDesc method sets the associated Appointment Description.
     * @param appointmentDesc The string to be given to the appointmentDesc.
     */
    public void setAppointmentDesc(String appointmentDesc) {
        this.appointmentDesc = appointmentDesc;
    }

    /**
     * The getAppointmentLocation method returns the associated Appointment Location.
     * @return The value in the appointmentLocation variable.
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    /**
     * The setAppointmentLocation method sets the associated Appointment Location.
     * @param appointmentLocation The string to be given to the appointmentLocation.
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * The getAppointmentType method returns the associated Appointment Type.
     * @return The value in the appointmentType variable.
     */
    public String getAppointmentType() {
        return appointmentType;
    }
    /**
     * The setAppointmentType method sets the associated Appointment Type.
     * @param appointmentType The string to be given to the appointmentType.
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * The getAppointmentStart method returns the associated Appointment Start Time.
     * @return The value in the appointmentStart variable.
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }
    /**
     * The setAppointmentStart method sets the associated Appointment Start Time.
     * @param appointmentStart The LocalDateTime to be given to the appointmentStart.
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * The getAppointmentStartStr method returns the associated Appointment Start Time in the form of a String.
     * @return The value in the appointmentStartStr variable.
     */
    public String getAppointmentStartStr() {
        return appointmentStartStr;
    }
    /**
     * The setAppointmentStartStr method sets the associated Appointment Start Time in the form of a String.
     * @param appointmentStartStr The string to be given to the appointmentStartStr.
     */
    public void setAppointmentStartStr(String appointmentStartStr) {
        this.appointmentStartStr = appointmentStartStr;
    }

    /**
     * The getAppointmentEnd method returns the associated Appointment End Time.
     * @return The value in the appointmentEnd variable.
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }
    /**
     * The setAppointmentEnd method sets the associated Appointment End Time.
     * @param appointmentEnd The LocalDateTime to be given to the appointmentEnd.
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * The getAppointmentEndStr method returns the associated Appointment End Time in the form of a String.
     * @return The value in the appointmentEndStr variable.
     */
    public String getAppointmentEndStr() {
        return appointmentEndStr;
    }
    /**
     * The setAppointmentEndStr method sets the associated Appointment End Time in the form of a String.
     * @param appointmentEndStr The string to be given to the appointmentEndStr.
     */
    public void setAppointmentEndStr(String appointmentEndStr) {
        this.appointmentEndStr = appointmentEndStr;
    }

    /**
     * The getContactID method returns the associated Contact ID.
     * @return The value in the contactID variable.
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * The setContactID method sets the associated Contact ID.
     * @param contactID The integer to be given to the contactID.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getContactName method returns the associated Contact Name.
     * @return The value in the contactName variable.
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * The setContactName method sets the associated Contact Name.
     * @param contactName The string to be given to the contactName.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The getContactEmail method returns the associated Contact Email.
     * @return The value in the contactEmail variable.
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * The setContactEmail method sets the associated Contact Email.
     * @param contactEmail The string to be given to the contactEmail.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * The getCustomerID method returns the associated Customer ID.
     * @return The value in the customerID variable.
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * The setCustomerID method sets the associated Customer ID.
     * @param customerID The integer to be given to the customerID.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The getCustomerName method returns the associated Customer Name.
     * @return The value in the customerName variable.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * The setCustomerName method sets the associated Customer Name.
     * @param customerName The string to be given to the customerName.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

package controller;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import utility.TimeListsInterface;
import utility.TimeMethods;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * The appointmentAddController is used as the Controller for appointmentAddForm.fxml.
 * @author Mary Williams
 * @version 1
 */
public class appointmentAddController implements Initializable {
    //TextFields, DatePickers, and ComboBoxes
    @FXML
    private TextField appIDTF;
    @FXML
    private TextField appTitleTF;
    @FXML
    private TextField appDescTF;
    @FXML
    private TextField appLocationTF;
    @FXML
    private TextField appTypeTF;

    @FXML
    private DatePicker appStartPicker;
    @FXML
    private ComboBox<String> appStartHourCombo;
    @FXML
    private ComboBox<String> appStartMinCombo;

    @FXML
    private DatePicker appEndPicker;
    @FXML
    private ComboBox<String> appEndHourCombo;
    @FXML
    private ComboBox<String> appEndMinCombo;

    @FXML
    private ComboBox<Contact> appContactCombo;
    @FXML
    private ComboBox<Customer> appCustomerCombo;
    @FXML
    private ComboBox<User> appUserCombo;

    //Buttons
    @FXML
    private Button appAddSaveButton;
    @FXML
    private Button appAddCancelButton;


    //Initialize -- LAMBDA expressions here for getting Hour/Minute Time Lists
        //Saves on creating observable lists for strings elsewhere and making whole methods to get them for 2 simple uses each.
    /**
     * The initialize method is used to fill combo boxes with appropriate data.
     * <p>
     * <b>Lambda expression:</b>
     * A lambda expression is used in this method to simplify retrieving Observable Lists of Strings for hours and minutes.
     * The same TimeListsInterface lambda expression is used for both the hours and minutes time lists, receiving a parameter of Int listTo, which specifies how to make the Observable List which is then returned.
     * The code is made more readable, concise, and easier to change in the future if needed - allowing for both the code instead the lambda expression to change, or for each call to it to have a different Integer for the list where it is used.
     * The lambda expression creates a String Observable List with a for loop to iterate through zero to Int listTo, adding each number to the list before then returning it.
     * The appointmentModifyController uses a similar lambda expression to achieve the same thing.
     * </p>
     * Hour and minute combo boxes are filled with Strings to indicate each hour and minute.
     * Contact, Customer, and User combo boxes are filled with a list of all of each type relevant.
     * @param url Resource pointer used by initialize.
     * @param resourceBundle Locale-specific objects used by initialize.
     * @see appointmentModifyController Same lambda expressions used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lambda expression TimeListsInterface
        TimeListsInterface timeStrings = (listTo) -> {
            ObservableList<String> times = FXCollections.observableArrayList();
            for(int i = 0; i <= listTo; i++){
                String x ="";
                if(i < 10){
                    x = "0"+i;
                }
                else{
                    x = Integer.toString(i);
                }
                times.add(x);
            }
            return times;
        };

        //Start/End Hour ComboBoxes
        appStartHourCombo.setItems(timeStrings.timeList(23));
        appStartHourCombo.setVisibleRowCount(5);
        appEndHourCombo.setItems(timeStrings.timeList(23));
        appEndHourCombo.setVisibleRowCount(5);

        //Start/End Min ComboBoxes
        appStartMinCombo.setItems(timeStrings.timeList(59));
        appStartMinCombo.setVisibleRowCount(5);
        appEndMinCombo.setItems(timeStrings.timeList(59));
        appEndMinCombo.setVisibleRowCount(5);


        //Contact Combo
        ContactDao contactDao = new ContactImp();
        ObservableList<Contact> allContacts = contactDao.getAllContacts();

        appContactCombo.setItems(allContacts);
        appContactCombo.setVisibleRowCount(5);
        appContactCombo.setPromptText("Select...");

        //Customer Combo
        CustomerDao customerDao = new CustomerImp();
        ObservableList<Customer> allCustomers = customerDao.getAllCustomers();

        appCustomerCombo.setItems(allCustomers);
        appCustomerCombo.setVisibleRowCount(5);
        appCustomerCombo.setPromptText("Select...");

        //User Combo
        UserDao userDao = new UserImp();
        ObservableList<User> allUsers = userDao.getAllUsers();

        appUserCombo.setItems(allUsers);
        appUserCombo.setVisibleRowCount(5);
        appUserCombo.setPromptText("Select...");
    }

    //Save/Cancel Button Mouse Events
    /**
     * The appAddSave method checks that the user has input valid data into each Text Field, selected a Contact, Customer, and User, creates a new Appointment in the database, and returns to mainForm.fxml.
     * Data validation checks performed include checking if any fields are left empty, that each Text Field input is not too long for the database, and that appropriate appointment times are picked.
     * Appropriate appointment times include being within business hours of 8am - 10pm EST, as well as not overlapping with existing appointments for the selected Customer.
     * Dialog boxes with relevant error messages are given to the user if any input is invalid or not completed.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Save' Button this method is called.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void appAddSave(MouseEvent event) throws IOException {
        //Create variables to hold data
        String appointmentTitle;
        String appointmentDesc;
        String appointmentLocation;
        String appointmentType;

        int userID;
        int customerID;
        int contactID;


        //Check if any TextFields are empty - Do not save without everything filled out.
        if(appTitleTF.getText().trim().isEmpty() ||
            appDescTF.getText().trim().isEmpty() ||
            appLocationTF.getText().trim().isEmpty() ||
            appTypeTF.getText().trim().isEmpty() ||
            appStartPicker.getValue() == null ||
            appStartHourCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appStartMinCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appEndPicker.getValue() == null ||
            appEndHourCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appEndMinCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appContactCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appCustomerCombo.getSelectionModel().getSelectedIndex() < 0 ||
            appUserCombo.getSelectionModel().getSelectedIndex() < 0){
            JOptionPane.showMessageDialog(null, "Please input data for each field.");
            return;
        }

        //Input from TextFields
        //Title - 50varchar
        if(appTitleTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Title less than 50 characters long.");
            return;
        }
        else{
            appointmentTitle = appTitleTF.getText().trim();
        }

        //Description - 50varchar
        if(appDescTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Description less than 50 characters long.");
            return;
        }
        else{
            appointmentDesc = appDescTF.getText().trim();
        }

        //Location - 50varchar
        if(appLocationTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Location less than 50 characters long.");
            return;
        }
        else{
            appointmentLocation = appLocationTF.getText().trim();
        }

        //Type - 50varchar
        if(appTypeTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter an Appointment Type less than 50 characters long.");
            return;
        }
        else{
            appointmentType = appTypeTF.getText().trim();
        }

        //Associated Customer - ID
        Customer selectedCustomer = appCustomerCombo.getSelectionModel().getSelectedItem();
        customerID = selectedCustomer.getCustomerID();

        //Associated User - ID
        User selectedUser = appUserCombo.getSelectionModel().getSelectedItem();
        userID = selectedUser.getUserID();

        //Associated Contact - ID
        Contact selectedContact = appContactCombo.getSelectionModel().getSelectedItem();
        contactID = selectedContact.getContactID();


        //Start/End Dates, Times, and DateTime Variables
        LocalDate appointmentStartDate = appStartPicker.getValue();
        //System.out.println(appointmentStartDate);
        int appStartHour = Integer.parseInt(appStartHourCombo.getSelectionModel().getSelectedItem());
        //System.out.println(appStartHour);
        int appStartMin = Integer.parseInt(appStartMinCombo.getSelectionModel().getSelectedItem());
        //System.out.println(appStartMin);
        LocalTime appointmentStartTime = LocalTime.of(appStartHour,appStartMin);
        //System.out.println(appointmentStartTime);
        LocalDateTime appointmentStart = LocalDateTime.of(appointmentStartDate,appointmentStartTime);
        //System.out.println(appointmentStart);

        LocalDate appointmentEndDate = appEndPicker.getValue();
        //System.out.println(appointmentEndDate);
        int appEndHour = Integer.parseInt(appEndHourCombo.getSelectionModel().getSelectedItem());
        //System.out.println(appEndHour);
        int appEndMin = Integer.parseInt(appEndMinCombo.getSelectionModel().getSelectedItem());
        //System.out.println(appEndMin);
        LocalTime appointmentEndTime = LocalTime.of(appEndHour,appEndMin);
        //System.out.println(appointmentEndTime);
        LocalDateTime appointmentEnd = LocalDateTime.of(appointmentEndDate,appointmentEndTime);
        //System.out.println(appointmentEnd);

        //Check that Start Date is before End Date
        if(appointmentEndDate.isBefore(appointmentStartDate)||appointmentStartDate.isAfter(appointmentEndDate)){
            JOptionPane.showMessageDialog(null,"Appointment Start must come before Appointment End.");
            return;
        }

        //Check that Start and End DateTime to EST is same day - 'Full appointment within Business hours'
        if(TimeMethods.checkSameDay(appointmentStart,appointmentEnd)){
            LocalTime localBusStart = TimeMethods.getLocalBusinessStart();
            LocalTime localBusEnd = TimeMethods.getLocalBusinessEnd();
            JOptionPane.showMessageDialog(null, "Please make sure the full appointment is scheduled within business hours:\n 08:00-22:00 EST; "+ localBusStart.format(DateTimeFormatter.ofPattern("HH:mm"))+"-"+localBusEnd.format(DateTimeFormatter.ofPattern("HH:mm"))+" Local.");
            return;
        }

        //Check that Start Time is before End Time
        if(appointmentStartDate.isEqual(appointmentEndDate)){
            if(appointmentStartTime.isAfter(appointmentEndTime)||appointmentEndTime.isBefore(appointmentStartTime)){
                JOptionPane.showMessageDialog(null,"Appointment Start must come before Appointment End.");
                return;
            }
        }

        //If outside of business hours - Inform user of hours appointments need to be within.
        if(TimeMethods.checkOutsideBusHours(appointmentStart,appointmentEnd)){
            LocalTime localBusStart = TimeMethods.getLocalBusinessStart();
            LocalTime localBusEnd = TimeMethods.getLocalBusinessEnd();
            JOptionPane.showMessageDialog(null, "Please make sure appointment times are within business hours:\n 08:00-22:00 EST; "+ localBusStart.format(DateTimeFormatter.ofPattern("HH:mm"))+"-"+localBusEnd.format(DateTimeFormatter.ofPattern("HH:mm"))+" Local.");
            return;
        }
        //If conflicting appointments for customer - inform user.
        AppointmentDao appointmentDao = new AppointmentImp();
        if(!(appointmentDao.getConflictingAppointments(customerID,appointmentStart,appointmentEnd).isEmpty())){
            JOptionPane.showMessageDialog(null,"Selected appointment times conflict with existing appointments for the selected customer.");
            return;
        }


        //Create the appointment object - pass to AppointmentImp to create in database.
        Appointment createAppointment = new Appointment(0,appointmentTitle,appointmentDesc,appointmentLocation,appointmentType,appointmentStart,appointmentEnd,userID,customerID,contactID);
        appointmentDao.createAppointment(createAppointment);


        //Close and return to Main Form automatically when saved.
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Main Form");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * The appAddCancel method returns the user to mainForm.fxml.
     * This method checks if the user has input anything into the Text fields or selected anything from a Combo box, sending them a confirmation that they would like to return without saving the Appointment.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Cancel' Button this method is called.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void appAddCancel(MouseEvent event) throws IOException {
        //Check if anything has been put into the TextFields before attempting to close the form.
        if(!(appTitleTF.getText().trim().isEmpty() &&
                appDescTF.getText().trim().isEmpty() &&
                appLocationTF.getText().trim().isEmpty() &&
                appTypeTF.getText().trim().isEmpty() &&
                appStartPicker.getValue() == null &&
                appStartHourCombo.getSelectionModel().getSelectedItem() == null &&
                appStartMinCombo.getSelectionModel().getSelectedItem() == null &&
                appEndPicker.getValue() == null &&
                appEndHourCombo.getSelectionModel().getSelectedItem() == null &&
                appEndMinCombo.getSelectionModel().getSelectedItem() == null &&
                appContactCombo.getSelectionModel().getSelectedItem() == null &&
                appCustomerCombo.getSelectionModel().getSelectedItem() == null &&
                appUserCombo.getSelectionModel().getSelectedItem() == null)){
            int dialogResult = JOptionPane.showConfirmDialog(null, "Cancel without saving this Appointment?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(!(dialogResult == JOptionPane.YES_OPTION)){
                return;
            }
        }
        //Close - return back to main form
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Main Form");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

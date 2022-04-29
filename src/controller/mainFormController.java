package controller;


import dao.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The mainFormController is used as the Controller for mainForm.fxml.
 * @author Mary Williams
 * @version 1
 */
public class mainFormController implements Initializable {
    //Customer Tab Items
    @FXML
    private TableView<_CustDivCountryCombo> customerTableView;
    @FXML
    private TableColumn<?, ?> customerID;
    @FXML
    private TableColumn<?, ?> customerName;
    @FXML
    private TableColumn<?, ?> customerAddress;
    @FXML
    private TableColumn<?, ?> customerPostCode;
    @FXML
    private TableColumn<?, ?> customerPhone;
    @FXML
    private TableColumn<?, ?> customerDivID;
    @FXML
    private TableColumn<?, ?> customerDivName;

    @FXML
    private Button customerAddButton;
    @FXML
    private Button customerModifyButton;
    @FXML
    private Button customerDeleteButton;

    //Appointment Tab Items
    @FXML
    private TableView<_AppContactCustCombo> appointmentTableView;
    @FXML
    private TableColumn<?, ?> appointmentID;
    @FXML
    private TableColumn<?, ?> appointmentTitle;
    @FXML
    private TableColumn<?, ?> appointmentDesc;
    @FXML
    private TableColumn<?, ?> appointmentLocation;
    @FXML
    private TableColumn<?, ?> appointmentType;
    @FXML
    private TableColumn<?, ?> appointmentContID;
    @FXML
    private TableColumn<?, ?> appointmentContName;
    @FXML
    private TableColumn<?, ?> appointmentContEmail;
    @FXML
    private TableColumn<?, ?> appointmentStart;
    @FXML
    private TableColumn<?, ?> appointmentEnd;
    @FXML
    private TableColumn<?, ?> appointmentCustID;

    @FXML
    private ToggleGroup appointmentFilter;
    @FXML
    private RadioButton allRadButton;
    @FXML
    private RadioButton monthRadButton;
    @FXML
    private RadioButton weekRadButton;

    @FXML
    private Button appointmentAddButton;
    @FXML
    private Button appointmentModifyButton;
    @FXML
    private Button appointmentDeleteButton;

    //Report Tab Items
    @FXML
    private TableView<MonthTypeCount> reportAppTableView;
    @FXML
    private TableColumn<?, ?> reportAppMonth;
    @FXML
    private TableColumn<?, ?> reportAppType;
    @FXML
    private TableColumn<?, ?> reportAppCount;

    @FXML
    private TableView<Appointment> reportScheduleTableView;
    @FXML
    private TableColumn<?, ?> reportScheduleAppID;
    @FXML
    private TableColumn<?, ?> reportScheduleTitle;
    @FXML
    private TableColumn<?, ?> reportScheduleType;
    @FXML
    private TableColumn<?, ?> reportScheduleDesc;
    @FXML
    private TableColumn<?, ?> reportScheduleStart;
    @FXML
    private TableColumn<?, ?> reportScheduleEnd;
    @FXML
    private TableColumn<?, ?> reportScheduleCustID;

    @FXML
    private ComboBox<Contact> reportSchedulePicker;

    @FXML
    private TableView<CountryDivCount> reportCustomerTableView;
    @FXML
    private TableColumn<?, ?> reportCustomerCountry;
    @FXML
    private TableColumn<?, ?> reportCustomerDivision;
    @FXML
    private TableColumn<?, ?> reportCustomerCount;

    @FXML
    private ComboBox<Country> reportCustomerPicker;


    //Main Initialize
    /**
     * The initialize method is used to fill tables and combo boxes with appropriate data.
     * It starts the Customer and Appointment tables with all data related to each, not filtering to anything specific.
     * The 'Number of Appointments by Type and Month in the Current Year' table is filled with a count of each type of Appointment within each Month of the current year.
     * Table placeholders are set for each table in case there are no customers or appointments to fill related tables.
     * Combo box for report 'Appointments Schedule by Contact' is filled with a list of all Contacts within the organization to allow selection of which schedule to view.
     * Combo box for report 'Number of Customers by Country and Division' is filled with a list of all Countries within the database to allow selection of which count of customers per division to view.
     * @param url Resource pointer used by initialize.
     * @param resourceBundle Locale-specific objects used by initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //Customer Tab - TableView
        _CustComboDao customerRecords = new _CustComboImp();
        ObservableList<_CustDivCountryCombo> allCustomerRecords = customerRecords.getCustomerTable();

        customerTableView.setItems(allCustomerRecords);

        customerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        customerPostCode.setCellValueFactory(new PropertyValueFactory<>("CustomerPostCode"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        customerDivID.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
        customerDivName.setCellValueFactory(new PropertyValueFactory<>("DivisionName"));

        customerTableView.setPlaceholder(new Label("There are no customers in the database."));


        //Appointment Tab - TableView
        _AppComboDao appointmentRecords = new _AppComboImp();
        ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getAppointmentTable();

        appointmentTableView.setItems(allAppointmentRecords);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("AppointmentTitle"));
        appointmentDesc.setCellValueFactory(new PropertyValueFactory<>("AppointmentDesc"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("AppointmentLocation"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        appointmentContID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        appointmentContName.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        appointmentContEmail.setCellValueFactory(new PropertyValueFactory<>("ContactEmail"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("AppointmentStartStr"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("AppointmentEndStr"));
        appointmentCustID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        appointmentTableView.setPlaceholder(new Label("There are no appointments in the database."));


        //Report Tab - TableViews
        //reportAppTableView
        reportAppTableView.setPlaceholder(new Label("There are no appointments in the database for the current year."));

        MonthTypeCountDao monthTypeCountDao = new MonthTypeCountImp();
        ObservableList<MonthTypeCount> appointmentsByMTRecords = monthTypeCountDao.getReportAppCountTable();

        reportAppTableView.setItems(appointmentsByMTRecords);

        reportAppMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        reportAppType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        reportAppCount.setCellValueFactory(new PropertyValueFactory<>("Count"));

        //reportScheduleTableView placeholder -- initialize reportSchedulePicker
        reportScheduleTableView.setPlaceholder(new Label("Please select a contact to view their schedule."));

        ContactDao contactDao = new ContactImp();
        ObservableList<Contact> allContacts = contactDao.getAllContacts();
        reportSchedulePicker.setItems(allContacts);
        reportSchedulePicker.setVisibleRowCount(5);
        reportSchedulePicker.setPromptText("Select...");

        //reportCustomerTableView placeholder -- initialize reportCustomerPicker
        reportCustomerTableView.setPlaceholder(new Label("Please select a country to view the number of customers by division."));

        CountryDao countryDao = new CountryImp();
        ObservableList<Country> allCountries = countryDao.getAllCountries();
        reportCustomerPicker.setItems(allCountries);
        reportCustomerPicker.setVisibleRowCount(5);
        reportCustomerPicker.setPromptText("Select...");

    }


    //Customer Tab functionality
    //Buttons
    /**
     * The customerAdd method opens the customerAddForm.fxml, allowing the user to add customers to the database.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Add' Button within the Customers Tab this method is called.
     * @exception IOException Failed to load customerAddForm.fxml.
     */
    @FXML
    void customerAdd(MouseEvent event) throws IOException {
        //Switch to Add Customer Form
        Parent parent = FXMLLoader.load(getClass().getResource("../view/customerAddForm.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Add Customer Form");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The customerModify method opens the customerModifyForm.fxml and passes the selected customer to modify to the customerModifyController.
     * If there are no customers in the database to select from, or if there is nothing selected, this method instead shows a dialog box with a related error message.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Modify' Button within the Customers Tab this method is called.
     * @exception IOException Failed to load customerModifyForm.fxml.
     */
    @FXML
    void customerModify(MouseEvent event) throws IOException {
        CustomerDao customerDao = new CustomerImp();
        _CustDivCountryCombo selectedCustomerCombo = customerTableView.getSelectionModel().getSelectedItem();

        if(customerDao.getAllCustomers().isEmpty()){
            JOptionPane.showMessageDialog(null,"There are no customers in the database to modify.");
            return;
        }
        else if(selectedCustomerCombo == null){
            JOptionPane.showMessageDialog(null,"Please select a customer to modify.");
            return;
        }
        else{
            //Switch to Modify Customer Form - Passing selectedCustomerCombo as parameter
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/customerModifyForm.fxml"));
            customerModifyController controller = new customerModifyController(selectedCustomerCombo);
            loader.setController(controller);
            Parent parent = loader.load();

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.hide();
            primaryStage.setScene(new Scene(parent));
            primaryStage.setTitle("Modify Customer Form");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }

    /**
     * The customerDelete method confirms that the user wants to delete the selected customer and gives the user a dialog box informing them if it succeeded or failed to delete the customer.
     * If there are no customers in the database to select from, or if there is nothing selected, this method instead shows a dialog box with a related error message.
     * If the selected customer has associated appointments, it notifies the user of this and asks for confirmation again.
     * This method displays customer information, including ID and Name when asking the user for confirmation to delete and when showing a 'successful delete' or 'failed to delete' dialog box.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Delete' Button within the Customers Tab this method is called.
     */
    @FXML
    void customerDelete(MouseEvent event) {
        CustomerDao customerDao = new CustomerImp();
        _CustDivCountryCombo selectedCustomerCombo = customerTableView.getSelectionModel().getSelectedItem();

        if(customerDao.getAllCustomers().isEmpty()){
            JOptionPane.showMessageDialog(null, "There are no Customers in the database to delete.");
            return;
        }
        else if(selectedCustomerCombo == null){
            JOptionPane.showMessageDialog(null, "Please select a Customer to delete.");
            return;
        }
        else{
            int customerID = selectedCustomerCombo.getCustomerID();
            String customerName = selectedCustomerCombo.getCustomerName();

            int dialogResultA = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Customer ID#: "+customerID+"; "+customerName+"?","Confirm",JOptionPane.YES_NO_OPTION);
            if(dialogResultA == JOptionPane.NO_OPTION){
                return;
            }
            else{
                AppointmentDao appointmentDao = new AppointmentImp();
                if(!(appointmentDao.getAppsByCustomer(customerID).isEmpty())){
                    int dialogResultB = JOptionPane.showConfirmDialog(null, "Customer ID#: "+customerID+"; "+customerName+" has associated appointments, are you still sure you want to delete the customer?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if(dialogResultB == JOptionPane.NO_OPTION){
                        return;
                    }
                    else{
                        appointmentDao.deleteAppsByCustomer(customerID);
                    }
                }

                if(customerDao.deleteCustomer(customerID)){
                    JOptionPane.showMessageDialog(null, "Customer ID#: "+customerID+"; "+customerName+" deleted.");;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to delete Customer ID#: "+customerID+"; "+customerName+".");
                }

                //Refresh customer table view to updated customer list.
                _CustComboDao customerRecords = new _CustComboImp();
                ObservableList<_CustDivCountryCombo> allCustomerRecords = customerRecords.getCustomerTable();

                customerTableView.setItems(allCustomerRecords);

                //Refresh appointment table view to updated appointment list.
                _AppComboDao appointmentRecords = new _AppComboImp();
                ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getAppointmentTable();

                appointmentTableView.setItems(allAppointmentRecords);
                allRadButton.setSelected(true);

                //Refresh appointments by month and type report
                MonthTypeCountDao monthTypeCountDao = new MonthTypeCountImp();
                ObservableList<MonthTypeCount> appointmentsByMTRecords = monthTypeCountDao.getReportAppCountTable();

                reportAppTableView.setItems(appointmentsByMTRecords);

                //Refresh schedule report
                reportScheduleTableView.setItems(null);
                reportScheduleTableView.setPlaceholder(new Label("Please select a contact to view their schedule."));

                //Refresh customers report
                reportCustomerTableView.setItems(null);
                reportCustomerTableView.setPlaceholder(new Label("Please select a country to view the number of customers by division."));
            }
        }
    }


    //Appointment Tab functionality
    //Rad Buttons
    /**
     * The appointmentAllFilter method fills the Appointment table with data of all appointments.
     * Table placeholder is set in case there are no appointments to display.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'All' Radio Button above the Appointments table within the Appointments Tab this method is called.
     */
    @FXML
    void appointmentAllFilter(MouseEvent event) {
        _AppComboDao appointmentRecords = new _AppComboImp();
        ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getAppointmentTable();

        appointmentTableView.setItems(allAppointmentRecords);
        appointmentTableView.setPlaceholder(new Label("There are no appointments in the database."));
    }

    /**
     * The appointmentMonthFilter method fills the Appointment table with data of appointments between the current time and a month from now.
     * Table placeholder is set in case there are no appointments within the next month to display.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Within the Next Month' Radio Button above the Appointments table within the Appointments Tab this method is called.
     */
    @FXML
    void appointmentMonthFilter(MouseEvent event) {
        _AppComboDao appointmentRecords = new _AppComboImp();
        ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getMonthAppointmentTable();

        appointmentTableView.setItems(allAppointmentRecords);
        appointmentTableView.setPlaceholder(new Label("There are no appointments within the next month."));
    }

    /**
     * The appointmentWeekFilter method fills the Appointment table with data of appointments between the current time and a week from now.
     * Table placeholder is set in case there are no appointments within the next week to display.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Within the Next Week' Radio Button above the Appointments table within the Appointments Tab this method is called.
     */
    @FXML
    void appointmentWeekFilter(MouseEvent event) {
        _AppComboDao appointmentRecords = new _AppComboImp();
        ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getWeekAppointmentTable();

        appointmentTableView.setItems(allAppointmentRecords);
        appointmentTableView.setPlaceholder(new Label("There are no appointments within the next week."));
    }

    //Buttons
    /**
     * The appointmentAdd method opens the appointmentAddForm.fxml, allowing the user to add appointments to the database.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Add' Button within the Appointments Tab this method is called.
     * @exception IOException Failed to load appointmentAddForm.fxml.
     */
    @FXML
    void appointmentAdd(MouseEvent event) throws IOException {
        //Switch to Add Appointment Form
        Parent parent = FXMLLoader.load(getClass().getResource("../view/appointmentAddForm.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Add Appointment Form");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The appointmentModify method opens the appointmentModifyForm.fxml, and passes the selected appointment to modify to the appointmentModifyController.
     * If there are no appointments in the database to select from, or if there is nothing selected, this method instead shows a dialog box with a related error message.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Modify' Button within the Appointments Tab this method is called.
     * @exception IOException Failed to load appointmentModifyForm.fxml.
     */
    @FXML
    void appointmentModify(MouseEvent event) throws IOException {
        AppointmentDao appointmentDao = new AppointmentImp();
        _AppContactCustCombo selectedAppointmentCombo = appointmentTableView.getSelectionModel().getSelectedItem();

        if(appointmentDao.getAllAppointments().isEmpty()){
            JOptionPane.showMessageDialog(null,"There are no appointments in the database to modify.");
            return;
        }
        else if(selectedAppointmentCombo == null){
            JOptionPane.showMessageDialog(null,"Please select an appointment to modify.");
            return;
        }
        else{
            //Switch to Modify Appointment Form - Passing selectedAppointmentCombo as parameter
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/appointmentModifyForm.fxml"));
            appointmentModifyController controller = new appointmentModifyController(selectedAppointmentCombo);
            loader.setController(controller);
            Parent parent = loader.load();

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.hide();
            primaryStage.setScene(new Scene(parent));
            primaryStage.setTitle("Modify Appointment Form");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }

    /**
     * The appointmentDelete method confirms that the user wants to delete the selected appointment and gives the user a dialog box informing them if it succeeded or failed to delete the customer.
     * If there are no appointments in the database to select from, or if there is nothing selected, this method instead shows a dialog box with a related error message.
     * This method displays appointment information, including ID, Type, and Title when asking the user for confirmation to delete and when showing a 'successful delete' or 'failed to delete' dialog box.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Delete' Button within the Appointments Tab this method is called.
     */
    @FXML
    void appointmentDelete(MouseEvent event) {
        AppointmentDao appointmentDao = new AppointmentImp();
        _AppContactCustCombo selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();

        if(appointmentDao.getAllAppointments().isEmpty()){
            JOptionPane.showMessageDialog(null, "There are no Appointments in the database to delete.");
            return;
        }
        else if(selectedAppointment == null){
            JOptionPane.showMessageDialog(null, "Please select an Appointment to delete.");
            return;
        }
        else{
            int appointmentID = selectedAppointment.getAppointmentID();
            String appointmentType = selectedAppointment.getAppointmentType();
            String appointmentTitle = selectedAppointment.getAppointmentTitle();

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel Appointment ID#: "+appointmentID+"; "+appointmentType+", "+appointmentTitle+"?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                if(appointmentDao.deleteAppointment(appointmentID)){
                    JOptionPane.showMessageDialog(null, "Appointment ID#: "+appointmentID+"; "+appointmentType+", "+appointmentTitle+" deleted.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to delete Appointment ID#: "+appointmentID+"; "+appointmentType+", "+appointmentTitle+".");
                }

                //Refresh appointment table view to updated appointment list.
                _AppComboDao appointmentRecords = new _AppComboImp();
                ObservableList<_AppContactCustCombo> allAppointmentRecords = appointmentRecords.getAppointmentTable();

                appointmentTableView.setItems(allAppointmentRecords);
                allRadButton.setSelected(true);

                //Refresh appointments by month and type report
                MonthTypeCountDao monthTypeCountDao = new MonthTypeCountImp();
                ObservableList<MonthTypeCount> appointmentsByMTRecords = monthTypeCountDao.getReportAppCountTable();

                reportAppTableView.setItems(appointmentsByMTRecords);

                //Refresh schedule report
                reportScheduleTableView.setItems(null);
                reportScheduleTableView.setPlaceholder(new Label("Please select a contact to view their schedule."));

                //Refresh customers report
                reportCustomerTableView.setItems(null);
                reportCustomerTableView.setPlaceholder(new Label("Please select a country to view the number of customers by division."));
            }
            else{
                return;
            }
        }
    }


    //Report Tab functionality
    //ComboBoxes
    /**
     * The reportSchedulePicked method fills the 'Appointments Schedule by Contact' table with a schedule of appointments for the selected Contact organized by Start date.
     * Table placeholder is set in case the selected Contact has to appointments.
     * @param event The ActionEvent that triggers this method. When the user picks a Contact from the Combo box above the 'Appointments Schedule by Contact' table within the Reports Tab this method is called.
     */
    @FXML
    void reportSchedulePicked(ActionEvent event) {
        Contact selectedContact = reportSchedulePicker.getSelectionModel().getSelectedItem();
        int contactID = selectedContact.getContactID();

        AppointmentDao appointmentDao = new AppointmentImp();
        ObservableList<Appointment> appointmentsByContact = appointmentDao.getReportScheduleTable(contactID);

        reportScheduleTableView.setPlaceholder(new Label("Selected contact has no appointments."));
        reportScheduleTableView.setItems(appointmentsByContact);

        reportScheduleAppID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        reportScheduleTitle.setCellValueFactory(new PropertyValueFactory<>("AppointmentTitle"));
        reportScheduleType.setCellValueFactory(new PropertyValueFactory<>("AppointmentType"));
        reportScheduleDesc.setCellValueFactory(new PropertyValueFactory<>("AppointmentDesc"));
        reportScheduleStart.setCellValueFactory(new PropertyValueFactory<>("AppointmentStartStr"));
        reportScheduleEnd.setCellValueFactory(new PropertyValueFactory<>("AppointmentEndStr"));
        reportScheduleCustID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    }

    /**
     * The reportCustomerPicked method fills the 'Number of Customers by Country and Division' table with a count of customers in each division for the selected Country.
     * Table placeholder is set in case the selected Country has no customers.
     * @param event The ActionEvent that triggers this method. When the user picks a Country from the Combo box above the 'Number of Customers by Country and Division' table within the Reports Tab this method is called.
     */
    @FXML
    void reportCustomerPicked(ActionEvent event) {
        Country selectedCountry = reportCustomerPicker.getSelectionModel().getSelectedItem();
        int countryID = selectedCountry.getCountryID();

        CountryDivCountDao countryDivCountDao = new CountryDivCountImp();
        ObservableList<CountryDivCount> customersByCDCount = countryDivCountDao.getReportCustomerCountTable(countryID);

        reportCustomerTableView.setPlaceholder(new Label("There are no customers in that country."));
        reportCustomerTableView.setItems(customersByCDCount);

        reportCustomerCountry.setCellValueFactory(new PropertyValueFactory<>("Country"));
        reportCustomerDivision.setCellValueFactory(new PropertyValueFactory<>("Division"));
        reportCustomerCount.setCellValueFactory(new PropertyValueFactory<>("Count"));
    }
}

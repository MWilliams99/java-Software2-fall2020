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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import model._CustDivCountryCombo;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The customerModifyController is used as the Controller for customerModifyForm.fxml.
 * @author Mary Williams
 * @version 1
 */
public class customerModifyController implements Initializable {
    //TextField and ComboBoxes
    @FXML
    private TextField customerIDTF;
    @FXML
    private TextField customerNameTF;
    @FXML
    private TextField customerAddressTF;
    @FXML
    private TextField customerPostCodeTF;
    @FXML
    private TextField customerPhoneTF;
    @FXML
    private ComboBox<Country> customerCountryCombo;
    @FXML
    private ComboBox<Division> customerDivCombo;

    //Buttons
    @FXML
    private Button customerModSaveButton;
    @FXML
    private Button customerModCancelButton;


    //Receive selectedCustomerCombo from MainForm
    _CustDivCountryCombo customerComboToModify;
    /**
     * The customerModifyController method is used to create a _CustDivCountryCombo within the Controller to work with as modifications are made.
     * @param selectedCustomerCombo The Customer selected in mainForm.fxml that is passed when customerModifyController is called, used to initialize Text Fields, selected values in Combo boxes, and gather the Customer ID.
     */
    public customerModifyController(_CustDivCountryCombo selectedCustomerCombo){
        customerComboToModify = selectedCustomerCombo;
    }

    //Initialize - Input into TextFields data from selectedCustomerCombo
    /**
     * The initialize method takes data from the customerComboToModify to initialize values into the Text Fields and determine which values are selected in the Combo boxes.
     * This method fills the Country combo box to all Countries and the Division Combo box to a list of divisions in the selected Country.
     * @param url Resource pointer used by initialize.
     * @param resourceBundle Locale-specific objects used by initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Customer: ID, Name, Address, PostCode, Phone, Country, Division
        int customerID = customerComboToModify.getCustomerID();
        String customerName = customerComboToModify.getCustomerName();
        String customerAddress = customerComboToModify.getCustomerAddress();
        String customerPostCode = customerComboToModify.getCustomerPostCode();
        String customerPhone = customerComboToModify.getCustomerPhone();
        Country selectedCountry = customerComboToModify.getCountry();
        int countryID = customerComboToModify.getCountryID();
        Division selectedDivision = customerComboToModify.getFld();

        //Input into TextFields
        customerIDTF.setText(Integer.toString(customerID));
        customerNameTF.setText(customerName);
        customerAddressTF.setText(customerAddress);
        customerPostCodeTF.setText(customerPostCode);
        customerPhoneTF.setText(customerPhone);

        //Country ComboBox
        CountryDao countryDao = new CountryImp();
        ObservableList<Country> allCountries = countryDao.getAllCountries();
        customerCountryCombo.setItems(allCountries);
        customerCountryCombo.setVisibleRowCount(5);
        customerCountryCombo.getSelectionModel().select(selectedCountry);

        //Division ComboBox
        DivisionDao divisionDao = new DivisionImp();
        ObservableList<Division> divsByCountry = divisionDao.getDivsByCountry(countryID);
        customerDivCombo.setItems(divsByCountry);
        customerDivCombo.setVisibleRowCount(5);
        customerDivCombo.getSelectionModel().select(selectedDivision);
    }

    //Adjust Division ComboBox when Country is picked
    /**
     * The customerCountryPicked method filters the Division Combo box to a list of divisions in the selected Country.
     * @param event The ActionEvent that triggers this method. When the user picks a Country from the Country Combo box this method is called.
     */
    @FXML
    void customerCountryPicked(ActionEvent event) {
        Country selectedCountry = customerCountryCombo.getSelectionModel().getSelectedItem();
        int countryID = selectedCountry.getCountryID();
        DivisionDao divisionDao = new DivisionImp();
        ObservableList<Division> divsByCountry = divisionDao.getDivsByCountry(countryID);

        customerDivCombo.setItems(divsByCountry);
        customerDivCombo.getSelectionModel().select(null);
        customerDivCombo.setVisibleRowCount(5);
        customerDivCombo.setPromptText("Select...");
    }

    //Save/Cancel Button Mouse Events
    /**
     * The customerModSave method checks that the user has input valid data into each Text Field and selected a Country and Division, updates the Customer in the database, and returns to mainForm.fxml.
     * Data validation checks performed include checking if any fields are left empty, and that each Text Field input is not too long for the database.
     * Dialog boxes with relevant error messages are given to the user if any input is invalid or not completed.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Save' Button this method is called.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void customerModSave(MouseEvent event) throws IOException {
        int customerID = customerComboToModify.getCustomerID();
        String customerName;
        String customerAddress;
        String customerPostCode;
        String customerPhone;

        //Check if any TextFields or ComboBoxes are empty - Do not save without everything filled out.
        if(customerNameTF.getText().trim().isEmpty() ||
                customerAddressTF.getText().trim().isEmpty() ||
                customerPostCodeTF.getText().trim().isEmpty() ||
                customerPhoneTF.getText().trim().isEmpty() ||
                customerCountryCombo.getSelectionModel().getSelectedItem() == null ||
                customerDivCombo.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Please input data for each field.");
            return;
        }

        //Input from TextFields into each String - 50 Name, 100 Address, 50 PostCode, 50 Phone
        //Customer Name - 50varchar
        if(customerNameTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Name less than 50 characters long.");
            return;
        }
        else{
            customerName = customerNameTF.getText().trim();
        }

        //Customer Address - 100varchar
        if(customerAddressTF.getText().trim().length() > 100){
            JOptionPane.showMessageDialog(null,"Please enter an Address less than 100 characters long.");
            return;
        }
        else{
            customerAddress = customerAddressTF.getText().trim();
        }

        //Customer PostCode - 50varchar
        if(customerPostCodeTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Post Code less than 50 characters long.");
            return;
        }
        else{
            customerPostCode = customerPostCodeTF.getText().trim();
        }

        //Customer Phone - 50varchar
        if(customerPhoneTF.getText().trim().length() > 50){
            JOptionPane.showMessageDialog(null,"Please enter a Phone Number less than 50 characters long.");
            return;
        }
        else{
            customerPhone = customerPhoneTF.getText().trim();
        }

        //Get DivisionID from customerDivCombo
        Division selectedDivision = customerDivCombo.getSelectionModel().getSelectedItem();
        int divisionID = selectedDivision.getDivisionID();

        //Create the customer object - pass to CustomerImp to update in database.
        Customer updateCustomer = new Customer(customerID,customerName,customerAddress,customerPostCode,customerPhone,divisionID);
        CustomerDao customerDao = new CustomerImp();
        customerDao.updateCustomer(customerID,updateCustomer);

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
     * The customerModCancel method returns the user to mainForm.fxml.
     * This method confirms that the user would like to return without modifying the Customer.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Cancel' Button this method is called.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void customerModCancel(MouseEvent event) throws IOException {
        //Check if anything has been input into the text fields before attempting to close the form.
        Country selectedCountry = customerCountryCombo.getSelectionModel().getSelectedItem();
        Division selectedDivision = customerDivCombo.getSelectionModel().getSelectedItem();
        if(!(customerIDTF.getText().trim().isEmpty() &&
                customerNameTF.getText().trim().isEmpty() &&
                customerAddressTF.getText().trim().isEmpty() &&
                customerPostCodeTF.getText().trim().isEmpty() &&
                customerPhoneTF.getText().trim().isEmpty() &&
                selectedCountry == null &&
                selectedDivision == null)){
            int dialogResult = JOptionPane.showConfirmDialog(null, "Cancel without modifying this Customer?", "Confirm", JOptionPane.YES_NO_OPTION);
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

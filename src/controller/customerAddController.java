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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The customerAddController is used as the Controller for customerAddForm.fxml.
 * @author Mary Williams
 * @version 1
 */
public class customerAddController implements Initializable {
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
    private Button customerAddSaveButton;
    @FXML
    private Button customerAddCancelButton;


    //Initialize
    /**
     * The initialize method is used to fill the Country combo box with all Countries and disables the Division combo box until a Country is picked.
     * @param url Resource pointer used by initialize.
     * @param resourceBundle Locale-specific objects used by initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDao countryDao = new CountryImp();
        ObservableList<Country> allCountries = countryDao.getAllCountries();

        customerCountryCombo.setItems(allCountries);
        customerCountryCombo.setVisibleRowCount(5);
        customerCountryCombo.setPromptText("Select...");

        customerDivCombo.setDisable(true);
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

        customerDivCombo.setDisable(false);
        customerDivCombo.setItems(divsByCountry);
        customerDivCombo.setPromptText("Select...");
        customerDivCombo.getSelectionModel().select(null);
        customerDivCombo.setVisibleRowCount(5);

    }

    //Save/Cancel Button Mouse Events
    /**
     * The customerAddSave method checks that the user has input valid data into each Text Field and selected a Country and Division, creates a new Customer in the database, and returns to mainForm.fxml.
     * Data validation checks performed include checking if any fields are left empty, and that each Text Field input is not too long for the database.
     * Dialog boxes with relevant error messages are given to the user if any input is invalid or not completed.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Save' Button this method is called.
     * @exception IOException Failed to load maniForm.fxml.
     */
    @FXML
    void customerAddSave(MouseEvent event) throws IOException {
        String customerName;
        String customerAddress;
        String customerPostCode;
        String customerPhone;

        //Check if any TextFields or ComboBoxes are empty - Do not save without everything filled out.
        if(customerNameTF.getText().trim().isEmpty() ||
                customerAddressTF.getText().trim().isEmpty() ||
                customerPostCodeTF.getText().trim().isEmpty() ||
                customerPhoneTF.getText().trim().isEmpty() ||
                customerCountryCombo.getSelectionModel().getSelectedIndex() < 0 ||
                customerDivCombo.getSelectionModel().getSelectedIndex() < 0){
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

        //Create the customer object - pass to CustomerImp to create in database.
        Customer createCustomer = new Customer(0,customerName,customerAddress,customerPostCode,customerPhone,divisionID);
        CustomerDao customerDao = new CustomerImp();
        customerDao.createCustomer(createCustomer);

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
     * The customerAddCancel method returns the user to mainForm.fxml.
     * This method checks if the user has input anything into the Text fields or selected anything from a Combo box, sending them a confirmation that they would like to return without saving the Customer.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Cancel' Button this method is called.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void customerAddCancel(MouseEvent event) throws IOException {
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
            int dialogResult = JOptionPane.showConfirmDialog(null, "Cancel without saving this Customer?", "Confirm", JOptionPane.YES_NO_OPTION);
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

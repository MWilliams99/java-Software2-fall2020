package model;

/**
 * The _CustDivCountryCombo class holds a combination of Customer, Division, and Country objects and is used in the main form Customers Table and for sending data to customerModifyController.
 * @author Mary Williams
 * @version 1
 */
public class _CustDivCountryCombo {
    private Customer customer;
    private Division fld;
    private Country country;
    //Customer id, name, address, postcode, phone
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostCode;
    private String customerPhone;
    //Division id, name
    private int divisionID;
    private String divisionName;
    //Country id
    private int countryID;


    //Constructor
    /**
     * The constructor creates a combination class of Customer, Division, and Country, as well as setting variables to make it easier to put data into the Customers Table.
     * @param customer The Customer object to be given to the Customer/Division/Country Combo.
     * @param fld The Division object to be given to the Customer/Division/Country Combo.
     * @param country The Country object to be given to the Customer/Division/Country Combo.
     */
    public _CustDivCountryCombo(Customer customer, Division fld, Country country) {
        this.customer = customer;
        this.fld = fld;
        this.country = country;

        this.customerID = customer.getCustomerID();
        this.customerName = customer.getCustomerName();
        this.customerAddress = customer.getCustomerAddress();
        this.customerPostCode = customer.getCustomerPostCode();
        this.customerPhone = customer.getCustomerPhone();
        this.divisionID = fld.getDivisionID();
        this.divisionName = fld.getDivisionName();
        this.countryID = country.getCountryID();
    }

    //Getters and Setters
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
     * The getFld method returns the Division object from the combination.
     * @return The Division object in the fld variable.
     */
    public Division getFld() {
        return fld;
    }
    /**
     * The setFld method sets the Division object in the combination.
     * @param fld The Division object to be given to the combination.
     */
    public void setFld(Division fld) {
        this.fld = fld;
    }

    /**
     * The getCountry method returns the Country object from the combination.
     * @return The Country object in the country variable.
     */
    public Country getCountry() {
        return country;
    }
    /**
     * The setCountry method sets the Country object in the combination.
     * @param country The Country object to be given to the combination.
     */
    public void setCountry(Country country) {
        this.country = country;
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

    /**
     * The getCustomerAddress method returns the associated Customer Address.
     * @return The value in the customerAddress variable.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    /**
     * The setCustomerAddress sets the associated Customer Address.
     * @param customerAddress The string to be given to the customerAddress.
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * The getCustomerPostCode method returns the associated Customer Post Code.
     * @return The value in the customerPostCode variable.
     */
    public String getCustomerPostCode() {
        return customerPostCode;
    }
    /**
     * The setCustomerPostCode method sets the associated Customer Post Code.
     * @param customerPostCode The string to be given to the customerPostCode.
     */
    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    /**
     * The getCustomerPhone method returns the associated Customer Phone Number.
     * @return The value in the customerPhone variable.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /**
     * The setCustomerPhone method sets the associated Customer Phone Number.
     * @param customerPhone The string to be given to the customerPhone.
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * The getDivisionID method returns the associated Division ID.
     * @return The value in the divisionID variable.
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * The setDivisionID method sets the associated Division ID.
     * @param divisionID The integer to be given to the divisionID.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The getDivisionName method returns the associated Division Name.
     * @return The value in the divisionName variable.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * The setDivisionName method sets the associated Division Name.
     * @param divisionName The string to be given to the divisionName.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * The getCountryID method returns the associated Country ID.
     * @return The value in the countryID variable.
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * The setCountryID method sets the associated Country ID.
     * @param countryID The integer to be given to the countryID.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}

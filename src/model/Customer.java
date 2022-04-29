package model;

/**
 * The Customer class simulates a Customer.
 * It allows for data to be held about the customer, as well as an associated Division ID.
 * @author Mary Williams
 * @version 1
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostCode;
    private String customerPhone;

    private int divisionID;

    //Constructor
    /**
     * The constructor creates a customer.
     * @param customerID The integer to be given to the ID of the Customer.
     * @param customerName The string to be given to the name of the Customer.
     * @param customerAddress The string to be given to the address of the Customer.
     * @param customerPostCode The string to be given to the post code of the Customer.
     * @param customerPhone The string to be given to the phone number of the Customer.
     * @param divisionID The integer to be given to the division ID associated with the Customer.
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostCode, String customerPhone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostCode = customerPostCode;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
    }

    //Getters and Setters
    /**
     * The getCustomerID method returns the customerID.
     * @return The value in the customerID variable.
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * The setCustomerID method sets the ID of the Customer.
     * @param customerID The integer to be given to the ID of the Customer.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * The getCustomerName method returns the customer's Name.
     * @return The value in the customerName variable.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * The setCustomerName method sets the Name of the Customer.
     * @param customerName The string to be given to the name of the Customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * The getCustomerAddress method returns the customer's Address.
     * @return The value in the customerAddress variable.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    /**
     * The setCustomerAddress method sets the Address of the Customer.
     * @param customerAddress The string to be given to the address of the Customer.
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * The getCustomerPostCode method returns the customer's Post code.
     * @return The value in the customerPostCode variable.
     */
    public String getCustomerPostCode() {
        return customerPostCode;
    }
    /**
     * The setCustomerPostCode method sets the Post code of the Customer.
     * @param customerPostCode The string to be given to the post code of the Customer.
     */
    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    /**
     * The getCustomerPhone method returns the customer's Phone number.
     * @return The value in the customerPhone variable.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /**
     * The setCustomerPhone method sets the Phone number of the Customer.
     * @param customerPhone The string to be given to the phone number of the Customer.
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
     * The setDivisionID method sets the Division ID associated with the Customer.
     * @param divisionID The integer to be given to the division ID associated with the Customer.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The toString method is used to change the default toString method, making it more readable.
     * @return The more readable Customer string which includes the customerID and customerName.
     */
    @Override
    public String toString(){
        return("#"+Integer.toString(customerID)+": "+customerName);
    }
}

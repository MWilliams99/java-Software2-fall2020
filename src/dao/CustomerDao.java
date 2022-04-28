package dao;

import javafx.collections.ObservableList;
import model.Customer;

/**
 * CustomerDao is the Interface for CustomerImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface CustomerDao {
    /**
     * The createCustomer method is used to create a customer in the database.
     * @param customerCreate The customer to create in the database.
     */
    void createCustomer(Customer customerCreate);

    /**
     * The getAllCustomers method is used to retrieve all customers from the database.
     * @return The Observable List of all Customers in the database.
     */
    ObservableList<Customer> getAllCustomers();

    /**
     * The getCustomer method is used to retrieve a single customer from the database that corresponds to the customerID parameter.
     * @param customerID The ID of the customer to retrieve from the database.
     * @return The Customer object with data retrieved from the database.
     */
    Customer getCustomer(int customerID);

    /**
     * The updateCustomer method is used to update a customer in the database.
     * @param customerID The ID of the customer to update in the database.
     * @param customerUpdate The customer with updated data to use in the database.
     */
    void updateCustomer(int customerID, Customer customerUpdate);

    /**
     * The deleteCustomer method is used to delete a customer in the database.
     * @param customerID The ID of the customer to delete in the database.
     * @return A true/false value used to indicate if the deletion succeeded or failed.
     */
    boolean deleteCustomer(int customerID);
}

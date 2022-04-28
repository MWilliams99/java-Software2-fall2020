package dao;

import javafx.collections.ObservableList;
import model.Contact;

/**
 * ContactDao is the Interface for ContactImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface ContactDao {
    /**
     * The getAllContacts method is used to retrieve all contacts from the database.
     * @return The Observable List of all Contacts in the database.
     */
    ObservableList<Contact> getAllContacts();

    /**
     * The getContact method is used to retrieve a single contact from the database that corresponds to the contactID parameter.
     * @param contactID The ID of the contact to retrieve from the database.
     * @return The Contact object with data retrieved from the database.
     */
    Contact getContact(int contactID);
}

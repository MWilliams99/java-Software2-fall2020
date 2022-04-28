package model;

/**
 * The Contact class simulates a Contact.
 * It holds data such as the Contact's ID, Name, and Email.
 * @author Mary Williams
 * @version 1
 */
public class Contact {
    private int contactID;
    private String contactName;
    private String contactEmail;

    //Constructor
    /**
     * The constructor creates a contact.
     * @param contactID The integer to be given to the ID of the Contact.
     * @param contactName The string to be given to the Name of the Contact.
     * @param contactEmail The string to be given to the Email of the Contact.
     */
    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    //Getters and Setters
    /**
     * The getContactID method returns the contactID.
     * @return The value in the contactID variable.
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * The setContactID method sets the ID of the Contact.
     * @param contactID The integer to be given to the ID of the Contact.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getContactName method returns the Name of the Contact.
     * @return The value in the contactName variable.
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * The setContactName method sets the Name of the Contact.
     * @param contactName The string to be given to the Name of the Contact.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The getContactEmail method returns the Email of the Contact.
     * @return The value in the contactEmail variable.
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * The setContactEmail method sets the Email of the Contact.
     * @param contactEmail The string to be given to the Email of the Contact.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * The toString method is used to change the default toString method, making it more readable.
     * @return The more readable Contact string which includes the contactID and contactName.
     */
    @Override
    public String toString(){
        return("#"+Integer.toString(contactID)+": "+contactName);
    }
}

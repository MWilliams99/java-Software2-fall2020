package model;

/**
 * The Division class simulates a First-Level Division.
 * It holds data such as the Division's ID and Name, as well as an associated Country ID.
 * @author Mary Williams
 * @version 1
 */
public class Division {
    private int divisionID;
    private String divisionName;

    private int countryID;

    //Constructor
    /**
     * The constructor creates a first-level division.
     * @param divisionID The integer to be given to the ID of the Division.
     * @param divisionName The string to be given to the Name of the Division.
     * @param countryID The integer to be given to the Country ID associated with the Division.
     */
    public Division(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    //Getters and Setters
    /**
     * The getDivisionID returns the divisionID.
     * @return The value in the divisionID variable.
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * The setDivisionID method sets the ID of the Division.
     * @param divisionID The integer to be given to the ID of the Division.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * The getDivisionName method returns the Name of the Division.
     * @return The value in the divisionName variable.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * The setDivisionName method sets the Name of the Division.
     * @param divisionName The string to be given to the Name of the Division.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * The getCountryID method returns the ID of the Division's associated Country.
     * @return The value in the countryID variable.
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * The setCountryID method sets the Country ID associated with the Division.
     * @param countryID The integer to be given to the country ID associated with the Division.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * The toString method is used to change the default toString method, making it more readable.
     * @return The more readable Division string which includes the divisionID and divisionName.
     */
    @Override
    public String toString(){
        return("#"+Integer.toString(divisionID)+": "+divisionName);
    }
}

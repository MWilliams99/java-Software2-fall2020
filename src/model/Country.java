package model;

/**
 * The Country class simulates a Country.
 * It holds data such as the Country's ID and Name.
 * @author Mary Williams
 * @version 1
 */
public class Country {
    private int countryID;
    private String countryName;

    //Constructor
    /**
     * The constructor creates a country.
     * @param countryID The integer to be given to the ID of the Country.
     * @param countryName The string to be given to the Name of the Country.
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    //Getters and Setters
    /**
     * The getCountryID method returns the countryID.
     * @return The value in the countryID variable.
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * The setCountryID method sets the ID of the Country.
     * @param countryID The integer to be given to the ID of the Country.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * The getCountryName method returns the Name of the Country.
     * @return the value in the countryName variable.
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * The setCountryName method sets the Name of the Country.
     * @param countryName The string to be given to the Name of the Country.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * The toString method is used to change the default toString method, making it more readable.
     * @return The more readable Country string which includes the countryID and countryName.
     */
    @Override
    public String toString(){
        return("#"+Integer.toString(countryID)+": "+countryName);
    }
}

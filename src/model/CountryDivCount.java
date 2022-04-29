package model;

/**
 * The CountryDivCount class is used to hold data for each row in the main form 'Number of Customers by Country and Division' Table.
 * @author Mary Williams
 * @version 1
 */
public class CountryDivCount {
    private String country;
    private String division;
    private int count;

    //Constructor
    /**
     * The constructor creates a Country, Division, Count record.
     * @param country The string to be given to the Country of the record.
     * @param division The string to be given to the Division of the record.
     * @param count The integer to be given to the Count of the record - this is the number of customers in a given division in the country.
     */
    public CountryDivCount(String country, String division, int count) {
        this.country = country;
        this.division = division;
        this.count = count;
    }

    //Getters and Setters
    /**
     * The getCountry method returns the Country of the record.
     * @return The value in the country variable.
     */
    public String getCountry() {
        return country;
    }
    /**
     * The setCountry method sets the Country of the record.
     * @param country The string to be given to the Country of the record.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * The getDivision method returns the Division of the record.
     * @return The value in the division variable.
     */
    public String getDivision() {
        return division;
    }
    /**
     * The setDivision method sets the Division of the record.
     * @param division The string to be given to the Division of the record.
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * The getCount method returns the Count of Customers in a Division for a given Country.
     * @return The value in the count variable.
     */
    public int getCount() {
        return count;
    }
    /**
     * The setCount method sets the Count of Customers in a Division for a given Country.
     * @param count The integer to be given to the Count in the record.
     */
    public void setCount(int count) {
        this.count = count;
    }
}

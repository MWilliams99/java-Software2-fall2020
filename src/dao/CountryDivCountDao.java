package dao;

import javafx.collections.ObservableList;
import model.CountryDivCount;

/**
 * CountryDivCountDao is the Interface for CountryDivCountImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface CountryDivCountDao {
    /**
     * The getReportCustomerCountTable method is used to get a Count of customers in each division for the selected country, for use in the 'Number of Customers by Country and Division' Table in the main form.
     * @param countryID The ID of the selected country to retrieve a count of customers in each division related to it.
     * @return The Observable List of CountryDivCount that hold the data retrieved from the database.
     */
    ObservableList<CountryDivCount> getReportCustomerCountTable(int countryID);
}

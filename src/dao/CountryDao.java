package dao;

import javafx.collections.ObservableList;
import model.Country;

/**
 * CountryDao is the Interface for CountryImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface CountryDao {
    /**
     * The getAllCountries method is used to retrieve all countries from the database.
     * @return The Observable list of all Countries in the database.
     */
    ObservableList<Country> getAllCountries();

    /**
     * The getCountry method is used to retrieve a single country from the database that corresponds to the countryID parameter.
     * @param countryID The ID of the country to retrieve from the database.
     * @return The Country object with data retrieved from the database.
     */
    Country getCountry(int countryID);
}

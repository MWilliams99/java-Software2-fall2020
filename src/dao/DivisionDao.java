package dao;

import javafx.collections.ObservableList;
import model.Division;

/**
 * DivisionDao is the Interface for DivisionImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface DivisionDao {
    /**
     * The getDivision method is used to retrieve a single division from the database that corresponds to the divisionID parameter.
     * @param divisionID The ID of the division to retrieve from the database.
     * @return The Division object with data retrieved from the database.
     */
    Division getDivision(int divisionID);

    /**
     * The getDivsByCountry method is used to retrieve all divisions from the database that correspond to the countryID parameter.
     * @param countryID The ID of the country to get related divisions from the database.
     * @return The Observable List of Division in the database that correspond to the country ID.
     */
    ObservableList<Division> getDivsByCountry(int countryID);
}

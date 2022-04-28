package dao;

import javafx.collections.ObservableList;
import model._CustDivCountryCombo;

/**
 * _CustComboDao is the Interface for _CustComboImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface _CustComboDao {
    /**
     * The getCustomerTable method is used to return all Customers for use in the main form Customers Table.
     * @return The Observable List of _CustDivCountryCombo that correlate to all Customers.
     */
    ObservableList<_CustDivCountryCombo> getCustomerTable();
}

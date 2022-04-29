package dao;

import javafx.collections.ObservableList;
import model.MonthTypeCount;

/**
 * MonthTypeCountDao is the Interface for MonthTypeCountImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface MonthTypeCountDao {
    /**
     * The getReportAppCountTable method is used to get a Count of appointments by type for each month in the database, for use in the 'Number of Appointments by Type and Month in the Current Year' Table in the main form.
     * @return The Observable List of MonthTypeCount that hold the data retrieved from the database.
     */
    ObservableList<MonthTypeCount> getReportAppCountTable();
}

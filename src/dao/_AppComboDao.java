package dao;

import javafx.collections.ObservableList;
import model._AppContactCustCombo;

/**
 * _AppComboDao is the Interface for _AppComboImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface _AppComboDao {
    /**
     * The getAppointmentTable method is used to return all Appointments for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to all Appointments.
     */
    ObservableList<_AppContactCustCombo> getAppointmentTable();
    /**
     * The getWeekAppointmentTable method is used to return Appointments between now and a week from now for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to Appointments between now and a week from now.
     */
    ObservableList<_AppContactCustCombo> getWeekAppointmentTable();
    /**
     * The getMonthAppointmentTable method is used to return Appointments between now and a month from now for use in the main form Appointments Table.
     * @return The Observable List of _AppContactCustCombo that correlate to Appointments between now and a month from now.
     */
    ObservableList<_AppContactCustCombo> getMonthAppointmentTable();
}

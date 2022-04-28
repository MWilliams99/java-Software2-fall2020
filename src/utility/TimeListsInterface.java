package utility;

import javafx.collections.ObservableList;

/**
 * TimeListsInterface is used for returning a list of Strings to indicate time.
 * @author Mary Williams
 * @version 1
 */
public interface TimeListsInterface {
    /**
     * The timeList method is used to return a String Observable List between 0 and Int listTo.
     * This interface method is used in conjunction with lambda expressions in appointmentAddController and appointmentModifyController.
     * @param listTo The integer to list up to in the String Observable List.
     * @return The String Observable List with numbers between 0 and Int listTo in String form.
     */
    ObservableList<String> timeList(int listTo);
}

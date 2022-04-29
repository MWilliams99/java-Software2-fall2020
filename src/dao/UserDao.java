package dao;

import javafx.collections.ObservableList;
import model.User;

/**
 * UserDao is the Interface for UserImp to ensure it uses all of these methods with appropriate returns and parameters.
 * @author Mary Williams
 * @version 1
 */
public interface UserDao {
    /**
     * The getAllUsers method is used to retrieve all users from the database.
     * @return The Observable List of all Users in the database.
     */
    ObservableList<User> getAllUsers();

    /**
     * The getUser method that receives an int userID parameter is used to retrieve a single user from the database that corresponds to the userID parameter.
     * @param userID The ID of the user to retrieve from the database.
     * @return The User object with data retrieved from the database.
     */
    User getUser(int userID);

    /**
     * The getUser method that receives a String userName parameter is used to retrieve a single user from the database that corresponds to the userName parameter.
     * @param userName The Username of the user to retrieve from the database.
     * @return The User object with data retrieved from the database.
     */
    User getUser(String userName);
}

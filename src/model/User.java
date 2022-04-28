package model;

import java.time.LocalDateTime;

/**
 * The User class simulates a User.
 * It holds data such as the User's ID, Name, and Password.
 * Static variables also exist in this method for use throughout the application, such as the current user logged in and login time.
 * @author Mary Williams
 * @version 1
 */
public class User {
    private int userID;
    private String userName;
    private String userPass;

    private static String currentUserName;
    private static LocalDateTime loginTime;
    private static LocalDateTime loginTime15;

    //Constructor
    /**
     * The constructor creates a User.
     * @param userID The integer to be given to the ID of the User.
     * @param userName The string to be given to the userName of the User.
     * @param userPass The string to be given to the userPass of the User.
     */
    public User(int userID, String userName, String userPass) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
    }

    //Getters and Setters
    /**
     * The getUserID method returns the userID.
     * @return The value in the userID variable.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * The setUserID method sets the userID of the User.
     * @param userID The integer to be given to the ID of the User.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * The getUserName method returns the username.
     * @return The value in the userName variable.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The setUserName method sets the userName of the User.
     * @param userName The string to be given to the userName of the User.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * The getUserPass method returns the user's password.
     * @return The value in the userPass variable.
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * The setUserPass method sets the userPass of the User.
     * @param userPass The string to be given to the password of the User.
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * The getCurrentUserName method returns the userName of the currently logged in user.
     * @return The value in the currentUserName variable.
     */
    public static String getCurrentUserName() {
        return currentUserName;
    }

    /**
     * The setCurrentUserName method sets the userName of the currently logged in user.
     * @param currentUserName The string to be given to the static currentUserName.
     */
    public static void setCurrentUserName(String currentUserName) {
        User.currentUserName = currentUserName;
    }

    /**
     * The getLoginTime method returns the time that the current user logged in.
     * @return The value in the loginTime variable.
     */
    public static LocalDateTime getLoginTime() {
        return loginTime;
    }

    /**
     * The getLoginTime15 method returns the time 15 minutes after the current user logged in.
     * @return The value in the loginTime15 variable.
     */
    public static LocalDateTime getLoginTime15() {
        return loginTime15;
    }

    /**
     * The setLoginTimes method sets the login time and login time plus 15 of the currently logged in user.
     * @param loginTime The LocalDateTime that the user logged in.
     */
    public static void setLoginTimes(LocalDateTime loginTime) {
        User.loginTime = loginTime.withSecond(0).withNano(0);
        User.loginTime15 = loginTime.plusMinutes(15);
    }

    /**
     * The toString method is used to change the default toString method, making it more readable.
     * @return The more readable User string which includes the userID and userName.
     */
    @Override
    public String toString(){
        return("#"+Integer.toString(userID)+": "+userName);
    }
}

package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;

/**
 * UserImp implements UserDao and holds methods to perform CRUD operations on users in the databse.
 * @author Mary Williams
 * @version 1
 */
public class UserImp implements UserDao{
    /**
     * The getAllUsers method is used to retrieve all users from the database.
     * @return The Observable List of all Users in the database.
     */
    @Override
    public ObservableList<User> getAllUsers() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create observablelist to hold returned users
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM users";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPass = rs.getString("Password");

                User user = new User(userID,userName,userPass);
                allUsers.add(user);
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
        }
        DBConnection.closeConnection();
        return allUsers;
    }
    /**
     * The getUser method that receives an int userID parameter is used to retrieve a single user from the database that corresponds to the userID parameter.
     * @param userID The ID of the user to retrieve from the database.
     * @return The User object with data retrieved from the database.
     */
    @Override
    public User getUser(int userID) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select user by ID
        String selectStatement = "SELECT * FROM users WHERE User_ID = ?";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setInt(1, userID);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                String userName = rs.getString("User_Name");
                String userPass = rs.getString("Password");


                User user = new User(userID,userName,userPass);
                DBConnection.closeConnection();
                return user;
            }
            else{
                DBConnection.closeConnection();
                return null;
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            DBConnection.closeConnection();
            return null;
        }
    }
    /**
     * The getUser method that receives a String userName parameter is used to retrieve a single user from the database that corresponds to the userName parameter.
     * @param userName The Username of the user to retrieve from the database.
     * @return The User object with data retrieved from the database.
     */
    @Override
    public User getUser(String userName) {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Select user by Name
        String selectStatement = "SELECT * FROM users WHERE User_Name = ?";

        try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
            ps.setString(1, userName);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if(rs.next()){
                int userID = rs.getInt("User_ID");
                String userPass = rs.getString("Password");


                User user = new User(userID,userName,userPass);
                DBConnection.closeConnection();
                return user;
            }
            else{
                DBConnection.closeConnection();
                return null;
            }
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            DBConnection.closeConnection();
            return null;
        }
    }
}

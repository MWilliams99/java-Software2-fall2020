package controller;

import dao.AppointmentDao;
import dao.AppointmentImp;
import dao.UserDao;
import dao.UserImp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.User;
import utility.FileOutputInterface;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The loginFormController is used as the Controller for loginForm.fxml.
 * @author Mary Williams
 * @version 1
 */
public class loginFormController implements Initializable {
    //Labels / TextFields / Button
    @FXML
    private Label loginLabel;
    @FXML
    private Label loginUserNameLabel;
    @FXML
    private Label loginPasswordLabel;
    @FXML
    private Label loginLocationLabel;

    @FXML
    private TextField userNameTF;
    @FXML
    private TextField userPassTF;

    @FXML
    private Button loginSubmitButton;


    //Initialize
    /**
     * The initialize method is used to translate the Labels and Button to French if the user's computer is set to the FR language Locale.
     * @param url Resource pointer used by initialize.
     * @param resourceBundle Locale-specific objects used by initialize.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId localZone = ZoneId.systemDefault();

        if(Locale.getDefault().getLanguage().equals("fr")){
           ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());

           loginLabel.setText(rb.getString("Login"));
           loginUserNameLabel.setText(rb.getString("Username"));
           loginPasswordLabel.setText(rb.getString("Password"));

           loginSubmitButton.setText(rb.getString("Submit"));

           loginLocationLabel.setText(rb.getString("Location")+": "+localZone.toString());
        }
        else{
           loginLocationLabel.setText("Location: "+localZone.toString());
        }
    }

    //Submit Login Functionality -- LAMBDA expressions here for file output, using FileOutputInterface
    /**
     * The loginSubmit method is used to confirm if the user has input a valid username and password when logging in and opens the mainForm.fxml upon successful login.
     * <p>
     * <b>Lambda expression:</b>
     * Lambda expressions are used in this method to simplify writing the output to login_activity.txt while making sure they stay consistent as far as using a String and LocalDateTime parameters.
     * Three instances of similar lambda expressions are used here to write to the file for either 'Failed login - invalid username', 'Failed login - invalid password', or 'Successful login'.
     * Using lambda expressions for this also makes the code more clear and readable, while allowing for some variations between each but still staying consistent in parameters.
     * The code could easily be changed to output to different files for successful versus failed login attempts, or to change the information output to the file.
     *
     * Parameters for the FileOutputInterface include a username String and loginAttempt LocalDateTime.
     * Username String is the input username from the user, this way it will record valid or invalid usernames as part of the attempt.
     * LoginAttempt LocalDateTime is the time at which the user attempted to login.
     * Each lambda expression outputting to the file includes the input Username, Local Time, and Local Date at time of login activity.
     * </p>
     * If the user does not input data for each field, inputs an invalid username, or inputs an incorrect password this method instead shows a dialog box with a related error message.
     * Login attempts, successful or failed, are written to a login_activity.txt file.
     * Error messages are displayed in French if the user's computer is set to the FR language Locale.
     * Upon successful login, this method gives the user a dialog box alerting them to any appointments within 15 minutes of logging in, or tells them if there are no upcoming appointments.
     * @param event The MouseEvent that triggers this method. When the user clicks on the 'Submit' Button.
     * @exception IOException Failed to load mainForm.fxml.
     */
    @FXML
    void loginSubmit(MouseEvent event) throws IOException {
        //Create Strings to hold the input Username and Password
        String userNameInput;
        String userPassInput;
        //Gather input Username and Password into Strings
        userNameInput = userNameTF.getText().trim();
        userPassInput = userPassTF.getText().trim();
        //Gather LocalDateTime at time of attempted login
        LocalDateTime loginAttemptInput = LocalDateTime.now();

        //Create userDao to query database.
        UserDao userDao = new UserImp();
        //Create User to hold gathered user.
        User user;
        //Query database with input Username and put into User object.
        user = userDao.getUser(userNameInput);

        //If either TextField is empty - dialog box asking to input for each.
        if(userNameTF.getText().trim().isEmpty() ||
            userPassTF.getText().trim().isEmpty()){
            if(Locale.getDefault().getLanguage().equals("fr")){
                ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());

                JOptionPane.showMessageDialog(null, rb.getString("Please")+" "+rb.getString("enter")+" "+rb.getString("a")+" "
                +rb.getString("Username")+" "+rb.getString("and")+" "+rb.getString("Password")+".");
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter a Username and Password.");
            }
            return;
        }
        //Else, check if valid username, and valid password.
        else{
            //If no user matches given username
            if(user == null){
                //Write output to file here: "Failed login - Invalid username: User --- at -DATE- -TIME-."
                FileOutputInterface output = (username, loginAttempt) -> {
                    try{
                        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
                        PrintWriter outputFile = new PrintWriter(fileWriter);

                        outputFile.println("Failed login - Invalid username: User '"+username+"' at "+loginAttempt.toLocalDate().toString()+" "+loginAttempt.toLocalTime().toString());
                        outputFile.close();
                    }
                    catch(IOException e){
                        //System.out.println(e);
                    }
                };
                output.outputToFile(userNameInput, loginAttemptInput);

                if(Locale.getDefault().getLanguage().equals("fr")){
                    ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
                    JOptionPane.showMessageDialog(null, rb.getString("Invalid")+" "+rb.getString("username")+". "+
                            rb.getString("Try")+" "+rb.getString("again")+".");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid username. Try again.");
                }
                return;
            }
            else{
                //If input password doesn't match the password of given username
                if(!(user.getUserPass().equals(userPassInput))){
                    //Write output to file here: "Failed login - Invalid password: User --- at -DATE- -TIME-."
                    FileOutputInterface output = (username, loginAttempt) -> {
                        try{
                            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
                            PrintWriter outputFile = new PrintWriter(fileWriter);

                            outputFile.println("Failed login - Invalid password: User '"+username+"' at "+loginAttempt.toLocalDate().toString()+" "+loginAttempt.toLocalTime().toString());
                            outputFile.close();
                        }
                        catch(IOException e){
                            //System.out.println(e);
                        }
                    };
                    output.outputToFile(userNameInput, loginAttemptInput);

                    if(Locale.getDefault().getLanguage().equals("fr")){
                        ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
                        JOptionPane.showMessageDialog(null, rb.getString("Invalid")+" "+rb.getString("password")+". "+
                                rb.getString("Try")+" "+rb.getString("again")+".");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid password. Try again.");
                    }
                    return;
                }

                //Accurate username and matching password
                //Write output to file here: "Successful login: User --- at -DATE- -TIME-."
                FileOutputInterface output = (username, loginAttempt) -> {
                    try{
                        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
                        PrintWriter outputFile = new PrintWriter(fileWriter);

                        outputFile.println("Successful login: User '"+username+"' at "+loginAttempt.toLocalDate().toString()+" "+loginAttempt.toLocalTime().toString());
                        outputFile.close();
                    }
                    catch(IOException e){
                        //System.out.println(e);
                    }

                };
                output.outputToFile(userNameInput, loginAttemptInput);

                //Valid username and password - set String currentUserName with user userName
                //This is for later in the application - to set 'createBy' and 'updateBy' to the current user.
                User.setCurrentUserName(user.getUserName());
                //Valid login - set LocalDateTime loginTime to system default at time of login.
                User.setLoginTimes(LocalDateTime.now());

                int userID = user.getUserID();
                AppointmentDao appointmentDao = new AppointmentImp();
                ObservableList<Appointment> userAppointmentsAlert = appointmentDao.getUserAppointmentAlert(userID);

                if(userAppointmentsAlert.isEmpty()){
                    JOptionPane.showMessageDialog(null, "You have no appointments within the next 15 minutes.\n" +
                            "There is no upcoming appointment.");
                }
                else{
                    int userAppNum = userAppointmentsAlert.size();
                    Appointment upcomingAppointment = userAppointmentsAlert.get(0);
                    int userAppID = upcomingAppointment.getAppointmentID();
                    String userAppTitle = upcomingAppointment.getAppointmentTitle();
                    LocalDate userAppStartD = upcomingAppointment.getAppointmentStart().toLocalDate();
                    LocalTime userAppStartT = upcomingAppointment.getAppointmentStart().toLocalTime();
                    LocalDate userAppEndD = upcomingAppointment.getAppointmentEnd().toLocalDate();
                    LocalTime userAppEndT = upcomingAppointment.getAppointmentEnd().toLocalTime();

                    JOptionPane.showMessageDialog(null, "You have "+userAppNum+
                            " appointment(s) within the next 15 minutes.\n" +
                            "The upcoming appointment is:\n" +
                            "Appointment ID: "+userAppID+
                            "\nTitle: "+userAppTitle+
                            "\nStart: "+userAppStartD+" "+userAppStartT.format(DateTimeFormatter.ofPattern("HH:mm"))+
                            "\nEnd: "+userAppEndD+" "+userAppEndT.format(DateTimeFormatter.ofPattern("HH:mm")));
                }

                //Switch to Main Form
                Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.hide();
                primaryStage.setScene(new Scene(parent));
                primaryStage.setTitle("Main Form");
                primaryStage.setResizable(false);
                primaryStage.show();
            }
        }
    }
}

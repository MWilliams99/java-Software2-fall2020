package utility;

import java.time.LocalDateTime;

/**
 * FileOutputInterface is used for outputting to a file.
 * @author Mary Williams
 * @version 1
 */
public interface FileOutputInterface {
    /**
     * The outputToFile method is used to output to a file using String username and LocalDateTime loginAttempt to enhance the information given in the output.
     * This interface method is used in conjunction with lambda expressions in loginFormController.
     * @param username The username that a user has input when attempting to login.
     * @param loginAttempt The time at which a user attempted to log in.
     */
    void outputToFile(String username, LocalDateTime loginAttempt);
}

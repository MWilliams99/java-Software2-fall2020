# Java WGU Software 2 Fall 2020

## Project Description
Software 2 project that I completed in Fall 2020 for the C195 course at Western Governors University.  
Every student is given the same set of requirements for this project, which can be found [here]().

This project was completed February 20th, 2021.

I used Java to code this project as that is what I hose to focus in during my Software Development degree. This project also includes javaFX to code the GUI fxml files and Javadoc to document the project code.  
MySQL was used for the database connections for this project.
I used IntelliJ IDEA, SceneBuilder, and MySQL Workbench to aid in the creation of this project.

While it was not required, the instructors recommended using the DAO design pattern as described [here]().

This project has the student create a program connected to a database, that will display customers and appointments that the user can add, edit, and delete. The program first launches with a login screen that pulls usernames and passwords from the database. Once the user is logged in, the program displays a main form with tabs for *Customers*, *Appointments*, and *Reports*.
- The *Customers* tab displays a table of customers already in the database, and includes 3 buttons allowing the user to add, edit, or delete customers.
- The *Appointments* tab displays a table of appointments already in the database, and includes 3 buttons allowing the user to add, edit, or delete appointments.  
Appointments rely on existing customers and must include a customer associated to it to be created. When a customer is deleted, all related appointments are also deleted.  
Appointments may also only be created within business hours, and cannot overlap for the selected customer.
- The *Reports* tab includes 3 tables displaying the required reports outlined in A.3.f. of the [Project Requirements]().  
The additional 3rd report I chose to create was a table showing a count of customers by first-level division for the selected country from a drop-down.

## Design Process
### Provided Database ERD
<img src="https://user-images.githubusercontent.com/101907789/165870610-600bf5c3-9e13-453d-869e-212556e51850.png" width="600"></img>

## Acknowledgements

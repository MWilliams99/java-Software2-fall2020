package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MonthTypeCount;
import utility.DBConnection;
import utility.DBQuery;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * MonthTypeCountImp implements MonthTypeCountDao and holds a method to gather a count of appointments by types for each month for use in the main form 'Number of Appointments by Type and Month in the Current Year' Table.
 * @author Mary Williams
 * @version 1
 */
public class MonthTypeCountImp implements MonthTypeCountDao{
    /**
     * The getReportAppCountTable method is used to get a Count of appointments by type for each month in the database, for use in the 'Number of Appointments by Type and Month in the Current Year' Table in the main form.
     * @return The Observable List of MonthTypeCount that hold the data retrieved from the database.
     */
    @Override
    public ObservableList<MonthTypeCount> getReportAppCountTable() {
        //Create connection and statement/query
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);

        //Create ObservableList to hold return
        ObservableList<MonthTypeCount> monthTypeCountRecord = FXCollections.observableArrayList();

        for(int x = 1; x < 13; x++){
            LocalDateTime monthCheck = LocalDateTime.of(LocalDate.now().getYear(),x, 1, 0, 0);
            LocalDateTime monthNext = monthCheck.plusMonths(1);

            //Select count of appointments by month and type
            String selectStatement = "SELECT Type, Count(*) as 'Count' FROM appointments " +
                    "WHERE Start >= ? AND Start < ? GROUP BY Type";

            //try..catch run sqlstatement
            try(PreparedStatement ps = conn.prepareStatement(selectStatement)){
                ps.setObject(1, monthCheck);
                ps.setObject(2,monthNext);
                ps.execute();

                //Gather result into resultset
                ResultSet rs = ps.getResultSet();

                String monthStr = monthCheck.getMonth().toString();
                String month = monthStr.substring(0,1) + monthStr.substring(1).toLowerCase();

                //System.out.println(month);

                //while next in rs - make new MonthTypeCount and put into observable list
                while(rs.next()){
                    String type = rs.getString("Type");
                    int count = rs.getInt("Count");

                    MonthTypeCount monthTypeCount = new MonthTypeCount(month,type,count);
                    monthTypeCountRecord.add(monthTypeCount);
                }
            }
            catch(SQLException e){
                //System.out.println(e);
            }
        }
        DBConnection.closeConnection();
        return monthTypeCountRecord;
    }
}

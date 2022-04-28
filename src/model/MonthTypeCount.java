package model;

/**
 * The MonthTypeCount class is used to hold data for each row in the main form 'Number of Appointments by Type and Month in the Current Year' Table.
 * @author Mary Williams
 * @version 1
 */
public class MonthTypeCount {
    private String month;
    private String type;
    private int count;

    //Constructor
    /**
     * The constructor creates a Month, Type, Count record.
     * @param month The string to be given to the Month of the record.
     * @param type The string to be given to the Type of the record.
     * @param count The integer to be given to the Count of the record - this is the number of one type of appointment in the month.
     */
    public MonthTypeCount(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    //Getters and Setters
    /**
     * The getMonth method returns the Month of the record.
     * @return The value in the month variable.
     */
    public String getMonth() {
        return month;
    }
    /**
     * The setMonth method sets the Month of the record.
     * @param month The string to be given to the Month of the record.
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * The getType method returns the Type of the record.
     * @return The value in the type variable.
     */
    public String getType() {
        return type;
    }
    /**
     * The setType method sets the Type of the record.
     * @param type The string to be given to the Type of the record.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The getCount method returns the Count of one Type of appointment in the Month.
     * @return The value in the count variable.
     */
    public int getCount() {
        return count;
    }
    /**
     * The setCount method sets the Count of one Type of appointment in the Month.
     * @param count The integer to be given to the Count in the record.
     */
    public void setCount(int count) {
        this.count = count;
    }
}

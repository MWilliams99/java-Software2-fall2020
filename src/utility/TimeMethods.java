package utility;

import java.time.*;

/**
 * The TimeMethods class holds utility methods related to time used throughout the application.
 * @author Mary Williams
 * @version 1
 */
public class TimeMethods {
    /**
     * The checkSameDay method checks if two LocalDateTimes are in the same day in EST time.
     * @param appointmentStart The appointment start time to test against the appointment end time.
     * @param appointmentEnd The appointment end time to test against the appointment start time.
     * @return A true/false value indicating the two LocalDateTimes are the same day or not.
     */
    public static boolean checkSameDay(LocalDateTime appointmentStart, LocalDateTime appointmentEnd){
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = appointmentStart.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = appointmentEnd.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();

        LocalDate appStartEstDate = appStartEST.toLocalDate();
        LocalDate appEndEstDate = appEndEST.toLocalDate();

        if(!(appStartEstDate.isEqual(appEndEstDate))){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * The checkOutsideBusHours method checks if two LocalDateTimes are outside of business hours in EST time.
     * @param appointmentStart The appointment start time to test against business hours.
     * @param appointmentEnd The appointment end time to test against business hours.
     * @return A true/false value indicating whether the two LocalDateTimes are outside of business hours or not.
     */
    public static boolean checkOutsideBusHours(LocalDateTime appointmentStart, LocalDateTime appointmentEnd){
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = appointmentStart.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = appointmentEnd.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
            //System.out.println(appStartEST);
            //System.out.println(appEndEST);
        LocalDateTime busStartEST = appStartEST.withHour(8).withMinute(0);
        LocalDateTime busEndEST = appEndEST.withHour(22).withMinute(0);
            //System.out.println(busStartEST);
            //System.out.println(busEndEST);

        if(appStartEST.isBefore(busStartEST) || appEndEST.isAfter(busEndEST)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * The getLocalBusinessStart method returns a LocalTime with the Start of business hours (8am EST) set to the Local Time Zone.
     * @return The LocalTime equal to the Start of business hours; 8am EST.
     */
    public static LocalTime getLocalBusinessStart(){
        LocalTime busStartEST = LocalTime.of(8,0);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime busEstDT = LocalDateTime.of(LocalDate.now(), busStartEST);
        LocalDateTime busLocalDT = busEstDT.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime busStartLocal = busLocalDT.toLocalTime();

        //System.out.println(busStartEST);
        //System.out.println(busStartLocal);
        return busStartLocal;

    }

    /**
     * The getLocalBusinessEnd method returns a LocalTime with the End of business hours (10pm EST) set to the Local Time Zone.
     * @return The LocalTime equal to the End of business hours; 10pm EST.
     */
    public static LocalTime getLocalBusinessEnd(){
        LocalTime busEndEST = LocalTime.of(22,0);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime busEstDT = LocalDateTime.of(LocalDate.now(), busEndEST);
        LocalDateTime busLocalDT = busEstDT.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime busEndLocal = busLocalDT.toLocalTime();

        //System.out.println(busEndEST);
        //System.out.println(busEndLocal);
        return busEndLocal;
    }
}

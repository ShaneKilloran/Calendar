package main.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeMaintenance {


    public static LocalDate stringToDate(String inputDate){
        DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        date = LocalDate.parse(inputDate, parsePattern);
        return date;
    }


    public static LocalTime stringToTime(String inputTime){
        DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time;
        time = LocalTime.parse(inputTime, parsePattern);
        return time;
    }

    public static String dateToString(LocalDate inputDate){
        DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date;
        date = inputDate.format(parsePattern);
        return date;
    }

    public static String timeToString(LocalTime inputTime){
        DateTimeFormatter parsePattern = DateTimeFormatter.ofPattern("HH:mm");
        String time;
        time = inputTime.format(parsePattern);
        return time;
    }


}

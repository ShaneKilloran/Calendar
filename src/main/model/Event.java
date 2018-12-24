package main.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static main.model.DateTimeMaintenance.stringToTime;

public abstract class Event implements Comparable<Event>{

    String name;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    public Event (String name, String startTime, String endTime){
        this.name=name;
        this.startTime=stringToTime(startTime);
        this.endTime=stringToTime(endTime);
    }



    //////////////////// Abstract Methods ////////////////////////////


    abstract public String prepForSave();

    abstract public Event specificLoad(String line) throws IOException;

    abstract public String toString();

    ////////////////////////// MISC ///////////////////////////////////


  @Override
  public int compareTo(Event e){
      return startTime.compareTo(e.getStartTime());
  }



    static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("/");
        return new ArrayList<>(Arrays.asList(splits));
    }



    ///////////////////// Getters and Setters //////////////////////////



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}

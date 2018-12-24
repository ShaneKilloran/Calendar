package main.model;

import java.util.ArrayList;

import static main.model.DateTimeMaintenance.stringToTime;

public class Class extends Event {

    private String location;
    private Boolean repeat;
    private int frequency;

    public Class(String name, String location, String startTime, String endTime, Boolean repeat, int frequency){

        super (name, startTime, endTime);
        this.location=location;
        this.repeat=repeat;
        this.frequency=frequency;

    }

    @Override
    public String prepForSave(){
        return "2/"+name+"/"+location+"/"+startTime+"/"+endTime+"/"+repeat+"/"+frequency+"/";
    }

    @Override
    public Event specificLoad(String line) {
        ArrayList<String> partsOfLine = splitOnSpace(line);

        name = partsOfLine.get(1);
        location = partsOfLine.get(2);
        startTime = stringToTime(partsOfLine.get(3));
        endTime = stringToTime(partsOfLine.get(4));
        repeat = Boolean.parseBoolean(partsOfLine.get(5));
        frequency = Integer.parseInt(partsOfLine.get(6));

        return this;
    }

    @Override
    public String toString() {
        return
                startTime + " - " + endTime + ": " +
                "Class: " + name + ". " +
                "Location: " + location + "." ;

    }
}

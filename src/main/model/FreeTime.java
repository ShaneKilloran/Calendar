package main.model;

import java.util.ArrayList;

import static main.model.DateTimeMaintenance.stringToTime;

public class FreeTime extends Event {


    private Boolean repeat;
    private int frequency;

    public FreeTime( String startTime, String endTime, Boolean repeat, int frequency){

        super ("Free Time", startTime, endTime);
        this.repeat=repeat;
        this.frequency=frequency;
    }


    @Override
    public String prepForSave(){
        return "4/"+startTime+"/"+endTime+"/"+repeat+"/"+frequency+"/";
    }

    @Override
    public Event specificLoad(String line) {
        ArrayList<String> partsOfLine = splitOnSpace(line);

        startTime = stringToTime(partsOfLine.get(1));
        endTime = stringToTime(partsOfLine.get(2));
        repeat = Boolean.parseBoolean(partsOfLine.get(3));
        frequency = Integer.parseInt(partsOfLine.get(4));

        return this;
    }

    @Override
    public String toString() {
        return
                         startTime + " - " + endTime + ":" +
                        " Free Time";
    }


}

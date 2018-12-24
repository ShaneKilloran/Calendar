package main.model;

import java.util.ArrayList;

import static main.model.DateTimeMaintenance.stringToTime;

public class Exam extends Event{

    private String location;


    public Exam (String name, String location, String startTime, String endTime){

        super (name, startTime, endTime);
        this.location=location;

    }


    @Override
    public String prepForSave(){
        return "3/"+name+"/"+location+"/"+startTime+"/"+endTime+"/";
    }

    @Override
    public Event specificLoad(String line) {
        ArrayList<String> partsOfLine = splitOnSpace(line);

        name = partsOfLine.get(1);
        location = partsOfLine.get(2);
        startTime = stringToTime(partsOfLine.get(3));
        endTime = stringToTime(partsOfLine.get(4));

        return this;
    }



    @Override
    public String toString() {
        return
                        startTime + " - " + endTime + ": " +
                        "Exam: " + name + ". " +
                        "Location: " + location + "." ;
    }

}

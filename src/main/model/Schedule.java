package main.model;

import exceptions.NoEventExistsException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static main.model.DateTimeMaintenance.dateToString;
import static main.model.DateTimeMaintenance.stringToDate;

public class Schedule {

    private Map<LocalDate, ArrayList<Event>> calendarMap;

    public Schedule() {

        calendarMap = new HashMap<>();
    }

    public void addToMap(LocalDate date, Event e){
        ArrayList<Event> eventsOnDay = new ArrayList<>();
        eventsOnDay.add(e);
        if (!calendarMap.containsKey(date)){
            calendarMap.put(date, eventsOnDay);
        }
        else{
            eventsOnDay.addAll(calendarMap.get(date));
            calendarMap.put(date, eventsOnDay);
        }
    }

    public void removeEventFromMap(LocalDate date, String eventName) throws NoEventExistsException {
        if (calendarMap.containsKey(date)) {
            ArrayList<Event> eventsOnDay = calendarMap.get(date);
            eventsOnDay.removeIf(event -> event.getName().equals(eventName));
            calendarMap.put(date, eventsOnDay);
        }
        else throw new NoEventExistsException();
    }

    public String searchDate(String date) throws NoEventExistsException{
        ArrayList<String> eventList = new ArrayList<>();
        ArrayList<Event> eventSort;
        if (calendarMap.containsKey(stringToDate(date))) {
        eventSort = calendarMap.get(stringToDate(date));
        Collections.sort(eventSort);
        for (Event e: eventSort){
            eventList.add(e.toString());
        }
        return String.join("\n", eventList);}

        else throw new NoEventExistsException();

    }


    //////////////// Load and Save Functions /////////////////////////////////


    // EFFECTS: Defines what is used to parse file. Currently: (/)
    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("/");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void loadSchedule() throws IOException {
        List<String> out = Files.readAllLines(Paths.get("src/saveFile.txt"));
        LocalDate dateCurrent = stringToDate("0001-01-01");
        for (String line : out) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            int read = Integer.parseInt(partsOfLine.get(0));
            if (read == 0){
                dateCurrent = stringToDate(partsOfLine.get(1));
            }
            switch (read) {
                case 0:
                    break;
                case 1:
                    Event nEvent = new NormalEvent("", "",  "00:00", "00:00", false, 0);
                    loadIndividual(nEvent, line, dateCurrent);
                    break;

                case 2:
                    Event cLass = new Class("", "",  "00:00", "00:00", false, 0);
                    loadIndividual(cLass, line, dateCurrent);
                    break;

                case 3:
                    Event exam = new Exam("", "",  "00:00", "00:00");
                    loadIndividual( exam, line, dateCurrent);
                    break;

                case 4:
                    Event fTime = new FreeTime( "00:00", "00:00", false, 0);
                    loadIndividual(fTime, line, dateCurrent);
                    break;

                default:
                    System.out.println("Error");


            }
        }


    }


    public void save() throws IOException {
        PrintWriter writer = new PrintWriter("src/saveFile.txt", "UTF-8");
        for (Map.Entry<LocalDate, ArrayList<Event>> entry : calendarMap.entrySet()) {
            String key = dateToString(entry.getKey());
            ArrayList<Event> value = entry.getValue();
            writer.println("0/" + key);
            for (Event e : value) {
                writer.println(e.prepForSave());
            }
        }
        writer.close();
    }



    private void loadIndividual(Event e, String s, LocalDate dateCurrent) throws IOException{
        e.specificLoad(s);
        addToMap(dateCurrent, e);
    }




    public Map<LocalDate, ArrayList<Event>> getCalendarMap() {
        return calendarMap;
    }

    public void setCalendarMap(Map<LocalDate, ArrayList<Event>> calendarMap) {
        this.calendarMap = calendarMap;
    }


}




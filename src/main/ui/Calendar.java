package main.ui;

import exceptions.NoEventExistsException;
import main.model.Class;
import main.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

import static main.model.DateTimeMaintenance.stringToDate;

public class Calendar extends JFrame {


    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField textField1;
    private JButton searchButton;
    private JComboBox eventType;
    private JTextArea nameOfEventTextArea;
    private JTextArea repeatEventTextArea;
    private JTextArea dateYyyyMmDdTextArea;
    private JTextArea locationTextArea1;
    private JTextArea startTime0000TextArea;
    private JTextArea endTime0000TextArea;
    private JTextField eventName;
    private JTextField eventDate;
    private JTextField eventStartTime;
    private JTextField eventEndTime;
    private JTextField eventLocation;
    private JButton addEventButton;
    private JComboBox eventRepeat;
    private JButton saveScheduleButton;
    private JTextField removeDate7;
    private JTextField removeName8;
    private JTextArea enterDateOfEventTextArea;
    private JTextArea enterNameOfEventTextArea;
    private JButton removeButton;
    private JLabel EventsOnDay;
    private JLabel removeSpeaker;
    private JLabel addEventLabel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendar");
        frame.setContentPane(new Calendar().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        frame.setVisible(true);
    }

    public Calendar() {

        Schedule schedule = new Schedule();
        try {
            schedule.loadSchedule();
        } catch (IOException e) {
            EventsOnDay.setText("An error occurred when loading schedule");
        }

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    EventsOnDay.setText(schedule.searchDate(textField1.getText()));
                    }
             catch (NoEventExistsException e2) {
                    EventsOnDay.setText("You have nothing scheduled on this day");



            }
        }});
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stringToDate(eventDate.getText());
                LocalDate date = stringToDate(eventDate.getText());
                String name = eventName.getText();
                String location = eventLocation.getText();
                String sTime = eventStartTime.getText();
                String eTime = eventEndTime.getText();
                int frequency = eventRepeat.getSelectedIndex();
                Boolean repeat;
                switch(frequency){
                    case 0:
                        repeat = false;
                        break;
                    case 1:
                        repeat = true;
                        break;
                    case 2:
                        repeat = true;
                        break;
                    default:
                        repeat = false;
                        break;

                }

                switch(eventType.getSelectedIndex()){
                    case 0:
                        addEventLabel.setText("Please select an event type");
                        break;
                    case 1:
                        Event nEvent = new NormalEvent(name, location, sTime, eTime, repeat, frequency);
                        schedule.addToMap(date, nEvent);
                        addEventLabel.setText("Event added to schedule");
                        break;
                    case 2:
                        Event cLass = new Class(name, location, sTime, eTime, repeat, frequency);
                        schedule.addToMap(date, cLass);
                        addEventLabel.setText("Class added to schedule");
                        break;
                    case 3:
                        Event exam = new Exam(name, location, sTime, eTime);
                        schedule.addToMap(date, exam);
                        addEventLabel.setText("Exam added to schedule");
                        break;
                    case 4:
                        Event fTime = new FreeTime(sTime, eTime, repeat, frequency);
                        schedule.addToMap(date, fTime);
                        addEventLabel.setText("Free time added to schedule");
                        break;





                }
                eventName.setText("");
                eventDate.setText("");
                eventLocation.setText("");
                eventStartTime.setText("");
                eventEndTime.setText("");
                eventRepeat.setSelectedIndex(0);

            }
        });
        saveScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    schedule.save();
                } catch (IOException e1) {
                    System.out.println("Save Error");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate date = stringToDate(removeDate7.getText());
                String name = removeName8.getText();

                try {
                    schedule.removeEventFromMap(date, name);
                    removeSpeaker.setText("Event has been removed");
                } catch (NoEventExistsException e1) {
                    removeSpeaker.setText("There is no event with this name on this date.");
                }

            }
        });
    }



}
















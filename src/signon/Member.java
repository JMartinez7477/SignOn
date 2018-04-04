/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Joshua
 */
public class Member {

    private String id;
    private String name;
    private ArrayList<Meeting> meetings;
    private ArrayList<Outreach>events;
    private ArrayList<Donation>donations;
    private int participation;
    private int cleanUp;
    private int sponsorshipLetter; 
    Meeting currentMeeting;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        meetings = new ArrayList<Meeting>();
        events = new ArrayList<Outreach>();
        donations = new ArrayList<Donation>();
        participation = 0;
        cleanUp = 0;
        sponsorshipLetter = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void createOldMeeting(long start, long end) {
        Meeting meeting = new Meeting(start, end);
        meetings.add(meeting);
    }

    public void newMeeting() {
        currentMeeting = new Meeting();
        System.out.println(name + " signed in at " +TimeAndDay.timeString);
    }

    public void endMeeting() {
        currentMeeting.setEndTime(System.currentTimeMillis());
        System.out.println(name + " sign out at " + TimeAndDay.timeString);
        meetings.add(currentMeeting);
    }

    public String getTotalTime() {
        long totalTime = 0;
        for(Meeting meeting : meetings) {
            totalTime += meeting.millisWorked();
        }

        long hours = TimeUnit.MILLISECONDS.toHours(totalTime);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(totalTime);
        return hours + ":" + minutes;

    }
    
    public String toString(){
        return id + ": "+name;
    }
}

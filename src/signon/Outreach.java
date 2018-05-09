/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

/**
 *
 * @author Joshua
 */
public class Outreach {

    private double numHours;
    private String eventName;

    public Outreach(double numHours, String eventName) {
        this.numHours = numHours;
        this.eventName = eventName;
    }

    public double getNumHours() {
        return numHours;
    }

    public void setNumHours(double numHours) {
        this.numHours = numHours;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getPoints() {
        return numHours * 2;
    }
    
    public String printInfo(){
        return eventName + " " + numHours;
    }

}
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
public class Meeting {
    final private long startTime;
    private long endTime;

    public Meeting() {
        startTime = System.currentTimeMillis();
    }

    public Meeting(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    public long millisWorked(){
        return endTime - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    
    public String toString(){
        return startTime + " " + endTime;
     }
    
    public String toPrintInfo(){
        return startTime + " " + endTime;
     }
    
    public String toNiceString(){
        return String.format("%-35s", TimeAndDay.dayFromMilli(startTime) + ": ") + 
               (TimeAndDay.timeFromMilli(startTime) + " - " +
                TimeAndDay.timeFromMilli(endTime));
    }
    
}

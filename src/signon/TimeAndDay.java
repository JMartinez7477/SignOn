/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Joshua
 */
public class TimeAndDay {
    private JPanel info;
    static String timeString;
    static String dayString;
    
    public TimeAndDay(){
        updateInfo();
    }

    public JPanel getInfo() {
        return info;
    }
    
    public void updateInfo() {
        JPanel panel = new JPanel();

        JLabel label1 = new JLabel(getTime());
        label1.setBackground(VorTX.blue);
        label1.setFont(VorTX.f124);
        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label1.setForeground(VorTX.green);

        JLabel label2 = new JLabel(getDay());
        label2.setBackground(VorTX.blue);
        label2.setFont(VorTX.f96);
        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label2.setForeground(VorTX.green);

        Box box = Box.createVerticalBox();
        box.add(label1);
        box.add(label2);
        panel.add(box);
        panel.setBackground(VorTX.blue);

        info = panel;
    }

    public static String getTime() {
        String good = timeFromMilli(System.currentTimeMillis());
        timeString = good;
        return good;
    }

    public static String getDay() {
        String good = dayFromMilli(System.currentTimeMillis());
        dayString = good;
        return good;
    }
    
    public static String dayFromMilli(long milliseconds){
        Date day = new Date(milliseconds);
        String dayStr = day.toString();
        String[] parts = dayStr.split(" ");
        String dayOfWeek = "";
        switch (parts[0]) {
            case "Mon":
                dayOfWeek = "Monday";
                break;
            case "Tue":
                dayOfWeek = "Tuesday";
                break;
            case "Wed":
                dayOfWeek = "Wednesday";
                break;
            case "Thu":
                dayOfWeek = "Thursday";
                break;
            case "Fri":
                dayOfWeek = "Friday";
                break;
            case "Sat":
                dayOfWeek = "Saturday";
                break;
            case "Sun":
                dayOfWeek = "Sunday";
                break;
            default:
                dayOfWeek = "";
                break;
        }

        String good = dayOfWeek + " " + parts[1] + " " + parts[2] + ", " + parts[5];
        return good;
    }
    
    public static String timeFromMilli(long milliseconds){
        java.sql.Time time = new java.sql.Time(milliseconds);
        String timeStr = time.toString();
        String[] split = timeStr.split(":");

        int hour = Integer.parseInt(split[0], 10);
        String good;
        
        if (hour < 9) {
            good = split[0].substring(1) + ":" + split[1] + "AM";
        } else if (hour == 24 || hour == 0) {
            good = "12:" + split[1] + "AM";
        } else if (hour == 12) {
            good = "12:" + split[1] + "PM";
        } else if(hour > 12){
            int normHour = hour - 12;
            good = normHour + ":" + split[1] + "PM";
        } else{
            good = split[0] + ":" + split[1] + "AM";
        }
        return good;
    }
}
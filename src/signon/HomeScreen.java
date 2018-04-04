///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package signon;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.GraphicsEnvironment;
//import java.awt.event.ActionListener;
//import java.util.Date;
//import java.sql.Time;
//import java.awt.event.ActionEvent;
//import javax.swing.AbstractAction;
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
///**
// *
// * @author Joshua
// */
//public class HomeScreen {
//
//    private JFrame home;
//    private JPanel info;
//    Font f2 = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 48);
//
//    public HomeScreen() {
//    }
//
//    public void setUpScreen() {
//        home = new JFrame();
//        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        home.setSize(1370, 740);
//        updateInfo();
//        home.add(info, BorderLayout.NORTH);
//        
//        Box b = Box.createVerticalBox();
//        IDEnter enter = new IDEnter();
//        b.add(enter.show);
//        b.add(signUpPanel());
//        b.setBackground(Color.blue);
//        
//        home.add(b);
//        home.setBackground(Color.blue);
//        home.setVisible(true);
//        doInfo();
//    }
//    
//    public JPanel signUpPanel(){
//        JPanel panel = new JPanel();
//        JButton button = new JButton(new AbstractAction("Create New User") {
//            public void actionPerformed(ActionEvent e) {
//                
//            }
//        });
//        button.setFont(f2);
//        panel.add(button);
//        panel.setBackground(Color.blue);
//        
//        return panel;
//    }
//    
//    public void updateScreen(){
//        updateInfo();
//        home.add(info, BorderLayout.NORTH);
//        home.setVisible(true);
//    }
//
//    public void doInfo() {
//        int delay = 1000;
//        ActionListener update = new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                updateScreen();
//            }
//        };
//        Timer timer = new Timer(delay, update);
//        timer.start();
//    }
//
//    public void updateInfo() {
//        JPanel panel = new JPanel();
//        Font f = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 124);
//        
//        JLabel label1 = new JLabel(getTime());
//        
//        label1.setBackground(Color.blue);
//        label1.setFont(f);
//        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//
//        JLabel label2 = new JLabel(getDay());
//        label2.setBackground(Color.blue);
//        label2.setFont(f);
//        label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
//
//        Box box = Box.createVerticalBox();
//        box.add(label1);
//        box.add(label2);
//        panel.add(box);
//        panel.setBackground(Color.blue);
//
//        info = panel;
//    }
//
//    public String getTime() {
//        Time time = new Time(System.currentTimeMillis());
//        String timeStr = time.toString();
//        String[] split = timeStr.split(":");
//
//        int hour = Integer.parseInt(split[0], 10);
//        String good;
//        if (hour < 12) {
//            good = split[0] + ":" + split[1] +"AM";
//        } else if (hour == 24) {
//            good = "12:" + split[1] + "AM";
//        } else if(hour == 12){
//            good = "12:" + split[1] + "PM";
//        }      
//        else {
//            int normHour = hour - 12;
//            good = normHour + ":" + split[1] + "PM";
//        }
//        return good;
//    }
//
//    public String getDay() {
//        Date day = new Date(System.currentTimeMillis());
//        String dayStr = day.toString();
//        String[] parts = dayStr.split(" ");
//        String dayOfWeek = "";
//        switch (parts[0]) {
//            case "Mon":
//                dayOfWeek = "Monday";
//                break;
//            case "Tue":
//                dayOfWeek = "Tuesday";
//                break;
//            case "Wed":
//                dayOfWeek = "Wednesday";
//                break;
//            case "Thu":
//                dayOfWeek = "Thursday";
//                break;
//            case "Fri":
//                dayOfWeek = "Friday";
//                break;
//            case "Sat":
//                dayOfWeek = "Saturday";
//                break;
//            case "Sun":
//                dayOfWeek = "Sunday";
//                break;
//            default:
//                dayOfWeek = "";
//                break;
//        }
//
//        String good = dayOfWeek + " " + parts[1] + " " + parts[2] + ", " + parts[5];
//        return good;
//    }
//}

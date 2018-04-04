/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import signon.Team;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author s531749
 */
public class SignUpID {
    JFrame frame;
    JFrame signedUp;
    JPanel addMember;
    Team team;
    boolean exited;
    Timer addedExit;
    
    String name;
    String id;
    boolean cancelled;
    
    Font f;
    Font f2;
    
    public SignUpID(ArrayList<Member> students){
        team = new Team(students);
        f = VorTX.f64;
        f2 = VorTX.f48;
        setUpScreen();
    }
    
    public void setUpScreen(){
        frame = new JFrame();
        frame.setSize(1280, 760);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(VorTX.logo.getImage());
        setUpAddPanel();
        Box b = Box.createVerticalBox();
        b.add(addMember);
        b.add(searchPanel());
        b.add(cancelPanel());
        b.setBackground(VorTX.blue);
        frame.add(b);
        frame.setBackground(VorTX.blue);
        //frame.setVisible(true);
    }
    
    public void setUpAddPanel(){
        addMember = new JPanel();
        
        JLabel label = new JLabel("Enter the Name of the Student to be Added:");
        label.setFont(f);
        label.setForeground(VorTX.green);
        addMember.add(label);
        
        JTextField nameEnter = new JTextField("", 30);
        nameEnter.setFont(f2);
        nameEnter.setForeground(VorTX.green);
        nameEnter.setBackground(VorTX.blue);
        
        addMember.add(nameEnter);
        
        JButton button = new JButton(new AbstractAction("Add Member") {
            public void actionPerformed(ActionEvent e) {
                try {
                    Document nameDoc = nameEnter.getDocument();
                    String studentName = nameDoc.getText(0, nameDoc.getLength());
                    name = studentName;
                    id = "" + (team.greatestID()+1);
                    addMember(name, id);
                    //System.out.println(studentName);
                } catch (BadLocationException ex) {
                    Logger.getLogger(SignUpID.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        button.setFont(f);
        addMember.add(button);
        addMember.setBackground(VorTX.blue);
    }
    
    public void addMember(String name, String id){
        frame.setVisible(false);
        frame.dispose();
        signedUp = signedUpFrame(name, id);
        
//        int delay = 500;
//        ActionListener update = new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                checkAddedExited(name);
//            }
//        };
//        
//        addedExit = new Timer(delay, update);
//        addedExit.start();
    }
    
//    public void checkAddedExited(String name){
//        if(exited == true){
//            addedExit.stop();
//            signedUp.setVisible(false);
//            signedUp.dispose();
//            team.addTeamMember(new Member(""+(team.greatestID()+1), name));
//            setUpScreen();
//        }
//    }
    
    public JPanel searchPanel(){
        JPanel panel = new JPanel();
        //Box box = Box.createVerticalBox();
        JLabel label = new JLabel("Enter the Name of the Student to search for their ID: ");
        label.setFont(f2);
        label.setForeground(VorTX.green);
        label.setBackground(VorTX.blue);
        panel.add(label);
        
        JTextField nameEnter = new JTextField("", 30);
        nameEnter.setFont(f2);
        nameEnter.setForeground(VorTX.green);
        nameEnter.setBackground(VorTX.blue);
        Document nameDoc = nameEnter.getDocument();
        
        JTextArea report = new JTextArea(1, 4);
        report.setFont(f2);
        report.setForeground(VorTX.green);
        report.setBackground(VorTX.blue);
        report.setEditable(false);
        
        nameDoc.addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    report.setText(idSearch(nameDoc.getText(0, nameDoc.getLength())));
                } catch (BadLocationException ex) {
                    Logger.getLogger(SignUpID.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    report.setText(idSearch(nameDoc.getText(0, nameDoc.getLength())));
                } catch (BadLocationException ex) {
                    Logger.getLogger(SignUpID.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            public void removeUpdate(DocumentEvent e) {
                try {
                    report.setText(idSearch(nameDoc.getText(0, nameDoc.getLength())));
                } catch (BadLocationException ex) {
                    Logger.getLogger(SignUpID.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(nameEnter);
        
        JLabel label2 = new JLabel("Your ID Number is: ");
        label2.setFont(f2);
        label2.setForeground(VorTX.green);
        label2.setBackground(VorTX.blue);
        panel.add(label2);
        
        panel.add(report);
        panel.setBackground(VorTX.blue);
        return panel;
    }
    
    public String idSearch(String name){
        String idFromTeam = team.idFromName(name);
        int id;
        if(idFromTeam.equals("None")){
            return "None";
        }else{
            id = Integer.parseInt(team.idFromName(name),10);
        }
        return ""+ id;
    }
    
    public JPanel cancelPanel(){
        JPanel panel = new JPanel();
        JButton button = new JButton(new AbstractAction("Cancel") {
            public void actionPerformed(ActionEvent e) {
                cancelled = true;
            }
        });
        button.setFont(f);
        panel.add(button);
        panel.setBackground(VorTX.blue);
        return panel;
    }
    
    public JFrame signedUpFrame(String name, String id){
        JFrame signedUp = new JFrame();
        signedUp.setIconImage(VorTX.logo.getImage());
        JPanel panel = new JPanel();
        Box b = Box.createVerticalBox();
        
        String messageTop = "Welcome, " + name;
        JLabel label = new JLabel(messageTop);
        label.setFont(f);
        label.setForeground(VorTX.green);
        label.setBackground(VorTX.blue);
        b.add(label);
        
        String messageBottom = "Your ID number is: " + id;
        JLabel label2 = new JLabel(messageBottom);
        label2.setFont(f);
        label2.setForeground(VorTX.green);
        label2.setBackground(VorTX.blue);
        b.add(label2);
        
        JButton button = new JButton(new AbstractAction("Okay") {
            public void actionPerformed(ActionEvent e) {
               exited=true;
            }
        });
        button.setFont(f);
        b.add(button);
        
        b.setForeground(VorTX.green);
        b.setBackground(VorTX.blue);
        
        panel.add(b);
        panel.setBackground(VorTX.blue);
        signedUp.add(panel,BorderLayout.NORTH);
        signedUp.setBackground(VorTX.blue);
        signedUp.setSize(1280, 760);
        signedUp.setBackground(VorTX.blue);
        signedUp.setVisible(true);
        
        return signedUp;
    }

    public boolean isExited() {
        return exited;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author Joshua
 */
public class IDEnter {

    JPanel show;
    JTextField idField;
    Document idText;

    JTextArea report;
    boolean good;

    private boolean finalized;

    private String id;
    private boolean inOrOut;
    private int indexOfStudent;
    private ArrayList<Member> students;

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(boolean inOrOut) {
        this.inOrOut = inOrOut;
    }

    public int getIndexOfStudent() {
        return indexOfStudent;
    }

    public void setIndexOfStudent(int indexOfStudent) {
        this.indexOfStudent = indexOfStudent;
    }
    
    public ArrayList<Member> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Member> students) {
        this.students = students;
    }

    public void changeMessage(String s) {
        report.setText(s);
    }

    public int idExists(String id) {
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                index = i;
                break;
            }

        }

        return index;
    }

    public void numInfo(boolean set, boolean inOrOut) {
        String idEntered = "";
        try {
            report.setText("");

            idEntered = idText.getText(0, idText.getLength());
            int idInt = Integer.parseInt(idEntered, 10);
            good = true;
//            if (idEntered.length() != 6) {
//                report.append("Please enter your 6 digit student ID.");
//                good = false;
//            }  
//            else if (idInt < 400000 || idInt > 800000) {
//                report.append("Please enter your team ID.");
//                good = false;
//            } 
            if (idExists(idEntered) == -1) {
                report.append("Please add new user to use that ID.");
                good = false;
            } else {
                report.append("Good");
                good = true;
            }

        } catch (NumberFormatException e) {
            report.setText("Please enter your team ID(Numbers Only).");
            good = false;
        } catch (Exception e) {
            report.setText(e.toString());
            good = false;
        }

        if (good && set) {
            id = idEntered;
            this.inOrOut = inOrOut;
            finalized = true;
            indexOfStudent = idExists(id);

            if (inOrOut) {
                report.setText("Welcome, " + students.get(indexOfStudent).getName());
            }else{
                report.setText("Goodbye, " + students.get(indexOfStudent).getName());
            }
        }
    }

    public IDEnter(ArrayList<Member> listStudents) {
        students = listStudents;
        finalized = false;

        show = new JPanel();
        idField = new JTextField("", 6);
        idField.setFont(VorTX.f96);
        idField.setBackground(VorTX.blue);
        idField.setForeground(VorTX.green);
        idText = idField.getDocument();

        report = new JTextArea(1, 27);
        report.setFont(VorTX.f48);
        report.setBackground(VorTX.blue);
        report.setForeground(VorTX.green);

        JPanel display = new JPanel();

        JLabel label = new JLabel("ID: ");
        label.setFont(VorTX.f96);
        label.setBackground(VorTX.blue);
        label.setForeground(VorTX.green);
        display.add(label);
        display.add(idField);
        display.setBackground(VorTX.blue);

        Box b = Box.createVerticalBox();
        b.add(display, BorderLayout.NORTH);
        JScrollPane pane = new JScrollPane(report);
        pane.setBackground(VorTX.blue);
        b.add(pane);
        b.setBackground(VorTX.blue);
        show.add(b, BorderLayout.NORTH);

        report.setEditable(false);
        //listeners
        idText.addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                numInfo(false, false);
            }

            public void insertUpdate(DocumentEvent e) {
                numInfo(false, false);
            }

            public void removeUpdate(DocumentEvent e) {
                numInfo(false, false);
            }
        });

        JPanel buttonPanel = new JPanel();

        JButton button = new JButton(new AbstractAction("Sign In") {

            public void actionPerformed(ActionEvent e) {
                numInfo(true, true);
            }
        });

        JButton button2 = new JButton(new AbstractAction("Sign Out") {

            public void actionPerformed(ActionEvent e) {
                numInfo(true, false);
            }
        });
        button.setFont(VorTX.f48);
        buttonPanel.add(button);
        button2.setFont(VorTX.f48);
        buttonPanel.add(button2);
        buttonPanel.setBackground(VorTX.blue);
        show.add(buttonPanel);
        show.setBackground(VorTX.blue);

        numInfo(false, false);
    }
}
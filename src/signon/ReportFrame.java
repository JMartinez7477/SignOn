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
import javax.swing.JFrame;
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
 * @author s531749
 */
public class ReportFrame extends JFrame{

    ArrayList<Member> students;

    public ReportFrame(ArrayList<Member> students) {
        super();
        this.students = students;
        setUpFrame();
    }

    public void setUpFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1370, 800);
    }

    JPanel idEnterPanel;
    JTextField idField;
    Document idText;

    JTextArea report;
    boolean good;

    private boolean exited;

    private String id;
    private int indexOfStudent;

    Font f = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 96);
    Font f2 = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 48);

    public boolean isFinalized() {
        return exited;
    }

    public void setFinalized(boolean finalized) {
        this.exited = finalized;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndexOfStudent() {
        return indexOfStudent;
    }

    public void setIndexOfStudent(int indexOfStudent) {
        this.indexOfStudent = indexOfStudent;
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
            if (idEntered.length() != 6) {
                report.append("There is no student with that ID.");
                good = false;
            } else if (idInt < 400000 || idInt > 800000) {
                report.append("There is no student with that ID.");
                good = false;
            } else if (idExists(idEntered) == -1) {
                report.append("There is no student with that ID.");
                good = false;
            } else {
                report.append("Good");
                good = true;
            }

        } catch (NumberFormatException e) {
            report.setText("Please enter your 6 digit student ID(Numbers Only).");
            good = false;
        } catch (Exception e) {
            report.setText(e.toString());
            good = false;
        }

        if (good && set) {
            id = idEntered;
            exited = true;
            indexOfStudent = idExists(id);

            report.setText("Welcome, " + students.get(indexOfStudent).getName());

        }
    }

    public void IDEnter() {
        exited = false;

        idEnterPanel = new JPanel();
        idField = new JTextField("", 6);
        idField.setFont(f);
        idField.setBackground(VorTX.blue);
        idField.setForeground(VorTX.green);
        idText = idField.getDocument();

        report = new JTextArea(1, 27);
        report.setFont(f2);
        report.setBackground(VorTX.blue);
        report.setForeground(VorTX.green);

        JPanel display = new JPanel();

        JLabel label = new JLabel("ID: ");
        label.setFont(f);
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
        idEnterPanel.add(b, BorderLayout.NORTH);

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

        JButton button = new JButton(new AbstractAction("Search") {

            public void actionPerformed(ActionEvent e) {
                numInfo(true, true);
            }
        });

        button.setFont(f2);
        buttonPanel.add(button);
        buttonPanel.setBackground(VorTX.blue);
        
        idEnterPanel.add(buttonPanel);
        idEnterPanel.setBackground(VorTX.blue);

        numInfo(false, false);
    }
}

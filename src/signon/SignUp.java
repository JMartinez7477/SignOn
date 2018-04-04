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
 * @author Joshua
 */
public class SignUp {

    JFrame frame;
    Font f = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 96);
    Font f2 = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[194], Font.BOLD, 86);

    JPanel show;
    JTextField nameField;
    Document nameText;

    JTextField idField;
    Document idText;

    JTextArea report;
    boolean good;
    boolean cancelled;

    private boolean finalized;

    private String name;
    private String id;

    public boolean isFinalized() {
        return finalized;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    

    public boolean idAvailable(String id, ArrayList<Member> students) {
        boolean available = true;
        for (Member student : students) {
            if (id.equals(student.getId())) {
                available = false;
                break;
            }
        }
        return available;
    }

    public void numInfo(boolean set, ArrayList<Member> students) {

        String nameEnter = "";
        String idEnter = "";
        try {
            report.setText("");
            nameEnter = nameText.getText(0, nameText.getLength());
            idEnter = idText.getText(0, idText.getLength());

            int idInt = Integer.parseInt(idEnter, 10);
            good = true;
            if (idEnter.length() != 6) {
                report.append("Please enter your name and your \n6 digit student ID.(Numbers Only)");
                good = false;
            } else if (idInt < 400000 || idInt > 800000) {
                report.append("Please enter your name and your \n6 digit student ID.(Numbers Only)");
                good = false;
            } else if(!idAvailable(idEnter, students)){
                report.append("ID already in use.");
                good = false;
            } else {
                report.append("Good");
            }

        } catch (NumberFormatException e) {
            report.setText("Please enter your name and your \n6 digit student ID.(Numbers Only)");
            good = false;
        } catch (Exception e) {
            report.setText(e.toString());
            good = false;
        }

        if (good && set) {
            id = idEnter;
            name = nameEnter;
            finalized = true;
        }

    }
    
    public void cancel(){
        cancelled = true;
        finalized = true;
    }

    public SignUp(ArrayList<Member> students) {
        cancelled = false;
        show = new JPanel();
        
        nameField = new JTextField("", 15);
        nameField.setFont(f2);
        nameField.setBackground(VorTX.blue);
        nameField.setForeground(VorTX.green);
        nameText = nameField.getDocument();

        idField = new JTextField("", 6);
        idField.setFont(f);
        idField.setBackground(VorTX.blue);
        idField.setForeground(VorTX.green);
        idText = idField.getDocument();

        report = new JTextArea(2, 7);
        report.setFont(f2);
        report.setBackground(VorTX.blue);
        report.setForeground(VorTX.green);

        JPanel display = new JPanel();
        JLabel label = new JLabel("Name: ");
        label.setFont(f2);
        label.setBackground(VorTX.blue);
        label.setForeground(VorTX.green);
        display.add(label);
        display.add(nameField);
        display.setBackground(VorTX.blue);

        JPanel display2 = new JPanel();
        JLabel label2 = new JLabel("ID: ");
        label2.setFont(f);
        label2.setBackground(VorTX.blue);
        label2.setForeground(VorTX.green);
        display2.add(label2);
        display2.add(idField);
        display2.setBackground(VorTX.blue);

        Box b = Box.createVerticalBox();
        b.add(display);
        b.add(display2);
        b.setBackground(VorTX.blue);

        Box all = Box.createVerticalBox();
        all.add(b, BorderLayout.NORTH);
        all.add(new JScrollPane(report));

        report.setEditable(false);
        //listeners
        nameText.addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                numInfo(false, students);
            }

            public void insertUpdate(DocumentEvent e) {
                numInfo(false, students);
            }

            public void removeUpdate(DocumentEvent e) {
                numInfo(false, students);
            }
        });
        idText.addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                numInfo(false, students);
            }

            public void insertUpdate(DocumentEvent e) {
                numInfo(false, students);
            }

            public void removeUpdate(DocumentEvent e) {
                numInfo(false, students);
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton button = new JButton(new AbstractAction("Sign Up") {

            public void actionPerformed(ActionEvent e) {
                if (good) {
                    numInfo(true, students);
                }
            }
        });
        button.setFont(f);
        buttonPanel.add(button);
        buttonPanel.setBackground(VorTX.blue);
        
        JPanel buttonPanel2 = new JPanel();
        JButton button2 = new JButton(new AbstractAction("Cancel") {

            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        button2.setFont(f);
        buttonPanel2.add(button2);
        buttonPanel2.setBackground(VorTX.blue);
        
        Box c = Box.createVerticalBox();
        c.add(buttonPanel);
        c.add(buttonPanel2);
        
        
        all.add(c, BorderLayout.SOUTH);
        all.setBackground(VorTX.blue);

        show.add(all);
        show.setBackground(VorTX.blue);

        frame = new JFrame("VorTX 3735");
        frame.setIconImage(VorTX.logo.getImage());
        frame.setSize(1370, 730);
        frame.setBackground(VorTX.blue);
        frame.add(show);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numInfo(false, students);
    }
}

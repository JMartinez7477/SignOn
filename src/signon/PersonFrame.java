/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
//import javafx.scene.paint.Color;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import signon.Member;
import signon.VorTX;

/**
 *
 * @author s531749
 */
public class PersonFrame extends JFrame {

    Member member;

    public PersonFrame(Member member) {
        super();
        this.member = member;
        setUp();
    }

    public void setUp() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1370, 760);
        Box b = Box.createVerticalBox();
        Box c = Box.createVerticalBox();

        JPanel infoPanel = new JPanel();
        
        JTextArea name = new JTextArea("Name: " + member.getName());
        name.setForeground(VorTX.green);
        name.setBackground(VorTX.blue);
        name.setFont(VorTX.f64);
        c.add(name);

        JTextArea id = new JTextArea("ID: " + member.getId());
        id.setForeground(VorTX.green);
        id.setBackground(VorTX.blue);
        id.setFont(VorTX.f64);
        c.add(id);

        JTextArea meetings = new JTextArea("Meetings Attended: "
                + member.getNumberOfMeetings());
        meetings.setForeground(VorTX.green);
        meetings.setBackground(VorTX.blue);
        meetings.setFont(VorTX.f64);
        c.add(meetings);

        JTextArea hours = new JTextArea("Total Hours: "
                + String.format("%.2f", member.getTotalTimeDouble()));
        hours.setForeground(VorTX.green);
        hours.setBackground(VorTX.blue);
        hours.setFont(VorTX.f64);
        c.add(hours);

        add(c,BorderLayout.NORTH);
        
        for (int i = 0; i < member.getNumberOfMeetings(); i++) {
            JTextArea area = new JTextArea(member.getMeetings().get(i).toNiceString());
            area.setForeground(VorTX.green);
            area.setBackground(VorTX.blue);
            area.setFont(VorTX.f48);
            //area.addMouseListener(new Listener(members.get(i)));
            b.add(area);
        }

        JButton exitButton = new JButton(new AbstractAction("Back") {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        exitButton.setFont(VorTX.f64);
        exitButton.setForeground(VorTX.green);
        exitButton.setBackground(VorTX.blue);
        add(exitButton,BorderLayout.SOUTH);

        JScrollPane scrollBar = new JScrollPane(b);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollBar);
        setForeground(VorTX.green);
        setBackground(VorTX.blue);
        setVisible(true);

    }

    public JScrollPane getMeetingsPanel() {
        JPanel panel = new JPanel();
        Box b = Box.createVerticalBox();

        for (int i = 0; i < member.getNumberOfMeetings(); i++) {
            JLabel label = new JLabel(member.getMeetings().get(i).toNiceString());
            label.setForeground(VorTX.green);
            label.setBackground(VorTX.blue);
            label.setFont(VorTX.f48);
            b.add(label);
        }

        b.setForeground(VorTX.green);
        b.setBackground(VorTX.blue);

        JScrollPane scrollBar = new JScrollPane(b);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollBar);
        panel.setForeground(VorTX.green);
        panel.setBackground(VorTX.blue);

        return scrollBar;
    }
}

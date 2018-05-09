/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.Document;
import signon.Condition;
import signon.Member;
import signon.VorTX;

/**
 *
 * @author S531749
 */
public class SearchFrame extends JFrame {

    SearchPanel searchPanel;
    private boolean cancelled;

    public SearchFrame(ArrayList<Member> members) {
        super("VorTX 3735");
        setUp(members);
    }

    public void setUp(ArrayList<Member> members) {
        setIconImage(VorTX.logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1370, 760);
        searchPanel = new SearchPanel(members);
        add(searchPanel);

        JButton exitButton = new JButton(new AbstractAction("Cancel") {
            public void actionPerformed(ActionEvent e) {
                cancelled = true;
            }
        });
        exitButton.setForeground(VorTX.green);
        exitButton.setBackground(VorTX.blue);
        exitButton.setFont(VorTX.f64);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(exitButton, BorderLayout.SOUTH);

        setForeground(VorTX.green);
        setBackground(VorTX.blue);
        setVisible(true);
    }

    public boolean isSearched() {
        return searchPanel.searched;
    }
    
    public boolean isCancelled(){
        return this.cancelled;
    }
}

class SearchPanel extends JPanel {

    ArrayList<Member> members;
    ArrayList<ConditionPanel> conditionPanels;
    boolean searched;

    ArrayList<Member> goodMembers;

    public SearchPanel(ArrayList<Member> members) {
        super();
        this.members = members;
        setUp();
    }

    public void setUp() {
        conditionPanels = new ArrayList();
        conditionPanels.add(new ConditionPanel(new Condition(Condition.Info.NAME), true));
        conditionPanels.add(new ConditionPanel(new Condition(Condition.Info.ID), false));
        conditionPanels.add(new ConditionPanel(new Condition(Condition.Info.HOURS), false));
        conditionPanels.add(new ConditionPanel(new Condition(Condition.Info.MEETINGS), false));

        Box b = Box.createVerticalBox();
        for (int i = 0; i < conditionPanels.size(); i++) {
            b.add(conditionPanels.get(i));
        }

        JButton searchButton = new JButton(new AbstractAction("Search") {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < conditionPanels.size(); i++) {
                    Member m = members.get(i);

                    conditionPanels.get(i).condition.setType(
                            (Condition.Type) conditionPanels.get(i).chooser.getSelectedItem());
                }
                String name = conditionPanels.get(0).getInput();
                int id = Integer.parseInt(conditionPanels.get(1).getInput());
                double hours = Double.parseDouble(conditionPanels.get(2).getInput());
                int meetings = Integer.parseInt(conditionPanels.get(3).getInput());
                goodMembers = search(name, id, hours, meetings);
                searched = true;
            }
        });
        searchButton.setForeground(VorTX.green);
        searchButton.setBackground(VorTX.blue);
        searchButton.setFont(VorTX.f64);
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);

        b.setForeground(VorTX.green);
        b.setBackground(VorTX.blue);
        add(b);

        add(searchButton);

        setForeground(VorTX.green);
        setBackground(VorTX.blue);
    }

    public ArrayList<Member> search(String name, int id, double hours, int meetings) {
        ArrayList<Member> goodMembers = new ArrayList();
        //System.out.println("Good Members:");
        for (Member currentMember : members) {
            boolean conditionsMet
                    = conditionPanels.get(0).condition.isMet(currentMember, name)
                    && conditionPanels.get(1).condition.isMet(currentMember, id)
                    && conditionPanels.get(2).condition.isMet(currentMember, hours)
                    && conditionPanels.get(3).condition.isMet(currentMember, meetings);
            if (conditionsMet) {
                //System.out.println(currentMember.toString());
                goodMembers.add(currentMember);
            }
        }
        return goodMembers;
    }

}

class ConditionPanel extends JPanel {

    Condition condition;
    boolean isString;
    JComboBox chooser;
    JTextField enterField;

    public ConditionPanel(Condition condition, boolean isString) {
        super();
        this.condition = condition;
        this.isString = isString;
        setUp();
    }

    public void setUp() {
        JLabel label = new JLabel(condition.getInfoString());
        label.setFont(VorTX.f48);
        label.setForeground(VorTX.green);
        label.setBackground(VorTX.blue);
        add(label);

        if (isString) {
            chooser = new JComboBox(condition.getStringTypes());
        } else {
            chooser = new JComboBox(condition.getDoubleTypes());
        }
        chooser.setFont(VorTX.f40);
        chooser.setForeground(VorTX.green);
        chooser.setBackground(VorTX.blue);
        add(chooser);

        enterField = new JTextField("", 18);
        enterField.setFont(VorTX.f40);
        enterField.setForeground(VorTX.green);
        enterField.setBackground(VorTX.blue);

        add(enterField);
        setForeground(VorTX.green);
        setBackground(VorTX.blue);
    }

    public String getInput() {
        String s = "";
        Document doc = enterField.getDocument();
        try {
            s = doc.getText(0, doc.getLength());
        } catch (Exception e) {
            System.out.println(e);
        }

        if (!isString && doc.getLength() == 0) {
            return "-1";
        }

        return s;
    }
}
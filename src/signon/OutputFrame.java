/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import signon.Member;
import signon.VorTX;

/**
 *
 * @author s531749
 */
public class OutputFrame extends JFrame {

    ArrayList<Member> members;
    private boolean exited;

    public OutputFrame(ArrayList<Member> members) {
        super("VorTX 3735");
        this.members = members;
        setUp();
    }

    public void setUp() {
        setIconImage(VorTX.logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1370, 760);
        Box b = Box.createVerticalBox();

        if(members.isEmpty()){
            JTextArea area = new JTextArea("There are no students who meet the criteria.");
            area.setForeground(VorTX.green);
            area.setBackground(VorTX.blue);
            area.setFont(VorTX.f64);
            b.add(area);
        }
        for (int i = 0; i < members.size(); i++) {
            JTextArea area = new JTextArea(members.get(i).toString());
            area.setForeground(VorTX.green);
            area.setBackground(VorTX.blue);
            area.setFont(VorTX.f64);
            area.addMouseListener(new Listener(members.get(i)));
            b.add(area);
        }
        
        JScrollPane scrollBar = new JScrollPane(b);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollBar);
        
        JButton exitButton = new JButton(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                exited = true;
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
    
    public boolean hasExited(){
        return this.exited;
    }
}

class Listener implements MouseListener {
        Member member;
        
        public Listener(Member member){
            this.member = member;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            //System.out.println(member.toString());
            PersonFrame frame = new PersonFrame(member);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
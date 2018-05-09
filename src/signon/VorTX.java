package signon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author Joshua
 */
public class VorTX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        fileHandler = new FileHandler("info.txt");
        students = fileHandler.load().getTeam();
//        students = new ArrayList<Student>();
//        students.add(new Student("531749", "Joshua Martinez"));
//        students.add(new Student("531745", "Joseph Martinez"));
        setUpScreen();
    }
    static FileHandler fileHandler;
    static ArrayList<Member> students;

    static private JFrame home;

    static ImageIcon logo;
    public static Color blue = new Color(0, 52, 76);
    public static Color green = new Color(124, 189, 71);
    public static Font f40;
    public static Font f48;
    public static Font f64;
    public static Font f96;
    public static Font f124;
    static Timer timer;
    static SignUpID sign;
    static SearchFrame searchFrame;
    static OutputFrame outputFrame;
    static Timer signUpCheck;
    static Timer idEnterCheck;
    static Timer saveTimer;
    static Timer searchedTimer;
    static Timer ouputExitedTimer;
    static TimeAndDay time;
    static IDEnter enter;

    public static void setUpScreen() {
        logo = new ImageIcon("logo.jpg");
        setUpFonts();
        home = new JFrame("VorTX 3735");
        home.setIconImage(logo.getImage());
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(1370, 760);
        time = new TimeAndDay();
        home.add(time.getInfo(), BorderLayout.NORTH);

        Box b = Box.createVerticalBox();
        enter = new IDEnter(students);
        b.add(enter.show);
        b.setBackground(blue);
        home.add(b);

        Box c = Box.createVerticalBox();
        c.add(signUpPanel(), BorderLayout.NORTH);
        c.add(exitPanel(), BorderLayout.SOUTH);
        home.add(c, BorderLayout.SOUTH);
        home.setBackground(blue);
        home.setVisible(true);
        doInfo();
        checkIfIDEntered();
        beginSaving();
    }

    public static void doNewFrame() {
        home = new JFrame("VorTX 3735");
        home.setIconImage(logo.getImage());
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(1370, 760);
        home.add(time.getInfo(), BorderLayout.NORTH);

        Box b = Box.createVerticalBox();
        enter = new IDEnter(students);
        b.add(enter.show);
        b.setBackground(blue);
        home.add(b);

        Box c = Box.createVerticalBox();
        c.add(signUpPanel(), BorderLayout.NORTH);
        c.add(exitPanel(), BorderLayout.SOUTH);
        home.add(c, BorderLayout.SOUTH);
        home.setBackground(blue);
        home.setVisible(true);
    }

    public static void setUpFonts() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        f40 = new Font(fonts[200], Font.BOLD, 40);
        f64 = new Font(fonts[200], Font.BOLD, 64);
        f48 = new Font(fonts[200], Font.BOLD, 48);
        f96 = new Font(fonts[200], Font.BOLD, 96);
        f124 = new Font(fonts[200], Font.BOLD, 124);
    }

    public static JPanel signUpPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton(new AbstractAction("Create or Search Users") {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
        button.setFont(f48);
        
        JButton button2 = new JButton(new AbstractAction("Reports") {
            public void actionPerformed(ActionEvent e) {
                doReport();
            }
        });
        button2.setFont(f48);
        
        panel.add(button);
        panel.add(button2);
        panel.setBackground(VorTX.blue);

        return panel;
    }

    public static JPanel exitPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        button.setFont(f48);
        panel.add(button);
        panel.setBackground(blue);
        return panel;
    }

    public static void exit() {
        JFrame exit = new JFrame("VorTX 3735");
        exit.setIconImage(logo.getImage());
        exit.setSize(1370, 730);
        exit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        JTextArea message = new JTextArea("Saving", 1, 17);
        message.setFont(f48);
        message.setBackground(blue);
        message.setForeground(green);
        message.setEditable(false);
        JScrollPane scroll = new JScrollPane(message);
        scroll.setBackground(blue);
        p.add(scroll, BorderLayout.NORTH);
        p.setBackground(blue);

        exit.add(p);
        timer.stop();
        idEnterCheck.stop();
        home.setVisible(false);
        home.dispose();
        exit.setBackground(blue);
        exit.setVisible(true);
        saveTimer.stop();
        save();

        message.setText("You may now exit.");
    }

    public static void beginSaving() {
        int delay = 300000;
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                save();
            }
        };
        saveTimer = new Timer(delay, update);
        saveTimer.start();
    }

    public static void save() {
        try {
            fileHandler.Save(students);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void doInfo() {
        int delay = 1000;
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                time.updateInfo();
                home.add(time.getInfo(), BorderLayout.NORTH);
                home.setVisible(true);
            }
        };
        timer = new Timer(delay, update);
        timer.start();
    }

    public static void signUp() {
        home.setVisible(false);
        sign = new SignUpID(students);
        sign.frame.setVisible(true);
        timer.stop();
        idEnterCheck.stop();
        int delay = 500;
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signUpCheckIfDone();
            }
        };
        signUpCheck = new Timer(delay, update);
        signUpCheck.start();
    }

    public static void signUpCheckIfDone() {
        if (sign.isCancelled()) {
            sign.frame.setVisible(false);
            signUpCheck.stop();
            sign.frame.dispose();

            doNewFrame();
            timer.start();
            idEnterCheck.start();
        }
        if (sign.isExited()) {

            String id = sign.getId();
            String name = sign.getName();

            sign.signedUp.setVisible(false);
            signUpCheck.stop();
            sign.signedUp.dispose();
            sign.frame.dispose();

            students.add(new Member(id, name));
            students.get(students.size() - 1).newMeeting();

            continueHomeScreen();
        }
    }

    public static void continueHomeScreen() {
        doNewFrame();
        timer.start();
        idEnterCheck.start();
    }

    public static void doReport() {
        searchFrame = new SearchFrame(students);
        home.setVisible(false);
        timer.stop();
        idEnterCheck.stop();

        int delay = 100;
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                checkSearched();
                checkSearchCancelled();
            }
        };
        searchedTimer = new Timer(delay, update);
        searchedTimer.start();
    }

    public static void checkSearched() {
        if (searchFrame.isSearched()) {
            searchedTimer.stop();
            searchFrame.setVisible(false);
            outputFrame = new OutputFrame(searchFrame.searchPanel.goodMembers);
            startExitedTimer();
        }
    }

    public static void checkSearchCancelled() {
        if (searchFrame.isCancelled()) {
            searchedTimer.stop();
            searchFrame.setVisible(false); 
            searchFrame.dispose();
            continueHomeScreen();
        }
    }
    
    public static void startExitedTimer(){
        int delay = 100;
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                checkExited();
            }
        };
        ouputExitedTimer = new Timer(delay, update);
        ouputExitedTimer.start();
    }
    
    public static void checkExited(){
        if(outputFrame.hasExited()){
            ouputExitedTimer.stop();
            outputFrame.setVisible(false);
            outputFrame.dispose();
            searchFrame.dispose();
            continueHomeScreen();
        }
    }

    public static void checkIfIDEntered() {
        int delay = 500;
        ActionListener check = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                idEntered();
            }
        };
        idEnterCheck = new Timer(delay, check);
        idEnterCheck.start();
    }

    public static void idEntered() {
        if (enter.isFinalized()) {
            idEnterCheck.stop();
            timer.stop();

            boolean inOrOut = enter.isInOrOut();
            int index = enter.getIndexOfStudent();
            if (inOrOut) {
                students.get(index).newMeeting();
            } else {
                students.get(index).endMeeting();
            }
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            home.dispose();
            doNewFrame();
            timer.start();
            idEnterCheck.start();
        }

    }
}

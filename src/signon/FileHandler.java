/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s531749
 */
public class FileHandler {

    private File file;
    private String fileName;
    private FileWriter fileWrite;
    private BufferedWriter writer;
    Scanner sc;

    private File logFile;
    private FileWriter logFileWriter;
    private BufferedWriter logWriter;

    public FileHandler(String fileName) throws IOException {
        this.fileName = fileName;
        file = new File(fileName);
        sc = new Scanner(file);

        startLogging();
    }

    public Team load() throws IOException {
        ArrayList<Member> team = new ArrayList<Member>();
        int length = sc.nextInt();
        for (int i = 0; i < length; i++) {
            team.add(loadStudent());
        }
        Team teamObb = new Team(team);
        return teamObb;
    }

    public Member loadStudent() {
        String id = sc.next();
        sc.nextLine();
        String name = sc.nextLine();
        Member current = new Member(id, name);
        int numMeetings = sc.nextInt();
        for (int i = 0; i < numMeetings; i++) {
            long start = sc.nextLong();
            long end = sc.nextLong();
            current.createOldMeeting(start, end);
        }
        sc.nextLine();

        //System.out.println(current.toString());
        return current;
    }

    public void Save(ArrayList<Member> team) throws IOException {
        try {
            fileWrite = new FileWriter(fileName);
            writer = new BufferedWriter(fileWrite);
        } catch (IOException io) {
            System.out.println("IO Error: " + io);
        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println("no error");
        writer.write("" + team.size());
        writer.newLine();
        for (Member member : team) {
            printStudentInfo(member);
        }
        writer.flush();
        fileWrite.flush();
        writer.close();
        fileWrite.close();
    }

    public void printStudentInfo(Member student) throws IOException {
        writer.write(student.getId());
        writer.newLine();
        writer.write(student.getName());
        writer.newLine();
        writer.write("" + student.getMeetings().size());
        writer.newLine();
        for (Meeting meeting : student.getMeetings()) {
            writer.write(meeting.toPrintInfo());
            writer.newLine();
        }
        writer.newLine();
    }

    public void startLogging() throws IOException {
        logFile = new File(("Log_" + TimeAndDay.getDateForFile() + ".txt"));
        try {
            logFileWriter = new FileWriter(logFile);
            logWriter = new BufferedWriter(logFileWriter);
        } catch (IOException io) {
            System.out.println("IO Error: " + io);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void logMessage(String message) throws IOException {
        logWriter.write(message);
        logWriter.newLine();
        logWriter.flush();
        logFileWriter.flush();
    }

    public void logMeeting(String name, boolean in) {
        try {
            if (in) {
                logMessage(name + " signed in at " + TimeAndDay.getTime());
            }else{
                logMessage(name + " signed out at " + TimeAndDay.getTime());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void logNewMember(String name, String id){
        try {
            logMessage(name + " joined the group with id " + id);
            logMeeting(name, true);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

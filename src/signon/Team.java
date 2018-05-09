/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

import java.util.ArrayList;

/**
 *
 * @author s531749
 */
public class Team {
    private ArrayList<Member> team;

    public Team(ArrayList<Member> team) {
        this.team = team;
    }

    public ArrayList<Member> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Member> team) {
        this.team = team;
    }
    
    public void addTeamMember(Member member){
        team.add(member);
    }
    
    public String idFromName(String name){   
        for (Member member : team) {
            if(member.getName().equals(name)){
                return member.getId();
            }
        }
        return "None";
    }
    
    public String nameFromID(String id){
        for (Member member : team) {
            if(member.getId().equals(id)){
                return member.getName();
            }
        }
        return "None";
    }
    
    public int greatestID(){
        int greatest = -1;
        for (Member member : team) {
            int memId = Integer.parseInt(member.getId(), 10);
            if (memId > greatest){
                greatest = memId;
            }
        }
        System.out.println(greatest);
        return greatest;
    }
    
    public int indexFromID(String ID){
        int index = -1;
        for (int i = 0; i < team.size(); i++) {
            if(team.get(i).getId().equals(ID)){
                index = i;
            }
        }
        return index;
    }
}
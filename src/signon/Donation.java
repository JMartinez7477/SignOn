/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

/**
 *
 * @author Joshua
 */
public class Donation {
    private double amt;
    final private long time;

    public Donation(double amt) {
        this.amt = amt;
        this.time = System.currentTimeMillis();
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
    
    public double getPoints(){
        return amt/2.0;
    }
    
    public String printInfo(){
        return "";
    }
}
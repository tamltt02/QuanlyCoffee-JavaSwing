/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class bieudongay {
    private double tongtien ;
    private int timee ;

    public bieudongay() {
    }

    public bieudongay(double tongtien,int time) {
        this.tongtien = tongtien;
        this.timee = time;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public int getTimee() {
        return timee;
    }

    public void setTimee(int timee) {
        this.timee = timee;
    }

    
    
}

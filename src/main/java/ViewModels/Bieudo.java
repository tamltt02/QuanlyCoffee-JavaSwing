/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Bieudo {
    private double tongtien ;
    private Date date ;

    public Bieudo() {
    }

    public Bieudo(double tongtien, Date date) {
        this.tongtien = tongtien;
        this.date = date;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}

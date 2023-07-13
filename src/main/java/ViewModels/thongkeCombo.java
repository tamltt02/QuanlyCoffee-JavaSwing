/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author ADMIN
 */
public class thongkeCombo {
    private String maCombo;
    private String tencombo ;
    private double giaban ;
    private long soluong ;

    public thongkeCombo() {
    }

    public thongkeCombo(String maCombo, String tencombo, double giaban, long soluong) {
        this.maCombo = maCombo;
        this.tencombo = tencombo;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public String getmaCombo() {
        return maCombo;
    }

    public void setmaCombo(String maCombo) {
        this.maCombo = maCombo;
    }

    public String gettencombo() {
        return tencombo;
    }

    public void settencombo(String tencombo) {
        this.tencombo = tencombo;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public long getSoluong() {
        return soluong;
    }

    public void setSoluong(long soluong) {
        this.soluong = soluong;
    }
    
}

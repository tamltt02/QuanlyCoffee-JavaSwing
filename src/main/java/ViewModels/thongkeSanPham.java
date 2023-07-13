/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author ADMIN
 */
public class thongkeSanPham {
    private String maSp;
    private String tensp ;
    private double giaban ;
    private long soluong ;

    public thongkeSanPham() {
    }

    public thongkeSanPham(String maSp, String tensp, double giaban, long soluong) {
        this.maSp = maSp;
        this.tensp = tensp;
        this.giaban = giaban;
        this.soluong = soluong;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
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

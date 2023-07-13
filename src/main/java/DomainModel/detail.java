/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModel;

/**
 *
 * @author ADMIN
 */
public class detail {
    private double tongtien;
    private long tonghd;
    private long tongsp;

    public detail() {
    }

    public detail(double tongtien, long tonghd, long tongsp) {
        this.tongtien = tongtien;
        this.tonghd = tonghd;
        this.tongsp = tongsp;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public long getTonghd() {
        return tonghd;
    }

    public void setTonghd(long tonghd) {
        this.tonghd = tonghd;
    }

    public long getTongsp() {
        return tongsp;
    }

    public void setTongsp(long tongsp) {
        this.tongsp = tongsp;
    }
    
}

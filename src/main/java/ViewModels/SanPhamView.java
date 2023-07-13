
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;


import DomainModel.Theloai;

/**
 *
 * @author lucif
 */
public class SanPhamView {

    private int ID_SanPham;
    private double giaTien;
    private String hinhAnh;
    private String maSanPham;
    private String tenSanPham;

    	private int trangThai;
        
        private Theloai theloai;

    public SanPhamView() {
    }

    public SanPhamView(int ID_SanPham, double giaTien, String hinhAnh, String maSanPham, String tenSanPham, int trangThai, Theloai theloai) {
        this.ID_SanPham = ID_SanPham;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.theloai = theloai;
    }


    public SanPhamView(int ID_SanPham, double giaTien, String hinhAnh, String maSanPham, String tenSanPham) {
        this.ID_SanPham = ID_SanPham;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
    }


    public int getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(int ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Theloai getTheloai() {
        return theloai;
    }

    public void setTheloai(Theloai theloai) {
        this.theloai = theloai;
    }

}

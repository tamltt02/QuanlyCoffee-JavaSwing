/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author lucif
 */
public class SelectedItems {
    
    private String hinhAnh;
    private String maSanPham;
    private String tenSanPham;
    private double giaTien;
    private int soLuong;

    public SelectedItems() {
    }

    public SelectedItems(String hinhAnh, String maSanPham, String tenSanPham, double giaTien, int soLuong) {
        this.hinhAnh = hinhAnh;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
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

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}

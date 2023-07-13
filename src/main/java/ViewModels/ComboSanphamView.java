/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModel.Combo;
import DomainModel.Sanpham;

/**
 *
 * @author LuongQuocBao
 */
public class ComboSanphamView {

    private int ID_Combo_SanPham;
    private int soLuong;
    private Combo combo;
    private Sanpham sanpham;

    public ComboSanphamView() {
    }

    public ComboSanphamView(int ID_Combo_SanPham, int soLuong, Combo combo, Sanpham sanpham) {
        this.ID_Combo_SanPham = ID_Combo_SanPham;
        this.soLuong = soLuong;
        this.combo = combo;
        this.sanpham = sanpham;
    }

    public ComboSanphamView(int soLuong, Combo combo, Sanpham sanpham) {
        this.soLuong = soLuong;
        this.combo = combo;
        this.sanpham = sanpham;
    }

    public int getID_Combo_SanPham() {
        return ID_Combo_SanPham;
    }

    public void setID_Combo_SanPham(int ID_Combo_SanPham) {
        this.ID_Combo_SanPham = ID_Combo_SanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Sanpham getSanpham() {
        return sanpham;
    }

    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }
    
    
}

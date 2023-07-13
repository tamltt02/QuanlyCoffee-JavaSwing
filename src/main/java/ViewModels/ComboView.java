/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author LuongQuocBao
 */
public class ComboView {
    	private int ID_ComBo;

	private double giaTien;

	private String HInhAnh;

	private String maComBo;

	private String tenComBo;

	private int trangThai;

    public ComboView() {
    }

    public ComboView(int ID_ComBo, double giaTien, String HInhAnh, String maComBo, String tenComBo, int trangThai) {
        this.ID_ComBo = ID_ComBo;
        this.giaTien = giaTien;
        this.HInhAnh = HInhAnh;
        this.maComBo = maComBo;
        this.tenComBo = tenComBo;
        this.trangThai = trangThai;
    }

    public ComboView(double giaTien, String HInhAnh, String maComBo, String tenComBo, int trangThai) {
        this.giaTien = giaTien;
        this.HInhAnh = HInhAnh;
        this.maComBo = maComBo;
        this.tenComBo = tenComBo;
        this.trangThai = trangThai;
    }
    
    

    public int getID_ComBo() {
        return ID_ComBo;
    }

    public void setID_ComBo(int ID_ComBo) {
        this.ID_ComBo = ID_ComBo;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHInhAnh() {
        return HInhAnh;
    }

    public void setHInhAnh(String HInhAnh) {
        this.HInhAnh = HInhAnh;
    }

    public String getMaComBo() {
        return maComBo;
    }

    public void setMaComBo(String maComBo) {
        this.maComBo = maComBo;
    }

    public String getTenComBo() {
        return tenComBo;
    }

    public void setTenComBo(String tenComBo) {
        this.tenComBo = tenComBo;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
        
        
}

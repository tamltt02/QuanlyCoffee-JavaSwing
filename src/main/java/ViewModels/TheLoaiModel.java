/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author LuongQuocBao
 */
public class TheLoaiModel {
    	private int maTheLoai;

	private String tenTheLoai;

    public TheLoaiModel() {
    }

    public TheLoaiModel(int maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
        
}

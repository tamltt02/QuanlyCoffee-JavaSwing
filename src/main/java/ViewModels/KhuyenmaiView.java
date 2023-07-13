/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class KhuyenmaiView {
    private int ID_KhuyenMai;

	private int chietKhau;

	private String maKhuyenMai;


	private Date ngayBatDau;

	private Date ngayKetThuc;

	private String tenKhuyenMai;

	private int trangThai;

    public KhuyenmaiView(int chietKhau, String maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, String tenKhuyenMai, int trangThai) {
        this.chietKhau = chietKhau;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenKhuyenMai = tenKhuyenMai;
        this.trangThai = trangThai;
    }

	public KhuyenmaiView() {
	}

    public KhuyenmaiView(int ID_KhuyenMai, int chietKhau, String maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, String tenKhuyenMai, int trangThai) {
        this.ID_KhuyenMai = ID_KhuyenMai;
        this.chietKhau = chietKhau;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenKhuyenMai = tenKhuyenMai;
        this.trangThai = trangThai;
    }

        
        

	public int getID_KhuyenMai() {
		return this.ID_KhuyenMai;
	}

	public void setID_KhuyenMai(int ID_KhuyenMai) {
		this.ID_KhuyenMai = ID_KhuyenMai;
	}
	
	public int getChietKhau() {
		return this.chietKhau;
	}

	public void setChietKhau(int chietKhau) {

		this.chietKhau = chietKhau;
	}

	public String getMaKhuyenMai() {
		return this.maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public Date getNgayBatDau() {
		return this.ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return this.ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getTenKhuyenMai() {
		return this.tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}	

}

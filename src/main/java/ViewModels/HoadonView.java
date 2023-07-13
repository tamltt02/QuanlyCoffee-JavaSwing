package ViewModels;

import DomainModel.Khuyenmai;
import DomainModel.Nhanvien;
import java.sql.Time;
import java.util.Date;



public class HoadonView {
	
	private int ID_HoaDon;

	private String ghiChu;

	private String maHoaDon;

	
	private Date ngayTao;

	private Time thoiGian;

	private int trangThai;

	private Khuyenmai khuyenmai;
        
	private Nhanvien nhanvien;

	public HoadonView() {
	}

    public HoadonView( String ghiChu, String maHoaDon, Date ngayTao, Time thoiGian, int trangThai, Khuyenmai khuyenmai, Nhanvien nhanvien) {
        this.ghiChu = ghiChu;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
        this.khuyenmai = khuyenmai;
        this.nhanvien = nhanvien;
    }

	public int getID_HoaDon() {
		return this.ID_HoaDon;
	}

	public void setID_HoaDon(int ID_HoaDon) {
		this.ID_HoaDon = ID_HoaDon;
	}

	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getMaHoaDon() {
		return this.maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Time getThoiGian() {
		return this.thoiGian;
	}

	public void setThoiGian(Time thoiGian) {
		this.thoiGian = thoiGian;
	}

	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	

	

	public Khuyenmai getKhuyenmai() {
		return this.khuyenmai;
	}

	public void setKhuyenmai(Khuyenmai khuyenmai) {
		this.khuyenmai = khuyenmai;
	}

	public Nhanvien getNhanvien() {
		return this.nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

}
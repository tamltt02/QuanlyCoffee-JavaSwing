package ViewModels;

import DomainModel.Ban;
import DomainModel.Hoadon;

public class Hoadoinchitiet {

	private int idHdct;

	private double donGia;

	private byte kieu;

	private int ma;

	private String maHoaDonChiTiet;

	private int soLuong;

	private Hoadon hoadon;

        private Ban ban ;
	public Hoadoinchitiet() {
	}

	public int getIdHdct() {
		return this.idHdct;
	}

	public void setIdHdct(int idHdct) {
		this.idHdct = idHdct;
	}

	public double getDonGia() {
		return this.donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public byte getKieu() {
		return this.kieu;
	}

	public void setKieu(byte kieu) {
		this.kieu = kieu;
	}

	public int getMa() {
		return this.ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getMaHoaDonChiTiet() {
		return this.maHoaDonChiTiet;
	}

	public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
		this.maHoaDonChiTiet = maHoaDonChiTiet;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Ban getBan() {
		return this.ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}

	public Hoadon getHoadon() {
		return this.hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}

}
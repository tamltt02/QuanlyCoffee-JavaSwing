package ViewModels;

import DomainModel.Ban;
import DomainModel.Hoadon;

public class HoadoinchitietView {

	private int idHdct;

	private double donGia;

	private byte kieu;

	private int ma;

	private String maHoaDonChiTiet;

	private int soLuong;

	private Hoadon hoadon;

        private Ban ban ;
        
        private String ghiChu ;
        
	public HoadoinchitietView() {
	}

    public HoadoinchitietView(int idHdct, double donGia, byte kieu, int ma, String maHoaDonChiTiet, int soLuong, Hoadon hoadon, Ban ban, String ghiChu) {
        this.idHdct = idHdct;
        this.donGia = donGia;
        this.kieu = kieu;
        this.ma = ma;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.soLuong = soLuong;
        this.hoadon = hoadon;
        this.ban = ban;
        this.ghiChu = ghiChu;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
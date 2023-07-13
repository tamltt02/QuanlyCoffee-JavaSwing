package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Entity
@NamedQuery(name="Hoadon.findAll", query="SELECT h FROM Hoadon h")
public class Hoadon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ID_HoaDon;

	private String ghiChu;

	private String maHoaDon;

	@Temporal(TemporalType.DATE)
	private Date ngayTao;

	private Time thoiGian;

	private int trangThai;

	//bi-directional many-to-one association to Hoadoinchitiet
	@OneToMany(mappedBy="hoadon")
	private List<Hoadoinchitiet> hoadoinchitiets;

	//bi-directional many-to-one association to Khuyenmai
	@ManyToOne
	@JoinColumn(name="IDKhuyenMai")
	private Khuyenmai khuyenmai;

	//bi-directional many-to-one association to Nhanvien
	@ManyToOne
	@JoinColumn(name="IDNhanVien")
	private Nhanvien nhanvien;

	public Hoadon() {
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

	public List<Hoadoinchitiet> getHoadoinchitiets() {
		return this.hoadoinchitiets;
	}

	public void setHoadoinchitiets(List<Hoadoinchitiet> hoadoinchitiets) {
		this.hoadoinchitiets = hoadoinchitiets;
	}

	public Hoadoinchitiet addHoadoinchitiet(Hoadoinchitiet hoadoinchitiet) {
		getHoadoinchitiets().add(hoadoinchitiet);
		hoadoinchitiet.setHoadon(this);

		return hoadoinchitiet;
	}

	public Hoadoinchitiet removeHoadoinchitiet(Hoadoinchitiet hoadoinchitiet) {
		getHoadoinchitiets().remove(hoadoinchitiet);
		hoadoinchitiet.setHoadon(null);

		return hoadoinchitiet;
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
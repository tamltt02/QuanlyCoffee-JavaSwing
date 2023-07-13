package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;




@Entity
@NamedQuery(name="Khuyenmai.findAll", query="SELECT k FROM Khuyenmai k")
public class Khuyenmai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ID_KhuyenMai;

	private int chietKhau;

	private String maKhuyenMai;

	@Temporal(TemporalType.DATE)
	private Date ngayBatDau;

	@Temporal(TemporalType.DATE)
	private Date ngayKetThuc;

	private String tenKhuyenMai;

	private int trangThai;

	//bi-directional many-to-one association to Hoadon
	@OneToMany(mappedBy="khuyenmai")
	private List<Hoadon> hoadons;

	public Khuyenmai() {
	}

    public Khuyenmai(int ID_KhuyenMai, int chietKhau, String maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, String tenKhuyenMai, int trangThai, List<Hoadon> hoadons) {
        this.ID_KhuyenMai = ID_KhuyenMai;
        this.chietKhau = chietKhau;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenKhuyenMai = tenKhuyenMai;
        this.trangThai = trangThai;
        this.hoadons = hoadons;
    }

    public Khuyenmai(int chietKhau, String maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, String tenKhuyenMai, int trangThai) {
        this.chietKhau = chietKhau;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tenKhuyenMai = tenKhuyenMai;
        this.trangThai = trangThai;
        
    }

    public Khuyenmai(int ID_KhuyenMai, int chietKhau, String maKhuyenMai, Date ngayBatDau, Date ngayKetThuc, String tenKhuyenMai, int trangThai) {
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

	public List<Hoadon> getHoadons() {
		return this.hoadons;
	}

	public void setHoadons(List<Hoadon> hoadons) {
		this.hoadons = hoadons;
	}

	public Hoadon addHoadon(Hoadon hoadon) {
		getHoadons().add(hoadon);
		hoadon.setKhuyenmai(this);

		return hoadon;
	}

	public Hoadon removeHoadon(Hoadon hoadon) {
		getHoadons().remove(hoadon);
		hoadon.setKhuyenmai(null);

		return hoadon;
	}


    @Override
    public String toString() {
        return   tenKhuyenMai+"   " +chietKhau+"%"  ;
    }
        

}
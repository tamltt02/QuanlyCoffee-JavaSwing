package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the nhanvien database table.
 *
 */
@Entity
@NamedQuery(name = "Nhanvien.findAll", query = "SELECT b FROM Nhanvien b")
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_NhanVien;

    private String diaChi;

    private String email;

    private int gioITinh;

    private String maNhanVien;

    private String matKhau;

    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    private String soDienThoai;

    private String taiKhoan;

    private String tenNhanVien;

    private int trangThai;

    private int vaiTro;

    //bi-directional many-to-one association to Hoadon
    @OneToMany(mappedBy = "nhanvien")
    private List<Hoadon> hoadons;

    public Nhanvien() {
    }

    public Nhanvien(String diaChi, String email, int gioITinh, String maNhanVien, String matKhau, Date ngaySinh, String soDienThoai, String taiKhoan, String tenNhanVien, int trangThai, int vaiTro) {
        this.diaChi = diaChi;
        this.email = email;
        this.gioITinh = gioITinh;
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
        this.vaiTro = vaiTro;
    }

    public Nhanvien(int ID_NhanVien, String diaChi, String email, int gioITinh, String maNhanVien, String matKhau, Date ngaySinh, String soDienThoai, String taiKhoan, String tenNhanVien, int trangThai, int vaiTro) {
        this.ID_NhanVien = ID_NhanVien;
        this.diaChi = diaChi;
        this.email = email;
        this.gioITinh = gioITinh;
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.taiKhoan = taiKhoan;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
        this.vaiTro = vaiTro;
    }


    public int getID_NhanVien() {
        return this.ID_NhanVien;
    }

    public void setID_NhanVien(int ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGioITinh() {
        return this.gioITinh;
    }

    public void setGioITinh(int gioITinh) {
        this.gioITinh = gioITinh;
    }

    public String getMaNhanVien() {
        return this.maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return this.matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getTenNhanVien() {
        return this.tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getVaiTro() {
        return this.vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public List<Hoadon> getHoadons() {
        return this.hoadons;
    }

    public void setHoadons(List<Hoadon> hoadons) {
        this.hoadons = hoadons;
    }

    public Hoadon addHoadon(Hoadon hoadon) {
        getHoadons().add(hoadon);
        hoadon.setNhanvien(this);

        return hoadon;
    }

    public Hoadon removeHoadon(Hoadon hoadon) {
        getHoadons().remove(hoadon);
        hoadon.setNhanvien(null);

        return hoadon;
    }

}

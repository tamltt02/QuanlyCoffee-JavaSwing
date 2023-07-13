package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the sanpham database table.
 *
 */
@Entity
@NamedQuery(name = "Sanpham.findAll", query = "SELECT s FROM Sanpham s")
//@NamedNativeQuery(name="updateSanPham",
//                  query= "Update SanPham s Set giaTien = ?, hinhAnh = ?, tenSanPham = ?, trangThai = ?, theloai = ? Where maSanPham = ?",
//                  resultSetMapping = "updateResult")
public class Sanpham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int ID_SanPham;

    private double giaTien;

    private String hinhAnh;

    private String maSanPham;

    private String tenSanPham;

    private int trangThai;

    //bi-directional many-to-one association to ComboSanpham
    @OneToMany(mappedBy = "sanpham")
    private List<ComboSanpham> comboSanphams;

    //bi-directional many-to-one association to Theloai
    @ManyToOne
    @JoinColumn(name = "MaTheLoai")
    private Theloai theloai;

    public Sanpham() {
    }

    public Sanpham(int ID_SanPham, double giaTien, String hinhAnh, String maSanPham, String tenSanPham, int trangThai, Theloai theloai) {
        this.ID_SanPham = ID_SanPham;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.theloai = theloai;
    }

    public Sanpham(int ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }
    
    

    public int getID_SanPham() {
        return this.ID_SanPham;
    }

    public void setID_SanPham(int ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    public double getGiaTien() {
        return this.giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhAnh() {
        return this.hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public List<ComboSanpham> getComboSanphams() {
        return this.comboSanphams;
    }

    public void setComboSanphams(List<ComboSanpham> comboSanphams) {
        this.comboSanphams = comboSanphams;
    }

    public ComboSanpham addComboSanpham(ComboSanpham comboSanpham) {
        getComboSanphams().add(comboSanpham);
        comboSanpham.setSanpham(this);

        return comboSanpham;
    }

    public ComboSanpham removeComboSanpham(ComboSanpham comboSanpham) {
        getComboSanphams().remove(comboSanpham);
        comboSanpham.setSanpham(null);

        return comboSanpham;
    }

    public Theloai getTheloai() {
        return this.theloai;
    }

    public void setTheloai(Theloai theloai) {
        this.theloai = theloai;
    }

    @Override
    public String toString() {
        return tenSanPham;
    }

}

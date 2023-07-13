package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the combo database table.
 *
 */
@Entity
@NamedQuery(name = "Combo.findAll", query = "SELECT c FROM Combo c")
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_ComBo;

    private double giaTien;

    private String HInhAnh;

    private String maComBo;

    private String tenComBo;

    private int trangThai;

    @OneToMany(mappedBy = "combo")
    private List<ComboSanpham> comboSanphams;

    public Combo() {
    }

    public Combo(int ID_ComBo, double giaTien, String HInhAnh, String maComBo, String tenComBo, int trangThai) {
        this.ID_ComBo = ID_ComBo;
        this.giaTien = giaTien;
        this.HInhAnh = HInhAnh;
        this.maComBo = maComBo;
        this.tenComBo = tenComBo;
        this.trangThai = trangThai;
    }

    public Combo(double giaTien, String HInhAnh, String maComBo, String tenComBo, int trangThai) {
        this.giaTien = giaTien;
        this.HInhAnh = HInhAnh;
        this.maComBo = maComBo;
        this.tenComBo = tenComBo;
        this.trangThai = trangThai;
    }

    public Combo(int ID_ComBo) {
        this.ID_ComBo = ID_ComBo;
    }

    public int getID_ComBo() {
        return this.ID_ComBo;
    }

    public void setID_ComBo(int ID_ComBo) {
        this.ID_ComBo = ID_ComBo;
    }

    public double getGiaTien() {
        return this.giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getHInhAnh() {
        return this.HInhAnh;
    }

    public void setHInhAnh(String HInhAnh) {
        this.HInhAnh = HInhAnh;
    }

    public String getMaComBo() {
        return this.maComBo;
    }

    public void setMaComBo(String maComBo) {
        this.maComBo = maComBo;
    }

    public String getTenComBo() {
        return this.tenComBo;
    }

    public void setTenComBo(String tenComBo) {
        this.tenComBo = tenComBo;
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
        comboSanpham.setCombo(this);

        return comboSanpham;
    }

    public ComboSanpham removeComboSanpham(ComboSanpham comboSanpham) {
        getComboSanphams().remove(comboSanpham);
        comboSanpham.setCombo(null);

        return comboSanpham;
    }

}

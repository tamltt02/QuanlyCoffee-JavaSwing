package DomainModel;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the combo_sanpham database table.
 *
 */
@Entity
@Table(name = "combo_sanpham")
//@NamedQuery(name = "ComboSanpham.findAll", query = "SELECT c FROM ComboSanpham c")
public class ComboSanpham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Combo_SanPham;

    //bi-directional many-to-one association to Combo
    @ManyToOne
    @JoinColumn(name = "ID_ComBo")
    private Combo combo;

    //bi-directional many-to-one association to Sanpham
    @ManyToOne
    @JoinColumn(name = "ID_SanPham")
    private Sanpham sanpham;

    @JoinColumn(name = "soLuong")
    private int soLuong;
    
    public ComboSanpham() {
    }

    public ComboSanpham(int ID_Combo_SanPham, int soLuong, Combo combo, Sanpham sanpham) {
        this.ID_Combo_SanPham = ID_Combo_SanPham;
        this.soLuong = soLuong;
        this.combo = combo;
        this.sanpham = sanpham;
    }

    public ComboSanpham(int soLuong, Combo combo, Sanpham sanpham) {
        this.soLuong = soLuong;
        this.combo = combo;
        this.sanpham = sanpham;
    }

    public int getID_Combo_SanPham() {
        return this.ID_Combo_SanPham;
    }

    public void setID_Combo_SanPham(int ID_Combo_SanPham) {
        this.ID_Combo_SanPham = ID_Combo_SanPham;
    }

    public int getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Combo getCombo() {
        return this.combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Sanpham getSanpham() {
        return this.sanpham;
    }

    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }

}

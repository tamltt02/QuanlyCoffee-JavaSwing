package DomainModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



@Entity
@NamedQuery(name="Theloai.findAll", query="SELECT t FROM Theloai t")
public class Theloai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int maTheLoai;

	private String tenTheLoai;

	//bi-directional many-to-one association to Sanpham
	@OneToMany(mappedBy="theloai")
	private List<Sanpham> sanphams;

	public Theloai() {
	}

	public int getMaTheLoai() {
		return this.maTheLoai;
	}

	public void setMaTheLoai(int maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return this.tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	public List<Sanpham> getSanphams() {
		return this.sanphams;
	}

	public void setSanphams(List<Sanpham> sanphams) {
		this.sanphams = sanphams;
	}

	public Sanpham addSanpham(Sanpham sanpham) {
		getSanphams().add(sanpham);
		sanpham.setTheloai(this);

		return sanpham;
	}

	public Sanpham removeSanpham(Sanpham sanpham) {
		getSanphams().remove(sanpham);
		sanpham.setTheloai(null);

		return sanpham;
	}

	@Override
	public String toString() {
		return tenTheLoai;
	}
	
	

}
package ViewModels;



public class TheloaiView  {

	private int maTheLoai;

	private String tenTheLoai;


	public TheloaiView() {
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

	
	@Override
	public String toString() {
		return tenTheLoai;
	}
	
	

}
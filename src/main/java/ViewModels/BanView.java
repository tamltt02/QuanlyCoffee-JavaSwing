package ViewModels;

public class BanView {
    private int ID_Ban;
    private String maBan;
    private int trangThai;

    private int soGhe;

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }


    public BanView() {
    }

    public BanView(int ID_Ban, String maBan, int trangThai,int soGhe) {
        this.ID_Ban = ID_Ban;
        this.maBan = maBan;
        this.trangThai = trangThai;
        this.soGhe = soGhe;

    }

    public BanView(String maBan, int trangThai, int soGhe) {
        this.maBan = maBan;
        this.trangThai = trangThai;
        this.soGhe = soGhe;
    }

    
    
    public int getID_Ban() {
        return ID_Ban;
    }

    public void setID_Ban(int ID_Ban) {
        this.ID_Ban = ID_Ban;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
    
    



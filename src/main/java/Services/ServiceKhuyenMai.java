/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Khuyenmai;
import DomainModel.Nhanvien;
import ViewModels.KhuyenmaiView;
import ViewModels.NhanvienView;
import Views.KhuyenMai;
import java.util.List;
import repositories.ImplBangKhuyenMai;
import repositories.ImplBangNhanVien;


/**
 *
 * @author Admin
 */
public class ServiceKhuyenMai {
    private final ImplBangKhuyenMai _dao;
    
    public ServiceKhuyenMai() {
        _dao = new ImplBangKhuyenMai();
        getlst();
    }
    
    public List<Khuyenmai> getlst() {
        return _dao.findAll();
    }
    
    public KhuyenmaiView create(KhuyenmaiView km) {
      _dao.create(new Khuyenmai(km.getChietKhau(), km.getMaKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getTenKhuyenMai(),km.getTrangThai()));
      return new KhuyenmaiView(km.getChietKhau(), km.getMaKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getTenKhuyenMai(),km.getTrangThai());
    }
    
    public KhuyenmaiView update(KhuyenmaiView km) {
      _dao.update(new Khuyenmai(km.getID_KhuyenMai(),km.getChietKhau(), km.getMaKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getTenKhuyenMai(),km.getTrangThai()));
      return new KhuyenmaiView(km.getID_KhuyenMai(),km.getChietKhau(), km.getMaKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getTenKhuyenMai(),km.getTrangThai());
    }
}

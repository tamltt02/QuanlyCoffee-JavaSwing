/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;


import DomainModel.Hoadon;
import DomainModel.Nhanvien;
import ViewModels.HoadonView;
import ViewModels.NhanvienView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangNhanVien;
import repositories.InterfaceBangNhanVien;

/**
 *
 * @author ADMIN
 */
public class ServiceNhanVien implements IServiceBangNhanVien{
       InterfaceBangNhanVien daonv;
    List<NhanvienView> listNVModel;
private  ImplBangNhanVien _dao;

    public ServiceNhanVien() {
        daonv = new ImplBangNhanVien();
daonv = new ImplBangNhanVien();
        _dao = new ImplBangNhanVien();
        getlst();
        
    }
    
    public List<Nhanvien> getlst() {
        return _dao.findAll();
    }
    
    public NhanvienView create(NhanvienView nv) {
        _dao.create(new Nhanvien(nv.getDiaChi(), nv.getEmail(), nv.getGioITinh(), nv.getMaNhanVien(), nv.getMatKhau(), nv.getNgaySinh(), nv.getSoDienThoai(), nv.getTaiKhoan(), nv.getTenNhanVien(), nv.getTrangThai(), nv.getVaiTro()));
        return new NhanvienView(nv.getDiaChi(), nv.getEmail(), nv.getGioITinh(), nv.getMaNhanVien(), nv.getMatKhau(), nv.getNgaySinh(), nv.getSoDienThoai(), nv.getTaiKhoan(), nv.getTenNhanVien(), nv.getTrangThai(), nv.getVaiTro());
    }

    public NhanvienView update(NhanvienView nv) {
        _dao.update(new Nhanvien(nv.getID_NhanVien(),nv.getDiaChi(), nv.getEmail(), nv.getGioITinh(), nv.getMaNhanVien(), nv.getMatKhau(), nv.getNgaySinh(), nv.getSoDienThoai(), nv.getTaiKhoan(), nv.getTenNhanVien(), nv.getTrangThai(), nv.getVaiTro()));
        return new NhanvienView(nv.getID_NhanVien(),nv.getDiaChi(), nv.getEmail(), nv.getGioITinh(), nv.getMaNhanVien(), nv.getMatKhau(), nv.getNgaySinh(), nv.getSoDienThoai(), nv.getTaiKhoan(), nv.getTenNhanVien(), nv.getTrangThai(), nv.getVaiTro());

    }
//D_NhanVien = ID_NhanVien;
//        this.diaChi = diaChi;
//        this.email = email;
//        this.gioITinh = gioITinh;
//        this.maNhanVien = maNhanVien;
//        this.matKhau = matKhau;
//        this.ngaySinh = ngaySinh;
//        this.soDienThoai = soDienThoai;
//        this.taiKhoan = taiKhoan;
//        this.tenNhanVien = tenNhanVien;
//        this.trangThai = trangThai;
//        this.vaiTro = vaiTro;
//        this.hoadons = hoadons;
//    }


//    @Override
//    public List<NhanvienView> findAll(int position, int pageSize) {
//        return daonv.findAll(position, pageSize);
//    }

    @Override
    public List<NhanvienView> findAll() {
        List<NhanvienView> list = new ArrayList();
        for (Nhanvien x : daonv.findAll()) {
            list.add(new NhanvienView(x.getID_NhanVien(), x.getDiaChi(), x.getEmail(), x.getGioITinh(), x.getMatKhau(), x.getMatKhau(), x.getNgaySinh(), x.getSoDienThoai(), x.getTaiKhoan(), x.getTenNhanVien(), x.getTrangThai(), x.getVaiTro()));
        }
       return list;
    }

    @Override
    public Nhanvien findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Nhanvien remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long totalCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanvienView> findAll(int position, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanvienView login(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanvienView quenmatkhau(String email, String taikhoan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}

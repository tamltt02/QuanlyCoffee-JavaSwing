/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Sanpham;

import ViewModels.SanPhamView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangSanPham;
import repositories.InterfaceBangSanPham;

/**
 *
 * @author lucif
 */
public class ServiceSanPham implements IServiceSanPham{

    private final ImplBangSanPham _daosp;
    private List<SanPhamView> _lstSanPhamMod = new ArrayList<SanPhamView>();
    private List<Sanpham> _lstSanPhams = new ArrayList<Sanpham>();

    public ServiceSanPham() {
        _daosp = new ImplBangSanPham();
    }

    @Override
    public List<SanPhamView> getSanPham() {
        _lstSanPhamMod = new ArrayList<>();
        var sanPham = _daosp.findAll(1, 9);
        for (Sanpham x : sanPham) {
            _lstSanPhamMod.add(new SanPhamView(x.getID_SanPham(), x.getGiaTien(), x.getHinhAnh(),
                    x.getMaSanPham(), x.getTenSanPham(), x.getTrangThai(), x.getTheloai()));
        }
        return _lstSanPhamMod;
    }

    @Override
    public SanPhamView getSanPhamById(int id) {
        var x = _daosp.findById(id);
        return new SanPhamView(x.getID_SanPham(), x.getGiaTien(), x.getHinhAnh(),
                x.getMaSanPham(), x.getTenSanPham(), x.getTrangThai(), x.getTheloai());
    }

    @Override
    public SanPhamView createNewSanPham(SanPhamView sanPhamModel) {
        sanPhamModel.setID_SanPham(0);
        var x = _daosp.create(new Sanpham(sanPhamModel.getID_SanPham(), sanPhamModel.getGiaTien(), sanPhamModel.getHinhAnh(),
                sanPhamModel.getMaSanPham(), sanPhamModel.getTenSanPham(), sanPhamModel.getTrangThai(), sanPhamModel.getTheloai()));
        return new SanPhamView(x.getID_SanPham(), x.getGiaTien(), x.getHinhAnh(),
                x.getMaSanPham(), x.getTenSanPham(), x.getTrangThai(), x.getTheloai());
    }

    @Override
    public SanPhamView updateSanPhamById(SanPhamView sanPhamModel) {
        var x = _daosp.update(new Sanpham(sanPhamModel.getID_SanPham(), sanPhamModel.getGiaTien(), sanPhamModel.getHinhAnh(),
                sanPhamModel.getMaSanPham(), sanPhamModel.getTenSanPham(), sanPhamModel.getTrangThai(), sanPhamModel.getTheloai()));
        return new SanPhamView(x.getID_SanPham(), x.getGiaTien(), x.getHinhAnh(),
                x.getMaSanPham(), x.getTenSanPham(), x.getTrangThai(), x.getTheloai());
    }

    @Override
    public int deleteSanPhamById(Sanpham sp) {
        int idsp = sp.getID_SanPham();
        _daosp.remove(idsp);
        return -1;
    }
    
    @Override
     public List<Sanpham> getlst() {
        return _daosp.findSP();
    }

    
    @Override
    public List<SanPhamView> findByType(int type) {
        List<SanPhamView> lstSPView = new ArrayList<>();
        InterfaceBangSanPham daoSP = new ImplBangSanPham();
        for (Sanpham x : daoSP.findByType(type)) {
            lstSPView.add(new SanPhamView(x.getID_SanPham(), x.getGiaTien(), x.getHinhAnh(), x.getMaSanPham(), x.getTenSanPham()));
        }
        return lstSPView;
    }

    @Override
    public long totalCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int findIDByMa(String ma){
        for (Sanpham x : getlst()) {
            if (x.getMaSanPham().equals(ma)) {
                return x.getID_SanPham();
            }
        }
        return -1;
    }
    
 
}

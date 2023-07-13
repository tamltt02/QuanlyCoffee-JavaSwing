/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Hoadon;
import ViewModels.HoadonView;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import repositories.ImplBangHoaDon;
import repositories.InterfaceBangHoaDon;

/**
 *
 * @author ADMIN
 */
public class ServiceHoaDon implements IServiceBangHoaDon{
    InterfaceBangHoaDon daohd ;
    public ServiceHoaDon() {
        daohd = new ImplBangHoaDon();
        
    }
    Hoadon setdata1(HoadonView hdv){
        Hoadon hd = new Hoadon();
        hd.setNgayTao(hdv.getNgayTao());
        hd.setGhiChu(hdv.getGhiChu());
        hd.setKhuyenmai(hdv.getKhuyenmai());
        hd.setNhanvien(hdv.getNhanvien());
        hd.setTrangThai(hdv.getTrangThai());
        hd.setThoiGian(hdv.getThoiGian());
   return hd ;
    }
    
        HoadonView setdata1(Hoadon hdv){
        HoadonView hd = new HoadonView();
        hd.setNgayTao(hdv.getNgayTao());
        hd.setGhiChu(hdv.getGhiChu());
        hd.setKhuyenmai(hdv.getKhuyenmai());
        hd.setNhanvien(hdv.getNhanvien());
        hd.setTrangThai(hdv.getTrangThai());
        hd.setThoiGian(hdv.getThoiGian());
   return hd ;
    }
    @Override
    public List<HoadonView> findAll(int position, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoadonView> findAll() {
        List<HoadonView> list = new ArrayList();
        for (Hoadon x : daohd.findAll()) {
            list.add(new HoadonView( x.getGhiChu(), x.getMaHoaDon(), x.getNgayTao(), x.getThoiGian(),x.getTrangThai(), x.getKhuyenmai(), x.getNhanvien()));
        }
       return list;
    }

    @Override
    public HoadonView findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoadonView create(Hoadon hoadon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoadonView update(Hoadon hoadon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long totalCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoadonView> findByTStatus(int a) {
        List<HoadonView> lisst = new ArrayList();
        for (HoadonView x : findAll()) {
            if(x.getTrangThai()== a){
              lisst.add(x);
        }
        }
        return lisst;
    }

    @Override
    public List<HoadonView> findByDate(Date date1, Date date2) {
        List<HoadonView> lisst = new ArrayList();
        for (Hoadon x : daohd.findByDate(date1, date2)) {
            lisst.add(new HoadonView(x.getGhiChu(), x.getMaHoaDon(), x.getNgayTao(), x.getThoiGian(), x.getTrangThai(), x.getKhuyenmai(), x.getNhanvien()));
        }
        return lisst;
    }



   
    
}

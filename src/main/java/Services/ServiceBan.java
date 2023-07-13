/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Ban;

import DomainModel.Hoadoinchitiet;
import DomainModel.Hoadon;
import ViewModels.BanView;
import ViewModels.SelectedItems;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangBan;
import repositories.ImplBangHoaDon;
import repositories.ImplBangHoaDonChiTiet;
import repositories.InterfaceBangBan;
import repositories.InterfaceBangHoaDon;
import repositories.InterfaceBangHoaDonChiTiet;

/**
 *
 * @author lucif
 */
public class ServiceBan implements IServiceBan {

    InterfaceBangBan daoBan = new ImplBangBan();
    InterfaceBangHoaDon daoHD = new ImplBangHoaDon();
    InterfaceBangHoaDonChiTiet daoHDCT = new ImplBangHoaDonChiTiet();
    ImplBangBan _dao;
    

    public ServiceBan() {
        _dao = new ImplBangBan();
        getlst();
    }
    
    public List<Ban> getlst() {
        return _dao.findAll();
    }
    
    public BanView create(BanView ban) {
        _dao.create(new Ban(ban.getMaBan(), ban.getTrangThai(),ban.getSoGhe()));
        return new BanView(ban.getMaBan(), ban.getTrangThai(),ban.getSoGhe());
    }

    public BanView update(BanView ban) {
        _dao.update(new Ban(ban.getID_Ban(),ban.getMaBan(),ban.getTrangThai(),ban.getSoGhe()));
        return new BanView(ban.getID_Ban(),ban.getMaBan(),ban.getTrangThai(),ban.getSoGhe());
    }

    @Override
    public List<BanView> findByStatus(int status) {
        if (status == 2) {
            List<BanView> lstBanView = new ArrayList<>();
            for (Ban x : daoBan.findAll()) {
                lstBanView.add(new BanView(x.getID_Ban(), x.getMaBan(), x.getTrangThai(), x.getSoGhe()));
            }
            return lstBanView;
        } else {
            List<BanView> lstBanView = new ArrayList<>();
            for (Ban x : daoBan.findByStatus(status)) {
                lstBanView.add(new BanView(x.getID_Ban(), x.getMaBan(), x.getTrangThai(), x.getSoGhe()));
            }
            return lstBanView;
        }
    }

    @Override
    public List<BanView> findBanGop(int idBanGop) {

        List<BanView> lstBanView = new ArrayList<>();
        for (Ban x : daoBan.findByStatus(1)) {
            if (x.getID_Ban() != idBanGop) {
                lstBanView.add(new BanView(x.getID_Ban(), x.getMaBan(), x.getTrangThai(), x.getSoGhe()));
            }
        }
        return lstBanView;

    }

    @Override
    public List<Hoadoinchitiet> showSelectedItems(int ID_ban, int trangthai) {
        if (trangthai == 0) {
            List<Hoadoinchitiet> lst = new ArrayList<>();
            return lst;
        } else {
            List<Hoadoinchitiet> lst = new ArrayList<>();
            lst = daoBan.findAllSelectedItem(ID_ban);
            return lst;
        }
    }

    @Override
    public void chuyenBan(int idBanDi, int idBanToi) {
        List<Hoadoinchitiet> lst = daoHDCT.findByIdBan(idBanDi);
        for (Hoadoinchitiet x : lst) {
            x.setBan(daoBan.findById2(idBanToi));
            if (x.getGhiChu() == null) {
                x.setGhiChu("");
            }
            x.setGhiChu(x.getGhiChu() + " Chuyển từ :B" + idBanDi);
            daoHDCT.update(x);
        }
        Ban bantoi = daoBan.findById2(idBanToi);
        Ban ban = daoBan.findById2(idBanDi);
        bantoi.setTrangThai(1);
        ban.setTrangThai(0);
        daoBan.update(ban);
        daoBan.update(bantoi);
    }

    @Override
    public void gopBan(int idBanDi, int idBanToi) {
        List<Hoadoinchitiet> lst = daoHDCT.findByIdBan(idBanDi);
        Hoadon hoadon = daoHD.findHoaDonByBan(idBanToi);
        Hoadon hoadonBanDi = daoHD.findHoaDonByBan(idBanDi);
        for (Hoadoinchitiet x : lst) {
            x.setBan(daoBan.findById2(idBanToi));
            x.setHoadon(hoadon);
            if (x.getGhiChu() == null) {
                x.setGhiChu("");
            }
            x.setGhiChu(x.getGhiChu() + " Gộp từ B" + idBanDi);
            daoHDCT.update(x);
        }
        Ban ban = daoBan.findById2(idBanDi);
        ban.setTrangThai(0);
        daoBan.update(ban);
        hoadonBanDi.setTrangThai(1);
        daoHD.update(hoadonBanDi);
    }

}

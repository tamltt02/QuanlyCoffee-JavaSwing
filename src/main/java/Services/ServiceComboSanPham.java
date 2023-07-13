/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.ComboSanpham;
import DomainModel.Sanpham;
import ViewModels.ComboSanphamView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangComboSanPham;

/**
 *
 * @author Ngọc Hùng
 */
public class ServiceComboSanPham {
    private final ImplBangComboSanPham _dao;

    public ServiceComboSanPham() {
        _dao = new ImplBangComboSanPham();
        getlst();
    }
    
    public List<ComboSanpham> getlst() {
        return _dao.findAll();
    }
    
    public List<ComboSanpham> findByIDCB(int id){
        List<ComboSanpham> lst = new ArrayList<>();
        for (ComboSanpham x : getlst()) {
            if (x.getCombo().getID_ComBo()==id) {
                lst.add(x);
            }
        }
        return lst;
    }
    
    public int findIDByMa(String ma){
        for (ComboSanpham x : getlst()) {
            if (x.getCombo().getMaComBo().equals(ma)) {
                return x.getCombo().getID_ComBo();
            }
        }
        return -1;
    }
    
    
    
    public ComboSanphamView create(ComboSanphamView cb) {
//int ID_Combo_SanPham, int soLuong, Combo combo, Sanpham sanpham
        _dao.create(new ComboSanpham(cb.getSoLuong(),cb.getCombo(),cb.getSanpham()));
        return new ComboSanphamView(cb.getSoLuong(),cb.getCombo(),cb.getSanpham());
    }

    public ComboSanphamView update(ComboSanphamView cb) {
        _dao.update(new ComboSanpham(cb.getID_Combo_SanPham(),cb.getSoLuong(),cb.getCombo(),cb.getSanpham()));
        return new ComboSanphamView(cb.getID_Combo_SanPham(),cb.getSoLuong(),cb.getCombo(),cb.getSanpham());
    }
}

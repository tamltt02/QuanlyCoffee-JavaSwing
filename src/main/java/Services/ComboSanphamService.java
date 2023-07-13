/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Combo;
import DomainModel.ComboSanpham;
import ViewModels.ComboSanphamView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangComboSanPham;

/**
 *
 * @author LuongQuocBao
 */
public class ComboSanphamService implements IComboSanphamService{
    private final ImplBangComboSanPham _daoCBSP;
    private List<ComboSanphamView> _lstComboMod = new ArrayList<ComboSanphamView>();
    private List<Combo> _lstCombo = new ArrayList<Combo>();

    public ComboSanphamService() {
        _daoCBSP = new ImplBangComboSanPham();
    }
    
    @Override
    public ComboSanphamView createNewCombo(ComboSanphamView comboSPModel) {
                comboSPModel.setID_Combo_SanPham(0);
        var x = _daoCBSP.create(new ComboSanpham(comboSPModel.getID_Combo_SanPham(), comboSPModel.getSoLuong(), comboSPModel.getCombo(),
                comboSPModel.getSanpham()));
        return new ComboSanphamView(x.getID_Combo_SanPham(), x.getSoLuong(), x.getCombo(), x.getSanpham());
    }
    
}

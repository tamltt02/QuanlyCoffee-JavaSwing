/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModel.Combo;
import ViewModels.ComboView;
import java.util.ArrayList;
import java.util.List;
import repositories.ImplBangCombo;

/**
 *
 * @author LuongQuocBao
 */
public class ComboService implements IComboService{
    private final ImplBangCombo _daoCB;
    private List<ComboView> _lstComboMod = new ArrayList<ComboView>();
    private List<Combo> _lstCombos = new ArrayList<Combo>();

    public ComboService() {
        _daoCB = new ImplBangCombo();
    }

    @Override
    public List<ComboView> getCombo() {
        _lstComboMod = new ArrayList<>();
        var combo = _daoCB.findAll(1, 9);
        for (Combo x : combo) {
            _lstComboMod.add(new ComboView(x.getID_ComBo(), x.getGiaTien(), x.getHInhAnh(),
                    x.getMaComBo(), x.getTenComBo(), x.getTrangThai()));
        }
        return _lstComboMod;
    }

    @Override
    public ComboView getComboById(String id) {
        var x = _daoCB.findById(Integer.parseInt(id) );
        return new ComboView(x.getID_ComBo(), x.getGiaTien(), x.getHInhAnh(),
                    x.getMaComBo(), x.getTenComBo(), x.getTrangThai());
    }

    @Override
    public ComboView createNewCombo(ComboView comboModel) {
        comboModel.setID_ComBo(0);
        var x = _daoCB.create(new Combo(comboModel.getID_ComBo(), comboModel.getGiaTien(), comboModel.getHInhAnh(),
                comboModel.getMaComBo(), comboModel.getTenComBo(), comboModel.getTrangThai()));
        return new ComboView(x.getID_ComBo(), x.getGiaTien(), x.getHInhAnh(),
                    x.getMaComBo(), x.getTenComBo(), x.getTrangThai());
    }

    @Override
    public ComboView updateComboById(ComboView comboModel) {
        var x = _daoCB.update(new Combo(comboModel.getID_ComBo(), comboModel.getGiaTien(), comboModel.getHInhAnh(),
                comboModel.getMaComBo(), comboModel.getTenComBo(), comboModel.getTrangThai()));
        return new ComboView(x.getID_ComBo(), x.getGiaTien(), x.getHInhAnh(),
                    x.getMaComBo(), x.getTenComBo(), x.getTrangThai());
    }

    @Override
    public int deleteComboById(Combo cb) {
        return -1;
    }
    
     public List<Combo> getlst() {
        return _daoCB.findAll(1, 9);
    }
}

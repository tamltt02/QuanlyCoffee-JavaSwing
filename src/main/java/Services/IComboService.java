/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Combo;
import ViewModels.ComboView;
import java.util.List;

/**
 *
 * @author LuongQuocBao
 */
public interface IComboService {
    List<ComboView> getCombo();
    
    ComboView getComboById(String id);
    
    ComboView createNewCombo(ComboView comboModel);
    
    ComboView updateComboById(ComboView comboModel);
    
    int deleteComboById(Combo cb);
}

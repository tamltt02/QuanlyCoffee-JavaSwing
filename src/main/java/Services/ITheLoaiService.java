/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Theloai;
import ViewModels.TheLoaiModel;
import java.util.List;

/**
 *
 * @author LuongQuocBao
 */
public interface ITheLoaiService {
            List<TheLoaiModel> getTheLoai(int position, int pageSize);
    
    TheLoaiModel getTheLoaiById(int id);
    
    TheLoaiModel createNewTheLoai(TheLoaiModel theLoaiModel);
    
    TheLoaiModel updateTheLoaiById(TheLoaiModel theLoaiModel);
    
    int deleteTheLoaiById(Theloai sp);
}

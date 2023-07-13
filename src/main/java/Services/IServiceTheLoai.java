/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Theloai;
import ViewModels.TheloaiView;
import java.util.List;

/**
 *
 * @author LuongQuocBao
 */
public interface IServiceTheLoai {
            List<TheloaiView> getTheLoai(int position, int pageSize);
    
    TheloaiView getTheLoaiById(int id);
    
    TheloaiView createNewTheLoai(TheloaiView theLoaiModel);
    
    TheloaiView updateTheLoaiById(TheloaiView theLoaiModel);
    
    int deleteTheLoaiById(Theloai sp);
}

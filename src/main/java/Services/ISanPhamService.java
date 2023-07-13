/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Sanpham;
import ViewModels.SanPhamModel;
import java.util.List;

/**
 *
 * @author LuongQuocBao
 */
public interface ISanPhamService {
        List<SanPhamModel> getSanPham();
    
    SanPhamModel getSanPhamById(int id);
    
    SanPhamModel createNewSanPham(SanPhamModel sanPhamModel);
    
    SanPhamModel updateSanPhamById(SanPhamModel sanPhamModel);
    
    int deleteSanPhamById(Sanpham sp);
}

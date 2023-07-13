/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Sanpham;

import ViewModels.SanPhamView;
import java.util.List;

/**
 *
 * @author lucif
 */
public interface IServiceSanPham {

    List<SanPhamView> getSanPham();

    SanPhamView getSanPhamById(int id);

    SanPhamView createNewSanPham(SanPhamView sanPhamModel);

    SanPhamView updateSanPhamById(SanPhamView sanPhamModel);

    int deleteSanPhamById(Sanpham sp);

    List<Sanpham> getlst();

    long totalCount();

    List<SanPhamView> findByType(int type);


}

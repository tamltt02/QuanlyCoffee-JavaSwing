package Services;

import java.util.List;
import DomainModel.Sanpham;
import ViewModels.SanPhamView;

public interface IServiceBangSanPham {
	
	 	List<SanPhamView> getSanPham();

    SanPhamView getSanPhamById(int id);

    SanPhamView createNewSanPham(SanPhamView sanPhamModel);

    SanPhamView updateSanPhamById(SanPhamView sanPhamModel);

    int deleteSanPhamById(Sanpham sp);

    List<Sanpham> getlst();

    long totalCount();

    List<SanPhamView> findByType(int type);
}

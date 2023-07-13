package Services;

import java.util.List;
import DomainModel.Khuyenmai;

import ViewModels.KhuyenmaiView;

public interface IServiceBangKhuyenMai {
	
	List<KhuyenmaiView> findAll(int page,int pagesize);
        
        List<KhuyenmaiView> findAll();
	
	Khuyenmai findById(int id);
    
	Khuyenmai create(KhuyenmaiView km);
    
	Khuyenmai update(KhuyenmaiView km);

    long totalCount();

    List<Khuyenmai> find (int page,int pagesize, String name);
}

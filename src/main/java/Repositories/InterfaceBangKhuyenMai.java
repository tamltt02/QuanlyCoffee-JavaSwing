package repositories;

import java.util.List;
import DomainModel.Khuyenmai;

public interface InterfaceBangKhuyenMai {
	
	List<Khuyenmai> findAll(int page,int pagesize);
	

        List<Khuyenmai> findAll();
        

	Khuyenmai findById(String maKM);
    
	Khuyenmai create(Khuyenmai khuyenmai);
    
	Khuyenmai update(Khuyenmai khuyenmai);

    
    long totalCount();

    List<Khuyenmai> find (int page,int pagesize, String name);
}

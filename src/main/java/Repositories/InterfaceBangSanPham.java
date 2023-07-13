package repositories;

import java.util.List;
import DomainModel.Sanpham;

public interface InterfaceBangSanPham {
	
	 	List<Sanpham> findAll(int position, int pageSize);

                
                List<Sanpham> findByType(int type);

	    
	 	Sanpham findById(int id);
	    
	 	Sanpham create(Sanpham SanPham);
		
	 	Sanpham update(Sanpham SanPham);
	    
	 	Sanpham remove(int id);
	    
	    long totalCount();

            List<Sanpham> findSP();

}

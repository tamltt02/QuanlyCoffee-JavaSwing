package repositories;

import java.util.Date;
import java.util.List;
import DomainModel.Hoadon;

public interface InterfaceBangHoaDon {
	
	List<Hoadon> findAll(int position, int pageSize);
	 List<Hoadon> findAll() ;
	Hoadon findById(int id);
    
	Hoadon create(Hoadon hoadon);
	
	Hoadon update(Hoadon hoadon);
    
    long totalCount();
    List<Hoadon> findByTStatus (int a, int position, int pageSize);
    List<Hoadon> findByDate (Date date1,Date date2);
     List<Hoadon> findByTStatusvaTrangthai (int a, Date date, int position, int pageSize);

     public Hoadon findHoaDonByBan (int idBan);

}

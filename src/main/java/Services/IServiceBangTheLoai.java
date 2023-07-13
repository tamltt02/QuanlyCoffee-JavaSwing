package Services;

import java.util.List;

import DomainModel.Theloai;


public interface IServiceBangTheLoai {
	
	List<Theloai> findAll();
    
	Theloai findById(int id);
}

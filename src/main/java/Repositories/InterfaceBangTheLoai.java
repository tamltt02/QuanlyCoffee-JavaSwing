package repositories;

import java.util.List;

import DomainModel.Theloai;


public interface InterfaceBangTheLoai {
	
	List<Theloai> findAll();
    
	Theloai findById(int id);
}

package repositories;

import java.util.List;
import DomainModel.Nhanvien;

public interface InterfaceBangNhanVien {



    List<Nhanvien> findAll(int position, int pageSize);

    List<Nhanvien> findAll();

    Nhanvien findById(int id);

    Nhanvien create(Nhanvien NhanVien);

    Nhanvien update(Nhanvien NhanVien);

    Nhanvien remove(int id);

    long totalCount();

    public Nhanvien login(String userName, String password);

    public Nhanvien findbyEmail(String email, String taikhoan);


}

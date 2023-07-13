package Services;

import java.util.List;
import DomainModel.Nhanvien;
import ViewModels.NhanvienView;

public interface IServiceBangNhanVien {

    List<NhanvienView> findAll(int position, int pageSize);

    List<NhanvienView> findAll();

    Nhanvien findById(int id);

    Nhanvien remove(int id);

    long totalCount();

    public NhanvienView login(String userName, String password);

    public NhanvienView quenmatkhau(String email, String taikhoan);
}

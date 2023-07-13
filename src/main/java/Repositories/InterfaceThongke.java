/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModel.detail;
import ViewModels.Bieudo;
import ViewModels.bieudongay;
import ViewModels.thongkeCombo;
import ViewModels.thongkeSanPham;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public interface InterfaceThongke {

    public double tongtien();

    public Long tonghd();

    public Long tongsp();

    List<detail> listdata();

    List<detail> listdatatheongay(Date date);

    List<detail> listdatatheokhoang(Date date1, Date date2);

    public List<detail> listdatatheothang(int thang, int year);

    public List<bieudongay> getbdByTKNgay(Date ngay);

    public void setDataThang(JPanel pnlNgay, int thang, int year);

    public List<Bieudo> getbdByTKtheokhosng(Date date1, Date date2);

    public void setDataNgay(JPanel pnlNgay, Date jdateNgay);

    public List<detail> listdatatheonam(int year);

    public List<Bieudo> getbdByTKnam(int year);

    public void setDataNam(JPanel pnlNgay, int nam);

    public void setDataKhoang(JPanel pnlNgay, Date ngayBD, Date ngayKT);

    public List<thongkeSanPham> thongkesptheongay(Date date);

    public List<thongkeCombo> thongkecbtheongay(Date date);

    public List<thongkeSanPham> thongkesptheokhoang(Date date1, Date date2);

    public List<thongkeCombo> thongkecbtheokhoang(Date date1, Date date2);

    public List<thongkeCombo> thongkecbtheothang(int thang, int nam);

    public List<thongkeSanPham> thongkesptheothang(int thang, int nam);

    public List<thongkeCombo> thongkecbtheonam(int nam);

    public List<thongkeSanPham> thongkesptheonam(int nam);

    public void  setDatangaynull (JPanel pnlNgay,Date date);
    
    public String guiBCN(Date date);

}

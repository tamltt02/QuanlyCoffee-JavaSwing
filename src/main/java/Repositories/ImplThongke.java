/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModel.Hoadon;
import DomainModel.detail;
import Utilities.JpaUtils;
import ViewModels.Bieudo;
import ViewModels.bieudongay;
import ViewModels.thongkeCombo;
import ViewModels.thongkeSanPham;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ADMIN
 */

public class ImplThongke implements InterfaceThongke {

    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
    SimpleDateFormat formatThang = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");

    @Override
    public double tongtien() {
         EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select SUM(hdct.donGia * hdct.soLuong) FROM  Hoadoinchitiet hdct";
        Query query = entityManager.createQuery(jsql);
        return (double) query.getSingleResult();
    }

    @Override
    public Long tonghd() {
         EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select count(h.ID_HoaDon) FROM Hoadon h";
        Query query = entityManager.createQuery(jsql);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long tongsp() {
         EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select sum(hdct.soLuong) FROM  Hoadoinchitiet hdct";
        Query query = entityManager.createQuery(jsql);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<detail> listdata() { EntityManager entityManager = JpaUtils.getEntityManager();
        List<detail> list = new ArrayList<>();
        String jsql = "select new DomainModel.detail(SUM(hdct.donGia * hdct.soLuong), count(h.ID_HoaDon), sum(hdct.soLuong)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon";
        Query query = entityManager.createQuery(jsql);
        list = query.getResultList();
        return list;
    }

    @Override
    public List<detail> listdatatheongay(Date date) { EntityManager entityManager = JpaUtils.getEntityManager();
        try {
            List<detail> list = new ArrayList<>();
            String jsql = "select new DomainModel.detail(SUM(hdct.donGia * hdct.soLuong), count(h.ID_HoaDon), sum(hdct.soLuong)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where h.ngayTao =?1";
            Query query = entityManager.createQuery(jsql);
            query.setParameter(1, date);
            list = query.getResultList();
            if (list != null) {
                return list;
            }
            System.out.println("làm gì có gì");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi r");
            return null;
        }
    }

    @Override
    public List<detail> listdatatheokhoang(Date date1, Date date2) { EntityManager entityManager = JpaUtils.getEntityManager();
        try {
            List<detail> list = new ArrayList<>();
            String jsql = "select new DomainModel.detail(SUM(hdct.donGia * hdct.soLuong), count(h.ID_HoaDon), sum(hdct.soLuong)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where h.ngayTao between ? 1 and ? 2";
            Query query = entityManager.createQuery(jsql);
            query.setParameter(1, date1);
            query.setParameter(2, date2);
            list = query.getResultList();
            if (list != null) {
                return list;
            }
            System.out.println("Loi");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi r");
            return null;
        }
    }

    @Override
    public List<detail> listdatatheothang(int thang, int year) { EntityManager entityManager = JpaUtils.getEntityManager();
        try {
            List<detail> list = new ArrayList<>();
            String jsql = "select new DomainModel.detail(SUM(hdct.donGia * hdct.soLuong), count(h.ID_HoaDon), sum(hdct.soLuong)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where month(h.ngayTao) = ?1 and year(h.ngayTao) = ?2";
            Query query = entityManager.createQuery(jsql);
            query.setParameter(1, thang);
            query.setParameter(2, year);
            list = query.getResultList();
            if (list != null) {
                return list;
            }
            System.out.println("làm gì có gì");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi r");
            return null;
        }
    }

    @Override
    public List<detail> listdatatheonam(int year) { EntityManager entityManager = JpaUtils.getEntityManager();
        try {
            List<detail> list = new ArrayList<>();
            String jsql = "select new DomainModel.detail(SUM(hdct.donGia * hdct.soLuong), count(h.ID_HoaDon), sum(hdct.soLuong)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where year(h.ngayTao) = ?1";
            Query query = entityManager.createQuery(jsql);
            query.setParameter(1, year);
            list = query.getResultList();
            if (list != null) {
                return list;
            }
            System.out.println("làm gì có gì");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi r");
            return null;
        }
    }

// biểu đồ theo ngày 
    @Override
    public List<bieudongay> getbdByTKNgay(Date ngay) { EntityManager entityManager = JpaUtils.getEntityManager();
        List<bieudongay> list = new ArrayList<>();
        String jsql = "select new ViewModels.bieudongay( SUM(hdct.donGia * hdct.soLuong),HOUR(h.thoiGian)) FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where h.ngayTao =?1 group by HOUR(h.thoiGian)";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, ngay);
        list = query.getResultList();;
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public void setDataNgay(JPanel pnlNgay, Date jdateNgay) {

        List<bieudongay> list = getbdByTKNgay(jdateNgay);
        System.out.println(list.size());
        DefaultCategoryDataset data = new DefaultCategoryDataset();
            for (bieudongay o : list) {
                String s = String.valueOf(o.getTongtien());
                float tien = Float.valueOf(s);
                String gio = String.valueOf(o.getTimee());
                System.out.println("" + tien + gio);
                data.addValue(tien, "Số tiền", gio);
               
               
            }
           
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu ngày".toUpperCase(), "Gio", "Số Tiền", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    public List<Bieudo> getbdByTKthang(int thang, int year) { EntityManager entityManager = JpaUtils.getEntityManager();
        List<Bieudo> list = new ArrayList<>();
        String jsql = "select new ViewModels.Bieudo(SUM(hdct.donGia * hdct.soLuong),h.ngayTao)  FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where month(h.ngayTao) = ?1 and year(h.ngayTao) = ?2 group by h.ngayTao";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, thang);
        query.setParameter(2, year);
        list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public void setDataThang(JPanel pnlNgay, int thang, int year) {
        try {
            List<Bieudo> list = getbdByTKthang(thang, year);
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            if (list != null) {
                for (Bieudo o : list) {
                    String s = String.valueOf(o.getTongtien());
                    float tien = Float.valueOf(s);
                    String ngay = String.valueOf(formatThang.format(o.getDate()));
                    System.out.println("" + tien + ngay);
                    data.addValue(tien, "Số tiền", ngay);
                }
            }
            JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu tháng".toUpperCase(), "Ngày", "Số Tiền", data,
                    PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

            pnlNgay.removeAll();
            pnlNgay.setLayout(new CardLayout());
            pnlNgay.add(chartPanel);
            pnlNgay.validate();
            pnlNgay.repaint();
        } catch (Exception e) {
            //  e.printStackTrace();
        }
    }

    @Override
    public List<Bieudo> getbdByTKnam(int year) { EntityManager entityManager = JpaUtils.getEntityManager();
        List<Bieudo> list = new ArrayList<>();
        String jsql = "select new ViewModels.Bieudo(SUM(hdct.donGia * hdct.soLuong),h.ngayTao)  FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where year(h.ngayTao) = ?1 group by h.ngayTao ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, year);
        list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public void setDataNam(JPanel pnlNgay, int nam) {

        List<Bieudo> list = getbdByTKnam(nam);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        if (list != null) {
            for (Bieudo o : list) {
                String s = String.valueOf(o.getTongtien());
                float so = Float.valueOf(s);
                String thang = String.valueOf(o.getDate().getMonth()+ 1);
                //  System.out.println("" + so + thang);
                data.addValue(so, "Số tiền", "Tháng " + thang);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu năm".toUpperCase(), " Tháng", "Số Tiền", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public List<Bieudo> getbdByTKtheokhosng(Date date1, Date date2) { EntityManager entityManager = JpaUtils.getEntityManager();
        List<Bieudo> list = new ArrayList<>();
        String jsql = "select new ViewModels.Bieudo(SUM(hdct.donGia * hdct.soLuong),h.ngayTao)  FROM  Hoadoinchitiet hdct join Hoadon h on h.ID_HoaDon = hdct.hoadon.ID_HoaDon where h.ngayTao between ? 1 and ? 2 group by h.ngayTao ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, date1);
        query.setParameter(2, date2);
        list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public void setDataKhoang(JPanel pnlNgay, Date ngayBD, Date ngayKT) {

        List<Bieudo> list = getbdByTKtheokhosng(ngayBD, ngayKT);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list != null) {
            for (Bieudo o : list) {
                String s = String.valueOf(o.getTongtien());
                float so = Float.valueOf(s);
                String ngay = formatThang.format(o.getDate());

                //   System.out.println("" + so + ngay);
                dataset.addValue(so, "Số tiền", ngay);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu theo khoảng".toUpperCase(), "Ngày", "Số Tiền", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public List<thongkeSanPham> thongkesptheongay(Date date) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeSanPham(s.maSanPham, s.tenSanPham,hdct.donGia,SUM(hdct.soLuong) )"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Sanpham s on hdct.ma = s.ID_SanPham "
                + "where h.ngayTao =?1  and hdct.kieu = 1 "
                + "GROUP BY s.maSanPham, s.tenSanPham,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, date);
        List<thongkeSanPham> list = query.getResultList();

        return list;
    }

    @Override
    public List<thongkeCombo> thongkecbtheongay(Date date) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeCombo(c.maComBo, c.tenComBo,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Combo c on hdct.ma = c.ID_ComBo "
                + "where h.ngayTao =?1  and hdct.kieu = 0 "
                + "GROUP BY c.maComBo, c.tenComBo,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, date);
        List<thongkeCombo> list = query.getResultList();
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public List<thongkeSanPham> thongkesptheokhoang(Date date1, Date date2) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeSanPham(s.maSanPham, s.tenSanPham,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Sanpham s on hdct.ma = s.ID_SanPham "
                + "where h.ngayTao between ? 1 and ? 2 and hdct.kieu = 1 "
                + "GROUP BY s.maSanPham, s.tenSanPham,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, date1);
        query.setParameter(2, date2);
        List<thongkeSanPham> list = query.getResultList();
        return list;
    }

    @Override
    public List<thongkeCombo> thongkecbtheokhoang(Date date1, Date date2) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeCombo(c.maComBo, c.tenComBo,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Combo c on hdct.ma = c.ID_ComBo "
                + "where h.ngayTao between ? 1 and ? 2 and hdct.kieu = 0 "
                + "GROUP BY c.maComBo, c.tenComBo,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";

        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, date1);
        query.setParameter(2, date2);
        List<thongkeCombo> list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public List<thongkeCombo> thongkecbtheothang(int thang, int nam) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeCombo(c.maComBo, c.tenComBo,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Combo c on hdct.ma = c.ID_ComBo "
                + "where month(h.ngayTao) = ?1 and year(h.ngayTao) = ?2 and hdct.kieu = 0 "
                + "GROUP BY c.maComBo, c.tenComBo,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";

        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, thang);
        query.setParameter(2, nam);
        List<thongkeCombo> list = query.getResultList();
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public List<thongkeSanPham> thongkesptheothang(int thang, int nam) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeSanPham(s.maSanPham, s.tenSanPham,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Sanpham s on hdct.ma = s.ID_SanPham "
                + "where month(h.ngayTao) = ?1 and year(h.ngayTao) = ?2 and hdct.kieu = 1"
                + "GROUP BY s.maSanPham, s.tenSanPham,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, thang);
        query.setParameter(2, nam);
        List<thongkeSanPham> list = query.getResultList();
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public List<thongkeCombo> thongkecbtheonam(int nam) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeCombo(c.maComBo, c.tenComBo,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Combo c on hdct.ma = c.ID_ComBo "
                + "where year(h.ngayTao) = ?1 and hdct.kieu = 0 "
                + "GROUP BY c.maComBo, c.tenComBo,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, nam);
        List<thongkeCombo> list = query.getResultList();
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public List<thongkeSanPham> thongkesptheonam(int nam) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "select new ViewModels.thongkeSanPham(s.maSanPham, s.tenSanPham,hdct.donGia,SUM(hdct.soLuong))"
                + " FROM  Hoadon h join Hoadoinchitiet hdct on h.ID_HoaDon = hdct.hoadon.ID_HoaDon"
                + " join Sanpham s on hdct.ma = s.ID_SanPham "
                + "where year(h.ngayTao) = ?1 and hdct.kieu = 1 "
                + "GROUP BY s.maSanPham, s.tenSanPham,hdct.donGia "
                + "order by SUM(hdct.soLuong) desc ";
        Query query = entityManager.createQuery(jsql);
        query.setParameter(1, nam);
        List<thongkeSanPham> list = query.getResultList();
        if (list == null) {
            System.out.println("làm gì có gì");
            return null;
        }
        return list;
    }

    @Override
    public void setDatangaynull(JPanel pnlNgay, Date date) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String s = "0";
        float so = Float.valueOf(s);
        String ngay = String.valueOf(date);
        dataset.addValue(so, "Số tiền", ngay);
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu".toUpperCase(), "Thời gian", "Số Tiền", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    public void GuiMail(String mes) {

        String username = "tamlttph19033@fpt.edu.vn";
        String password = "vtjekpdqurdtzpfs";

        try {

            // your host email smtp server details
            Properties pr = new Properties();

            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
            pr.put("mail.smtp.starttls.enable", "true");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr,
                    new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //set email message details
            Message message = new MimeMessage(session);
            System.setProperty("mail.mime.charset", "UTF-8");

            message.setRecipient(Message.RecipientType.TO, new InternetAddress("lethithanhtam24102002hn@gmail.com"));
            message.setFrom(new InternetAddress("tamlttph19033@fpt.edu.vn"));
            message.setSubject("Báo cáo ");
            message.setText(mes);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Hoadon> getBillHuyNgay(Date date) { EntityManager entityManager = JpaUtils.getEntityManager();
        String jsql = "SELECT h FROM Hoadon h where h.trangThai = 2 and h.ngayTao = ?1";
        TypedQuery<Hoadon> query = entityManager.createQuery(jsql.toString(), Hoadon.class);
        query.setParameter(1, date);
        List<Hoadon> list = query.getResultList();
        return list;
    }

    @Override
    public String guiBCN(Date date) {

        String message = "";
        try {
            List<detail> list1 = listdatatheongay(date);

            for (detail x : list1) {
                message = message + "\t\t\t\tBáo cáo trong năm\n"
                        + "|---------------------------------------------------------------------------|\n"
                        + " |                                                  \t\t\t\t\t|\n"
                        + "\t\tTổng món bán trong năm: " + String.valueOf(x.getTongsp()) + "\t\t\n"
                        + "\t\tTổng bill trong năm: " + String.valueOf(x.getTonghd()) + "\t\t\n"
                        + "\t\tTổng tiền trong năm: " + String.valueOf(x.getTongtien()) + "VNĐ" + "\t\t\n";
            }
            for (Hoadon x : getBillHuyNgay(date)) {

                message = message + "\t|-----------------------------------------------------|\n"
                        + "\t|\tMã hóa đơn bị hủy là: " + String.valueOf(x.getMaHoaDon() + "\t\n"
                                + "\t|\tMã nhân viên order: " + String.valueOf(x.getNhanvien().getTenNhanVien())) + "\t\n"
                        + "\t|\tNgày :  " + String.valueOf(x.getNgayTao() + "\t\n"
                                + "\t|\tGiờ: " + String.valueOf(x.getThoiGian()) + "\t\n"
                                + "\t|\tLý do: " + String.valueOf(x.getGhiChu()) + "\t\n"
                                + "\t|-----------------------------------------------------|\n"
                        );

            }

            GuiMail(message);
            return "Gửi mail thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Gửi mail thất bại!";
        }
    }

}

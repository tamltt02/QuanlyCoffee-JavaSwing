/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Application.Login;
import Application.Main;
import DomainModel.Ban;
import DomainModel.Combo;
import DomainModel.Hoadoinchitiet;
import DomainModel.Hoadon;
import DomainModel.Khuyenmai;
import DomainModel.Sanpham;
import Services.IServiceBan;
import Services.IServiceCombo;
import Services.IServiceSanPham;
import Services.ServiceBan;
import Services.ServiceCombo;
import Services.ServiceSanPham;
import Utilities.GetID;
import ViewModels.BanView;
import ViewModels.ComboView;
import ViewModels.SanPhamView;
import ViewModels.SelectedItems;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import repositories.ImplBangBan;
import repositories.ImplBangCombo;
import repositories.ImplBangHoaDon;
import repositories.ImplBangHoaDonChiTiet;
import repositories.ImplBangKhuyenMai;
import repositories.ImplBangNhanVien;
import repositories.ImplBangSanPham;
import repositories.InterfaceBangBan;
import repositories.InterfaceBangComBo;
import repositories.InterfaceBangHoaDon;
import repositories.InterfaceBangHoaDonChiTiet;
import repositories.InterfaceBangKhuyenMai;
import repositories.InterfaceBangNhanVien;
import repositories.InterfaceBangSanPham;

/**
 *
 * @author lucif
 */
public class BanHang extends javax.swing.JPanel {

    InterfaceBangSanPham daoSP = new ImplBangSanPham();
    InterfaceBangComBo daoCB = new ImplBangCombo();
    InterfaceBangBan daoBan = new ImplBangBan();
    InterfaceBangKhuyenMai daoKM = new ImplBangKhuyenMai();
    InterfaceBangHoaDon daoHD = new ImplBangHoaDon();
    InterfaceBangNhanVien daoNV = new ImplBangNhanVien();
    InterfaceBangHoaDonChiTiet daoHDCT = new ImplBangHoaDonChiTiet();
    IServiceBan svsBan = new ServiceBan();
    IServiceCombo svsCombo = new ServiceCombo();
    IServiceSanPham svsSP = new ServiceSanPham();
    List<Hoadoinchitiet> lstSelected = new ArrayList<>();
    List<Khuyenmai> lstKhuyenMai = new ArrayList<>();
    Locale locale = new Locale("vi", "VN");
    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
    Ban selectedBan;
    JButton btn;
    GetID util = new GetID();
    long millis = System.currentTimeMillis();
    Hoadon hoadon = new Hoadon();

    /**
     * Creates new form a
     */
    public BanHang() {
        initComponents();
        selectedBan = daoBan.findById2(-1);
        format.setRoundingMode(RoundingMode.HALF_UP);
        setTableHeader();
        loadTableBan(svsBan.findByStatus(2), btn_allBan);
        loadTableSanPham(btn_cafe, 1);
        txt_tongTien.setEnabled(false);
        txt_tienPhaiTra.setEnabled(false);
        txt_tienThua.setEnabled(false);
        txt_maHoaDon.setEnabled(false);
        btn_luu.setEnabled(false);
        btn_thanhToan.setEnabled(false);
        btn_huy.setEnabled(false);
        CbcKhuyenMai();
    }
void demoLoad(){
    loadTableBan(svsBan.findByStatus(2), btn_allBan);
}
    void removeColorBan() {
        btn_allBan.setBackground(Color.white);
        btn_banHD.setBackground(Color.white);
        btn_banTrong.setBackground(Color.white);
        btn_mangVe.setBackground(Color.white);
        btn_allBan.setForeground(Color.black);
        btn_banHD.setForeground(Color.black);
        btn_banTrong.setForeground(Color.black);
        btn_mangVe.setForeground(Color.black);
    }

    void removeColorListSP() {
        btn_cafe.setBackground(Color.white);
        btn_comBo.setBackground(Color.white);
        btn_doAn.setBackground(Color.white);
        btn_doUongKhac.setBackground(Color.white);
        btn_cafe.setForeground(Color.black);
        btn_comBo.setForeground(Color.black);
        btn_doAn.setForeground(Color.black);
        btn_doUongKhac.setForeground(Color.black);
    }

    void loadTableBan(List<BanView> bv, JButton btn) {
        DefaultTableModel tbl = (DefaultTableModel) tbl_ban.getModel();
        tbl.setRowCount(0);
        for (BanView x : bv) {
            tbl.addRow(new Object[]{
                x.getMaBan(),
                x.getTrangThai() == 1 ? "Hoạt động" : "Trống",
                x.getSoGhe()
            });
        }
        removeColorBan();
        btn.setBackground(Color.darkGray);
        btn.setForeground(Color.white);

    }

    void loadForm() {
        double money = 0;
        for (Hoadoinchitiet x : lstSelected) {
            money = money + (x.getSoLuong() * x.getDonGia());
        }
        txt_tongTien.setText(String.valueOf(money));
        Khuyenmai km = (Khuyenmai) cbc_maKhuyenMai.getSelectedItem();
        txt_tienPhaiTra.setText(String.valueOf(Double.parseDouble(txt_tongTien.getText())
                - (Double.parseDouble(txt_tongTien.getText()) * km.getChietKhau() / 100)));
        if (!txt_khachDua.getText().isBlank()) {
            txt_tienThua.setText(String.valueOf(Double.parseDouble(txt_khachDua.getText())
                    - (Double.parseDouble(txt_tienPhaiTra.getText()))));
        }
        txt_khachDua.setText("");
        txt_tienThua.setText("");
    }

    void loadTableSanPham(JButton btn, int type) {
        DefaultTableModel tbl = (DefaultTableModel) tbl_listSP.getModel();
        tbl.setRowCount(0);
        if (type == 0) {
            for (ComboView x : svsCombo.findAll()) {
                JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(x.getHInhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                tbl.addRow(new Object[]{
                    imageLabel,
                    x.getMaComBo(),
                    x.getTenComBo(),
                    x.getGiaTien(),
                    "Thêm"
                });
            }
        } else {
            for (SanPhamView x : svsSP.findByType(type)) {
                JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(x.getHinhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                tbl.addRow(new Object[]{
                    imageLabel,
                    x.getMaSanPham(),
                    x.getTenSanPham(),
                    x.getGiaTien(),
                    "Thêm"
                });
            }
        }
        removeColorListSP();
        tbl_listSP.getColumn("Thêm").setCellRenderer(new ButtonRenderer());
        tbl_listSP.getColumn("Thêm").setCellEditor(new ButtonEditor(new JCheckBox()));
        btn.setBackground(Color.darkGray);
        btn.setForeground(Color.white);
    }

    void loadTableSelected(List<Hoadoinchitiet> lst) {
        DefaultTableModel tbl = (DefaultTableModel) tbl_selectedSP.getModel();
        tbl.setRowCount(0);
        for (Hoadoinchitiet x : lst) {
            if (x.getKieu() == 0) {
                Combo cb = daoCB.findById( x.getMa());
                JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(cb.getHInhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                tbl.addRow(new Object[]{
                    x.getMaHoaDonChiTiet(),
                    imageLabel,
                    cb.getMaComBo(),
                    cb.getTenComBo(),
                    cb.getGiaTien(),
                    x.getSoLuong(),
                    x.getGhiChu()
                });
            } else {
                Sanpham sp = daoSP.findById(x.getMa());
                JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(sp.getHinhAnh());
                Image img = imageicon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                tbl.addRow(new Object[]{
                    x.getMaHoaDonChiTiet(),
                    imageLabel,
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    sp.getGiaTien(),
                    x.getSoLuong(),
                    x.getGhiChu()
                });
            }
        }
    }

    public void CbcKhuyenMai() {
        lstKhuyenMai = daoKM.findAll();
        cbc_maKhuyenMai.setModel(new DefaultComboBoxModel(lstKhuyenMai.toArray()));
    }

    void setTableHeader() {
        tbl_selectedSP.getColumn("Hình ảnh").setCellRenderer(new myCDTableRenderer());
        tbl_selectedSP.setRowHeight(60);
        tbl_listSP.getColumn("Hình ảnh").setCellRenderer(new myCDTableRenderer());
        tbl_listSP.setRowHeight(60);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            setText((value == null) ? "" : value.toString());
            return this;

        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
///

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                double money = 0;
                int temp = 0;
                int index = tbl_listSP.getSelectedRow();
                int idSP = Integer.parseInt(tbl_listSP.getValueAt(index, 1).toString().substring(2));
                int kieu = tbl_listSP.getValueAt(index, 1).toString().substring(0, 2).contains("CB") ? 0 : 1;
                String sl = JOptionPane.showInputDialog("Nhập số lượng");
                if (sl == null) {
                    return label;
                }
                if (sl.isBlank()) {
                    JOptionPane.showMessageDialog(new JPanel(), "Mời nhập số lượng");
                    return label;
                }
                if (Integer.parseInt(sl) <= 0) {
                    JOptionPane.showMessageDialog(new JPanel(), "Mời nhập số lượng lớn hơn 0");
                    return label;
                } else {
                    if (lstSelected.isEmpty()) {
                        Hoadoinchitiet hdct = new Hoadoinchitiet();
//                        hdct.setMaHoaDonChiTiet(util.getIDMax("HDCT", daoHDCT.findAll().get(daoHDCT.findAll().size() - 1).getIdHdct()));
                        hdct.setDonGia(Double.parseDouble(tbl_listSP.getValueAt(index, 3).toString()));
                        hdct.setMa(Integer.parseInt(tbl_listSP.getValueAt(index, 1).toString().substring(2)));
                        hdct.setSoLuong(Integer.parseInt(sl));
                        if (tbl_listSP.getValueAt(index, 1).toString().contains("CB")) {
                            hdct.setKieu(0);
                        } else {
                            hdct.setKieu(1);
                        }
                        hdct.setBan(selectedBan);
                        hdct.setHoadon(hoadon);
                        lstSelected.add(hdct);
                    } else {
                        if (selectedBan.getTrangThai() == 0) {
                            for (Hoadoinchitiet x : lstSelected) {
                                if (x.getKieu() == kieu & x.getMa() == idSP) {
                                    x.setSoLuong(x.getSoLuong() + Integer.parseInt(sl));
                                    temp++;
                                    break;
                                }
                            }
                            if (temp == 0) {
                                Hoadoinchitiet hdct = new Hoadoinchitiet();
//                            hdct.setMaHoaDonChiTiet(util.getIDMax("HDCT", daoHDCT.findAll().get(daoHDCT.findAll().size() - 1).getIdHdct()));
                                hdct.setDonGia(Double.parseDouble(tbl_listSP.getValueAt(index, 3).toString()));
                                hdct.setMa(Integer.parseInt(tbl_listSP.getValueAt(index, 1).toString().substring(2)));
                                hdct.setSoLuong(Integer.parseInt(sl));
                                if (tbl_listSP.getValueAt(index, 1).toString().contains("CB")) {
                                    hdct.setKieu(0);
                                } else {
                                    hdct.setKieu(1);
                                }
                                hdct.setBan(selectedBan);
                                hdct.setHoadon(hoadon);
                                lstSelected.add(hdct);
                            }
                        } else {
                            for (Hoadoinchitiet x : lstSelected) {
                                if (x.getKieu() == kieu & x.getMa() == idSP) {
                                    x.setSoLuong(x.getSoLuong() + Integer.parseInt(sl));
                                    daoHDCT.update(x);
                                    temp++;
                                    break;
                                }
                            }
                            if (temp == 0) {
                                Hoadoinchitiet hdct = new Hoadoinchitiet();
                                hdct.setMaHoaDonChiTiet(util.getIDMax("HDCT", daoHDCT.findAll().get(daoHDCT.findAll().size() - 1).getIdHdct()));
                                hdct.setDonGia(Double.parseDouble(tbl_listSP.getValueAt(index, 3).toString()));
                                hdct.setMa(Integer.parseInt(tbl_listSP.getValueAt(index, 1).toString().substring(2)));
                                hdct.setSoLuong(Integer.parseInt(sl));
                                if (tbl_listSP.getValueAt(index, 1).toString().contains("CB")) {
                                    hdct.setKieu(0);
                                } else {
                                    hdct.setKieu(1);
                                }
                                hdct.setBan(selectedBan);
                                hdct.setHoadon(daoHD.findHoaDonByBan(selectedBan.getID_Ban()));
                                lstSelected.add(hdct);
                                daoHDCT.create(hdct);
                            }
                        }

                    }
                }
                loadForm();
                loadTableSelected(lstSelected);

            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public void ButtonEditor() {
        btn = new JButton();
        btn.setOpaque(true);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("K hiểu đoạn này lắm");
            }
        });
    }

    //Start Right Click Bàn
    class JPopUP extends JPopupMenu {

        public JPopUP(JTable tbl) {
            int index = tbl_ban.getSelectedRow();
            JMenuItem createTB;
            JMenuItem chuyenBan;
            JMenuItem gopBan;
            createTB = new JMenuItem("Tạo hóa đơn");
            chuyenBan = new JMenuItem("Chuyển bàn");
            gopBan = new JMenuItem("Gộp bàn");

            createTB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = tbl_ban.getSelectedRow();
                    hoadon = CreateNewHoaDon("");
                    selectedBan = daoBan.findById2(Long.parseLong(tbl_ban.getValueAt(index, 0).toString().substring(1)));
                    txt_maHoaDon.setText(hoadon.getMaHoaDon());
                }

            });
            chuyenBan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = tbl_ban.getSelectedRow();
                    String trangThai = tbl_ban.getValueAt(index, 1).toString();
                    if (trangThai.equals("Trống")) {
                        JOptionPane.showMessageDialog(new JPanel(), "Bàn đang trống");
                    } else {
                        ChuyenBan cb = new ChuyenBan(new Main(), true, 0, Integer.parseInt(tbl_ban.getValueAt(index, 0).toString().substring(1)));
                        cb.setVisible(true);
                    }
                }
            }
            );

            gopBan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = tbl_ban.getSelectedRow();
                    String trangThai = tbl_ban.getValueAt(index, 1).toString();
                    if (trangThai.equals("Trống")) {
                        JOptionPane.showMessageDialog(new JPanel(), "Bàn đang trống");
                    } else {
                        new ChuyenBan(new Main(), true, 1, Integer.parseInt(tbl_ban.getValueAt(index, 0).toString().substring(1))).setVisible(true);
                    }
                }

            });
            if (daoBan.findById2(Long.parseLong(tbl_ban.getValueAt(index, 0).toString().substring(1))).getTrangThai() == 0) {
                add(createTB);
            };
            if (daoBan.findById2(Long.parseLong(tbl_ban.getValueAt(index, 0).toString().substring(1))).getTrangThai() == 1) {
                add(chuyenBan);
                add(gopBan);
            }
        }

    }

    //End Right Click Bàn
    //Start Right Click Lst sản phẩm
    //Start Right Click Bàn
    class JPopUPSP extends JPopupMenu {

        public JPopUPSP(JTable tbl) {
            int index = tbl_selectedSP.getSelectedRow();
            JMenuItem giamSL;
            JMenuItem huy;
            giamSL = new JMenuItem("Giảm số lượng");
            huy = new JMenuItem("Hủy");

            giamSL.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tbl_selectedSP.getValueAt(index, 0) != null) {
                        // Đây là giữ liệu thật
                        int IdHDCT = Integer.parseInt(tbl_selectedSP.getValueAt(index, 0).toString().substring(4));
                        Hoadoinchitiet hdct = daoHDCT.findById(IdHDCT);
                        String sl = JOptionPane.showInputDialog("Nhập số lượng muốn giảm");
                        if (sl != null) {
                            if (sl.isBlank()) {
                                JOptionPane.showMessageDialog(tbl_selectedSP, "Mời nhập số lượng lớn hơn 0 và là chữ số");
                            } else {
                                if (Integer.parseInt(sl) >= hdct.getSoLuong()) {
                                    JOptionPane.showMessageDialog(tbl_selectedSP, "Bạn không thể giảm số lượng quá số lượng Order");
                                } else {
                                    String note = JOptionPane.showInputDialog("Nhập lý do giảm");
                                    if (note != null) {
                                        if (note.isBlank()) {
                                            JOptionPane.showMessageDialog(tbl_selectedSP, "Phải nhập lý do để có thể giảm");
                                        } else {
                                            if (hdct.getGhiChu() == null) {
                                                hdct.setGhiChu("");
                                            }
                                            hdct.setGhiChu(hdct.getGhiChu() + " " + note);
                                            hdct.setSoLuong(hdct.getSoLuong() - Integer.parseInt(sl));
                                            daoHDCT.update(hdct);
                                            loadForm();
                                            int trangThai = tbl_ban.getValueAt(index, 1).toString().equals("Trống") ? 0 : 1;
                                            lstSelected = svsBan.showSelectedItems(selectedBan.getID_Ban(), trangThai);
                                            loadTableSelected(lstSelected);
                                        }
                                    }
                                }
                            }
                        }
                        // Đây là giữ liệu giả
                    } else {
                        String sl = JOptionPane.showInputDialog("Nhập số lượng muốn giảm");
                        if (sl != null) {
                            if (sl.isBlank()) {
                                JOptionPane.showMessageDialog(tbl_selectedSP, "Mời nhập số lượng lớn hơn 0 và là chữ số");
                            } else {
                                if (Integer.parseInt(sl) >= Integer.parseInt(tbl_selectedSP.getValueAt(index, 5).toString())) {
                                    JOptionPane.showMessageDialog(tbl_selectedSP, "Bạn không thể giảm số lượng quá số lượng Order");
                                } else {
                                    for (Hoadoinchitiet x : lstSelected) {
                                        String maSP = (x.getKieu() == 0) ? "CB" + x.getMa() : "SP" + x.getMa();
                                        if (maSP.equals(tbl_selectedSP.getValueAt(index, 2))) {
                                            x.setSoLuong(x.getSoLuong() - Integer.parseInt(sl));
                                        }
                                    }
                                    loadForm();
                                    loadTableSelected(lstSelected);
                                }
                            }
                        }
                    }

                }

            });
            huy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tbl_selectedSP.getValueAt(index, 0) != null) {
                        int IdHDCT = Integer.parseInt(tbl_selectedSP.getValueAt(index, 0).toString().substring(4));
                        Hoadoinchitiet hdct = daoHDCT.findById(IdHDCT);
                        String note = JOptionPane.showInputDialog("Nhập lý do hủy");
                        if (note != null) {
                            if (note.isBlank()) {
                                JOptionPane.showMessageDialog(new Panel(), "Phải nhập lý do để có thể hủy");
                            } else {
                                if (hdct.getGhiChu() == null) {
                                    hdct.setGhiChu("");
                                }
                                hdct.setGhiChu(hdct.getGhiChu() + " " + note);
                                hdct.setSoLuong(0);
                                daoHDCT.update(hdct);
                                loadForm();
                                int trangThai = tbl_ban.getValueAt(index, 1).toString().equals("Trống") ? 0 : 1;
                                lstSelected = svsBan.showSelectedItems(selectedBan.getID_Ban(), trangThai);
                                loadTableSelected(lstSelected);
                            }
                        }
                        //Đây là dữ liệu giả
                    } else {
                        for (int i = 0; i < lstSelected.size(); i++) {
                             String maSP = (lstSelected.get(i).getKieu() == 0) ? "CB" + lstSelected.get(i).getMa() : "SP" + lstSelected.get(i).getMa();
                            if (maSP.equals(tbl_selectedSP.getValueAt(index, 2))) {
                                lstSelected.remove(lstSelected.get(i));
                            }
                        }
                        if(lstSelected.size()==0){
                            lstSelected= new ArrayList<>();
                        }
                        loadForm();
                        loadTableSelected(lstSelected);
                    }
                }
            }
            );
            add(huy);
            add(giamSL);

        }

    }

    //End Right click lst sản phẩm
    public Hoadon CreateNewHoaDon(String note) {
        Hoadon hd = new Hoadon();
        hd.setGhiChu(note);
        int maxIdHD = daoHD.findAll().get(daoHD.findAll().size() - 1).getID_HoaDon();
        hd.setNhanvien(daoNV.findById(Login.getCurrentUser().getID_NhanVien()));
        hd.setMaHoaDon(util.getIDMax("HD", maxIdHD));
        hd.setNgayTao(java.util.Calendar.getInstance().getTime());
        hd.setThoiGian(new Time(millis));
        hd.setTrangThai(0);
        hd.setKhuyenmai((Khuyenmai) cbc_maKhuyenMai.getSelectedItem());
        return hd;

    }

    public void CreateNewHoaDonChiTiet() {
        for (Hoadoinchitiet x : lstSelected) {
            x.setMaHoaDonChiTiet(util.getIDMax("HDCT", daoHDCT.findAll().get(daoHDCT.findAll().size() - 1).getIdHdct()));
            x.setHoadon(daoHD.findById(Integer.parseInt(txt_maHoaDon.getText().substring(2))));
            x.setBan(selectedBan);
            daoHDCT.create(x);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        btn_allBan = new javax.swing.JButton();
        btn_banTrong = new javax.swing.JButton();
        btn_banHD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ban = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        txt_tienPhaiTra = new javax.swing.JTextField();
        txt_khachDua = new javax.swing.JTextField();
        txt_tienThua = new javax.swing.JTextField();
        cbc_maKhuyenMai = new javax.swing.JComboBox<>();
        btn_thanhToan = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_huy = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_maHoaDon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_selectedSP = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btn_cafe = new javax.swing.JButton();
        btn_doUongKhac = new javax.swing.JButton();
        btn_doAn = new javax.swing.JButton();
        btn_comBo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_listSP = new javax.swing.JTable();
        btn_mangVe = new javax.swing.JButton();

        setBackground(new java.awt.Color(209, 171, 134));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setText("Bán hàng");

        btn_allBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_allBan.setText("Tất cả");
        btn_allBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allBanActionPerformed(evt);
            }
        });

        btn_banTrong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_banTrong.setText("Bàn trống");
        btn_banTrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_banTrongActionPerformed(evt);
            }
        });

        btn_banHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_banHD.setText("Bàn hoạt động");
        btn_banHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_banHDActionPerformed(evt);
            }
        });

        tbl_ban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Bàn", "Trạng thái", "Số ghế"
            }
        ));
        tbl_ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_banMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_ban);

        jPanel1.setBackground(new java.awt.Color(145, 45, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tổng tiền");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Giảm giá");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tiền phải trả");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Khách đưa");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tiền thừa");

        txt_tongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_tienPhaiTra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txt_khachDua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_khachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_khachDuaCaretUpdate(evt);
            }
        });

        txt_tienThua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cbc_maKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbc_maKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbc_maKhuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbc_maKhuyenMaiItemStateChanged(evt);
            }
        });

        btn_thanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pay.png"))); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });

        btn_luu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btn_luu.setText("Lưu");
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        btn_huy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_huy.setText("Hủy");
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Mã hóa đơn");

        txt_maHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_khachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbc_maKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_thanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_maHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(txt_tongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cbc_maKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_tienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_khachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_luu)
                    .addComponent(btn_thanhToan)
                    .addComponent(btn_huy)))
        );

        jPanel2.setBackground(new java.awt.Color(145, 45, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đã chọn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_selectedSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HDCT", "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Ghi chú"
            }
        ));
        tbl_selectedSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_selectedSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_selectedSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(145, 45, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btn_cafe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffee-cup.png"))); // NOI18N
        btn_cafe.setText("Caffe");
        btn_cafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cafeActionPerformed(evt);
            }
        });

        btn_doUongKhac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smoothie.png"))); // NOI18N
        btn_doUongKhac.setText("Đồ uống khác");
        btn_doUongKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doUongKhacActionPerformed(evt);
            }
        });

        btn_doAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cupcake.png"))); // NOI18N
        btn_doAn.setText("Đồ ăn");
        btn_doAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doAnActionPerformed(evt);
            }
        });

        btn_comBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coffeeNcookie.png"))); // NOI18N
        btn_comBo.setText("Combo");
        btn_comBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_comBoActionPerformed(evt);
            }
        });

        tbl_listSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Thêm"
            }
        ));
        jScrollPane2.setViewportView(tbl_listSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_cafe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_doUongKhac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_doAn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_comBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cafe)
                    .addComponent(btn_doUongKhac)
                    .addComponent(btn_doAn)
                    .addComponent(btn_comBo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        btn_mangVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_mangVe.setText("Mang về");
        btn_mangVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mangVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_allBan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_banTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_banHD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_mangVe, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_allBan)
                            .addComponent(btn_banTrong)
                            .addComponent(btn_banHD)
                            .addComponent(btn_mangVe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_doAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doAnActionPerformed
        loadTableSanPham(btn_doAn, 3);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_doAnActionPerformed

    private void btn_allBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allBanActionPerformed
        loadTableBan(svsBan.findByStatus(2), btn_allBan);// TODO add your handling code here:
        selectedBan = daoBan.findById2(-1);
        lstSelected = new ArrayList<>();
        loadTableSelected(lstSelected);
        txt_maHoaDon.setText("");
    }//GEN-LAST:event_btn_allBanActionPerformed

    private void btn_banTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_banTrongActionPerformed
        loadTableBan(svsBan.findByStatus(0), btn_banTrong);
        selectedBan = daoBan.findById2(-1);
        lstSelected = new ArrayList<>();
        loadTableSelected(lstSelected);// TODO add your handling code here:
        txt_maHoaDon.setText("");
    }//GEN-LAST:event_btn_banTrongActionPerformed

    private void btn_banHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_banHDActionPerformed
        loadTableBan(svsBan.findByStatus(1), btn_banHD);
        selectedBan = daoBan.findById2(-1);
        lstSelected = new ArrayList<>();
        loadTableSelected(lstSelected);
        txt_maHoaDon.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_btn_banHDActionPerformed

    private void btn_cafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cafeActionPerformed
        loadTableSanPham(btn_cafe, 1);// TODO add your handling code here:
    }//GEN-LAST:event_btn_cafeActionPerformed

    private void btn_comBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comBoActionPerformed
        loadTableSanPham(btn_comBo, 0);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_comBoActionPerformed

    private void btn_doUongKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doUongKhacActionPerformed
        loadTableSanPham(btn_doUongKhac, 2);// TODO add your handling code here:
    }//GEN-LAST:event_btn_doUongKhacActionPerformed

    private void cbc_maKhuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbc_maKhuyenMaiItemStateChanged
        loadForm();        // TODO add your handling code here:
    }//GEN-LAST:event_cbc_maKhuyenMaiItemStateChanged

    private void txt_khachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_khachDuaCaretUpdate
        loadForm();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_khachDuaCaretUpdate

    private void tbl_banMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_banMouseClicked
        int index = tbl_ban.getSelectedRow();
        selectedBan = daoBan.findById2(Long.parseLong(tbl_ban.getValueAt(index, 0).toString().substring(1)));
        int trangThai = tbl_ban.getValueAt(index, 1).toString().equals("Trống") ? 0 : 1;
        lstSelected = svsBan.showSelectedItems(selectedBan.getID_Ban(), trangThai);
        loadTableSelected(lstSelected);
        if (selectedBan.getTrangThai() == 0) {
            txt_maHoaDon.setText("");
            btn_thanhToan.setEnabled(false);
            btn_luu.setEnabled(true);
            btn_huy.setEnabled(false);
        } else {
            txt_maHoaDon.setText(daoHD.findHoaDonByBan(selectedBan.getID_Ban()).getMaHoaDon());
            btn_thanhToan.setEnabled(true);
            btn_luu.setEnabled(false);
            btn_huy.setEnabled(true);
        }
        if (selectedBan.getID_Ban() == -1) {
            btn_thanhToan.setEnabled(true);
        }
//        txt_maHoaDon.setText(svsBan.showSelectedItems(Integer.parseInt(selectedBan) , 0));
//        svsBan.showSelectedItems(Integer.parseInt(selectedBan.substring(1)), trangThai);
        tbl_ban.setSelectionBackground(Color.red);
        final JPopUP pop = new JPopUP(tbl_ban);
        if (SwingUtilities.isRightMouseButton(evt)) {
            pop.show(evt.getComponent(), evt.getX(), evt.getY());
        };
        loadForm();
    }//GEN-LAST:event_tbl_banMouseClicked

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        if (txt_khachDua.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền!!!");
        } else if (Double.parseDouble(txt_khachDua.getText()) < Double.parseDouble(txt_tienPhaiTra.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng đưa thêm tiền!!!");
        } else {
            int maxIdHD = daoHD.findAll().get(daoHD.findAll().size() - 1).getID_HoaDon();
            txt_maHoaDon.setText(util.getIDMax("HD", maxIdHD));
            if (selectedBan.getID_Ban() == -1) {
                String note = JOptionPane.showInputDialog("Ghi chú");
                Hoadon hd = CreateNewHoaDon(note);
                hd.setTrangThai(1);
                daoHD.create(hd);
                CreateNewHoaDonChiTiet();
//                Ban ban = daoBan.findById(0);
//                ban.setTrangThai(0);
//                daoHD.update(hd);
//                daoBan.update(ban);
                loadTableBan(svsBan.findByStatus(2), btn_allBan);
            } else {
                Hoadon hd = daoHD.findHoaDonByBan(selectedBan.getID_Ban());
                selectedBan.setTrangThai(0);
                hd.setTrangThai(1);
                daoHD.update(hd);
                daoBan.update(selectedBan);
                loadTableBan(svsBan.findByStatus(2), btn_allBan);
            }
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        }
        loadForm();
        
    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        if (txt_maHoaDon.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Mời bạn tạo hóa đơn trước khi lưu sản phẩm");
            return;
        }
        if (selectedBan.getID_Ban() == -1) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn bàn");
        } else {
//            if (tbl_ban.getValueAt(tbl_ban.getSelectedRow(), 1).equals("Trống")) {

            if (lstSelected.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời bạn chọn sản phẩm trước khi lưu hóa đơn");
            } else {
                String note = JOptionPane.showInputDialog("Ghi chú hóa đơn");
                daoHD.create(CreateNewHoaDon(note));
                CreateNewHoaDonChiTiet();
                selectedBan.setTrangThai(1);
                daoBan.update(selectedBan);
                List<BanView> lst = svsBan.findByStatus(2);
                loadTableBan(svsBan.findByStatus(2), btn_allBan);
//            }
                lstSelected = new ArrayList<>();
                txt_khachDua.setText("");
                loadForm();
                JOptionPane.showMessageDialog(this, "Lưu thành công");
            }
        }

    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_mangVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mangVeActionPerformed
        selectedBan = daoBan.findById2(-1);
        lstSelected = new ArrayList<>();
        int maxIdHD = daoHD.findAll().get(daoHD.findAll().size() - 1).getID_HoaDon();
        txt_maHoaDon.setText(util.getIDMax("HD", maxIdHD));
        btn_thanhToan.setEnabled(true);
        btn_luu.setEnabled(false);
        loadTableSelected(lstSelected);
        List<BanView> lst = new ArrayList<>();
        loadTableBan(lst, btn_mangVe);

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mangVeActionPerformed

    private void tbl_selectedSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_selectedSPMouseClicked
        final JPopUPSP pop = new JPopUPSP(tbl_selectedSP);
        if (SwingUtilities.isRightMouseButton(evt)) {
            pop.show(evt.getComponent(), evt.getX(), evt.getY());
        };        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_selectedSPMouseClicked

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        int i = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy đơn không");
        if (i == 0) {
            String note = JOptionPane.showInputDialog("Ghi chú");
            if (note != null) {
                if (note.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Phải nhập lý do để có thể hủy đơn");
                } else {
                    Hoadon hd = daoHD.findById(Integer.parseInt(txt_maHoaDon.getText().substring(2)));
                    if (hd.getGhiChu() == null) {
                        hd.setGhiChu("");
                    }
                    selectedBan.setTrangThai(0);
                    daoBan.update(selectedBan);
                    hd.setGhiChu(hd.getGhiChu() + " " + note);
                    hd.setTrangThai(2);
                    daoHD.update(hd);
                    JOptionPane.showMessageDialog(this, "Hủy đơn thành công");
                }
            }
        }
        loadForm();
        loadTableBan(svsBan.findByStatus(2), btn_allBan);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_huyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_allBan;
    private javax.swing.JButton btn_banHD;
    private javax.swing.JButton btn_banTrong;
    private javax.swing.JButton btn_cafe;
    private javax.swing.JButton btn_comBo;
    private javax.swing.JButton btn_doAn;
    private javax.swing.JButton btn_doUongKhac;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_mangVe;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JComboBox<String> cbc_maKhuyenMai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_ban;
    private javax.swing.JTable tbl_listSP;
    private javax.swing.JTable tbl_selectedSP;
    private javax.swing.JTextField txt_khachDua;
    private javax.swing.JTextField txt_maHoaDon;
    private javax.swing.JTextField txt_tienPhaiTra;
    private javax.swing.JTextField txt_tienThua;
    private javax.swing.JTextField txt_tongTien;
    // End of variables declaration//GEN-END:variables

    private static class myCDTableRenderer implements TableCellRenderer {

        public myCDTableRenderer() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            return (Component) value;
        }
    }
}

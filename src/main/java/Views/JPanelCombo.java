/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.Combo;
import DomainModel.ComboSanpham;
import DomainModel.Sanpham;
import Services.ComboSanphamService;
import Services.ComboService;
import Services.ServiceSanPham;
import Services.ServiceCombo;
import Services.ServiceComboSanPham;
import Services.TheLoaiService;
import ViewModels.ComboSanphamView;
import ViewModels.ComboView;
import java.awt.Button;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import repositories.ImplBangCombo;
import repositories.ImplBangSanPham;
import repositories.ImplBangTheLoai;

/**
 *
 * @author LuongQuocBao
 */
public class JPanelCombo extends javax.swing.JPanel {

    DefaultTableModel _DefaultTableModelSanPham = new DefaultTableModel();
    DefaultTableModel _DefaultTableModelCombo = new DefaultTableModel();
    DefaultTableModel _DefaultTableModelCTSP = new DefaultTableModel();

    ServiceSanPham _ServiceSanPham = new ServiceSanPham();
    ServiceCombo _ServiceCombo = new ServiceCombo();
    ServiceComboSanPham _ServiceComboSanPham = new ServiceComboSanPham();

    JPopupMenu _jpmSanPham = new JPopupMenu();

    ImplBangSanPham _implSP = new ImplBangSanPham();
    ImplBangCombo _imlCB = new ImplBangCombo();
//    String _inputSoLuong;

    /**
     * Creates new form PanelCombo
     */
    public JPanelCombo() {
        initComponents();
        trangThai();
        loadTable_SanPham(_ServiceSanPham.getlst());
        loadTable_Combo(_ServiceCombo.getlst());
//        loadTable_ChiTietCombo(_ServiceComboSanPham.getlst());
    }

    void trangThai() {
        cbxTrangThai.removeAllItems();
        cbxTrangThai.addItem("Hoạt động");
        cbxTrangThai.addItem("Không hoạt động");
    }

    void loadTable_Combo(List<Combo> lst) {
        _DefaultTableModelCombo = (DefaultTableModel) tblCombo.getModel();
        if (lst.isEmpty()) {
            _DefaultTableModelCombo.setRowCount(0);
            return;
        }
        _DefaultTableModelCombo.setRowCount(0);
        for (Combo x : lst) {
            _DefaultTableModelCombo.addRow(new Object[]{
                x.getMaComBo(), x.getTenComBo(), x.getGiaTien(), x.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    void loadTable_ChiTietCombo(List<ComboSanpham> lst) {
        _DefaultTableModelCTSP = (DefaultTableModel) tbl_CTCB.getModel();
        if (lst.isEmpty()) {
            _DefaultTableModelCTSP.setRowCount(0);
            return;
        }
        _DefaultTableModelCTSP.setRowCount(0);
        for (ComboSanpham x : lst) {
            _DefaultTableModelCTSP.addRow(new Object[]{
                x.getCombo().getMaComBo().toString(), x.getSanpham().getMaSanPham().toString(), x.getSoLuong()
            });
        }
    }

    void loadTable_SanPham(List<Sanpham> lst) {
        _DefaultTableModelSanPham = (DefaultTableModel) tbl_SanPham.getModel();
        if (lst.isEmpty()) {
            _DefaultTableModelSanPham.setRowCount(0);
            return;
        }
        _DefaultTableModelSanPham.setRowCount(0);
        for (Sanpham x : lst) {
            _DefaultTableModelSanPham.addRow(new Object[]{
                x.getMaSanPham(), x.getTenSanPham(), x.getGiaTien(), x.getHinhAnh(), x.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    ComboView getGUI(int style) {
        if (style == 1) {
            return new ComboView(Double.parseDouble(txtGiaTien.getText()), btn_HinhAnh.getText(), txtMaCB.getText(), txtTenCB.getText(), cbxTrangThai.getSelectedItem().equals("Hoạt động") ? 1 : 0);
        }
        return new ComboView(_ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getID_ComBo(), Double.parseDouble(txtGiaTien.getText()), btn_HinhAnh.getText(), txtMaCB.getText(), txtTenCB.getText(), cbxTrangThai.getSelectedItem().equals("Hoạt động") ? 1 : 0);
    }

    void MenuSanPham() {
        _jpmSanPham.removeAll();
        JMenuItem item = new JMenuItem("Thêm");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String _inputSoLuong = JOptionPane.showInputDialog("Mời bạn nhập số lượng");
                if (Integer.parseInt(_inputSoLuong) <= 0) {
                    JOptionPane.showMessageDialog(_jpmSanPham, "Số lượng nhập phải lớn hơn 0! Yêu cầu nhập lại!");
                } else {
                    if (checkTrungMaSP(tbl_SanPham.getValueAt(tbl_SanPham.getSelectedRow(), 0).toString())) {
                        _ServiceComboSanPham.update(new ComboSanphamView(_ServiceSanPham.findIDByMa(tbl_SanPham.getValueAt(tbl_SanPham.getSelectedRow(), 0).toString()),Integer.parseInt(_inputSoLuong),
                            new Combo(_ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getID_ComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getGiaTien(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getHInhAnh(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getMaComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getTenComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getTrangThai()),
                            _implSP.findById(_ServiceSanPham.getlst().get(tbl_SanPham.getSelectedRow()).getID_SanPham())));
                    loadTable_ChiTietCombo(_ServiceComboSanPham.findByIDCB(_ServiceComboSanPham.findIDByMa(txtMaCB.getText())));
               
                    } else {
                        _ServiceComboSanPham.create(new ComboSanphamView(Integer.parseInt(_inputSoLuong),
                                new Combo(_ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getID_ComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getGiaTien(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getHInhAnh(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getMaComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getTenComBo(), _ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getTrangThai()),
                                _implSP.findById(_ServiceSanPham.getlst().get(tbl_SanPham.getSelectedRow()).getID_SanPham())));
                        loadTable_ChiTietCombo(_ServiceComboSanPham.findByIDCB(_ServiceComboSanPham.findIDByMa(txtMaCB.getText())));

                    }
                }
            }
        });
        _jpmSanPham.add(item);
    }

    boolean checkTrungMaSP(String MaSP) {
        for (ComboSanpham x : _ServiceComboSanPham.findByIDCB(_ServiceComboSanPham.findIDByMa(txtMaCB.getText()))) {
            if (x.getSanpham().getMaSanPham().equals(MaSP)) {
                return true;
            }

        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_CTCB = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaCB = new javax.swing.JTextField();
        txtTenCB = new javax.swing.JTextField();
        txtGiaTien = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_HinhAnh = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCombo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(209, 171, 134));

        jPanel1.setBackground(new java.awt.Color(209, 171, 134));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết combo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_CTCB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã combo", "Mã sản phẩm", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(tbl_CTCB);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(209, 171, 134));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết combo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trạng thái");

        btn_Them.setBackground(new java.awt.Color(153, 255, 153));
        btn_Them.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(204, 204, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loading.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã combo");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên combo");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Giá tiền");

        btn_HinhAnh.setText("+");
        btn_HinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HinhAnhActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(153, 255, 255));
        btn_Sua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCB, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(txtTenCB)
                                    .addComponent(txtGiaTien)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(btn_HinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them)
                    .addComponent(btnLamMoi)
                    .addComponent(btn_Sua))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(209, 171, 134));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết combo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tblCombo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã CB", "Tên CB", "Giá tiền", "Trạng thái"
            }
        ));
        tblCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComboMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCombo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(209, 171, 134));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết combo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Hình ảnh", "Trạng thái"
            }
        ));
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Combo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        _ServiceCombo.create(getGUI(1));
        loadTable_Combo(_ServiceCombo.getlst());
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_HinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HinhAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hình ảnh", "png", "jpg");//lọc
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);//cho phép chọn 1 ảnh

        int a = fileChooser.showDialog(this, "Chọn file");// hiển thị hộp chọn
        if (a == JFileChooser.APPROVE_OPTION) // đã chọn
        {
            File f = fileChooser.getSelectedFile();
            ImageIcon imageicon = new ImageIcon(f.getAbsolutePath());
            Image image = (imageicon).getImage().getScaledInstance(btn_HinhAnh.getWidth(), btn_HinhAnh.getHeight(), Image.SCALE_SMOOTH);
            imageicon = new ImageIcon(image);
            btn_HinhAnh.setIcon(imageicon);
            btn_HinhAnh.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_btn_HinhAnhActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
//        int index = tbl_SanPham.getSelectedRow();
//        txtMaSP.setText(tbl_SanPham.getModel().getValueAt(index, 0).toString());
//        cbxTrangThai.setSelectedItem(tbl_SanPham.getModel().getValueAt(index, 4).toString());
//        txtTenSP.setText(tbl_SanPham.getModel().getValueAt(index, 1).toString());
//        txtGiaTien.setText(tbl_SanPham.getModel().getValueAt(index, 2).toString());
//        txtAnh.setText(tbl_SanPham.getModel().getValueAt(index, 3).toString());
        MenuSanPham();
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        // TODO add your handling code here:
        _ServiceCombo.update(getGUI(0));
        loadTable_Combo(_ServiceCombo.getlst());
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void tblComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComboMouseClicked
        // TODO add your handling code here:
        int choose = tblCombo.getSelectedRow();
        txtMaCB.setText(_ServiceCombo.getlst().get(choose).getMaComBo());
        txtGiaTien.setText(String.valueOf(_ServiceCombo.getlst().get(choose).getGiaTien()));
        txtTenCB.setText(_ServiceCombo.getlst().get(choose).getTenComBo());
        cbxTrangThai.setSelectedItem(_ServiceCombo.getlst().get(choose).getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động");
//        btn_HinhAnh.setIcon(imageicon);
//        btn_HinhAnh.setText(f.getAbsolutePath());

//load data combo sản phẩm chi tiết
        loadTable_ChiTietCombo(_ServiceComboSanPham.findByIDCB(_ServiceCombo.getlst().get(tblCombo.getSelectedRow()).getID_ComBo()));
    }//GEN-LAST:event_tblComboMouseClicked

    private void tbl_SanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            _jpmSanPham.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbl_SanPhamMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btn_HinhAnh;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCombo;
    private javax.swing.JTable tbl_CTCB;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaCB;
    private javax.swing.JTextField txtTenCB;
    // End of variables declaration//GEN-END:variables
}

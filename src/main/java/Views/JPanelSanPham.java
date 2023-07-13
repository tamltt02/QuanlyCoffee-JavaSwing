/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModel.Sanpham;
import DomainModel.Theloai;
import Services.ServiceSanPham;
import Services.ServiceTheLoai;
import Utilities.GetID;
import Utilities.HibernateUtil;
import ViewModels.SanPhamView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.Utilities;
import repositories.ImplBangBan;
import repositories.ImplBangSanPham;
import repositories.ImplBangTheLoai;

/**
 *
 * @author LuongQuocBao
 */
public class JPanelSanPham extends javax.swing.JPanel {

    DefaultTableModel _DefaultTableModel = new DefaultTableModel();
    ServiceSanPham _ServiceSanPham = new ServiceSanPham();
    ServiceTheLoai _TheLoaiService = new ServiceTheLoai();
    ImplBangTheLoai _daoTL = new ImplBangTheLoai();
    ImplBangSanPham _daoSP = new ImplBangSanPham();
    ImplBangBan _daoBan = new ImplBangBan();
    GetID getid = new GetID();

    /**
     * Creates new form PanelSanPham
     */
    public JPanelSanPham() {
        initComponents();
        setTableHeader();
        loadTable(_ServiceSanPham.getlst());
        System.out.println(_ServiceSanPham.getlst().size());
        TheLoai();
        trangThai();
        txtMaSP.setEnabled(false);
    }

    void trangThai() {
        cbxTrangThai.removeAllItems();
        cbxTrangThai.addItem("Hoạt động");
        cbxTrangThai.addItem("Không hoạt động");
    }

    void TheLoai() {
        cbxTheLoai.removeAllItems();
        for (Theloai x : _daoTL.findAll()) {
            cbxTheLoai.addItem(String.valueOf(x.getTenTheLoai()));
        }
    }

    void loadTable(List<Sanpham> lstSanphams) {
        _DefaultTableModel = (DefaultTableModel) tblSanPham.getModel();
        if (lstSanphams.isEmpty()) {
            _DefaultTableModel.setRowCount(0);
            return;
        }
        _DefaultTableModel.setRowCount(0);
        for (Sanpham x : lstSanphams) {
            JLabel imageLabel = new JLabel();
                ImageIcon imageicon = new ImageIcon(x.getHinhAnh());
                Image img = imageicon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            _DefaultTableModel.addRow(new Object[]{
                x.getMaSanPham(), 
                x.getTenSanPham(),
                x.getGiaTien(),
                imageLabel,
                x.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    Theloai getTL(String TL) {
        for (Theloai x : _TheLoaiService.getlst()) {
            if (cbxTheLoai.getSelectedItem().toString().equals(TL)) {
                return x;
            }
        }
        return null;
    }

    String getTL(int maTL) {
        for (Theloai x : _TheLoaiService.getlst()) {
            if (cbxTheLoai.getSelectedItem().toString().equals(maTL)) {
                return x.getTenTheLoai();
            }
        }
        return null;
    }

    SanPhamView getGUI() {
        int ma = 0;
//        for (int i = 0; i < _ServiceSanPham.getlst().size(); i++) {
//            ma++;
//        }
        ma = _daoBan.getlst().get(_daoBan.getlst().size()-1).getID_Ban();
        System.out.println(ma);
        return new SanPhamView(-1, Double.parseDouble(txtGiaTien.getText()), txtAnh.getText(), getid.getIDMax("SP", ma),
                txtTenSP.getText(), cbxTrangThai.getSelectedItem().toString() == "Hoạt đông" ? 1 : 0, getTL(cbxTheLoai.getSelectedItem().toString()));
    }

     void setTableHeader() {
        tblSanPham.getColumn("Hình ảnh").setCellRenderer(new myCDTableRenderer());
        tblSanPham.setRowHeight(90);
    }
     
         private static class myCDTableRenderer implements TableCellRenderer {

        public myCDTableRenderer() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            return (Component) value;
        }
    }
//        class ButtonRenderer extends JButton implements TableCellRenderer {
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//
//            setText((value == null) ? "" : value.toString());
//            return this;
//
//        }
//    }
//            public void addTableHeader() {
//        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//        Object[] newIdentifiers = new Object[]{"Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Hình ảnh", "Trạng thái"};
//        model.setColumnIdentifiers(newIdentifiers);
//        tblSanPham.setFillsViewportHeight(true);
//        tblSanPham.getColumn("Hình ảnh").setCellRenderer(new CellRenderer());
// 
//    }
// 
//    class CellRenderer implements TableCellRenderer {
// 
//        @Override
//        public Component getTableCellRendererComponent(JTable table,
//                Object value,
//                boolean isSelected,
//                boolean hasFocus,
//                int row,
//                int column) {
// 
//            TableColumn tb = tblSanPham.getColumn("Hình ảnh");
//            tb.setMaxWidth(60);
//            tb.setMinWidth(60);
// 
//            tblSanPham.setRowHeight(60);
// 
//            return (Component) value;
//        }
// 
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAnh = new javax.swing.JTextField();
        lblHinh = new javax.swing.JLabel();
        btn_sua = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        cbxTheLoai = new javax.swing.JComboBox<>();
        txtGiaTien = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setBackground(new java.awt.Color(209, 171, 134));
        setPreferredSize(new java.awt.Dimension(1160, 590));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sản phẩm");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 174, -1));

        jTextField1.setText("Tìm kiếm");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 300, -1));

        jPanel1.setBackground(new java.awt.Color(209, 171, 134));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mã Sản Phẩm");

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên sản Phẩm");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Trạng thái");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTrangThaiActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hình ảnh");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giá tiền");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Thể loại");

        txtAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAnhMouseClicked(evt);
            }
        });
        txtAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnhActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 255));
        btn_sua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loading.png"))); // NOI18N
        btn_clear.setText("Reset");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        cbxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTienActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(153, 255, 153));
        btn_them.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cbxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 870, 270));

        jPanel2.setBackground(new java.awt.Color(209, 171, 134));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 890, 260));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        txtGiaTien.setText("");
        txtMaSP.setText("");
        txtAnh.setText("");
        txtTenSP.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getModel().getValueAt(index, 0).toString());
        cbxTrangThai.setSelectedItem(tblSanPham.getModel().getValueAt(index, 4).toString());
        txtTenSP.setText(tblSanPham.getModel().getValueAt(index, 1).toString());
        txtGiaTien.setText(tblSanPham.getModel().getValueAt(index, 2).toString());
        txtAnh.setText(tblSanPham.getModel().getValueAt(index, 3).toString());
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
                int choose = JOptionPane.showConfirmDialog(this, "Xác nhận!");
        if (choose == 0) {
            _ServiceSanPham.createNewSanPham(getGUI());
        loadTable(_ServiceSanPham.getlst());
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        
    }//GEN-LAST:event_btn_themActionPerformed

    private void txtGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTienActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void txtAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnhActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hình ảnh", "png", "jpg");//lọc
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);//cho phép chọn 1 ảnh

        int a = fileChooser.showDialog(this, "Chọn file");// hiển thị hộp chọn
        if (a == JFileChooser.APPROVE_OPTION) // đã chọn
        {
            File f = fileChooser.getSelectedFile();
            ImageIcon imageicon = new ImageIcon(f.getAbsolutePath());
            Image image = (imageicon).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
            imageicon = new ImageIcon(image);
            lblHinh.setIcon(imageicon);
            txtAnh.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_txtAnhActionPerformed

    private void txtAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hình ảnh", "png", "jpg");//lọc
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);//cho phép chọn 1 ảnh

        int a = fileChooser.showDialog(this, "Chọn file");// hiển thị hộp chọn
        if (a == JFileChooser.APPROVE_OPTION) // đã chọn
        {
            File f = fileChooser.getSelectedFile();
            ImageIcon imageicon = new ImageIcon(f.getAbsolutePath());
            Image image = (imageicon).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH);
            imageicon = new ImageIcon(image);
            lblHinh.setIcon(imageicon);
            txtAnh.setText(f.getAbsolutePath());
        }
    }//GEN-LAST:event_txtAnhMouseClicked

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        int index = tblSanPham.getSelectedRow();
        int ma = _daoSP.findSP().get(index).getID_SanPham(); 
        if (tblSanPham.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn bàn");
            return;
        }

        int choose = JOptionPane.showConfirmDialog(this, "Xác nhận!");
        if (choose == 0) {
            _ServiceSanPham.updateSanPhamById(new SanPhamView(-1, Double.parseDouble(txtGiaTien.getText()), txtAnh.getText(), getid.getIDMax("SP", index),
                txtTenSP.getText(), cbxTrangThai.getSelectedItem().toString() == "Hoạt đông" ? 1 : 0, getTL(cbxTheLoai.getSelectedItem().toString())));
        loadTable(_ServiceSanPham.getlst());
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Sửa thất bại!");
        
        
    }//GEN-LAST:event_btn_suaActionPerformed

    private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JComboBox<String> cbxTheLoai;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtAnh;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
